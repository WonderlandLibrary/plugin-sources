package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.MinecraftServer;

public class SpeedP extends PositionCheck {
    private Double lastDistance;
    private Float friction;
    private Boolean lastOffset;

    public SpeedP(final PlayerData playerData) {
        super(playerData, "Speed P", "Breached limit", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SPEED, 0);
    }

    @Override
    public void handle() {
        final double distance = positionTracker.getDeltaXZ();
        if (lastOffset != null && friction != null) {
            double attribute = attributeTracker.getWalkSpeed();
            attribute *= 1.2999999523162842;
            attribute *= 1.0f + attributeTracker.getSpeedBoost() * 0.2f;
            if (collisionTracker.isLastClientGround()) {
                friction *= 0.91f;
                attribute *= 0.16277135908603668 / Math.pow(friction, 3.0);
                if (!collisionTracker.isClientGround() && positionTracker.getY() > positionTracker.getLastY()) {
                    attribute += 0.20000000298023224;
                }
            }
            else {
                attribute = 0.026000000536441803;
                friction = 0.91f;
            }
            attribute += MathUtil.hypot(velocityTracker.getVelocityX(), velocityTracker.getVelocityZ());
            if (lastOffset) {
                attribute += 0.05;
            }
            if (collisionTracker.isInWeb()) {
                attribute += 0.01;
            }
            if (lastDistance != null) {
                double excess = distance - lastDistance - attribute;
                boolean exempt = playerData.getPlayer().getAllowFlight() || teleportTracker.isTeleport() || this.collisionTracker.isInWater();

                if (excess > 0.001 && !exempt) {
                    if (increaseBuffer() > 7) {
                        handleViolation(new DetailedPlayerViolation(this, ""));
                    }
                } else {
                    decreaseBufferBy(0.1D);
                }
            }
            this.lastDistance = distance * friction;
        }
        this.lastOffset = positionTracker.getLastDeltaX() > 0.0 && positionTracker.getDeltaXZ() == 0.0 && !positionTracker.isMoved();
        this.friction = MinecraftServer.getServer().getWorld().getType(new BlockPosition(positionTracker.getX(), positionTracker.getY() - 1.0, positionTracker.getZ())).getBlock().frictionFactor;
    }
}
