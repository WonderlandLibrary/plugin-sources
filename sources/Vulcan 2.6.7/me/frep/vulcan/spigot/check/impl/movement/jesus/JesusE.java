package me.frep.vulcan.spigot.check.impl.movement.jesus;

import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Jesus", type = 'E', complexType = "Speed", description = "Moving too quickly on water.")
public class JesusE extends AbstractCheck
{
    public JesusE(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final boolean onLiquid = this.data.getPositionProcessor().isOnLiquid();
            final boolean exempt = this.isExempt(ExemptType.LILY_PAD, ExemptType.STAIRS, ExemptType.SLAB, ExemptType.CAMPFIRE, ExemptType.CARPET, ExemptType.END_ROD, ExemptType.FENCE, ExemptType.NOT_MOVING, ExemptType.CANCELLED_PLACE, ExemptType.CHAIN, ExemptType.SNOW, ExemptType.SWIMMING_JESUS, ExemptType.BED, ExemptType.TRAPDOOR, ExemptType.BOAT, ExemptType.FULLY_SUBMERGED, ExemptType.BUBBLE_COLUMN, ExemptType.VELOCITY, ExemptType.DEPTH_STRIDER, ExemptType.DOLPHINS_GRACE, ExemptType.SWIMMING_ON_OLD_VERSION, ExemptType.SPECTATOR, ExemptType.BLOCK_PLACE, ExemptType.NEAR_SOLID, ExemptType.SLOW_FALLING, ExemptType.CHUNK, ExemptType.SCAFFOLDING, ExemptType.SLIME) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptTeleport();
            double maxSpeed = PlayerUtil.getBaseSpeed(this.data, 0.166f);
            if (ServerUtil.isHigherThan1_9() && this.data.getPositionProcessor().getSinceEntityCollisionTicks() < 20) {
                maxSpeed += 0.2;
            }
            if (onLiquid && !exempt) {
                if (deltaXZ > maxSpeed && this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaXZ=" + deltaXZ);
                    this.data.getActionProcessor().setUpdateSwim(true);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
