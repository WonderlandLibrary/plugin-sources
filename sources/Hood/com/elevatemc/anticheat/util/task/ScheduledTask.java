package com.elevatemc.anticheat.util.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduledTask {
    private int transaction;
    private Runnable task;
}