package com.elevatemc.anticheat.base.tracker.impl;


import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.math.MathHelper;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.values.OptifineFastMath;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.EntityZombie;
import org.bukkit.util.Vector;

@Getter
@Setter
public class PredictionTracker extends Tracker {

    /*
        Bot prediction - start
     */
    public int botID, rayCastEntityID, entityAReportedFlags, botTicks, entityATotalAttacks, movedBotTicks, randomBotSwingTicks, randomBotDamageTicks, rayCastFailHitTimes;
    public boolean hasBot, moveBot, WaitingForBot, hasRaycastBot, hasHitRaycast;
    public BotTypes botType;
    public double EntityAFollowDistance, rayCastEntityRoation;
    public float EntityAMovementOffset, EntityAStartYaw, rayCastStartYaw;
    public long lastEntitySpawn, entityHitTime, lastEntityBotHit, lastRaycastSpawn, lastRaycastGood, raycastEntity2HitTimes;

    public EntityZombie entityZombie, entityPlayer2;
    public EntityPlayer entityPlayer;
    public PlayerData forcedUser;

    /*
        Bot prediction - end
     */

    /*
        Player bruteforce - start
     */

    private float moveForward, moveStrafe;

    private double motionX, motionZ;
    private double distance = Double.MAX_VALUE;

    private boolean usingItem, sprinting, jump, slowdown, fastMath;
    public PredictionTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {

            Vector startMotion = new Vector(playerData.getPositionTracker().getDeltaX(), 0.0, playerData.getPositionTracker().getDeltaZ());
            // Bruteforce iteration start
            // Credit goes out to Tecnio.
            this.distance = Double.MAX_VALUE;
            iteration:
            {
                for (int f = -1; f < 2; f++) {
                    for (int s = -1; s < 2; s++) {
                        for (int sp = 0; sp < 2; sp++) {
                            for (int jp = 0; jp < 2; jp++) {
                                for (int ui = 0; ui < 2; ui++) {
                                    for (int hs = 0; hs < 2; hs++) {
                                        for (int fm = 0; fm < 2; fm++) {
                                            boolean sprinting = sp == 0;
                                            boolean jump = jp == 0;
                                            boolean usingItem = ui == 0;
                                            boolean slowdown = hs == 0;
                                            boolean fastMath = fm == 1;
                                            final boolean ground = playerData.getCollisionTracker().isLastClientGround();
                                            final boolean sneaking = playerData.getActionTracker().isSneaking();

                                            if (f <= 0.0F && sprinting && ground) continue;

                                            float moveForward = f;
                                            float moveStrafe = s;

                                            if (usingItem) {
                                                moveForward *= 0.2F;
                                                moveStrafe *= 0.2F;
                                            }

                                            if (sneaking) {
                                                moveForward *= (float) 0.3D;
                                                moveStrafe *= (float) 0.3D;
                                            }

                                            moveForward *= 0.98F;
                                            moveStrafe *= 0.98F;

                                            double motionX = playerData.getPositionTracker().getLastDeltaX();
                                            double motionZ = playerData.getPositionTracker().getLastDeltaZ();

                                            if (playerData.getCollisionTracker().isLastLastClientGround()) {
                                                motionX *= 0.6F * 0.91F;
                                                motionZ *= 0.6F * 0.91F;
                                            } else {
                                                motionX *= 0.91F;
                                                motionZ *= 0.91F;
                                            }

                                            if (slowdown) {
                                                motionX *= 0.6;
                                                motionZ *= 0.6;
                                            }

                                            if (Math.abs(motionX) < 0.005D) motionX = 0.0;
                                            if (Math.abs(motionZ) < 0.005D) motionZ = 0.0;

                                            if (jump && sprinting) {
                                                final float radians = playerData.getRotationTracker().getYaw() * 0.017453292F;

                                                if (fastMath) {
                                                    motionX -= OptifineFastMath.sin(radians) * 0.2F;
                                                    motionZ += OptifineFastMath.cos(radians) * 0.2F;
                                                } else {
                                                    motionX -= MathHelper.sin(radians) * 0.2F;
                                                    motionZ += MathHelper.cos(radians) * 0.2F;
                                                }
                                            }

                                            float friction = 0.91F;
                                            if (playerData.getCollisionTracker().isLastClientGround()) friction *= 0.6F;

                                            final float moveSpeed = (float) playerData.getAttributeTracker().getMoveSpeed(sprinting);
                                            final float moveFlyingFriction;

                                            if (ground) {
                                                final float moveSpeedMultiplier = 0.16277136F / (friction * friction * friction);

                                                moveFlyingFriction = moveSpeed * moveSpeedMultiplier;
                                            } else {
                                                moveFlyingFriction = (float) (sprinting
                                                        ? ((double) 0.02F + (double) 0.02F * 0.3D)
                                                        : 0.02F);
                                            }

                                            final float[] moveFlying = this.moveFlying(moveForward, moveStrafe, moveFlyingFriction, fastMath);

                                            motionX += moveFlying[0];
                                            motionX += moveFlying[1];

                                            Vector motion = new Vector(motionX, 0.0, motionZ);

                                            final double distance = startMotion.distanceSquared(motion);

                                            // Set the lowest distance outcome
                                            if (distance < this.distance) {
                                                this.distance = distance;
                                                this.motionX = motionX;
                                                this.motionZ = motionZ;
                                                this.moveStrafe = moveStrafe;
                                                this.moveForward = moveForward;

                                                this.sprinting = sprinting;
                                                this.jump = jump;
                                                this.usingItem = usingItem;
                                                this.slowdown = slowdown;
                                                this.fastMath = fastMath;

                                                if (distance < 1e-14) break iteration;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private float[] moveFlying(final float moveForward, final float moveStrafe, final float friction, final boolean fastMath) {
        float diagonal = moveStrafe * moveStrafe + moveForward * moveForward;

        float moveFlyingFactorX = 0.0F;
        float moveFlyingFactorZ = 0.0F;

        if (diagonal >= 1.0E-4F) {
            diagonal = MathHelper.sqrt_float(diagonal);

            if (diagonal < 1.0F) {
                diagonal = 1.0F;
            }

            diagonal = friction / diagonal;

            final float strafe = moveStrafe * diagonal;
            final float forward = moveForward * diagonal;

            final float rotationYaw = playerData.getRotationTracker().getYaw();

            final float f1 = sin(fastMath, rotationYaw * (float) Math.PI / 180.0F);
            final float f2 = cos(fastMath, rotationYaw * (float) Math.PI / 180.0F);

            final float factorX = strafe * f2 - forward * f1;
            final float factorZ = forward * f2 + strafe * f1;

            moveFlyingFactorX = factorX;
            moveFlyingFactorZ = factorZ;
        }

        return new float[] {
                moveFlyingFactorX,
                moveFlyingFactorZ
        };
    }

    private float sin(final boolean fastMath, final float yaw) {
        return fastMath ? OptifineFastMath.sin(yaw) : MathHelper.sin(yaw);
    }

    private float cos(final boolean fastMath, final float yaw) {
        return fastMath ? OptifineFastMath.cos(yaw) : MathHelper.cos(yaw);
    }

    public enum BotTypes {

        NORMAL,

        WATCHDOG,
        FOLLOW;
    }
}

