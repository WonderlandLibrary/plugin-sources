package me.frep.vulcan.spigot.check.impl.combat.velocity;

import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Velocity", type = 'B', complexType = "Horizontal", description = "Horizontal velocity modifications.")
public class VelocityB extends AbstractCheck
{
    public VelocityB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final boolean push = this.data.getActionProcessor().getSincePushTicks() < 20;
            if (push || this.data.getPositionProcessor().isNearBorder()) {
                return;
            }
            final boolean collidingHorizontally = this.data.getPositionProcessor().isCollidingHorizontally();
            final boolean collidingVertically = this.data.getPositionProcessor().isCollidingVertically();
            if (!collidingHorizontally) {
                if (!collidingVertically) {
                    final int velocityTicks = this.data.getVelocityProcessor().getTransactionFlyingTicks();
                    if (velocityTicks == 1) {
                        final VelocityProcessor.VelocitySnapshot snapshot = this.data.getVelocityProcessor().getSnapshot();
                        if (snapshot == null) {
                            return;
                        }
                        final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
                        final double velocityXZ = MathUtil.hypot(snapshot.getVelocity().getX(), snapshot.getVelocity().getZ());
                        if (velocityXZ < 0.01) {
                            return;
                        }
                        final double ratio = deltaXZ / velocityXZ;
                        final boolean exempt = this.isExempt(ExemptType.COLLIDING_VERTICALLY, ExemptType.LIQUID, ExemptType.FROZEN, ExemptType.CHUNK, ExemptType.FALL_DAMAGE, ExemptType.ENDER_PEARL, ExemptType.WEB, ExemptType.VOID, ExemptType.SLOW_FALLING, ExemptType.PLACED_WEB, ExemptType.ENDER_PEARL, ExemptType.TRAPDOOR, ExemptType.SOUL_SAND, ExemptType.SLIME, ExemptType.COMBO_MODE, ExemptType.FLIGHT, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.NETHERITE_ARMOR, ExemptType.FULLY_STUCK, ExemptType.BUKKIT_VELOCITY, ExemptType.PARTIALLY_STUCK, ExemptType.VEHICLE, ExemptType.MYTHIC_MOB, ExemptType.ELYTRA, ExemptType.SWEET_BERRIES);
                        if (ratio < 0.21 && !exempt) {
                            if (this.increaseBuffer() > this.MAX_BUFFER) {
                                this.fail("percent=" + ratio * 100.0 + "% deltaXZ=" + deltaXZ + " velocityXZ=" + velocityXZ);
                            }
                        }
                        else {
                            this.decayBuffer();
                        }
                    }
                }
            }
        }
    }
}
