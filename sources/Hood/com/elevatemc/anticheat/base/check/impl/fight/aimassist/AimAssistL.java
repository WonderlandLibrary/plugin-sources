package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
public class AimAssistL extends RotationCheck {

    public AimAssistL(PlayerData playerData) {
        super(playerData, "Aim Assist L", "Invalid rotation modulos", new ViolationHandler(30,
                3000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() > 10 || rotationTracker.isZooming()) {
            return;
        }
        float deltaYaw = rotationTracker.getDeltaYaw();
        float deltaPitch = rotationTracker.getDeltaPitch();

        double divYaw = (double) MathUtil.getGcd((long) (rotationTracker.getDeltaYaw() * MathUtil.EXPANDER), (long) (rotationTracker.getLastDeltaYaw() * MathUtil.EXPANDER));
        double divPitch = (double) MathUtil.getGcd((long) (rotationTracker.getDeltaPitch() * MathUtil.EXPANDER), (long) (rotationTracker.getLastDeltaPitch() * MathUtil.EXPANDER));

        double constantYaw = divYaw / MathUtil.EXPANDER;
        double constantPitch = divPitch / MathUtil.EXPANDER;

        double currentX = rotationTracker.getDeltaYaw() / constantYaw;
        double currentY = rotationTracker.getDeltaPitch() / constantPitch;

        double previousX = rotationTracker.getLastDeltaYaw() / constantYaw;
        double previousY = rotationTracker.getLastDeltaPitch() / constantPitch;

        if (deltaYaw > 0.1F && deltaPitch > 0.1F && deltaYaw < 20.f && deltaPitch < 20.f) {

            double moduloX = currentX / previousX;
            double moduloY = currentY / previousY;

            double floorModuloX = Math.abs(Math.floor(moduloX) - moduloX);
            double floorModuloY = Math.abs(Math.floor(moduloY) - moduloY);

            boolean invalidX = moduloX > 90.0F && floorModuloX > 0.1F;
            boolean invalidY = moduloY > 90.0F && floorModuloY > 0.1F;

            boolean improper = Double.isInfinite(moduloY) || moduloX > 500 || moduloY > 500;

            if ((invalidX || invalidY) && !improper) {
                if (increaseBuffer() > 24.0) {
                    handleViolation(new DetailedPlayerViolation(this, String.format("X %s Y %s", format(moduloX), format(moduloY))));
                    multiplyBuffer(.5);
                } else {
                    decreaseBufferBy(.15);
                }
            }
        }
    }
}
