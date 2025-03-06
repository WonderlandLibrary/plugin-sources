package me.frep.vulcan.spigot.check.impl.movement.boatfly;

import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Boat;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Boat Fly", type = 'C', complexType = "Hover", description = "Hovering in a boat.")
public class BoatFlyC extends AbstractCheck
{
    public BoatFlyC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isVehicleMove()) {
            if (ServerUtil.isLowerThan1_9()) {
                return;
            }
            final boolean boat = this.data.getPlayer().getVehicle() instanceof Boat;
            final int vehicleTicks = this.data.getPositionProcessor().getVehicleTicks();
            if (vehicleTicks > 10 && boat) {
                final Boat boatEntity = (Boat)this.data.getPlayer().getVehicle();
                if (Vulcan.INSTANCE.getFishingRodPulledBoats().containsKey(boatEntity.getEntityId())) {
                    return;
                }
                final double deltaY = this.data.getPositionProcessor().getVehicleDeltaY();
                final double lastDeltaY = this.data.getPositionProcessor().getLastVehicleDeltaY();
                final double acceleration = Math.abs(deltaY - lastDeltaY);
                final int airTicks = this.data.getPositionProcessor().getVehicleAirTicks();
                final boolean invalid = deltaY > -0.05 && acceleration < 0.025 && airTicks > 5;
                if (this.data.getPositionProcessor().isVehicleNearEntity()) {
                    return;
                }
                if (ServerUtil.isHigherThan1_13() && !boatEntity.hasGravity()) {
                    return;
                }
                final boolean ice = this.data.getPositionProcessor().getSinceVehicleNearIceTicks() < 50;
                final boolean liquid = this.data.getPositionProcessor().getSinceVehicleNearLiquidTicks() < 50;
                final boolean slime = this.data.getPositionProcessor().getSinceVehicleNearSlimeTicks() < 50;
                final boolean bubbleColumn = this.data.getPositionProcessor().getSinceVehicleNearBubbleColumnTicks() < 50;
                final boolean piston = this.data.getPositionProcessor().getSinceVehicleNearPistonTicks() < 50;
                final int boatsAround = this.data.getPositionProcessor().getBoatsAround();
                if (invalid && !ice && !liquid && !slime && !bubbleColumn && !piston && boatsAround == 1) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("accel=" + acceleration + " deltaY=" + deltaY);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
