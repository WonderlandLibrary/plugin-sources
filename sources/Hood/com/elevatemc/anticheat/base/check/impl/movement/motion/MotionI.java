package com.elevatemc.anticheat.base.check.impl.movement.motion;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import org.bukkit.GameMode;

public class MotionI extends PositionCheck {
    public MotionI(PlayerData playerData) {
        super(playerData, "Motion I", "Large movement", new ViolationHandler(20, 3000), Category.MOVEMENT, SubCategory.MOTION, 5);
    }

    @Override
    public void handle() {
        boolean exempt = teleportTracker.isTeleport()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isVerticallyColliding()
                || Hood.get().isLagging()
                || collisionTracker.isNearPiston()
                || teleportTracker.isTeleport()
                || positionTracker.getY() % 1.0 != 0.0
                || positionTracker.getLastY() % 1.0 != 0.0;

        boolean onGround = collisionTracker.isClientGround() || collisionTracker.isServerGround();
        boolean gamemode = playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL);

        double deltaXZ = positionTracker.getDeltaXZ();
        double maxDelta = 0.33 + attributeTracker.getSpeedBoost() * 0.06;

        if (attributeTracker.getWalkSpeed() > 0.2f) {
            maxDelta += attributeTracker.getWalkSpeed() * 0.28634357f * 3.0f;
        }

        if (deltaXZ > 1.3) return;

        if (deltaXZ > maxDelta && !onGround && !exempt && gamemode) {
            if (increaseBuffer() > 4.0) {
                handleViolation(new DetailedPlayerViolation(this,"D " + deltaXZ + " MAX " + maxDelta));
            }
        }
    }
}
