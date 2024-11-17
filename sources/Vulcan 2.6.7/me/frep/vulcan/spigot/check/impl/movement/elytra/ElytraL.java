package me.frep.vulcan.spigot.check.impl.movement.elytra;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Elytra", type = 'L', complexType = "Ascending", description = "Moving up wrongly.")
public class ElytraL extends AbstractCheck
{
    public ElytraL(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (!this.data.getActionProcessor().isBukkitGliding()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final float pitch = this.data.getRotationProcessor().getPitch();
            final boolean exempt = this.isExempt(ExemptType.DRAGON_DAMAGE, ExemptType.EXPLOSION, ExemptType.FIREWORK, ExemptType.TELEPORT, ExemptType.FISHING_ROD, ExemptType.RIPTIDE, ExemptType.BUBBLE_COLUMN, ExemptType.LIQUID, ExemptType.SLIME, ExemptType.PISTON, ExemptType.BUKKIT_VELOCITY, ExemptType.WORLD_CHANGE, ExemptType.SWIMMING, ExemptType.BED, ExemptType.LEVITATION, ExemptType.FLIGHT) || this.data.getPositionProcessor().getSinceFuckingEntityTicks() < 200 || this.data.getVelocityProcessor().getSinceHighVelocityTicks() < 50;
            double max = 1.35;
            if (this.data.getActionProcessor().getJumpBoostAmplifier() > 0) {
                max += this.data.getActionProcessor().getJumpBoostAmplifier() * 0.1;
            }
            final int riptideTicks = this.data.getPositionProcessor().getSinceRiptideTicks();
            final boolean riptide = riptideTicks < 400;
            final boolean invalid = deltaY > max && pitch > -25.0f;
            if (invalid && !exempt && !riptide) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaY=" + deltaY + " pitch=" + pitch + " rt=" + riptideTicks);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
