package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SpeedC extends PositionCheck {

    public SpeedC(PlayerData playerData) {
        super(playerData, "Speed C", "Checks for onGround speed", new ViolationHandler(15, 3000L), Category.MOVEMENT, SubCategory.SPEED,1);
    }

    @Override
    public void handle() {
        double deltaXZ = positionTracker.getDeltaXZ();
        double deltaY = positionTracker.getDeltaY();
        double maxSpeed = PlayerUtil.getBaseGroundSpeed(playerData);

        int groundTicks = collisionTracker.getClientGroundTicks();

        List<String> tags = new ArrayList<>();

        switch (groundTicks) {
            case 0: {
                maxSpeed += 0.333;
                if (deltaY > 0.4) {
                    maxSpeed += 0.02;
                    break;
                }
                break;
            }
            case 1: {
                maxSpeed += 0.0275;
                break;
            }
            case 2: {
                maxSpeed += 0.145;
                break;
            }
            case 3: {
                maxSpeed += 0.075;
                break;
            }
            case 4: {
                maxSpeed += 0.045;
                break;
            }
            case 5: {
                maxSpeed += 0.0305;
                break;
            }
            case 6: {
                maxSpeed += 0.015;
                break;
            }
            case 7: {
                maxSpeed += 0.01;
                break;
            }
            case 8: {
                maxSpeed += 0.007;
                break;
            }
            case 9: {
                maxSpeed += 0.005;
                break;
            }
        }
        boolean velocity = velocityTracker.isOnFirstTick();
        if (velocity) {
            tags.add("Velocity");
            final double velocityXZ = Math.hypot(Math.abs(velocityTracker.peekVelocity().getX()), velocityTracker.peekVelocity().getZ());
            maxSpeed += velocityXZ + 0.15;
        }
        boolean ice = collisionTracker.isOnIce();
        if (ice) {
            tags.add("Ice");
            maxSpeed += 0.85;
        }
        boolean collidingVertically = collisionTracker.isVerticallyColliding() || collisionTracker.isLastVerticallyColliding() || collisionTracker.isLastLastVerticallyColliding();
        if (collidingVertically) {
            tags.add("Vertical collision");
            ++maxSpeed;
        }
        boolean slime = collisionTracker.isOnSlime() || collisionTracker.isLastOnSlime();
        if (slime) {
            tags.add("Slime");
            ++maxSpeed;
        }
        boolean trapdoor = collisionTracker.isNearPiston();
        if (trapdoor) {
            tags.add("Trapdoor");
            ++maxSpeed;
        }
        if (collisionTracker.isInWater() && isWearingDepthStrider(getPlayerData().getPlayer().getPlayer())) {
            tags.add("Depth Strider");
            maxSpeed += 0.25;
        }
        boolean fence = collisionTracker.isOnFence();
        if (fence) {
            tags.add("Fence");
            maxSpeed += 0.25;
        }

        boolean exempt = teleportTracker.isTeleport()
                || collisionTracker.isInWater()
                || collisionTracker.isInLava()
                || collisionTracker.isLastInLava()
                || collisionTracker.isLastInWater()
                || playerData.getPlayer().getAllowFlight()
                || Hood.get().isLagging();

        double difference = deltaXZ - maxSpeed;
        boolean invalid = difference > 1.0E-5 && groundTicks >= 0;

        if (invalid && !exempt) {
            if (increaseBuffer() > 13.5) {
                multiplyBuffer(0.25);
                setVl(vl + 1);
                handleViolation(new DetailedPlayerViolation(this, "T " + groundTicks + " D " + format(difference) + " T [" + tags + "]"));
                staffAlert(new PlayerViolation(this));
            }
        }
        else {
            decreaseBufferBy(0.175);
        }
    }

    public boolean isWearingDepthStrider(final Player player) {
        return player.getInventory().getBoots() != null && player.getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) > 0;
    }
}