package com.elevatemc.anticheat.base.check.impl.movement.ground;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class GroundB extends PositionCheck {
    private int groundSpoofTicks;

    public GroundB(PlayerData playerData) {
        super(playerData, "Ground B", "Ground Spoof Check", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.GROUND, 0);
    }

    @Override
    public void handle() {
        if (collisionTracker.isOnStairs()
                || collisionTracker.isOnSlabs()
                || collisionTracker.isOnFence()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater()
                || collisionTracker.isLastOnFence()
                || playerData.getPlayer().isDead()) {
            return;
        }


    }
}