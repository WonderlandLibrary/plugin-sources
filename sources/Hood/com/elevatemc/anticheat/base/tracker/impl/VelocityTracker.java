package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityVelocity;
import lombok.Getter;
import org.bukkit.GameMode;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
public class VelocityTracker extends Tracker {
    private final Deque<Vector3d> possibleVelocities = new ArrayDeque<>();

    private double velocityX, velocityY, velocityZ;

    private int ticksSinceVelocity = 1000;
    private State state = State.IDLE;
    private boolean resetState = false;
    public VelocityTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            if (!canVerifyVelocity()) {
                // We are in a situation that we can't verify if the velocity that was taken is 100% correct
                state = State.IDLE;
            } else if (resetState) {
                resetState = false;
                state = State.IDLE;
            } else if (state == State.SANDWICHED) {
                // We can't set the state to idle yet because our velocity checks to have to verify the last movement
                resetState = true;
            }
            ticksSinceVelocity++;
        }
    }

    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_VELOCITY) {
            WrapperPlayServerEntityVelocity entityVelocity = new WrapperPlayServerEntityVelocity(event);

            if (entityVelocity.getEntityId() == playerData.getEntityId()) {
                if (canVerifyVelocity()) {
                    Vector3d velocity = entityVelocity.getVelocity();

                    resetState = false;
                    state = State.IDLE;

                    playerData.getTransactionTracker().sendTransaction();
                    event.getTasksAfterSend().add(() -> playerData.getTransactionTracker().sendTransaction());

                    if (velocity.getY() == -0.04) {
                        entityVelocity.setVelocity(velocity.add(new Vector3d(0, 1 / 8000D, 0)));
                        velocity = entityVelocity.getVelocity();
                    }

                    Vector3d finalVelocity = velocity;

                    playerData.getTransactionTracker().confirmPre(() -> {
                        velocityX = finalVelocity.getX();
                        velocityY = finalVelocity.getY();
                        velocityZ = finalVelocity.getZ();

                        possibleVelocities.add(new Vector3d(velocityX, velocityY, velocityZ));
                        ticksSinceVelocity = 0;
                        state = State.PROCESSING;
                    });

                    playerData.getTransactionTracker().scheduleTrans(1,() -> {
                        if (possibleVelocities.size() > 1) {
                            Vector3d list = possibleVelocities.peekLast();

                            possibleVelocities.clear();
                            possibleVelocities.add(list);
                            state = State.SANDWICHED;
                        }
                    });
                }
            }
        }
    }

    public Vector3d peekVelocity() {
        return this.possibleVelocities.peekLast();
    }

    public boolean isOnFirstTick() {
        return ticksSinceVelocity <= 1;
    }

    public boolean canVerifyVelocity() {
        boolean verify = playerData.getCollisionTracker().isVerticallyColliding()
                || playerData.getCollisionTracker().isNearWall()
                || playerData.getCollisionTracker().isInWater()
                || playerData.getTeleportTracker().isTeleport()
                || playerData.getTicksExisted() < 100
                || playerData.getPlayer().getAllowFlight()
                || playerData.getPlayer().getGameMode().equals(GameMode.CREATIVE);

        return !verify
                && !playerData.getPositionTracker().isJumped()
                && playerData.getPositionTracker().isMoved();
    }

    public enum State {
        IDLE,
        PROCESSING,
        SANDWICHED
    }

}