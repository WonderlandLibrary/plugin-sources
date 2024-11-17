package me.frep.vulcan.spigot.check.impl.movement.antilevitation;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Anti Levitation", type = 'A', complexType = "Ignore", description = "Ignored Levitation effect.")
public class AntiLevitationA extends AbstractCheck
{
    public AntiLevitationA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (ServerUtil.isLowerThan1_9() || this.data.getActionProcessor().getLevitationAmplifier() < 0) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final boolean mathOnGround = this.data.getPositionProcessor().isMathematicallyOnGround();
            final boolean onGround = this.data.getPositionProcessor().isClientOnGround();
            final boolean levitation = this.data.getActionProcessor().isHasLevitation();
            final boolean touchingAir = this.data.getPositionProcessor().isTouchingAir();
            final boolean invalid = levitation && (onGround || deltaY < 0.0 || (!touchingAir && mathOnGround));
            final boolean exempt = this.isExempt(ExemptType.SCAFFOLDING, ExemptType.VOID, ExemptType.COLLIDING_VERTICALLY, ExemptType.TRAPDOOR, ExemptType.BED, ExemptType.SLIME, ExemptType.HONEY, ExemptType.VELOCITY, ExemptType.RIPTIDE, ExemptType.BUKKIT_VELOCITY, ExemptType.JOINED, ExemptType.SWIMMING, ExemptType.CHUNK, ExemptType.JOINED_CHUNK_LOAD, ExemptType.WEB, ExemptType.SLEEPING, ExemptType.DEATH, ExemptType.FROZEN, ExemptType.BLOCK_PLACE, ExemptType.BLOCK_BREAK, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.FULLY_STUCK, ExemptType.PARTIALLY_STUCK, ExemptType.WORLD_CHANGE, ExemptType.VEHICLE, ExemptType.BOAT, ExemptType.GLIDING, ExemptType.SLOW_FALLING, ExemptType.BUBBLE_COLUMN, ExemptType.SPECTATOR, ExemptType.DOLPHINS_GRACE, ExemptType.SHULKER_BOX) || this.data.getPositionProcessor().isExemptTeleport() || this.data.getPositionProcessor().isExemptClimbable() || this.data.getPositionProcessor().isExemptFlight() || this.data.getPositionProcessor().isExemptCreative() || this.data.getPositionProcessor().isExemptLiquid() || this.data.getPositionProcessor().isExemptElytra();
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " onGround=" + onGround + " amplifier=" + this.data.getActionProcessor().getLevitationAmplifier());
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
