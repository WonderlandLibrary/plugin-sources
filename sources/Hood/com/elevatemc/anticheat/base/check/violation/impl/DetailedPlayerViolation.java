package com.elevatemc.anticheat.base.check.violation.impl;

import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.util.chat.CC;
import lombok.Getter;

@Getter
public class DetailedPlayerViolation extends PlayerViolation {
    private final String data;

    public DetailedPlayerViolation(Check check, int level, Object data) {
        super(check, level);

        this.data = String.valueOf(data);
    }

    public DetailedPlayerViolation(Check check, Object data) {
        super(check, 1);

        this.data = CC.translate(data.toString());
    }
}