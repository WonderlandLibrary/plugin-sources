package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;

public class KillAuraI extends RotationCheck {

    private double streak;
    public KillAuraI(PlayerData playerData) {
        super(playerData, "Aura 6I", "Identical aim rotations", new ViolationHandler(3, 3000L),
                Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle() {
        float yawDelta = rotationTracker.getDeltaYaw();
        float pitchDelta = rotationTracker.getDeltaPitch();
        float lastYawDelta = rotationTracker.getLastDeltaYaw();
        float lastPitchDelta = rotationTracker.getLastDeltaPitch();

        if (actionTracker.getLastAttack() > 10) {
            resetBuffer();
            streak = 0.0;
            return;
        }
        if (teleportTracker.isTeleport()) {
            resetBuffer();
            return;
        }

        float magicVal = pitchDelta * 100.0f / lastPitchDelta;

        if (magicVal > 60) {
            decreaseBuffer();
            streak = Math.max(0.0, streak - 0.125);
        }

        if (yawDelta > 0.0 && pitchDelta > 0.0) {
            int roundedYaw = Math.round(yawDelta);
            int previousRoundedYaw = Math.round(lastYawDelta);

            float yawDeltaChange = Math.abs(yawDelta - lastYawDelta);
            boolean invalid = roundedYaw == previousRoundedYaw && yawDelta > 0.01 && yawDelta > 1.5f && yawDeltaChange > 0.001 && pitchDelta > 0.5 && pitchDelta <= 20.0f;

            if (invalid) {
                if (increaseBuffer() > 1.0) {
                    ++streak;
                }
                if (streak > 6.0) {
                    handleViolation(new DetailedPlayerViolation(this,"Y " + format(roundedYaw) + " C " + format(yawDeltaChange)));
                }
            } else {
                decreaseBuffer();
            }
        }
    }
}
