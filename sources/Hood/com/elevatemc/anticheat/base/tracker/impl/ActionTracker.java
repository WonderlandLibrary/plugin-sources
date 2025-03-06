package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientEntityAction;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerDigging;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

@Getter
public class ActionTracker extends Tracker {
    private int lastAttack, entityId, respawnTicks, sprintingTicks, sneakingTicks;

    private boolean digging, eating, sprinting, sneaking, blocking, inventory, sendingAction;

    public ActionTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerIncomingPreHandler(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_DIGGING) {
            WrapperPlayClientPlayerDigging playerDigging = new WrapperPlayClientPlayerDigging(event);
            switch (playerDigging.getAction()) {
                case START_DIGGING: {
                    digging = true;
                    break;
                }

                case CANCELLED_DIGGING: {
                    digging = false;
                    break;
                }
                case RELEASE_USE_ITEM: {
                    blocking = false;
                    eating = false;
                    break;
                }
            }
        }

        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity useEntity = new WrapperPlayClientInteractEntity(event);
            Entity entity = SpigotReflectionUtil.getEntityById(useEntity.getEntityId());
            if (useEntity.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK && entity instanceof Player) {
                lastAttack = 0;
                entityId = useEntity.getEntityId();
            }
        }
        if (PacketUtil.isFlying(event.getPacketType())) {
            tick();
            inventory = false;
            sendingAction = false;
        }

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {
            if (playerData.getPlayer().getItemInHand().toString().contains("SWORD")) {
                blocking = true;
            }
            if (playerData.getPlayer().getItemInHand().getType().isEdible()) {
                eating = true;
            }
        }

        if (event.getPacketType() == PacketType.Play.Client.ENTITY_ACTION) {
            sendingAction = true;
            WrapperPlayClientEntityAction entityAction = new WrapperPlayClientEntityAction(event);
            switch (entityAction.getAction()) {
                case START_SPRINTING: {
                    sprinting = true;
                    break;
                }

                case START_SNEAKING: {
                    sneaking = true;
                    break;
                }

                case STOP_SPRINTING: {
                    sprinting = false;
                    break;
                }

                case STOP_SNEAKING: {
                    sneaking = false;
                    break;
                }
            }
        }
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            inventory = true;
        }
    }


    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Play.Server.RESPAWN) {
            // Whenever a respawn packet is called, it gets sent out twice, we'll counter this using transactions.
            if (playerData.getTransactionTracker().getTransReceived().get() == playerData.getTransactionTracker().getLastTransactionSent()) {
                playerData.getTransactionTracker().sendTransaction();
                respawnTicks = 0;
            }
        }
    }

    public void tick() {
        blocking = false;
        eating = false;
        if (sprinting) {
            ++this.sprintingTicks;
        } else {
            sprintingTicks = 0;
        }
        if (sneaking) {
            ++this.sneakingTicks;
        } else {
            sneakingTicks = 0;
        }

        ++lastAttack;
        ++respawnTicks;
    }
}