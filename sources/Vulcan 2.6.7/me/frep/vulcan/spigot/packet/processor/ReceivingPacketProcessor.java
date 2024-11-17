package me.frep.vulcan.spigot.packet.processor;

import java.util.Iterator;
import me.frep.vulcan.spigot.check.AbstractCheck;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import io.github.retrooper.packetevents.packetwrappers.play.in.transaction.WrappedPacketInTransaction;
import io.github.retrooper.packetevents.packetwrappers.play.in.pong.WrappedPacketInPong;
import io.github.retrooper.packetevents.packetwrappers.play.in.clientcommand.WrappedPacketInClientCommand;
import io.github.retrooper.packetevents.packetwrappers.play.in.vehiclemove.WrappedPacketInVehicleMove;
import io.github.retrooper.packetevents.packetwrappers.play.in.helditemslot.WrappedPacketInHeldItemSlot;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;

public class ReceivingPacketProcessor
{
    public void handle(final PlayerData data, final Packet packet) {
        if (packet.isFlying()) {
            final WrappedPacketInFlying wrapper = new WrappedPacketInFlying(packet.getRawPacket());
            data.setFlyingWrapper(wrapper);
            if (data.is1_17() && wrapper.isPosition() && wrapper.isLook()) {
                final double x = wrapper.getX();
                final double y = wrapper.getY();
                final double z = wrapper.getZ();
                final double lastX = data.getPositionProcessor().getX();
                final double lastY = data.getPositionProcessor().getY();
                final double lastZ = data.getPositionProcessor().getZ();
                final double deltaX = Math.abs(x - lastX);
                final double deltaY = Math.abs(y - lastY);
                final double deltaZ = Math.abs(z - lastZ);
                if (deltaX == 0.0 && deltaY == 0.0 && deltaZ == 0.0) {
                    return;
                }
            }
            data.getCombatProcessor().handleFlying();
            data.getConnectionProcessor().handleFlying();
            data.getVelocityProcessor().handleFlying();
            data.getActionProcessor().handleFlying(wrapper);
            data.getPositionProcessor().handleFlying(wrapper);
            if (wrapper.isRotating()) {
                data.getRotationProcessor().handle(wrapper.getYaw(), wrapper.getPitch());
            }
        }
        if (packet.isEntityAction()) {
            final WrappedPacketInEntityAction wrapper2 = new WrappedPacketInEntityAction(packet.getRawPacket());
            data.setEntityActionWrapper(wrapper2);
            data.getActionProcessor().handleEntityAction(wrapper2);
        }
        if (packet.isBlockDig()) {
            final WrappedPacketInBlockDig wrapper3 = new WrappedPacketInBlockDig(packet.getRawPacket());
            data.setBlockDigWrapper(wrapper3);
            data.getActionProcessor().handleBlockDig(wrapper3);
            data.getPositionProcessor().handleBlockDig(wrapper3);
        }
        if (packet.isHeldItemSlot()) {
            final WrappedPacketInHeldItemSlot wrapper4 = new WrappedPacketInHeldItemSlot(packet.getRawPacket());
            data.setHeldItemSlotWrapper(wrapper4);
        }
        if (packet.isVehicleMove()) {
            final WrappedPacketInVehicleMove wrapper5 = new WrappedPacketInVehicleMove(packet.getRawPacket());
            data.getPositionProcessor().handleVehicleMove(wrapper5);
        }
        if (packet.isClientCommand()) {
            final WrappedPacketInClientCommand wrapper6 = new WrappedPacketInClientCommand(packet.getRawPacket());
            data.getActionProcessor().handleClientCommand(wrapper6);
        }
        if (packet.isPong()) {
            final WrappedPacketInPong wrapper7 = new WrappedPacketInPong(packet.getRawPacket());
            data.getActionProcessor().handlePong(wrapper7);
            data.getPositionProcessor().handlePong(wrapper7);
            data.getVelocityProcessor().handlePong(wrapper7);
            data.getConnectionProcessor().handlePong(wrapper7);
        }
        if (packet.isTransaction()) {
            final WrappedPacketInTransaction wrapper8 = new WrappedPacketInTransaction(packet.getRawPacket());
            data.getConnectionProcessor().handleTransaction(wrapper8);
            data.getActionProcessor().handleTransaction(wrapper8);
            data.getPositionProcessor().handleTransaction(wrapper8);
            data.getVelocityProcessor().handleTransaction(wrapper8);
        }
        if (packet.isKeepAlive()) {
            data.getConnectionProcessor().handleKeepAlive();
        }
        if (packet.isBlockPlace()) {
            data.getActionProcessor().handleBlockPlace();
        }
        if (packet.isCloseWindow()) {
            data.getActionProcessor().handleCloseWindow();
        }
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper9 = new WrappedPacketInUseEntity(packet.getRawPacket());
            data.setUseEntityWrapper(wrapper9);
            data.getCombatProcessor().handleUseEntity(wrapper9);
        }
        if (packet.isWindowClick()) {
            data.getActionProcessor().handleWindowClick();
        }
        if (packet.isArmAnimation()) {
            data.getClickProcessor().handleArmAnimation();
            data.getPositionProcessor().handleArmAnimation();
            data.getActionProcessor().handleArmAnimation();
        }
        for (final AbstractCheck check : data.getChecks()) {
            check.handle(packet);
        }
    }
}
