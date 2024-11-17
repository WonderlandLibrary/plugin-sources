package me.frep.vulcan.spigot.check.impl.movement.elytra;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Elytra", type = 'G', complexType = "Acceleration", description = "Invalid acceleration.")
public class ElytraG extends AbstractCheck
{
    public ElytraG(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            if (ServerUtil.isLowerThan1_9() || !this.data.getActionProcessor().isBukkitGliding()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double lastDeltaXZ = this.data.getPositionProcessor().getLastDeltaXZ();
            final double acceleration = deltaXZ - lastDeltaXZ;
            final boolean invalid = acceleration > 0.03 && deltaY > -0.5 && deltaXZ > 0.65;
            final boolean exempt = this.isExempt(ExemptType.FIREWORK, ExemptType.EXPLOSION, ExemptType.RIPTIDE, ExemptType.DRAGON_DAMAGE, ExemptType.FLIGHT, ExemptType.VELOCITY, ExemptType.SLIME, ExemptType.DOLPHINS_GRACE);
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("accel=" + acceleration + " deltaXZ=" + deltaXZ + " deltaY=" + deltaY);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
