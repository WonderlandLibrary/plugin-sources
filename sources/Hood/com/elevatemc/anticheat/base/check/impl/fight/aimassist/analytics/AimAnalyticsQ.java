package com.elevatemc.anticheat.base.check.impl.fight.aimassist.analytics;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.chat.CC;
import com.elevatemc.anticheat.util.math.EvictingList;
import com.elevatemc.anticheat.util.math.LinearRegression;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAnalyticsQ extends RotationCheck {

    private final EvictingList<Double> data = new EvictingList<>(250), lel = new EvictingList<>(250);


    public AimAnalyticsQ(PlayerData playerData) {
        super(playerData, "Analytics Q", "yes", new ViolationHandler(8, 3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 2);
    }

    @Override
    public void handle() {
        boolean attacking = actionTracker.getLastAttack() <= 4;

        handle:
        {
            if (!attacking || rotationTracker.isZooming()) break handle;

            double deltaYaw = rotationTracker.getDeltaYaw();
            double deltaPitch = rotationTracker.getPitch();

            data.add(deltaYaw);
            lel.add(deltaPitch);

            if (data.isFull()) {

                Double[] regressionX = new Double[data.size()];
                Double[] regressionY = new Double[lel.size()];

                regressionX = data.toArray(regressionX);
                regressionY = lel.toArray(regressionY);

                final LinearRegression regression = new LinearRegression(regressionX, regressionY);

                double st1 = MathUtil.getStandardDeviation(data);
                double st2 = MathUtil.getStandardDeviation(lel);
                double error = regression.interceptStdErr();

                if (st1 < 4 && st2 < 4 && error < 0.3) {
                    handleViolation(new DetailedPlayerViolation(this, "X " + format(st1) + " Y " + format(st2) + " ERROR " + format(error)));
                }
                data.clear();
                lel.clear();
            }
        }
    }
}