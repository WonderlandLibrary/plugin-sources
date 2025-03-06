package me.frep.vulcan.spigot.check.impl.movement.boatfly;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Boat;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Boat Fly", type = 'B', complexType = "Vertical", description = "Moving too quickly horizontally.")
public class BoatFlyB extends AbstractCheck
{
    public BoatFlyB(final PlayerData data) {
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
            final String spigotID = Vulcan.INSTANCE.getSpigot();
            if (vehicleTicks > 10 && boat) {
                final Boat boatEntity = (Boat)this.data.getPlayer().getVehicle();
                final double deltaXZ = this.data.getPositionProcessor().getVehicleDeltaXZ();
                final double deltaY = this.data.getPositionProcessor().getVehicleDeltaY();
                final boolean ice = this.data.getPositionProcessor().getSinceVehicleNearIceTicks() < 100;
                final boolean liquid = this.data.getPositionProcessor().getSinceVehicleNearLiquidTicks() < 100;
                final boolean slime = this.data.getPositionProcessor().getSinceVehicleNearSlimeTicks() < 100;
                final boolean bubbleColumn = this.data.getPositionProcessor().getSinceVehicleNearBubbleColumnTicks() < 100;
                final int boatsAround = this.data.getPositionProcessor().getBoatsAround();
                if (ServerUtil.isHigherThan1_13() && !boatEntity.hasGravity()) {
                    return;
                }
                if (Vulcan.INSTANCE.getFishingRodPulledBoats().containsKey(boatEntity.getEntityId())) {
                    return;
                }
                double max = 0.25;
                if (this.isExempt(ExemptType.WATERLOGGED)) {
                    max += 0.1;
                }
                final boolean invalid = deltaXZ > max && deltaY > -0.05 && !liquid && !slime && !bubbleColumn && !ice;
                if (invalid && boatsAround == 1) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaXZ=" + deltaXZ + " deltaY=" + deltaY);
                        if (Config.BOAT_FLY_B_KICKOUT) {
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
