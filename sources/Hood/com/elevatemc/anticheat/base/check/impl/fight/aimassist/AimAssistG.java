package com.elevatemc.anticheat.base.check.impl.fight.aimassist;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;

public class AimAssistG extends RotationCheck {

    public AimAssistG(PlayerData playerData) {
        super(playerData, "Aim Assist G", "Not constant rotations", new ViolationHandler(20, 5000L), Category.COMBAT, SubCategory.AIM_ASSIST, 1);
    }

    @Override
    public void handle() {
        if (actionTracker.getLastAttack() < 5) {

            if (rotationTracker.isZooming()) return;

            float deltaYaw = rotationTracker.getDeltaYaw();
            float deltaPitch = rotationTracker.getDeltaPitch();

            float lastDeltaYaw = rotationTracker.getLastDeltaYaw();
            float lastDeltaPitch = rotationTracker.getLastDeltaPitch();

            double divisorYaw = MathUtil.getGcd((long) (deltaYaw * MathUtil.EXPANDER), (long) (lastDeltaYaw * MathUtil.EXPANDER));
            double divisorPitch = MathUtil.getGcd((long) (deltaPitch * MathUtil.EXPANDER), (long) (lastDeltaPitch * MathUtil.EXPANDER));

            double constantYaw = divisorYaw / MathUtil.EXPANDER;
            double constantPitch = divisorPitch / MathUtil.EXPANDER;

            double currentX = deltaYaw / constantYaw;
            double currentY = deltaPitch / constantPitch;

            double previousX = lastDeltaYaw / constantYaw;
            double previousY = lastDeltaPitch / constantPitch;

            if (deltaYaw > 0.1F && deltaPitch > 0.1F && deltaYaw < 20.f && deltaPitch < 20.f) {
                double moduloX = currentX % previousX;
                double moduloY = currentY % previousY;

                double floorModuloX = Math.abs(Math.floor(moduloX) - moduloX);
                double floorModuloY = Math.abs(Math.floor(moduloY) - moduloY);

                boolean invalidX = moduloX > 90.d && floorModuloX > 0.1D;
                boolean invalidY = moduloY > 90.d && floorModuloY > 0.1D;

                boolean improper = Double.isInfinite(moduloY) || moduloX > 500 || moduloY > 500;

                if (invalidX && invalidY && !improper) {
                    if (increaseBuffer() > 12.0) {
                        handleViolation(new DetailedPlayerViolation(this, String.format("Y %s P %s", format(deltaYaw), format(deltaPitch))));
                        multiplyBuffer(.25);
                    }
                } else {
                    decreaseBufferBy(.35);
                }
            }
        }
    }
}
