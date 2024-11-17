package me.frep.vulcan.spigot.check.impl.combat.velocity;

import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Velocity", type = 'C', complexType = "Ignored Vertical", description = "Vertical velocity modifications.")
public class VelocityC extends AbstractCheck
{
    private boolean lastSprint;
    private double firstTickBuffer;
    private double secondTickBuffer;
    private double thirdTickBuffer;
    private double fourthTickBuffer;
    
    public VelocityC(final PlayerData data) {
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
                    final VelocityProcessor.VelocitySnapshot snapshot = this.data.getVelocityProcessor().getSnapshot();
                    if (snapshot == null) {
                        return;
                    }
                    final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
                    final double lastDeltaXZ = this.data.getPositionProcessor().getLastDeltaXZ();
                    final double velocityXZ = MathUtil.hypot(snapshot.getVelocity().getX(), snapshot.getVelocity().getZ());
                    final int velocityTicks = this.data.getVelocityProcessor().getTransactionFlyingTicks();
                    if (velocityTicks > 50) {
                        return;
                    }
                    final boolean sprinting = this.data.getActionProcessor().isSprinting();
                    final double minimum = (sprinting || this.lastSprint) ? 0.275 : 0.7;
                    final boolean exempt = this.isExempt(ExemptType.COLLIDING_VERTICALLY, ExemptType.LIQUID, ExemptType.FROZEN, ExemptType.CHUNK, ExemptType.LEVITATION, ExemptType.FALL_DAMAGE, ExemptType.ENDER_PEARL, ExemptType.WEB, ExemptType.VOID, ExemptType.SLOW_FALLING, ExemptType.BUKKIT_VELOCITY, ExemptType.ENDER_PEARL, ExemptType.TRAPDOOR, ExemptType.SOUL_SAND, ExemptType.SLIME, ExemptType.COMBO_MODE, ExemptType.PLACED_WEB, ExemptType.FLIGHT, ExemptType.ATTRIBUTE_MODIFIER, ExemptType.NETHERITE_ARMOR, ExemptType.FULLY_STUCK, ExemptType.FISHING_ROD, ExemptType.PARTIALLY_STUCK, ExemptType.VEHICLE, ExemptType.MYTHIC_MOB, ExemptType.ELYTRA, ExemptType.SWEET_BERRIES);
                    if (velocityXZ < 0.01 || exempt) {
                        return;
                    }
                    switch(velocityTicks) {
                        case 1:
                            double ratio = deltaXZ / velocityXZ;
                            if (ratio < minimum) {
                                if (++this.firstTickBuffer > this.MAX_BUFFER) {
                                    this.fail("tick=1 percent=" + ratio * 100.0D + "% s=" + sprinting + " ls=" + this.lastSprint + " deltaXZ=" + deltaXZ + " velocityXZ=" + velocityXZ);
                                }
                            } else if (this.firstTickBuffer > 0.0D) {
                                this.firstTickBuffer -= this.BUFFER_DECAY;
                            }
                            break;
                        case 2:
                            double ratio2 = deltaXZ / (lastDeltaXZ * (double)0.91F * (double)0.6F);
                            double minimum2 = !sprinting && !this.lastSprint ? 0.32D : 0.1D;
                            if (ratio2 < minimum2) {
                                if (++this.secondTickBuffer > this.MAX_BUFFER) {
                                    this.fail("tick=2 percent=" + ratio2 * 100.0D + "% s=" + sprinting + " ls=" + this.lastSprint + " deltaXZ=" + deltaXZ + " velocityXZ=" + velocityXZ);
                                }
                            } else if (this.secondTickBuffer > 0.0D) {
                                this.secondTickBuffer -= this.BUFFER_DECAY;
                            }
                            break;
                        case 3:
                            double ratio3 = deltaXZ / (lastDeltaXZ * (double)0.91F);
                            double minimum3 = !sprinting && !this.lastSprint ? 0.25D : 0.1D;
                            if (ratio3 < minimum3) {
                                if (++this.thirdTickBuffer > this.MAX_BUFFER) {
                                    this.fail("tick=3 percent=" + ratio3 * 100.0D + "% s=" + sprinting + " ls=" + this.lastSprint + " deltaXZ=" + deltaXZ + " velocityXZ=" + velocityXZ);
                                }
                            } else if (this.thirdTickBuffer > 0.0D) {
                                this.thirdTickBuffer -= this.BUFFER_DECAY;
                            }
                            break;
                        case 4:
                            double ratio4 = deltaXZ / (lastDeltaXZ * (double)0.91F);
                            double minimum4 = !sprinting && !this.lastSprint ? 0.25D : 0.1D;
                            if (ratio4 < minimum4) {
                                if (++this.fourthTickBuffer > this.MAX_BUFFER) {
                                    this.fail("tick=4 percent=" + ratio4 * 100.0D + "% s=" + sprinting + " ls=" + this.lastSprint + " deltaXZ=" + deltaXZ + " velocityXZ=" + velocityXZ);
                                }
                            } else if (this.fourthTickBuffer > 0.0D) {
                                this.fourthTickBuffer -= this.BUFFER_DECAY;
                            }
                    }
                    this.lastSprint = sprinting;
                }
            }
        }
    }
}
