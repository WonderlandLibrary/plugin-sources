package com.elevatemc.anticheat.util.violation;

import lombok.Getter;

@Getter
public enum Probability {
    BLATANT,
    HIGH,
    SECURE,
    PROBABLE,
    LOW,
    FALSE,
    NOT;
}
