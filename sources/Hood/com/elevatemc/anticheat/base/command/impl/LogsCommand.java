package com.elevatemc.anticheat.base.command.impl;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.check.violation.log.Log;
import com.elevatemc.anticheat.manager.AlertManager;
import com.elevatemc.anticheat.util.chat.CC;
import com.elevatemc.anticheat.util.http.HttpUtil;
import com.elevatemc.elib.eLib;
import com.elevatemc.elib.util.UUIDUtils;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;

public class LogsCommand implements CommandExecutor {

    private double totalVL = 0D;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Bukkit.getScheduler().runTaskAsynchronously(Hood.get(), () -> {
            Player player = (Player) sender;
            if (!HoodPlugin.INSTANCE.getAlertManager().getAllowedPlayers().contains(player.getUniqueId())) {
                return;
            }
            if (args.length < 2) {
                sendMessage(player, "&c" + "/logs (player) (type)");
            } else {
                String type = args[1];
                if (type.equalsIgnoreCase("basic")) {
                    long now = System.currentTimeMillis();
                    sendMessage(player, "&eHood lookup for &d" + args[0] + "'s &elogs");

                    UUID uuid = eLib.getInstance().getUuidCache().uuid(args[0]);


                    if (uuid == null) {
                        sendMessage(player, "&cPlayer doesn't exist");
                        return;
                    }

                    Iterable<Log> logs = Hood.get().getLogHandler().getCollection("PlayerViolations")
                            .find(Filters.eq("uuid", uuid.toString()))
                            .sort(Indexes.descending("_id"))
                            .map(Log::fromDocument);

                    Map<String, Integer> checkCounts = new HashMap<>();
                    Set<String> uniqueChecks = new HashSet<>();

                    Hood.get().getLogManager().getQueuedLogs().stream()
                            .filter(log -> log.getUuid().equals(uuid))
                            .forEach(log -> {
                                String check = log.getCheck();
                                totalVL += log.getVl();

                                int count = checkCounts.getOrDefault(check, 0) + 1;
                                checkCounts.put(check, count);
                            });

                    List<BaseComponent> logComponents = new ArrayList<>();
                    List<BaseComponent> pastieComponent = new ArrayList<>();

                    logs.forEach(log -> {
                        String check = log.getCheck();

                        int count = checkCounts.getOrDefault(check, 0) + 1;

                        checkCounts.put(check, count);
                    });

                    if (!logs.iterator().hasNext()) {
                        sendMessage(player, CC.YELLOW + "No logs found for " + CC.RED + UUIDUtils.name(uuid));
                        return;
                    }

                    for (Log log : logs) {
                        String check = log.getCheck();

                        if (!uniqueChecks.contains(check)) {
                            String logMessage = createLogMessage(log, checkCounts.get(check));
                            String pastieMessage = createLogMessage(log, now, checkCounts.get(check));

                            TextComponent logComponent = new TextComponent(logMessage);
                            TextComponent pasteComponent = new TextComponent(pastieMessage);

                            logComponents.add(logComponent);
                            pastieComponent.add(pasteComponent);
                            uniqueChecks.add(check);
                        }
                    }


                    TextComponent textComponent = new TextComponent("");
                    TextComponent logsComponent = new TextComponent("");
                    logComponents.forEach(textComponent::addExtra);
                    pastieComponent.forEach(logsComponent::addExtra);
                    String key = HttpUtil.getPastie(logsComponent.toPlainText());

                    if (key == null) {
                        sendMessage(player, CC.RED + "An error occurred while uploading the logs.");
                        return;
                    }

                    sendMessage(player, "&eStatistics");
                    sendMessage(player, "&eTotal Logs: &d" + totalVL);
                    sendMessage(player, "&eChecks: &d");
                    logComponents.forEach(logComponent -> player.spigot().sendMessage(logComponent));
                    player.sendMessage("");
                    player.sendMessage(ChatColor.GRAY + "Web Logs: " + ChatColor.WHITE + "https://paste.md-5.net/" + key);
                } else if (type.equalsIgnoreCase("advanced")) {

                    UUID uuid = eLib.getInstance().getUuidCache().uuid(args[0]);


                    if (uuid == null) {
                        sendMessage(player, "&cPlayer doesn't exist");
                        return;
                    }

                    long now = System.currentTimeMillis();


                    Iterable<Log> logs = Hood.get().getLogHandler().getCollection("PlayerViolations")
                            .find(Filters.eq("uuid", uuid.toString()))
                            .sort(Indexes.descending("_id"))
                            .map(Log::fromDocument);

                    StringBuilder sb = new StringBuilder();

                    // Since the queued logs may not be pushed to the database yet we will have to check them too
                    Hood.get().getLogManager().getQueuedLogs().stream()
                           .filter(log -> log.getUuid().equals(uuid))
                            .forEach(log -> appendLog(sb, log, now));

                    logs.forEach(log -> appendLog(sb, log, now));

                    if (sb.isEmpty()) {
                        sendMessage(player, CC.RED + "No logs found for " + UUIDUtils.name(uuid));
                        return;
                    }

                    String key = HttpUtil.getPastie(sb.toString());

                    sendMessage(player, String.format("Uploaded log to %shttps://paste.md-5.net/%s", CC.GRAY, key));
                }
            }
        });
        return false;
    }

    public void sendMessage(final Player player, final String message) {
        player.sendMessage(CC.translate(message));
    }


    private String createLogMessage(Log log, int flagCount) {
        String check = log.getCheck();

        return CC.YELLOW + check + CC.PINK + " x" + flagCount;
    }
    private String createLogMessage(Log log, long now, int flagCount) {
        String timeAgo = "[" + DurationFormatUtils.formatDuration(now - log.getTimestamp(), "dd:HH:mm:ss") + "]";
        String check = log.getCheck();

        return timeAgo + " " + check + " x" + flagCount + " " + log.getData() + "\n";
    }

    private void appendLog(StringBuilder sb, Log log, long now) {
        sb.append("[")
                .append(org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(now - log.getTimestamp(), "dd:HH:mm:ss"))
                .append("] ");
        sb.append(log.getCheck()).append(" VL ");
        sb.append(log.getVl()).append(" ");
        sb.append(log.getData());
        sb.append("\n");
    }
}
