package me.frep.vulcan.spigot.check.impl.movement.jesus;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Jesus", type = 'A', complexType = "Ground", description = "Walking on water.")
public class JesusA extends AbstractCheck
{
    public JesusA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final boolean onLiquid = this.data.getPositionProcessor().isOnLiquid();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final boolean clientOnGround = this.data.getPositionProcessor().isClientOnGround();
            final boolean serverOnGround = this.data.getPositionProcessor().isMathematicallyOnGround();
            final boolean exempt = this.isExempt(ExemptType.LILY_PAD, ExemptType.STAIRS, ExemptType.SLAB, ExemptType.CAMPFIRE, ExemptType.CARPET, ExemptType.END_ROD, ExemptType.FENCE, ExemptType.BOAT, ExemptType.CANCELLED_PLACE, ExemptType.CHAIN, ExemptType.SNOW, ExemptType.SWIMMING_JESUS, ExemptType.BED, ExemptType.TRAPDOOR, ExemptType.BOAT, ExemptType.DEPTH_STRIDER, ExemptType.DOLPHINS_GRACE, ExemptType.SPECTATOR, ExemptType.SCAFFOLDING, ExemptType.NEAR_SOLID, ExemptType.NOT_MOVING, ExemptType.BLOCK_PLACE) || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptGliding() || this.data.getPositionProcessor().isExemptElytra() || this.data.getPositionProcessor().isExemptLevitation() || this.data.getPositionProcessor().isExemptVehicle() || this.data.getPositionProcessor().isExemptRiptide() || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptChunk();
            if (onLiquid && (clientOnGround || serverOnGround) && !exempt && deltaXZ > 0.1) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail();
                    this.data.getActionProcessor().setUpdateSwim(true);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
