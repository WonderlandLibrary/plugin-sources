package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.elevatemc.anticheat.util.reach.data.PlayerReachEntity;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.*;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class EntityTracker extends Tracker {

    public EntityTracker(PlayerData data) {
        super(data);
    }
    public final Map<Integer, PlayerReachEntity> playerMap = new ConcurrentHashMap<>();
    private boolean hasSentPreWavePacket = true;

    public PlayerReachEntity getTrackedPlayer(int entityId) {
        return playerMap.get(entityId);
    }

    /*
        Spawn player
        Destory Entities
        Entity Relative move
        Entity Relative Move and rotation
        Entity Rotation
        Entity Teleport
     */
    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE) {
            WrapperPlayServerEntityRelativeMove relative = new WrapperPlayServerEntityRelativeMove(event);

            final PlayerReachEntity reachEntity = this.playerMap.get(relative.getEntityId());
            if (reachEntity != null) {
                this.handleMoveEntity(relative.getEntityId(), relative.getDeltaX(), relative.getDeltaY(), relative.getDeltaZ(), true, true);
            }
        }else if (event.getPacketType() == PacketType.Play.Server.ENTITY_RELATIVE_MOVE_AND_ROTATION) {
            WrapperPlayServerEntityRelativeMoveAndRotation wrapper = new WrapperPlayServerEntityRelativeMoveAndRotation(event);
            handleRelEntityMoveLook(wrapper);
        }else if (event.getPacketType() == PacketType.Play.Server.SPAWN_PLAYER) {
            WrapperPlayServerSpawnPlayer wrapper = new WrapperPlayServerSpawnPlayer(event);

            Vector3d position = wrapper.getPosition();

            int lastTransactionSent = playerData.getTransactionTracker().getLastTransactionSent();
            playerData.getTransactionTracker().scheduleTrans(1,() -> {
                PlayerReachEntity reachEntity = new PlayerReachEntity(playerData, position.getX(), position.getY(), position.getZ());
                reachEntity.setLastTransactionHung(lastTransactionSent);
                this.playerMap.put(wrapper.getEntityId(), reachEntity);
            });
        }else if (event.getPacketType() == PacketType.Play.Server.ENTITY_TELEPORT) {
            WrapperPlayServerEntityTeleport wrapper = new WrapperPlayServerEntityTeleport(event);
            handleTeleportEntity(wrapper);
        }else if (event.getPacketType() == PacketType.Play.Server.ENTITY_ROTATION) {
            WrapperPlayServerEntityRotation wrapper = new WrapperPlayServerEntityRotation(event);

            handleEntityLook(wrapper);
        }else if (event.getPacketType() == PacketType.Play.Server.DESTROY_ENTITIES) {
            WrapperPlayServerDestroyEntities wrapper = new WrapperPlayServerDestroyEntities(event);

            playerData.getTransactionTracker().scheduleTrans(1,() -> {
                for (int entityId : wrapper.getEntityIds()) {
                    playerMap.remove(entityId);
                }
            });
        }
    }

    @Override
    public void registerIncomingPostHandler(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            for (final PlayerReachEntity entity : playerData.getEntityTracker().playerMap.values()) {
                entity.onMovement(true);
            }
        }
    }

    public void handleEntityLook(final WrapperPlayServerEntityRotation wrapper) {
        handleMoveEntity(wrapper.getEntityId(), 0, 0, 0, true, false);
    }

    public void handleRelEntityMoveLook(final WrapperPlayServerEntityRelativeMoveAndRotation wrapper) {
        handleRelMove(wrapper.getEntityId(), wrapper.getDeltaX(), wrapper.getDeltaY(), wrapper.getDeltaZ());
    }

    private void handleRelMove(int entityId, double deltaX, double deltaY, double deltaZ) {
        final PlayerReachEntity reachEntity = this.playerMap.get(entityId);
        if (reachEntity != null) {
            this.handleMoveEntity(entityId, deltaX, deltaY, deltaZ, true, true);
        }
    }

    public void handleTeleportEntity(final WrapperPlayServerEntityTeleport wrapper) {
        final PlayerReachEntity reachEntity = this.playerMap.get(wrapper.getEntityId());
        if (reachEntity != null) {
            final Vector3d pos = wrapper.getPosition();
            this.handleMoveEntity(wrapper.getEntityId(), pos.getX(), pos.getY(), pos.getZ(), false, true);
        }
    }

    private void handleMoveEntity(final int entityId, final double deltaX, final double deltaY, final double deltaZ, boolean isRelative, boolean hasPos) {
        if (!hasSentPreWavePacket) {
            hasSentPreWavePacket = true;
            playerData.getTransactionTracker().sendTransaction();
        }

        final PlayerReachEntity reachEntity = this.playerMap.get(entityId);

        if (reachEntity != null) {
            if (reachEntity.getLastTransactionHung() == playerData.getTransactionTracker().getLastTransactionSent()) {
                playerData.getTransactionTracker().sendTransaction();
            }
            reachEntity.setLastTransactionHung(playerData.getTransactionTracker().getLastTransactionSent());


            playerData.getTransactionTracker().confirmPre(() -> reachEntity.onFirstTransaction(isRelative, hasPos, deltaX, deltaY, deltaZ, playerData));
            playerData.getTransactionTracker().scheduleTrans(1, reachEntity::onSecondTransaction);
        }
    }

    public void preFlush() {
        playerData.getTransactionTracker().sendTransaction(true);
    }

    public void onTickStart() {
        hasSentPreWavePacket = false;
    }
}