package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.math.Tuple;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class AimAnalyticsK extends RotationCheck {

    private final Deque<Float> yawSamples = new LinkedList<>();
    private final Deque<Float> pitchSamples = new LinkedList<>();

    public AimAnalyticsK(PlayerData playerData) {
        super(playerData, "Analytics K", "", new ViolationHandler(5, 30000L), Category.COMBAT, SubCategory.KILL_AURA, 1);
    }

    @Override
    public void handle() {
        float deltaYaw = rotationTracker.getDeltaYaw();
        float deltaPitch = rotationTracker.getDeltaPitch();

        boolean exempt = rotationTracker.isZooming() || actionTracker.getLastAttack() > 25;
        boolean valid = deltaYaw != 0.0 && deltaPitch != 0.0 && deltaYaw <= 30.0f && deltaPitch <= 30.0f;
        handle: {
            if (exempt) break handle;

            if (valid) {
                yawSamples.add(deltaYaw);
                pitchSamples.add(deltaPitch);

                if (yawSamples.size() + pitchSamples.size() == 240) {
                    Tuple<List<Double>, List<Double>> outliersYaw = MathUtil.getOutliers(yawSamples);
                    Tuple<List<Double>, List<Double>> outliersPitch = MathUtil.getOutliers(pitchSamples);

                    double duplicateYawAmount = MathUtil.getDuplicates(yawSamples);
                    double duplicatePitchAmount = MathUtil.getDuplicates(pitchSamples);

                    int olX = outliersYaw.one.size() + outliersYaw.two.size();
                    int olY = outliersPitch.one.size() + outliersPitch.two.size();

                    if (duplicateYawAmount < 15.0 && duplicatePitchAmount <= 9.0 && olX < 30 && olY < 30) {
                        if (increaseBuffer() > 1) {
                            handleViolation(new DetailedPlayerViolation(this, "YD " + duplicateYawAmount + " PD " + duplicatePitchAmount));
                        }
                    } else {
                        resetBuffer();
                    }
                }
            }
        }
    }
}
