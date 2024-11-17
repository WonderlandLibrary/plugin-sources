package me.frep.vulcan.spigot.packet.processor;

import java.util.Iterator;
import io.github.retrooper.packetevents.packetwrappers.play.out.explosion.WrappedPacketOutExplosion;
import io.github.retrooper.packetevents.packetwrappers.play.out.gamestatechange.WrappedPacketOutGameStateChange;
import io.github.retrooper.packetevents.packetwrappers.play.out.removeentityeffect.WrappedPacketOutRemoveEntityEffect;
import io.github.retrooper.packetevents.packetwrappers.play.out.entityeffect.WrappedPacketOutEntityEffect;
import io.github.retrooper.packetevents.packetwrappers.play.out.abilities.WrappedPacketOutAbilities;
import me.frep.vulcan.spigot.check.AbstractCheck;
import io.github.retrooper.packetevents.packetwrappers.play.out.position.WrappedPacketOutPosition;
import io.github.retrooper.packetevents.packetwrappers.play.out.entitymetadata.WrappedPacketOutEntityMetadata;
import io.github.retrooper.packetevents.packetwrappers.play.out.updateattributes.WrappedPacketOutUpdateAttributes;
import io.github.retrooper.packetevents.packetwrappers.play.out.entity.WrappedPacketOutEntity;
import io.github.retrooper.packetevents.packetwrappers.play.out.entityvelocity.WrappedPacketOutEntityVelocity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;

public class SendingPacketProcessor
{
    public void handle(final PlayerData data, final Packet packet) {
        if (packet.isVelocity()) {
            final WrappedPacketOutEntityVelocity wrapper = new WrappedPacketOutEntityVelocity(packet.getRawPacket());
            if (wrapper.getEntityId() == data.getPlayer().getEntityId()) {
                data.getVelocityProcessor().handle(wrapper.getVelocityX(), wrapper.getVelocityY(), wrapper.getVelocityZ());
            }
        }
        if (packet.isRelEntityMove()) {
            if (data.getCombatProcessor().getTrackedPlayer() == null || data.getCombatProcessor().getLastTrackedPlayer() == null) {
                return;
            }
            final WrappedPacketOutEntity wrapper2 = new WrappedPacketOutEntity(packet.getRawPacket());
            if (wrapper2.getEntityId() == data.getCombatProcessor().getTrackedPlayer().getEntityId()) {
                data.getCombatProcessor().handleRelEntityMove(wrapper2);
            }
        }
        if (packet.isUpdateAttributes()) {
            final WrappedPacketOutUpdateAttributes wrapper3 = new WrappedPacketOutUpdateAttributes(packet.getRawPacket());
            if (wrapper3.getEntityId() == data.getPlayer().getEntityId()) {
                data.getActionProcessor().handleUpdateAttributes(wrapper3);
            }
        }
        if (packet.isMetaData()) {
            final WrappedPacketOutEntityMetadata wrapper4 = new WrappedPacketOutEntityMetadata(packet.getRawPacket());
            if (wrapper4.getEntityId() == data.getPlayer().getEntityId()) {
                data.getActionProcessor().handleMetaData(wrapper4);
            }
        }
        if (packet.isTeleport()) {
            final WrappedPacketOutPosition wrapper5 = new WrappedPacketOutPosition(packet.getRawPacket());
            data.getActionProcessor().handleServerPosition(wrapper5);
            data.getCombatProcessor().handleServerPosition();
            for (final AbstractCheck check : data.getChecks()) {
                check.handle(packet);
            }
        }
        if (packet.isAbilitiesOut()) {
            final WrappedPacketOutAbilities wrapper6 = new WrappedPacketOutAbilities(packet.getRawPacket());
            data.getActionProcessor().handleAbilities(wrapper6);
            data.getPositionProcessor().handleAbilities(wrapper6);
        }
        if (packet.isHeldItemSlotOut()) {
            for (final AbstractCheck check2 : data.getChecks()) {
                check2.handle(packet);
            }
        }
        if (packet.isEntityEffect()) {
            final WrappedPacketOutEntityEffect wrapper7 = new WrappedPacketOutEntityEffect(packet.getRawPacket());
            if (wrapper7.getEntityId() == data.getPlayer().getEntityId()) {
                data.getActionProcessor().handleEntityEffect(wrapper7);
            }
        }
        if (packet.isRemoveEntityEffect()) {
            final WrappedPacketOutRemoveEntityEffect wrapper8 = new WrappedPacketOutRemoveEntityEffect(packet.getRawPacket());
            if (wrapper8.getEntityId() == data.getPlayer().getEntityId()) {
                data.getActionProcessor().handleRemoveEntityEffect(wrapper8);
            }
        }
        if (packet.isGameStateChange()) {
            final WrappedPacketOutGameStateChange wrapper9 = new WrappedPacketOutGameStateChange(packet.getRawPacket());
            data.getActionProcessor().handleGameStateChange(wrapper9);
        }
        if (packet.isExplosion()) {
            final WrappedPacketOutExplosion wrapper10 = new WrappedPacketOutExplosion(packet.getRawPacket());
            data.getActionProcessor().handleExplosion(wrapper10);
        }
    }
}
