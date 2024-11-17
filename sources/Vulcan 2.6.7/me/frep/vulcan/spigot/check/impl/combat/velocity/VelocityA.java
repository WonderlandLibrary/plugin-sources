package me.frep.vulcan.spigot.check.impl.combat.velocity;

import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Velocity", type = 'A', complexType = "Vertical", description = "Vertical velocity modifications.")
public class VelocityA extends AbstractCheck
{
    public VelocityA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final boolean push = this.data.getActionProcessor().getSincePushTicks() < 20;
            if (push || this.data.getPositionProcessor().isNearBorder()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final VelocityProcessor.VelocitySnapshot snapshot = this.data.getVelocityProcessor().getSnapshot();
            if (snapshot == null) {
                return;
            }
            final double velocityY = snapshot.getVelocity().getY();
            if (velocityY < 0.01) {
                return;
            }
            final int ticksSinceVelocity = this.data.getVelocityProcessor().getTransactionFlyingTicks();
            final double ratio = deltaY / velocityY;
            final boolean poison = this.data.getActionProcessor().getSincePoisonDamageTicks() < 10;
            if (ticksSinceVelocity == 1 && !poison) {
                final boolean exempt = this.isExempt(ExemptType.COLLIDING_VERTICALLY, ExemptType.LIQUID, ExemptType.FROZEN, ExemptType.CHUNK, ExemptType.FALL_DAMAGE, ExemptType.ENDER_PEARL, ExemptType.WEB, ExemptType.VOID, ExemptType.SLOW_FALLING, ExemptType.ENDER_PEARL, ExemptType.TRAPDOOR, ExemptType.SOUL_SAND, ExemptType.SLIME, ExemptType.COMBO_MODE, ExemptType.FLIGHT, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.NETHERITE_ARMOR, ExemptType.FULLY_STUCK, ExemptType.PARTIALLY_STUCK, ExemptType.VEHICLE, ExemptType.MYTHIC_MOB, ExemptType.ELYTRA, ExemptType.FIREBALL, ExemptType.SWEET_BERRIES, ExemptType.BUKKIT_VELOCITY, ExemptType.PLACED_WEB);
                double minRatio = 0.999;
                if (this.isExempt(ExemptType.COLLIDING_HORIZONTALLY)) {
                    minRatio -= 0.1;
                }
                if (ratio < minRatio && velocityY > 0.0 && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("tick=1 percent=" + ratio * 100.0 + "% deltaY=" + deltaY + " velocity=" + velocityY);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
