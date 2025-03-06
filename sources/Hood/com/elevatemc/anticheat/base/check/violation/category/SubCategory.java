package com.elevatemc.anticheat.base.check.violation.category;

import lombok.Getter;

public enum SubCategory {
    // Combat
    AIM_ASSIST(Category.COMBAT),
    KILL_AURA(Category.COMBAT),
    REACH(Category.COMBAT),
    VELOCITY(Category.COMBAT),
    BACK_TRACK(Category.COMBAT),

    // Misc
    BAD_PACKETS(Category.MISC),
    PING_SPOOF(Category.MISC),
    SIMULATION(Category.MISC),
    TIMER(Category.MISC),
    REFILL(Category.MISC),

    // Movement
    FLIGHT(Category.MOVEMENT),
    GROUND(Category.MISC),
    MOTION(Category.MOVEMENT),
    WATERSPEED(Category.MOVEMENT),
    DISABLER(Category.MOVEMENT),
    SCAFFOLD(Category.MOVEMENT),
    SPEED(Category.MOVEMENT);



    @Getter
    private final Category category;

    SubCategory(final Category category) {
        this.category = category;
    }
}