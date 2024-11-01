package com.elevatemc.anticheat.base.check.impl.misc.badpackets;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class BadPacketsA extends RotationCheck {
    public BadPacketsA(PlayerData playerData) {
        super(playerData, "Bad Packets A", "Impossible Pitch Check", new ViolationHandler(1, 60L), Category.MISC, SubCategory.BAD_PACKETS, 0);
    }

    @Override
    public void handle() {
        if (teleportTracker.isTeleport()) {
            return;
        }

        float pitch = Math.abs(rotationTracker.getPitch());

        if (pitch > 90F) {
            handleViolation(new DetailedPlayerViolation(this, "P " + pitch));
            staffAlert(new PlayerViolation(this));
        }
    }
}