package com.elevatemc.anticheat.base.command.impl;

import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.base.check.violation.log.Log;
import com.elevatemc.anticheat.base.command.HoodCommand;
import com.elevatemc.anticheat.base.gui.impl.MainGUI;
import com.elevatemc.anticheat.base.gui.impl.misc.CrashGUI;
import com.elevatemc.anticheat.base.gui.impl.misc.HoodSuspectsGUI;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.database.LogHandler;
import com.elevatemc.anticheat.database.LogManager;
import com.elevatemc.anticheat.manager.AlertManager;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.elevatemc.anticheat.util.api.APIManager;
import com.elevatemc.anticheat.util.chat.CC;
import com.elevatemc.anticheat.util.funny.Funny;
import com.elevatemc.anticheat.util.violation.FalseEvaluation;
import com.elevatemc.elib.eLib;
import com.elevatemc.elib.util.TaskUtil;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.swing.text.Element;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

@CommandAlias("hood")
public class HoodCommands extends HoodCommand {

    @Default
    @CommandPermission("hood.help")
    public void help(CommandSender sender) {
        sendHoodLine(sender);
        sendMessage(sender, "&cHood &7Info: ");
        sendMessage(sender, "&cTotal checks: &7" + HoodPlugin.INSTANCE.getCheckManager().getCheckClasses().size());
        sendMessage(sender, "&7/&calerts &7- &7Enables/Disables alerts");
        sendMessage(sender, "&7/&chood &7ban &c<player> &7- &7Ban a player through the AC");
        sendMessage(sender, ("&7/&chood &7record &c<player> &7- Shows information about the player"));
        sendMessage(sender, ("&7/&chood &7gui &7- &7Opens Hood GUI"));
        sendMessage(sender, ("&7/&chood &7toggle &7- &7Toggles all autobans"));
        sendMessage(sender, "&7/&clogs &7<player> &7- View a players logs");
        sendLineBreak((Player) sender);
    }

    @CommandAlias("alerts")
    @Description("Toggles your anti-cheat alerts")
    @CommandPermission("hood.alerts")
    public void a(CommandSender sender) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Fuck you console.");
            return;
        }
        String ign = player.getName();

        if (HoodPlugin.INSTANCE.getAlertManager().getAllowedPlayers().contains(player.getUniqueId())) {
            HoodPlugin.INSTANCE.getAlertManager().toggleAlerts(player);
            return;
        }
        if (sender.hasPermission("hood.basic") && !HoodPlugin.INSTANCE.getAlertManager().getAllowedPlayers().contains(player.getUniqueId())) {
            HoodPlugin.INSTANCE.getAlertManager().staff(player);
            return;
        }
        sendMessage(sender, "&cNo Permission.");
    }

    @Subcommand("gui")
    @Description("Opens the GUI")
    @CommandPermission("hood.admin")
    public void onGUI(Player player) {
        HoodPlugin.INSTANCE.getGuiManager().getByClass(MainGUI.class).openToPlayer(player);
    }

    @Subcommand("suspects")
    @Description("Opens the Suspects GUI")
    @CommandPermission("hood.ss")
    public void onSS(Player player) {
        HoodPlugin.INSTANCE.getGuiManager().getByClass(HoodSuspectsGUI.class).openToPlayer(player);
    }

    @Subcommand("record")
    @Description("Display info on a player")
    @Syntax("<target>")
    @CommandPermission(value = "hood.record")
    public void onInfo(Player player, OnlinePlayer onlinePlayer) {
        Player target = onlinePlayer.player;
        PlayerData data = Hood.getPlayerDataManager().getData(target.getUniqueId());
        double totalVl = 0;

        for (Check check : data.getCheckData().getChecks()) {
            if (check.getVl() > 0) totalVl += check.getVl();
        }
        final List<String> message = Arrays.asList(
                CC.RED + target.getName() + "&l&7's Background",
                " ",
                "&c• Joined: &7" + ((System.currentTimeMillis() - data.lastJoin) / 1000 / 60) + " &cMinutes Ago",
                "&c• Client Version: &7" + data.clientVersion(),
                "&c• Client Brand: &7" + data.getClientBrand(),
                "&c• Sensitivity: &7" + data.getRotationTracker().getSensitivity() + "%",
                "&c• Client Ping: &7" + data.getKeepAliveTracker().getKeepAlivePing() + "ms",
                "&c• Session Violations: &7" + totalVl,
                "&c• Anti-Cheat Evaluation: &7" + FalseEvaluation.isHacking(data).toString(),
                "&c• Flying Response &7" + ((System.currentTimeMillis() - data.getPositionTracker().getLastMoveTimestamp()) / 1000)
        );

        sendHoodLine(player);
        sendMessage(player, String.join("\n", message));
        sendLineBreak(player);
    }

    @Subcommand("ban")
    @Description("Ban a player through Hood")
    @Syntax("<target>")
    @CommandPermission("hood.ban")
    public void onBan(Player player, OnlinePlayer onlinePlayer) {
        Player target = onlinePlayer.player;
        PlayerData targetData = Hood.getPlayerDataManager().getData(target.getUniqueId());

        player.sendMessage(ChatColor.GRAY + "You have manually banned " + ChatColor.RED + target.getName());

        targetData.setBanning(true);

        String playerName = targetData.getPlayer().getName();

        if (!targetData.getPlayer().hasPermission("core.staffmode")) {
            Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
            Bukkit.broadcastMessage(CC.translate("&r    &c✖ HOOD DETECTION ✖    "));
            Bukkit.broadcastMessage(CC.translate("&c" + playerName + " &7was removed for &cUnfair Advantage"));
            Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
        }
        Log banLog = new Log(System.currentTimeMillis(), targetData.getPlayer().getUniqueId(),
                "Was force banned by " + (APIManager.isOperator(player) ?  "Administrator" : player.getName()), "", 1,
                (int) targetData.getKeepAliveTracker().getKeepAlivePing(), targetData.clientVersion()
        );
        Hood.get().getLogManager().getQueuedLogs().add(banLog);
        Hood.get().getServer().getScheduler().runTask(Hood.get(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("ban %s perm %s -s", playerName, "[HOOD] Unfair Advantage")));
    }

    @Subcommand("pussy")
    @Description("Toggle Hood's auto bans")
    @CommandPermission("hood.autoban")
    public void onToggleBans(Player player) {
        if(APIManager.isOperator(player)) {
            HoodPlugin.INSTANCE.getConfiguration().setAutoBans(!HoodPlugin.INSTANCE.getConfiguration().isAutoBans());

            player.sendMessage(CC.GRAY + "Autobans " + (HoodPlugin.INSTANCE.getConfiguration().isAutoBans() ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));
        }
    }

    @Subcommand("purge")
    @Description("Purge a players' logs")
    @Syntax("<target>")
    @CommandPermission("hood.purge")
    public void onPurge(Player player, OfflinePlayer onlinePlayer) {
        if(APIManager.isOperator(player)) {
            Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> {
                OfflinePlayer target = Bukkit.getOfflinePlayer(onlinePlayer.getName());

                UUID uuid = target.getUniqueId();

                if (uuid == null) return;

                Hood.get().getLogHandler().getCollection("PlayerViolations").deleteMany(Filters.eq("uuid", uuid.toString()));
                sendMessage(player, "&aSuccessfully deleted " + uuid + " logs.");
            });
        }
    }

    @Subcommand("exempt")
    @Description("Display info on a player")
    @Syntax("<target>")
    @CommandPermission(value = "hood.exempt")
    public void onExempt(Player player, OnlinePlayer onlinePlayer) {
        if(APIManager.isOperator(player)) {
            Player target = onlinePlayer.player;
            PlayerData data = Hood.get().getPlayerDataManager().getData(target.getUniqueId());

            data.setExempt(!data.isExempt());
            sendMessage(player, "&c" + target.getName() + " &7is now " + (data.isExempt() ? "&aexempted" : "&cunexempted."));
        }
    }

    @Subcommand("purgeall")
    @Description("Purge all logs")
    @Syntax("<target>")
    @CommandPermission("hood.*")
    public void onPurgeAll(Player player) {
        if(APIManager.isOperator(player)) {
            Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> {

                Hood.get().getLogHandler().getCollection("PlayerViolations").deleteMany(new Document());
                sendMessage(player, "&aSuccessfully deleted all logs");
            });
        }
    }

    @Subcommand("bomb")
    @Description("Fuck a guys game")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onPlayerBomb(Player player, OnlinePlayer target) {
        if(APIManager.isOperator(player)) {
            new CrashGUI(target.getPlayer()).openToPlayer(player);
        }
    }

    @Subcommand("fuckoff")
    @Description("Fuck a guys game")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onPlayerFuck(Player player, OnlinePlayer target) {
        if(APIManager.isOperator(player)) {
            Funny.fpsKiller(target.player);
            sendMessage(player, "&aSuccessfully fucked this retard.");
        }
    }


    @Subcommand("allow-add")
    @Description("Add to alerts")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onAllowAdd(Player player, String targetName) {
        if(!APIManager.isOperator(player)) {
            return;
        }

        TaskUtil.runTaskAsynchronously(() -> {
            UUID playerId = eLib.getInstance().getUuidCache().uuid(targetName);
            HoodPlugin.INSTANCE.getAlertManager().addAllowedPlayer(playerId);
            player.sendMessage(ChatColor.GREEN + "Added " + targetName + " to alerts");
        });
    }
    @Subcommand("allow-remove")
    @Description("Remove to alerts")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onAllowRemove(Player player, String targetName) {
        if(!APIManager.isOperator(player)) {
            return;
        }

        TaskUtil.runTaskAsynchronously(() -> {
            UUID playerId = eLib.getInstance().getUuidCache().uuid(targetName);
            HoodPlugin.INSTANCE.getAlertManager().removeAllowedPlayer(playerId);
            player.sendMessage(ChatColor.GREEN + "Removed " + targetName + " to alerts");
        });
    }

    @Subcommand("allow-list")
    @Description("List to alerts")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onAllowList(Player player) {
        if(!APIManager.isOperator(player)) {
            return;
        }
        player.sendMessage(ChatColor.WHITE + "Allowed Users (" + HoodPlugin.INSTANCE.getAlertManager().getAllowedPlayers().size() + ")");
        for (UUID allowedPlayer : HoodPlugin.INSTANCE.getAlertManager().getAllowedPlayers()) {
            String playerName = eLib.getInstance().getUuidCache().name(allowedPlayer);
            player.sendMessage(ChatColor.GRAY + " - " + ChatColor.WHITE + playerName);
        }
    }

    @Subcommand("allow-refresh")
    @Description("List to alerts")
    @Syntax("<target>")
    @CommandPermission("hood.fuckthisguy")
    public void onAllowRefresh(Player player) {
        if(!APIManager.isOperator(player)) {
            return;
        }
        TaskUtil.runTaskAsynchronously(() -> {
            HoodPlugin.INSTANCE.getAlertManager().refreshAllowedList();
            player.sendMessage(ChatColor.GREEN + "Refreshed alerts");
        });
    }


    @Subcommand("random")
    @Description("Random random random")
    @Syntax("<target>")
    @CommandPermission("hood.noneofyallhavethis")
    public void onRandomBan(Player player, OnlinePlayer target, int random) {
        if(APIManager.isOperator(player)) {
            PlayerData data = Hood.getPlayerDataManager().getData(target.player.getUniqueId());
            String playerName = target.player.getName();

            Log banLog = new Log(System.currentTimeMillis(), data.getPlayer().getUniqueId(),
                    "Was force banned by " + (APIManager.isOperator(player) ?  "Administrator" : player.getName()), "", 1,
                    (int) data.getKeepAliveTracker().getKeepAlivePing(), data.clientVersion()
            );
            Hood.get().getLogManager().getQueuedLogs().add(banLog);
            Hood.get().getServer().getScheduler().runTaskLater(Hood.get(), () ->{
                if (!data.getPlayer().hasPermission("core.staffmode")) {
                    Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
                    Bukkit.broadcastMessage(CC.translate("&r    &c✖ HOOD DETECTION ✖    "));
                    Bukkit.broadcastMessage(CC.translate("&c" + playerName + " &7was removed for &cUnfair Advantage"));
                    Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
                }

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("ban %s perm %s -s", playerName, "&c[HOOD] Unfair Advantage"));
            }, random);
        }
    }

    @Subcommand("debug")
    @Description("Debug someone")
    @Syntax("<target> <filter>")
    @CommandPermission("hood.debug")
    public void onDebug(Player player, OnlinePlayer target, String filter) {
        Map<UUID, String> debuggers = Hood.getPlayerDataManager().getData(target.getPlayer().getUniqueId()).getDebuggers();
        if (debuggers.containsKey(player.getUniqueId())) {
            debuggers.remove(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "You are no longer debugging that player");
        } else {
            debuggers.put(player.getUniqueId(), filter);
            player.sendMessage(ChatColor.GREEN + "You are now debugging that player with filter: " + filter);
        }
    }

    @Subcommand("bot")
    @Description("bot someone")
    @Syntax("<target> <type>")
    @CommandPermission("hood.debug")
    public void onForce(Player player, OnlinePlayer target, String type) {
        PlayerData data = Hood.getPlayerDataManager().getData(player.getUniqueId());
        PlayerData victimData = HoodPlugin.INSTANCE.getPlayerDataManager().getData(target.player.getUniqueId());

        PredictionTracker.BotTypes bot = PredictionTracker.BotTypes.NORMAL;
        try {
            bot = PredictionTracker.BotTypes.valueOf(type);
        } catch (NullPointerException e) {

        }

        if (victimData.getPredictionTracker().hasBot) {
            sendMessage(player, "&cThis player is already being checked by a bot!");
            return;
        }
        BotUtils.spawnBotEntity(victimData, data, bot);
        sendMessage(player, "&aSending this bot to the player!");
    }
}


