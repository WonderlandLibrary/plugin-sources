package com.elevatemc.anticheat.database;

import com.elevatemc.anticheat.base.check.violation.log.BanwaveLog;
import com.elevatemc.anticheat.base.check.violation.log.Log;
import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
public class LogManager {
    private final Queue<Log> queuedLogs = new ConcurrentLinkedQueue<>();
    private final Queue<BanwaveLog> queuedCheaters = new ConcurrentLinkedQueue<>();
}