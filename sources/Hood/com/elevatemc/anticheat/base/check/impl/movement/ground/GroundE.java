package com.elevatemc.anticheat.base.check.impl.movement.ground;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import org.bukkit.GameMode;

public class GroundE extends PositionCheck {

    public GroundE(PlayerData playerData) {
        super(playerData, "Ground E", "WHERE THE FUCK ARE YOU GOING", ViolationHandler.EXPERIMENTAL,Category.MOVEMENT, SubCategory.GROUND, 1);
    }

    @Override
    public void handle() {
        boolean exempt =  playerData.getPlayer().getAllowFlight()
                || Hood.get().isLagging()
                || playerData.getPlayer().isInsideVehicle()
                || collisionTracker.isInWeb()
                || collisionTracker.isInWater()
                || collisionTracker.isOnIce()
                || collisionTracker.isLastOnIce()
                || teleportTracker.isTeleport()
                || collisionTracker.isOnFence()
                || collisionTracker.isLastOnFence()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || !playerData.getPlayer().getGameMode().equals(GameMode.SURVIVAL);

        if (exempt) return;

        final double deltaY = positionTracker.getDeltaY();
        final boolean clientGround = collisionTracker.isClientGround();
        final boolean serverGround = collisionTracker.isServerGround();

        final boolean spoof = clientGround && !serverGround && deltaY != 0.0;
        final boolean anti = !clientGround && serverGround && deltaY == 0.0;
        if (spoof || anti) {
            if (increaseBuffer() > 9) {
                handleViolation(new PlayerViolation(this));
            }
        } else {
            decreaseBufferBy(0.025);
        }
    }
}
