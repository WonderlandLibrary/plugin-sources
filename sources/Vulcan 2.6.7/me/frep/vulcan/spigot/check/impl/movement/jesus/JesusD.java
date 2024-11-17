package me.frep.vulcan.spigot.check.impl.movement.jesus;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Jesus", type = 'D', complexType = "Jump", description = "Jumping on/in water.")
public class JesusD extends AbstractCheck
{
    public JesusD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean onLiquid = this.data.getPositionProcessor().isOnLiquid();
            boolean airBelow;
            if (ServerUtil.isHigherThan1_13()) {
                airBelow = (this.data.getPositionProcessor().getBlockBelowModern() != null && BlockUtil.isAir(this.data.getPositionProcessor().getBlockBelowModern()));
            }
            else {
                airBelow = (this.data.getPositionProcessor().getBlockBelow() != null && BlockUtil.isAir(this.data.getPositionProcessor().getBlockBelow()));
            }
            final boolean exempt = this.isExempt(ExemptType.LILY_PAD, ExemptType.STAIRS, ExemptType.SLAB, ExemptType.CAMPFIRE, ExemptType.CARPET, ExemptType.END_ROD, ExemptType.FENCE, ExemptType.BOAT, ExemptType.NOT_MOVING, ExemptType.CANCELLED_PLACE, ExemptType.CHAIN, ExemptType.SNOW, ExemptType.SWIMMING_JESUS, ExemptType.BED, ExemptType.TRAPDOOR, ExemptType.BOAT, ExemptType.FULLY_SUBMERGED, ExemptType.VELOCITY, ExemptType.BUBBLE_COLUMN, ExemptType.SWIMMING_ON_OLD_VERSION, ExemptType.SPECTATOR, ExemptType.NEAR_SOLID, ExemptType.SLIME, ExemptType.HONEY, ExemptType.CHUNK, ExemptType.SCAFFOLDING) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptTeleport();
            if (onLiquid && airBelow && !exempt) {
                if (deltaY > 0.0 && this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY);
                    this.data.getActionProcessor().setUpdateSwim(true);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
