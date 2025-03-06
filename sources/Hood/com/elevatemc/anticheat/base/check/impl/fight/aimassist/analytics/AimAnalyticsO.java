package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.util.math.EvictingList;

public class AimAnalyticsO extends RotationCheck {

    private final EvictingList<Long> yaw = new EvictingList<>(250), pitch = new EvictingList<>(250);
    private long fillingYawStart, fillingPitchStart;

    long doneYaw, donePitch;

    public AimAnalyticsO(PlayerData playerData) {
        super(playerData, "Analytics O", "Timing", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.AIM_ASSIST, 0);
        reset();
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10) return;

        long now = System.currentTimeMillis();

        if (rotationTracker.getYawAccel() > 0.0) yaw.add(now);
        if (rotationTracker.getPitchAccel() > 0.0) pitch.add(now);

        if (yaw.isFull()) {
            doneYaw = (now - fillingYawStart) / 1000;
            yaw.clear();
            fillingYawStart = now;
        }

        if (pitch.isFull()) {
            donePitch = (now - fillingPitchStart) / 1000;
            pitch.clear();
            fillingPitchStart = now;
        }

        //if (doneYaw > 0 && donePitch > 0) debug("Analytics finished yaw filling in " + doneYaw + " second(s) finished pitch filling in " + donePitch + " second(s)");
    }


    public void reset() {
        long currentTime = System.currentTimeMillis();

        fillingPitchStart = currentTime;
        fillingYawStart = currentTime;
    }
}
