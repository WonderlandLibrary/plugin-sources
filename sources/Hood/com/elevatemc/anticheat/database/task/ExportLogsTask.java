package com.elevatemc.anticheat.database.task;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.check.violation.log.Log;
import com.elevatemc.anticheat.database.LogHandler;
import com.elevatemc.anticheat.database.LogManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.Queue;
import java.util.stream.Collectors;

public class ExportLogsTask implements Runnable {

    private static BukkitTask task;
    public long lastRun;
    public void start() {
        assert task == null : "ExportLogsTask has already been started!";

        task = Bukkit.getScheduler().runTaskTimerAsynchronously(Hood.get(), this, 300L, 300L);
    }
    @Override
    public void run() {
        lastRun = System.currentTimeMillis();
        LogHandler dbManager = Hood.get().getLogHandler();
        LogManager logManager = Hood.get().getLogManager();

        Queue<Log> queuedLogs = logManager.getQueuedLogs();

        if (task == null) {
            System.out.println("EXPORT LOGS TASK IS NULL !!!!!!!!!");
        }

        if (logManager == null) {
            System.out.println("LOG MANAGER IS NULL !!!!!!!");
            return;
        }

        if (dbManager == null) {
            System.out.println("DB MANAGER IS NULL !!!!!!!");
            return;
        }

        if (queuedLogs.isEmpty()) {
            System.out.println("QUEUED LOGS ARE EMPTY !!!!!!!");
            return;
        }
        dbManager.getCollection("PlayerViolations").insertMany(
                    queuedLogs.stream()
                            .map(Log::toDocument)
                            .collect(Collectors.toList()));

        System.out.println("Saving " + queuedLogs.size() + " logs...");
        queuedLogs.clear();
    }

    public void stop() {
        if (task == null) return;

        task.cancel();
        task = null;
    }
}
