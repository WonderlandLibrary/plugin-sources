package me.frep.vulcan.spigot.punishment;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import me.frep.vulcan.spigot.util.LogUtil;
import me.frep.vulcan.spigot.config.Stats;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.apache.commons.lang.StringUtils;
import java.awt.Color;
import me.frep.vulcan.spigot.util.discord.DiscordWebhook;
import me.frep.vulcan.spigot.hook.discord.DiscordHelper;
import me.frep.vulcan.spigot.Vulcan;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import me.frep.vulcan.api.check.Check;
import me.frep.vulcan.api.event.VulcanPunishEvent;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.AbstractCheck;

public class PunishmentManager
{
    public void handlePunishment(final AbstractCheck check, final PlayerData data) {
        if (!Config.PUNISHABLE.get(check.getClassName())) {
            return;
        }
        if (System.currentTimeMillis() - data.getLastPunishment() < Config.PUNISHMENT_DELAY) {
            return;
        }
        data.setLastPunishment(System.currentTimeMillis());
        if (data.getPlayer().hasPermission("vulcan.bypass." + check.getClassName().toLowerCase())) {
            return;
        }
        if (Config.ENABLE_API) {
            final VulcanPunishEvent event = new VulcanPunishEvent(data.getPlayer(), check);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
        }
        final int punishmentId = ThreadLocalRandom.current().nextInt(50000);
        if (Vulcan.INSTANCE.getDiscordHelper() != null) {
            DiscordHelper.getInstance().sendDiscordPunishMessage(check, data);
        }
        if (Config.PUNISHMENT_WEBHOOK) {
            if (!Config.PUNISHMENT_WEBHOOK_URL.equalsIgnoreCase("insert-url-here")) {
                final DiscordWebhook webhook = new DiscordWebhook(Config.PUNISHMENT_WEBHOOK_URL);
                webhook.setAvatarUrl(Config.PUNISHMENT_WEBHOOK_AVATAR_URL);
                webhook.setUsername(Config.PUNISHMENT_WEBHOOK_USERNAME);
                final DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();
                embedObject.setTitle(Config.PUNISHMENT_WEBHOOK_TITLE);
                embedObject.setThumbnail(Config.PUNISHMENT_WEBHOOK_THUMBNAIL.replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%name%", data.getPlayer().getName()));
                embedObject.setColor(new Color(Config.PUNISHMENT_WEBHOOK_COLOR_R, Config.PUNISHMENT_WEBHOOK_COLOR_G, Config.PUNISHMENT_WEBHOOK_COLOR_B));
                embedObject.setDescription(Config.PUNISHMENT_WEBHOOK_DESCRIPTION.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%vl%", Integer.toString(Config.MAX_VIOLATIONS.get(check.getClassName()))).replaceAll("%client-brand", data.getClientBrand()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%ping%", Integer.toString(data.getConnectionProcessor().getKeepAlivePing())).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%description%", check.getDescription()).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%tps%", MathUtil.trim(ServerUtil.getTPS())).replaceAll("%check%", check.getName()));
                if (Config.PUNISHMENT_WEBHOOK_DESCRIPTION_FIELD) {
                    embedObject.addField("Description", check.getDescription(), true);
                }
                if (Config.PUNISHMENT_WEBHOOK_SERVER_NAME_FIELD) {
                    embedObject.addField("Server Name", Config.SERVER_NAME, true);
                }
                if (Config.PUNISHMENT_WEBHOOK_CLIENT_BRAND_FIELD) {
                    embedObject.addField("Client Brand", data.getClientBrand(), true);
                }
                if (Config.PUNISHMENT_WEBHOOK_CLIENT_VERSION_FIELD) {
                    embedObject.addField("Client Version", PlayerUtil.getClientVersionToString(data.getPlayer()), true);
                }
                if (Config.PUNISHMENT_WEBHOOK_PING_TPS_FIELD) {
                    embedObject.addField("Ping | TPS", data.getConnectionProcessor().getKeepAlivePing() + "ms | " + MathUtil.trim(ServerUtil.getTPS()), true);
                }
                if (Config.PUNISHMENT_WEBHOOK_LOCATION_FIELD) {
                    embedObject.addField("Location", "(" + data.getPlayer().getWorld().getName() + ", " + data.getPositionProcessor().getBlockX() + ", " + data.getPositionProcessor().getBlockY() + ", " + data.getPositionProcessor().getBlockZ() + ")", true);
                }
                webhook.addEmbed(embedObject);
                if (!Config.ALERTS_WEBHOOK_CONTENT.equals("")) {
                    webhook.setContent(Config.ALERTS_WEBHOOK_CONTENT.replaceAll("%player%", data.getPlayer().getName()));
                }
                try {
                    webhook.execute();
                }
                catch (final Exception ex) {}
            }
        }
        Stats.set("punishments", Stats.getPunishments() + 1);
        if (Config.PUNISHMENT_FILE_ENABLED) {
            LogUtil.logPunishment(check, data);
        }
        if (!Config.PUNISHMENT_MESSAGE.equalsIgnoreCase("")) {
            if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                Vulcan.INSTANCE.getAlertManager().sendMessage(PlaceholderAPI.setPlaceholders(data.getPlayer(), Config.PUNISHMENT_MESSAGE.replaceAll("%prefix%", Config.PREFIX).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%player%", data.getPlayer().getName()).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%vl%", Integer.toString(check.getVl()))));
            }
            else {
                Vulcan.INSTANCE.getAlertManager().sendMessage(Config.PUNISHMENT_MESSAGE.replaceAll("%prefix%", Config.PREFIX).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%player%", data.getPlayer().getName()).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%opponent%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%vl%", Integer.toString(check.getVl())));
            }
        }
        final boolean broadcastPunishment = Config.BROADCAST_PUNISHMENT.get(check.getClassName());
        if (broadcastPunishment) {
            Config.PUNISHMENT_BROADCAST.forEach(message -> ServerUtil.broadcast(message.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%opponent%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%type%", Character.toString(check.getCheckInfo().type()))));
        }
        final List<String> punishmentCommands = Config.PUNISHMENT_COMMANDS.get(check.getClassName());
        if (!punishmentCommands.isEmpty()) {
            new BukkitRunnable() {
                public void run() {
                    punishmentCommands.forEach(command -> {
                        final Object val$data = data;
                        final Object val$punishmentId = punishmentId;
                        final Object val$check = check;
                        if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                            PlaceholderAPI.setPlaceholders(data.getPlayer(), command);
                        }
                        if (command.startsWith("Bungee:") || command.startsWith("bungee:")) {
                            PunishmentManager.this.sendPluginMessage(data.getPlayer(), ColorUtil.translate(command.replaceAll("Bungee:", "").replaceAll("bungee:", "").replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%ping%", Integer.toString(data.getConnectionProcessor().getKeepAlivePing())).replaceAll("%tps%", MathUtil.trim(ServerUtil.getTPS())).replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%type%", Character.toString(check.getCheckInfo().type()))) + "#VULCAN#" + check.getDisplayName() + "#VULCAN#" + check.getDisplayType() + "#VULCAN#" + check.getVl() + "#VULCAN#" + data.getPlayer().getName() + "#VULCAN#" + check.getMaxVl());
                        }
                        else if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                            ServerUtil.dispatchCommand(ColorUtil.translate(PlaceholderAPI.setPlaceholders(data.getPlayer(), command.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%ping%", Integer.toString(data.getConnectionProcessor().getKeepAlivePing())).replaceAll("%tps%", MathUtil.trim(ServerUtil.getTPS())).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%type%", Character.toString(check.getCheckInfo().type())))));
                        }
                        else {
                            ServerUtil.dispatchCommand(ColorUtil.translate(command.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%ping%", Integer.toString(data.getConnectionProcessor().getKeepAlivePing())).replaceAll("%tps%", MathUtil.trim(ServerUtil.getTPS())).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%punishment-id", Integer.toString(punishmentId)).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%type%", Character.toString(check.getCheckInfo().type()))));
                        }
                    });
                }
            }.runTask(Vulcan.INSTANCE.getPlugin());
        }
        data.getChecks().forEach(check1 -> check1.setVl(0));
    }
    
    public static String spigot() {
        return "%%__USER__%%";
    }
    
    public static String nonce() {
        return "%%__NONCE__%%";
    }
    
    public static String resource() {
        return "%%__RESOURCE__%%";
    }
    
    private void sendPluginMessage(final Player player, final String msg) {
        if (!Config.PLUGIN_MESSAGING) {
            return;
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("punishment");
        out.writeUTF(msg);
        player.sendPluginMessage(Vulcan.INSTANCE.getPlugin(), "vulcan:bungee", out.toByteArray());
    }
}
