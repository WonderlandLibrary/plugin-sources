package com.elevatemc.anticheat.base.check.impl.movement.speed;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathHelper;
import org.bukkit.enchantments.Enchantment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpeedL extends PositionCheck {

    float playerF;
    public SpeedL(PlayerData playerData) {
        super(playerData, "Speed L", "Prediction speed", new ViolationHandler(15, 300L), Category.MOVEMENT, SubCategory.SPEED,4);
    }

    @Override
    public void handle() {
        boolean exempt = playerData.getPlayer().getAllowFlight() || teleportTracker.isTeleport() || playerData.getPlayer().isDead() || playerData.getPlayer().isInsideVehicle()
                || attributeTracker.getWalkSpeed() != 0.2F;

        if (playerData.getPlayer().getInventory().getBoots() != null && playerData.getPlayer().getInventory().getBoots().containsEnchantment(Enchantment.DEPTH_STRIDER)) {
            return;
        }

        if (!exempt) {
            if (!positionTracker.isSentMotion()) return;

            if (collisionTracker.isLastLastClientGround()) {
                playerF = 0.91F * 0.6F;

                if (collisionTracker.isOnIce()) {
                    //debug(friction);
                    playerF =  0.91F * 0.98F;
                }
                if (collisionTracker.isOnSlime()) {
                    //debug(friction);
                    playerF = 0.91F * 0.8F;
                }
                if (collisionTracker.isOnIce() && collisionTracker.isOnSlime()) {
                    //debug(friction);
                    playerF = (0.91F * 0.8F) * 0.98F;
                }
            } else {
                playerF = 0.91F;
            }

            double deltaXZ = positionTracker.getDeltaXZ(), lastDeltaXZ = positionTracker.getLastDeltaXZ();

            double predictedXZ = lastDeltaXZ * playerF;
            double deltaY = positionTracker.getDeltaY();
            //debug(predictedXZ);

            boolean onGround = collisionTracker.isClientGround(), lastOnGround = collisionTracker.isLastClientGround();

            if (!onGround && lastOnGround && deltaY > 0.0) {
                //debug("dY=" + deltaY);
                //debug("p=" + predictedXZ);
                predictedXZ += 0.2F;
            }

            if (actionTracker.getLastAttack() < 4) {
                //debug(predictedXZ);
                predictedXZ += 0.0101f;
            }

            if (velocityTracker.isOnFirstTick()) {
                //debug(predictedXZ);
                predictedXZ += 0.0101f;
            }

            predictedXZ += movingFlyingV3(playerData);

            double totalSpeed = deltaXZ - predictedXZ;

            if (onGround || lastOnGround) {
                //debug("dXZ=" + deltaXZ);
                // debug("S=" + totalSpeed);

                if (deltaXZ > 0.37 && totalSpeed > 0.8) {
                    if (increaseBuffer() > 3) handleViolation(new DetailedPlayerViolation(this,"XZ " + format(deltaXZ) + " S " + format(totalSpeed)));
                } else {
                   decreaseBuffer();
                }
            }
        }
    }

    // Credits to FlyCode (Rhys)
    public double movingFlyingV3(PlayerData user) {

        double preD = 0.01D;

        double mx = user.getPositionTracker().getDeltaX();
        double mz = user.getPositionTracker().getDeltaZ();

        float motionYaw = (float) (Math.atan2(mz, mx) * 180.0D / Math.PI) - 90.0F;

        int direction;

        motionYaw -= user.getRotationTracker().getYaw();

        while (motionYaw > 360.0F)
            motionYaw -= 360.0F;
        while (motionYaw < 0.0F)
            motionYaw += 360.0F;

        motionYaw /= 45.0F;

        float moveS = 0.0F;
        float moveF = 0.0F;

        if (Math.abs(Math.abs(mx) + Math.abs(mz)) > preD) {
            direction = (int) new BigDecimal(motionYaw).setScale(1, RoundingMode.HALF_UP).doubleValue();

            if (direction == 1) {
                moveF = 1F;
                moveS = -1F;


            } else if (direction == 2) {
                moveS = -1F;


            } else if (direction == 3) {
                moveF = -1F;
                moveS = -1F;


            } else if (direction == 4) {
                moveF = -1F;

            } else if (direction == 5) {
                moveF = -1F;
                moveS = 1F;

            } else if (direction == 6) {
                moveS = 1F;

            } else if (direction == 7) {
                moveF = 1F;
                moveS = 1F;

            } else if (direction == 8) {
                moveF = 1F;

            } else if (direction == 0) {
                moveF = 1F;
            }
        }

        moveS *= 0.98F;
        moveF *= 0.98F;

        float strafe = 1F, forward = 1F;
        float f = strafe * strafe + forward * forward;

        if (playerData.getActionTracker().getLastAttack() <= 4) {
            strafe *= 0.2F;
            forward *= 0.2F;
        }


        if (user.getActionTracker().isSneaking() && !user.getActionTracker().isSprinting()) {
            strafe *= 0.3F;
            forward *= 0.3F;
        }


        float friction;

        float var3 = (0.6F * 0.91F);
        float getAIMoveSpeed = 0.13000001F;


        if (user.getAttributeTracker().getSpeedBoost()> 0) {
            switch (user.getAttributeTracker().getSpeedBoost()) {
                case 0: {
                    getAIMoveSpeed = 0.23400002F;
                    break;
                }

                case 1: {
                    getAIMoveSpeed = 0.156F;
                    break;
                }

                case 2: {
                    getAIMoveSpeed = 0.18200001F;
                    break;
                }

                case 3: {
                    getAIMoveSpeed = 0.208F;
                    break;
                }

                case 4: {
                    getAIMoveSpeed = 0.23400001F;
                    break;
                }

            }
        }

        float var4 = 0.16277136F / (var3 * var3 * var3);

        if (user.getCollisionTracker().isClientGround()) {
            friction = getAIMoveSpeed * var4;
        } else {
            friction = 0.026F;
        }


        float f4 = 0.026F;
        float f5 = 0.8F;

        if (collisionTracker.isInWater() || collisionTracker.isInLava()) {


            if (user.getPlayer().getInventory().getBoots() != null
                    && user.getPlayer().getInventory().getBoots().getEnchantments() != null) {

                float f3 = user.getPlayer().getInventory().getBoots().getEnchantmentLevel(Enchantment.DEPTH_STRIDER);

                if (f3 > 3.0F) {
                    f3 = 3.0F;
                }

                if (!user.getCollisionTracker().isLastClientGround()) {
                    f3 *= 0.5F;
                }

                if (f3 > 0.0F) {
                    f5 += (0.54600006F - f5) * f3 / 3.0F;
                    f4 += (getAIMoveSpeed - f4) * f3 / 3.0F;
                }

                friction = f4;

                this.playerF = f5;
            }
        }


        if (f >= 1.0E-4F) {
            f = MathHelper.sqrt_float(f);
            if (f < 1.0F) {
                f = 1.0F;
            }
            f = friction / f;
            strafe = strafe * f;
            forward = forward * f;
            float f1 = MathHelper.sin(user.getRotationTracker().getYaw() * (float) Math.PI / 180.0F);
            float f2 = MathHelper.cos(user.getRotationTracker().getYaw() * (float) Math.PI / 180.0F);
            float motionXAdd = (strafe * f2 - forward * f1);
            float motionZAdd = (forward * f2 + strafe * f1);
            return Math.hypot(motionXAdd, motionZAdd);
        }

        this.playerF = f5;

        return 0;
    }
}
