package com.elevatemc.anticheat.manager;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.base.check.violation.base.AbstractPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.log.Log;
import com.elevatemc.anticheat.base.check.violation.punishment.PlayerBan;
import com.elevatemc.anticheat.util.chat.CC;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
public class AlertManager {
    public static final ReplaceOptions REPLACE_OPTIONS = new ReplaceOptions().upsert(true);

    private final Set<UUID> allowedPlayers = new HashSet<>();
    private final Set<Player> alertsEnabled = new HashSet<>();
    private final Set<Player> staff = new HashSet<>();
    private String checkId;

    public void toggleAlerts(final Player player) {
        if (alertsEnabled.contains(player)) {
            alertsEnabled.remove(player);
            player.sendMessage(CC.translate("&cAnticheat alerts have been disabled."));
        } else {
            alertsEnabled.add(player);
            player.sendMessage(CC.translate("&aAnticheat alerts have been enabled."));
        }
    }

    public void staff(final Player player) {
        if (staff.contains(player)) {
            staff.remove(player);
            player.sendMessage(CC.translate("&cAnticheat alerts have been disabled."));
        } else {
            staff.add(player);
            player.sendMessage(CC.translate("&aAnticheat alerts have been enabled."));
        }
    }

    private final ExecutorService executorService = Executors.newSingleThreadExecutor(
            new ThreadFactoryBuilder()
                    .setPriority(3)
                    .setNameFormat("Hood Violation Thread")
                    .build()
    );

    public AlertManager() {
        this.refreshAllowedList();
    }

    public void refreshAllowedList() {
        this.allowedPlayers.clear();
        MongoCollection<Document> collection = Hood.get().getLogHandler().getCollection("HoodAlerts");
        for (Document document : collection.find()) {
            UUID uuid = UUID.fromString(document.getString("uuid"));
            this.allowedPlayers.add(uuid);
        }
    }

    public void addAllowedPlayer(UUID uuid) {
        this.allowedPlayers.add(uuid);
        MongoCollection<Document> collection = Hood.get().getLogHandler().getCollection("HoodAlerts");
        collection.replaceOne(Filters.eq("uuid", uuid.toString()), new Document().append("uuid", uuid.toString()), REPLACE_OPTIONS);
    }

    public void removeAllowedPlayer(UUID uuid) {
        this.allowedPlayers.remove(uuid);
        MongoCollection<Document> collection = Hood.get().getLogHandler().getCollection("HoodAlerts");
        collection.deleteOne(Filters.eq("uuid", uuid.toString()));
    }

    public void handleViolation(AbstractPlayerViolation violation) {
        executorService.submit(() -> {
            if (!violation.getCheck().isEnabled()) return;
            Check check = violation.getCheck();
            PlayerData playerData = check.getPlayerData();

            if (playerData.isBanning()) return;
            if (playerData.getPlayer().getUniqueId().toString().equalsIgnoreCase("9c463156-4b05-4284-85e9-49e8dbc3a599"))
                return;
            if (playerData.isExempt()) return;
            if (playerData.getPlayer().hasMetadata("freeze")) return;

            if (playerData.getTicksExisted() < 100) return;

            check.setVl(check.getVl() + 1);

            double vl = check.getVl();

            if (playerData.getPlayer() == null) {
                Bukkit.getScheduler().runTask(Hood.get(), () -> playerData.getPlayer().kickPlayer("Failed to initialize your data."));
            }

            String playerName = playerData.getPlayer().getName();
            String checkName = check.getName();

            ViolationHandler handler = check.getViolationHandler();

            boolean experimental = handler == ViolationHandler.EXPERIMENTAL;

            String data = violation instanceof DetailedPlayerViolation ? " " + violation.getData() : "";

            String message = String.format("§5[AC] §d%s §7failed " + (experimental ? "§d%s §7§l[*]." : "§d%s.") + " §fVL %s§7 (%s) %s", playerName, check.getName(), check.getVl(), check.getPlayerData().getKeepAliveTracker().getKeepAlivePing(), data);

            Bukkit.getOnlinePlayers().stream().filter(alertsEnabled::contains).forEach(player -> player.sendMessage(message));

            if (!HoodPlugin.INSTANCE.getConfiguration().isDatabaseEnabled()) {
                return;
            }

            Log log = new Log(playerData.getPlayer().getUniqueId(), checkName, data, check.getVl(), (int) check.getPlayerData().getKeepAliveTracker().getKeepAlivePing(), playerData.getClientBrand());

            Hood.get().getLogManager().getQueuedLogs().add(log);


            if (!experimental && vl > handler.getMaxViolations() && HoodPlugin.INSTANCE.getConfiguration().isAutoBans()) {
                if (Hood.get().isLagging()) {
                    return;
                }
                String banMessage = String.format("§5[AC] §d%s §7was autobanned for " + "§d%s. (%s)" + " §fVL %s§7 %s", playerName, check.getName(), check.getKeepAliveTracker().getKeepAlivePing(), check.getVl(), data);
                Log banLog = new Log(System.currentTimeMillis(), playerData.getUuid(), checkName, "Ban occurred | Ping: " + playerData.getKeepAliveTracker().getKeepAlivePing() + " Location: (" + playerData.getPlayer().getLocation() + ") Version: (" + playerData.getClientVersion() + ") Brand: (" + playerData.getClientBrand() + ")", check.getVl(), (int) playerData.getKeepAliveTracker().getKeepAlivePing(), playerData.getClientBrand());

                Hood.get().getLogManager().getQueuedLogs().add(banLog);

                Bukkit.getOnlinePlayers().stream().filter(alertsEnabled::contains).forEach(player -> player.sendMessage(banMessage));
                handleBan(new PlayerBan(playerData, "Unfair Advantage"));
            }
        });
    }

    public void staffAlert(AbstractPlayerViolation violation) {
        if (!violation.getCheck().isEnabled()) return;
        Check check = violation.getCheck();
        PlayerData playerData = check.getPlayerData();

        if (playerData.isBanning()) return;
        if (playerData.getPlayer().getUniqueId().toString().equalsIgnoreCase("9c463156-4b05-4284-85e9-49e8dbc3a599"))
            return;
        if (playerData.isExempt()) return;
        if (playerData.getPlayer().hasMetadata("frozen")) return;

        String playerName = playerData.getPlayer().getName();

        disguiseCheck(violation);

        final String alertMessage = CC.translate("&8&l[&d&l⚠&8&l] &d%player% &7&o(%ping%) &7is using &b%check%")
                .replaceAll("%player%", playerName)
                .replaceAll("%ping%", String.valueOf(playerData.getKeepAliveTracker().getKeepAlivePing()))
                .replaceAll("%check%", this.checkId);

        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission("hood.basic")).filter(staff::contains).forEach(player -> player.sendMessage(alertMessage));

    }

    public void handleBan(PlayerBan ban) {
        Hood.get().getServer().getScheduler().runTask(Hood.get(), () -> {
            PlayerData playerData = ban.getPlayerData();

            if (!HoodPlugin.INSTANCE.getConfiguration().isAutoBans()) {
                return;
            }

            if (playerData.isBanning()) {
                return;
            }

            playerData.setBanning(true);

            String playerName = playerData.getPlayer().getName();
            if (!playerData.getPlayer().hasPermission("core.staffmode")) {
                Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
                Bukkit.broadcastMessage(CC.translate("&r    &c✖ HOOD DETECTION ✖    "));
                Bukkit.broadcastMessage(CC.translate("&c" + playerName + " &7was removed for &cUnfair Advantage"));
                Bukkit.broadcastMessage(CC.translate("&7&m---------------------"));
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("ban %s perm %s -s", playerName, ban.getReason()));
        });
    }

    public void disguiseCheck(AbstractPlayerViolation violation) {

        String name = violation.getCheck().getName();

        if (name.contains("Aura")) {
            checkId = "Aura";
        } else if (name.contains("Velocity")) {
            checkId = "Velocity";
        } else if (name.contains("HitSelect")) {
            checkId = "Hit Select";
        } else if (name.contains("Back Track")) {
            checkId = "Back Track";
        } else if (name.contains("Aim")) {
            checkId = "Aim";
        } else if (name.contains("Range")) {
            checkId = "Range";
        } else if (name.contains("Bad Packets")) {
            checkId = "Game-Protocol";
        } else if (name.contains("Ping Spoof")) {
            checkId = "Spoof";
        } else if (name.contains("Timer")) {
            checkId = "Timer";
        } else if (name.contains("Refill")) {
            checkId = "Refill";
        } else if (name.contains("Disabler")) {
            checkId = "Disabler";
        } else if (name.contains("Flight")) {
            checkId = "Fly";
        } else if (name.contains("Ground")) {
            checkId = "Ground";
        } else if (name.contains("Motion")) {
            checkId = "Motion";
        } else if (name.contains("Speed")) {
            checkId = "Speed";
        }
    }
}
