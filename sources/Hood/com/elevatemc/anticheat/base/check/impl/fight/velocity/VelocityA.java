package com.elevatemc.anticheat.base.check.impl.fight.velocity;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.VelocityTracker;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityVelocity;

public class VelocityA extends PacketCheck {
    public VelocityA(PlayerData playerData) {
        super(playerData, "Velocity A", "Vertical Velocity Modifications", new ViolationHandler(5, 3000L), Category.COMBAT, SubCategory.VELOCITY, 1);
    }

    private static final double THRESHOLD = 0.00000000001;
    private int tick = Integer.MIN_VALUE;
    private double bestAccuracy = Double.MAX_VALUE;
    private int bestRatio = Integer.MAX_VALUE;
    private double nextMotionY = 0;
    private double bufferOtherTicks = 0;

    @Override
    public void handle(PacketReceiveEvent event) {
        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            if (tick > 0) {
                // We check if we can verify velocity
                if (!velocityTracker.canVerifyVelocity() || velocityTracker.getVelocityY() < 0) {
                    reset();
                    return;
                }

                // We calculate the accuracy of the next y movement
                double deltaY = positionTracker.getDeltaY();
                double accuracy = Math.abs(deltaY - nextMotionY);

                // If the simulation failed we will flag otherwise we will process the next tick
                if (accuracy > THRESHOLD) {
                    if (bufferOtherTicks++ > 7) {
                        int ratio = (int) Math.abs(Math.round((deltaY / nextMotionY) * 100));
                        handleViolation(new DetailedPlayerViolation(this,"T " + tick + " R " + ratio + "%"));
                        staffAlert(new PlayerViolation(this));
                    }
                    reset();
                } else {
                    // We simulate the next tick motionY beforehand
                    boolean willSendMotion = simulateMotionY();

                    if (willSendMotion) {
                        tick++;
                    } else {
                        reset();
                        bufferOtherTicks = Math.max(0, bufferOtherTicks - 0.25D);
                    }
                }
            } else if (tick == 0) {
                // We first check if velocity processor assumes we can still process velocity
                if (velocityTracker.getState() != VelocityTracker.State.IDLE) {
                    // Basically we are uncertain which tick the velocity will be processed on, so we check every client tick until we have a correct motion
                    double deltaY = positionTracker.getDeltaY();
                    double velocityY = velocityTracker.peekVelocity().getY();
                    double accuracy = Math.abs(deltaY - velocityY);

                    // We check the accuracy of the upwards motion to the velocity, if this was correct we stop processing the first tick and go on to process all the next ticks
                    if (accuracy < THRESHOLD) {
                        // We reset the best accuracy
                        bestAccuracy = Double.MAX_VALUE;

                        // We simulate the next tick motionY beforehand
                        boolean willSendMotion = simulateMotionY();

                        if (willSendMotion) {
                            tick = 1;
                        } else {
                            reset();
                        }

                        // Valid velocity
                        decreaseBufferBy(0.25);

                        return;
                    }

                    // We keep track of the best accuracy so if they flag we know their setting
                    if (accuracy < bestAccuracy) {
                        bestAccuracy = accuracy;
                        bestRatio = (int) Math.round((deltaY / velocityY) * 100);
                    }

                    // Our last movement has been processed but none of the movements were correct
                    if (velocityTracker.getState() == VelocityTracker.State.SANDWICHED) {
                        if (increaseBuffer() > 5) {
                            handleViolation(new DetailedPlayerViolation(this,"T 0 R " + bestRatio + "%"));
                        }
                        reset();
                    }
                }
            }
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

    // This will return false if the next simulation is under 0.03
    private boolean simulateMotionY() {
        nextMotionY = positionTracker.getDeltaY();
        nextMotionY -= 0.08D;
        nextMotionY *= 0.9800000190734863D;

        // The client will not send a position if position change is under 0.03 (we could make this more accurate if we track horizontal and vertical in the same check)
        return nextMotionY > 0.03;
    }

    private void reset() {
        tick = Integer.MIN_VALUE;
        bestAccuracy = Double.MAX_VALUE;
    }
}