package com.elevatemc.anticheat.base.tracker.impl;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.tracker.Tracker;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityEffect;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerRemoveEntityEffect;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerUpdateAttributes;
import lombok.Getter;

@Getter
public class AttributeTracker extends Tracker {
    private double walkSpeed = 0.10000000149011612D;

    private int jumpBoost, speedBoost, slowness;


    public AttributeTracker(PlayerData playerData) {
        super(playerData);
    }

    @Override
    public void registerOutgoingPreHandler(PacketSendEvent event) {
         if (event.getPacketType() == PacketType.Play.Server.UPDATE_ATTRIBUTES) {
            WrapperPlayServerUpdateAttributes updateAttributes = new WrapperPlayServerUpdateAttributes(event);

            for (WrapperPlayServerUpdateAttributes.Property snapshot : updateAttributes.getProperties()) {
                if (snapshot.getKey().equals("generic.movementSpeed")) {
                    playerData.getTransactionTracker().confirmPre(() -> {
                        // Don't calculate modifiers because it is not very accurate in some situations
                        // ,and it is easier to just handle on case by case basis
                        walkSpeed = snapshot.getValue();
                    });
                    break;
                }
            }
        }

        if (event.getPacketType() == PacketType.Play.Server.ENTITY_EFFECT) {
            WrapperPlayServerEntityEffect entityEffect = new WrapperPlayServerEntityEffect(event);


            if (entityEffect.getEntityId() != playerData.getPlayer().getEntityId()) {
                return;
            }


            int amplifier = entityEffect.getEffectAmplifier();

            playerData.getTransactionTracker().confirmPre(() -> {
                switch (entityEffect.getPotionType().getId(playerData.getClientVersion())) {
                    case 1: {
                        this.speedBoost = amplifier + 1;
                        break;
                    }

                    case 2: {
                        this.slowness = amplifier + 1;
                        break;
                    }

                    case 8: {
                        this.jumpBoost = amplifier + 1;
                        break;
                    }
                }
            });
        }

        if (event.getPacketType() == PacketType.Play.Server.REMOVE_ENTITY_EFFECT) {
            WrapperPlayServerRemoveEntityEffect removeEntityEffect = new WrapperPlayServerRemoveEntityEffect(event);

            if (removeEntityEffect.getEntityId() != playerData.getEntityId()) {
                return;
            }

            playerData.getTransactionTracker().confirmPre(() -> {
                switch (removeEntityEffect.getPotionType().getId(ClientVersion.getById(playerData.getProtocolVersion()))) {
                    case 1: {
                        speedBoost = 0;
                        break;
                    }

                    case 2: {
                        slowness = 0;
                        break;
                    }

                    case 8: {
                        jumpBoost = 0;
                        break;
                    }
                }
            });
        }
    }

    public double getMoveSpeed(boolean sprint) {
        double baseValue = walkSpeed;

        if (sprint) {
            baseValue += baseValue * 0.30000001192092896D;
        }

        baseValue += baseValue * speedBoost * 0.20000000298023224D;
        baseValue += baseValue * slowness * -0.15000000596046448D;

        return baseValue;
    }
}