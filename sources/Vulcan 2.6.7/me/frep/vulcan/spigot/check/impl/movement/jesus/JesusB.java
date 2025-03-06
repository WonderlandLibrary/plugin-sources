package me.frep.vulcan.spigot.check.impl.movement.jesus;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Jesus", type = 'B', complexType = "Motion", description = "Invalid Y-change in water.")
public class JesusB extends AbstractCheck
{
    public JesusB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final boolean onLiquid = this.data.getPositionProcessor().isOnLiquid();
            final boolean exempt = this.isExempt(ExemptType.LILY_PAD, ExemptType.STAIRS, ExemptType.SLAB, ExemptType.CAMPFIRE, ExemptType.CARPET, ExemptType.END_ROD, ExemptType.FENCE, ExemptType.BOAT, ExemptType.CANCELLED_PLACE, ExemptType.CHAIN, ExemptType.SNOW, ExemptType.SWIMMING_JESUS, ExemptType.BED, ExemptType.TRAPDOOR, ExemptType.BOAT, ExemptType.FULLY_SUBMERGED, ExemptType.BUBBLE_COLUMN, ExemptType.VELOCITY, ExemptType.DEPTH_STRIDER, ExemptType.DOLPHINS_GRACE, ExemptType.SWIMMING_ON_OLD_VERSION, ExemptType.SPECTATOR, ExemptType.SOUL_SAND, ExemptType.NOT_MOVING, ExemptType.NEAR_SOLID, ExemptType.CHUNK, ExemptType.SCAFFOLDING, ExemptType.SLOW_FALLING) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptTeleport();
            if (onLiquid && !exempt) {
                if (Math.abs(deltaY) < 0.015 && deltaXZ > 0.11) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaY=" + deltaY + " deltaXZ=" + deltaXZ);
                        this.data.getActionProcessor().setUpdateSwim(true);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
