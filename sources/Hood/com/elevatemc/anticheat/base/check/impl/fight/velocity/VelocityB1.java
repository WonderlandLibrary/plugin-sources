package com.elevatemc.anticheat.base.check.impl.fight.velocity;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class VelocityB1 extends PositionCheck {
    public VelocityB1(PlayerData playerData) {
        super(playerData, "Velocity B1", "Velo!", ViolationHandler.EXPERIMENTAL, Category.COMBAT, SubCategory.VELOCITY, 2);
    }

    @Override
    public void handle() {
        if (velocityTracker.getTicksSinceVelocity() <= 5) {
            if (collisionTracker.isVerticallyColliding()
                    || collisionTracker.isOnClimbable()
                    || collisionTracker.isInWater()
                    || collisionTracker.isInLava()
                    || collisionTracker.isInWeb()
                    || positionTracker.isJumped()) {
                decreaseBufferBy(0.01D);
                return;
            }

            double deltaY = positionTracker.getDeltaY();
            double velocityY = velocityTracker.getVelocityY();
            double y = positionTracker.getLastY();

            if (velocityY < 0) return;

            if (y % 1.0 == 0.0) {
                double ratio = deltaY / velocityY * 100.0;

                if (ratio == 0.0) {
                    if (increaseBuffer() > 8) handleViolation(new PlayerViolation(this));
                } else {
                    resetBuffer();
                }
            }
        }
    }
}
