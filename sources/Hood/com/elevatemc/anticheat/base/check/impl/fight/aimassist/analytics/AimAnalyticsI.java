package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.LinearRegression;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.google.common.collect.Lists;
import lombok.val;

import java.util.List;

public class AimAnalyticsI extends RotationCheck {

    private final List<Double> samplesYaw = Lists.newArrayList();
    private final List<Double> samplesPitch = Lists.newArrayList();

    public AimAnalyticsI(PlayerData playerData) {
        super(playerData, "Analytics I", "Invalid rotation", new ViolationHandler(10, 3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10 || rotationTracker.isZooming()) {
            return;
        }

        double deltaYaw = rotationTracker.getDeltaYaw();
        double deltaPitch = rotationTracker.getDeltaPitch();

        handle:
        {
            if (deltaYaw == 0.0 || deltaPitch == 0.0)
                break handle;

            samplesYaw.add(deltaYaw);
            samplesPitch.add(deltaPitch);

            if (samplesYaw.size() + samplesPitch.size() == 60) {

                val outliersYaw = MathUtil.getOutliers(samplesYaw);
                val outliersPitch = MathUtil.getOutliers(samplesPitch);

                Double[] regressionX = new Double[samplesYaw.size()];
                Double[] regressionY = new Double[samplesPitch.size()];

                regressionX = samplesYaw.toArray(regressionX);
                regressionY = samplesPitch.toArray(regressionY);

                final LinearRegression regression = new LinearRegression(regressionX, regressionY);

                int fails = 0;

                for (int i = 0; i < 30; i++) {
                    double tempX = regressionX[i];
                    double tempY = regressionY[i];

                    double predicted = regression.predict(tempX);
                    double subtracted = predicted - tempY;

                    fails = subtracted > 0.1 ? fails + 1 : fails;
                }

                double intercepts = regression.interceptStdErr();
                double slope = regression.slopeStdErr();

                int outliersX = outliersYaw.one.size() + outliersYaw.two.size();
                int outliersY = outliersPitch.one.size() + outliersPitch.two.size();

                if (intercepts > 1.4 && slope > 0.0 && fails > 15 && outliersX < 10 && outliersY < 10) {
                    if (increaseBuffer() > 6) {
                        handleViolation(new DetailedPlayerViolation(this, "I " + format(intercepts) + " S " + format(slope)));
                    }
                } else decreaseBufferBy(0.15);

                samplesYaw.clear();
                samplesPitch.clear();
            }
        }
    }
}

