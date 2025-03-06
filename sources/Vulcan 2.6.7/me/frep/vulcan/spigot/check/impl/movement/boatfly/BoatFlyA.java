package me.frep.vulcan.spigot.check.impl.movement.boatfly;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Boat;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Boat Fly", type = 'A', complexType = "Vertical", description = "Moving upwards in a boat.")
public class BoatFlyA extends AbstractCheck
{
    public BoatFlyA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isVehicleMove()) {
            if (ServerUtil.isLowerThan1_9()) {
                return;
            }
            final boolean boat = this.data.getPlayer().getVehicle() != null && this.data.getPlayer().getVehicle() instanceof Boat;
            final int vehicleTicks = this.data.getPositionProcessor().getVehicleTicks();
            if (boat && vehicleTicks > 10) {
                final Boat boatEntity = (Boat)this.data.getPlayer().getVehicle();
                final double deltaY = this.data.getPositionProcessor().getVehicleDeltaY();
                final boolean liquid = this.data.getPositionProcessor().getSinceVehicleNearLiquidTicks() < 100;
                final boolean slime = this.data.getPositionProcessor().getSinceVehicleNearSlimeTicks() < 100;
                final boolean bubbleColumn = this.data.getPositionProcessor().getSinceVehicleNearBubbleColumnTicks() < 100;
                final boolean piston = this.data.getPositionProcessor().getSinceVehicleNearPistonTicks() < 50;
                final boolean bed = this.data.getPositionProcessor().getSinceVehicleNearBedTicks() < 40;
                final boolean touchingAir = this.data.getPositionProcessor().isVehicleInAir();
                if (ServerUtil.isHigherThan1_13() && !boatEntity.hasGravity()) {
                    return;
                }
                if (Vulcan.INSTANCE.getFishingRodPulledBoats().containsKey(boatEntity.getEntityId())) {
                    return;
                }
                if (deltaY > 0.01 && !slime && !liquid && !bubbleColumn && !piston && !bed && touchingAir) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaY=" + deltaY);
                        if (Config.BOAT_FLY_A_KICKOUT) {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.data.getPlayer().leaveVehicle());
                        }
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
