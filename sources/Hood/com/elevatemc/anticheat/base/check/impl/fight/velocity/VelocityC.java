package com.elevatemc.anticheat.base.check.impl.fight.velocity;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.VelocityTracker;
import com.elevatemc.anticheat.util.math.MathHelper;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.elevatemc.anticheat.util.math.client.impl.OptifineMath;
import com.elevatemc.anticheat.util.reach.values.LegacyFastMath;
import com.elevatemc.anticheat.util.reach.values.VanillaMath;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityVelocity;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.MinecraftServer;

public class VelocityC extends PacketCheck {
    private static final double THRESHOLD = 0.00000000001;
    private int tick = Integer.MIN_VALUE;
    private double bestAccuracy = Double.MAX_VALUE;
    private double currentMotionX = 0;
    private double currentMotionZ = 0;
    private double currentMotionAccuracy = Double.MAX_VALUE;
    private final int[] bufferOtherTicks = new int[20];

    private boolean attacking = false;

    public VelocityC(PlayerData playerData) {
        super(playerData, "Velocity C", "Horizontal Velocity Modifications",new ViolationHandler(15, 30000L), Category.COMBAT, SubCategory.VELOCITY, 2);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        boolean flying = WrapperPlayClientPlayerFlying.isFlying(event.getPacketType());

        if (flying) {
            if (tick > 0) {
                // We check if we can verify velocity
                if (!velocityTracker.canVerifyVelocity()) {
                    reset();
                    return;
                }

                // We calculate the accuracy of the next y movement
                boolean willSendMotion = simulateNextMotionXZ();

                if (willSendMotion) {
                    if (currentMotionAccuracy > THRESHOLD) {
                        if (bufferOtherTicks[tick]++ > 8) {
                            handleViolation(new DetailedPlayerViolation(this,"T " + tick + " S " + currentMotionAccuracy));
                        }

                        reset();
                    } else {
                        bufferOtherTicks[tick] = Math.max(bufferOtherTicks[tick] - 1, 0);

                        tick++;
                        if (tick > 10) {
                            reset();
                        }
                    }
                } else {
                    bufferOtherTicks[tick] = Math.max(bufferOtherTicks[tick] - 1, 0);
                    reset();
                }
            } else if (tick == 0) {
                // We first check if velocity processor assumes we can still process velocity
                if (velocityTracker.getState() != VelocityTracker.State.IDLE) {
                    // Ok lets simulate the first tick of the velocity
                    double[] simulation = simulatePossibilities(velocityTracker.peekVelocity().getX(), velocityTracker.peekVelocity().getZ());
                    double accuracy = simulation[2];

                    if (accuracy < bestAccuracy) {
                        bestAccuracy = simulation[2];
                    }

                    // We check the accuracy of the horizontal motion, if this was correct we stop processing the first tick and go on to process all the next ticks
                    if (accuracy < THRESHOLD) {
                        // We reset the best accuracy
                        bestAccuracy = Double.MAX_VALUE;

                        // We will simulate the next possible motion variables on the next tick
                        currentMotionX = positionTracker.getDeltaX();
                        currentMotionZ = positionTracker.getDeltaZ();
                        tick = 1;

                        // Valid velocity
                        decreaseBufferBy(0.25);

                        return;
                    }

                    // We keep track of the best accuracy so if they flag we know their setting
                    if (accuracy < bestAccuracy) {
                        bestAccuracy = accuracy;
                    }

                    // Our last movement has been processed but none of the movements were correct
                    if (velocityTracker.getState() == VelocityTracker.State.SANDWICHED) {
                        if (increaseBuffer() > 5) {
                            handleViolation(new DetailedPlayerViolation(this,"T 0 S " + bestAccuracy));
                        }
                        reset();
                    }
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                this.attacking = true;
            }
        }

        if (flying) {
            attacking = false;
        }
    }

    @Override
    public void handle(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_VELOCITY) {
            WrapperPlayServerEntityVelocity entityVelocity = new WrapperPlayServerEntityVelocity(event);
            if (entityVelocity.getEntityId() == playerData.getEntityId()) {
                if (velocityTracker.canVerifyVelocity() && entityVelocity.getVelocity().getY() > 0) {
                    transactionTracker.confirmPre(() -> {
                        reset();
                        tick = 0;
                    });
                } else {
                    reset();
                }
            }
        }
    }

    private boolean simulateNextMotionXZ() {
        double bestAccuracy = Double.MAX_VALUE;
        double bestMotionX = 0;
        double bestMotionZ = 0;

        for (boolean onGround : TRUE_FALSE) {
            double motionX = currentMotionX;
            double motionZ = currentMotionZ;

            float friction = 0.91F;

            if (onGround) {
                friction = MinecraftServer.getServer()
                        .getWorld()
                        .getType(new BlockPosition(Math.floor(positionTracker.getX()), positionTracker.getY() - 1.0, Math.floor(positionTracker.getZ())))
                        .getBlock().frictionFactor * 0.91F;
            }

            motionX *= friction;
            motionZ *= friction;

            double[] simulation = simulatePossibilities(motionX, motionZ);
            double accuracy = simulation[2];
            if (accuracy < bestAccuracy) {
                bestAccuracy = accuracy;
                bestMotionX = simulation[0];
                bestMotionZ = simulation[1];
            }
        }

        currentMotionAccuracy = bestAccuracy;
        currentMotionX = bestMotionX;
        currentMotionZ = bestMotionZ;

        return currentMotionX * currentMotionX + currentMotionZ * currentMotionZ > 9.0E-4D;
    }

    private void reset() {
        tick = Integer.MIN_VALUE;
        bestAccuracy = Double.MAX_VALUE;
        currentMotionAccuracy = Double.MAX_VALUE;
    }


    private static final boolean[] TRUE_FALSE = new boolean[]{true, false};
    private static final float[] INPUTS = new float[]{-1, 0, 1};

    /*
        Movement simulation code based on prismarine-physics / MCP / mcpk.wiki / other stuff I found
        This took me a long time since this is my first ever simulation based anticheat check I made.
        I also have deep hate to 0.03. This method returns the best possible simulation.
        Made by Beanes
     */
    public double[] simulatePossibilities(double startMotionX, double startMotionZ) {
        if (Math.abs(startMotionX) < 0.005) {
            startMotionX = 0;
        }

        if (Math.abs(startMotionZ) < 0.005) {
            startMotionZ = 0;
        }

        double deltaXZ = positionTracker.getDeltaXZ();
        float yaw = rotationTracker.getYaw();
        float blockSlipperiness = MinecraftServer.getServer()
                .getWorld()
                .getType(new BlockPosition(Math.floor(positionTracker.getX()), positionTracker.getY() - 1.0, Math.floor(positionTracker.getZ())))
                .getBlock().frictionFactor;
        boolean is1_8 = playerData.getClientVersion().isNewerThanOrEquals(ClientVersion.V_1_8);
        boolean onGround = collisionTracker.isLastClientGround();

        double bestMotionX = 0;
        double bestMotionZ = 0;
        double bestAccuracy = Double.MAX_VALUE;

        for (float moveStrafe : INPUTS) {
            for (float moveForward : INPUTS) {
                for (boolean isJumping : TRUE_FALSE) {
                    for (boolean isSprinting : TRUE_FALSE) {
                        for (boolean isSneaking : TRUE_FALSE) {
                            for (boolean isBlocking : TRUE_FALSE) { // TODO: make a proper eating / blocking handler
                                for (boolean isFastMath : TRUE_FALSE) { // TODO: Detect fast math.
                                    double[] simulation = simulate(startMotionX, startMotionZ, yaw, moveStrafe, moveForward, blockSlipperiness, isJumping, isSprinting, isSneaking, onGround, attacking, isBlocking, isFastMath, is1_8);

                                    double simulatedXZ = MathUtil.hypot(simulation[0], simulation[1]);
                                    double accuracy = Math.abs(deltaXZ - simulatedXZ);

                                    if (accuracy < bestAccuracy) {
                                        bestAccuracy = accuracy;
                                        bestMotionX = simulation[0];
                                        bestMotionZ = simulation[1];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return new double[]{bestMotionX, bestMotionZ, bestAccuracy};
    }
    /*
        This is a 1:1 to minecraft movement code simulation
        Returns a double with the resulting motionX and motionZ
     */
    public double[] simulate(double startMotionX, double startMotionZ, float yaw, float moveStrafe, float moveForward, float blockSlipperiness, boolean isJumping, boolean isSprinting, boolean isSneaking, boolean isOnGround, boolean isAttacking, boolean isBlocking, boolean isFastMath, boolean is1_8) {
        double motionX = startMotionX;
        double motionZ = startMotionZ;

        OptifineMath math = new OptifineMath();

        if (isSprinting && isAttacking) {
            motionX *= 0.6;
            motionZ *= 0.6;
        }

        if (isJumping && isSprinting) {
            float yawDegrees = yaw * 0.017453292F;

            if (!isFastMath) { // We first check vanilla math
                motionX -= VanillaMath.sin(yawDegrees) * 0.2F;
                motionZ += VanillaMath.cos(yawDegrees) * 0.2F;
            } else {
                if (is1_8) {
                    motionX -= math.sin(yawDegrees) * 0.2F;
                    motionZ += math.cos(yawDegrees) * 0.2F;
                } else {
                    motionX -= LegacyFastMath.sin(yawDegrees) * 0.2F;
                    motionZ += LegacyFastMath.cos(yawDegrees) * 0.2F;
                }
            }
        }

        if (isSneaking) {
            moveStrafe *= 0.3F;
            moveForward *= 0.3F;
        }

        if (isBlocking) {
            moveStrafe *= 0.2F;
            moveForward *= 0.2F;
        }

        float strafe = moveStrafe * 0.98F;
        float forward = moveForward * 0.98F;

        // Normal movement
        float acceleration;

        if (isOnGround) {
            double attributeSpeed = 0.10000000149011612; // Base speed
            if (isSprinting) {
                attributeSpeed *= 1.0D + 0.30000001192092896;
            }
            int speedLevel = attributeTracker.getSpeedBoost();
            if (speedLevel > 0) {
                attributeSpeed *= 1.0D + (0.20000000298023224 * speedLevel);
            }
            int slownessLevel = attributeTracker.getSlowness();
            if (slownessLevel > 0) {
                attributeSpeed *= 1.0D + (-0.15000000596046448D * slownessLevel);
            }

            float moveSpeed = (float) MathHelper.clamp_double(attributeSpeed, 0.0D, 1024.0D);
            float inertia = blockSlipperiness * 0.91F;
            acceleration = moveSpeed * (0.16277136F / (inertia * inertia * inertia));
        } else {
            acceleration = isSprinting ? 0.025999999F : 0.02F;
        }

        // moveFlying
        float speed = strafe * strafe + forward * forward;
        if (speed >= 1.0E-4F)
        {
            speed = (float)Math.sqrt(speed);

            if (speed < 1.0F)
            {
                speed = 1.0F;
            }

            speed = acceleration / speed;
            strafe = strafe * speed;
            forward = forward * speed;
            float f1;
            float f2;
            if (isFastMath) { // We first check vanilla math
                f1 = VanillaMath.sin(yaw * (float)Math.PI / 180.0F);
                f2 = VanillaMath.cos(yaw * (float)Math.PI / 180.0F);
            } else {
                if (is1_8) {
                    f1 = math.sin(yaw * (float)Math.PI / 180.0F);
                    f2 = math.cos(yaw * (float)Math.PI / 180.0F);
                } else {
                    f1 = LegacyFastMath.sin(yaw * (float)Math.PI / 180.0F);
                    f2 = LegacyFastMath.cos(yaw * (float)Math.PI / 180.0F);
                }
            }
            motionX += (strafe * f2 - forward * f1);
            motionZ += (forward * f2 + strafe * f1);
        }

        return new double[]{motionX, motionZ};
    }
}
