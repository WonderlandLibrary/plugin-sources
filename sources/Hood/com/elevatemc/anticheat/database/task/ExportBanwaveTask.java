package com.elevatemc.anticheat.database.task;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.check.violation.log.BanwaveLog;
import com.elevatemc.anticheat.database.LogHandler;
import com.elevatemc.anticheat.database.LogManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Queue;
import java.util.stream.Collectors;

public class ExportBanwaveTask extends BukkitRunnable {
    private final LogHandler databaseManager = Hood.get().getLogHandler();
    private final LogManager logManager = Hood.get().getLogManager();

    @Override
    public void run() {
        Queue<BanwaveLog> queuedLogs = logManager.getQueuedCheaters();

        if (queuedLogs.isEmpty()) {
            return;
        }

        databaseManager.getCollection("HoodBanwave").insertMany(queuedLogs.stream()
                .map(BanwaveLog::toDocument)
                .collect(Collectors.toList()));
        System.out.println("Exported Banwaved players!");

        queuedLogs.clear();
    }
}
