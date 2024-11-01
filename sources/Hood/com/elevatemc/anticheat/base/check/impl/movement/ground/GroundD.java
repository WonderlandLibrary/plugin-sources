package com.elevatemc.anticheat.base.check.impl.movement.ground;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;

public class GroundD extends PositionCheck {

    double limit = 0.5625f;
    boolean lastlastlasltalsaewo0eidhawpoudhwapd= false;
    public GroundD(PlayerData playerData) {
        super(playerData, "Ground D", "WHERE THE FUCK ARE YOU GOING", ViolationHandler.EXPERIMENTAL,Category.MOVEMENT, SubCategory.GROUND, 1);
    }

    @Override
    public void handle() {
        boolean exempt = playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().isInsideVehicle()
                || collisionTracker.isOnSlime()
                || collisionTracker.isLastOnSlime()
                || collisionTracker.isInWater()
                || teleportTracker.isTeleport();

        boolean hasMoved = positionTracker.isMoved();

        if (!hasMoved) return;
        if (exempt) return;

        boolean serverOnGround = collisionTracker.isLastServerGround();;
        boolean lastLastServerOnGround = collisionTracker.isLastLastServerGround();

        double deltaY = positionTracker.getDeltaY();

        //Bukkit.broadcastMessage("Y: " + deltaY + " server= " + serverOnGround  + " lastLast: " + lastLastServerOnGround + " LASLTLASLT " + lastlastlasltalsaewo0eidhawpoudhwapd);

        if (velocityTracker.isOnFirstTick()) {
            limit += attributeTracker.getJumpBoost() * 0.2f + velocityTracker.peekVelocity().getY();
        } else {
            limit += attributeTracker.getJumpBoost() * 0.2f;
        }

        if (deltaY > limit && (serverOnGround && lastlastlasltalsaewo0eidhawpoudhwapd)) {
            if (increaseBuffer() > 3) {
                handleViolation(new DetailedPlayerViolation(this, "Y " + format(deltaY) + " M " + format(limit)));
                staffAlert(new PlayerViolation(this));
                multiplyBuffer(0.25);
            }
        } else {
            decreaseBufferBy(0.75);
        }

        // Im not fucking adding this to collision tracker that's just plain retarded
        // It's retarded enough how it currently is.
        lastlastlasltalsaewo0eidhawpoudhwapd = lastLastServerOnGround;
    }
}
