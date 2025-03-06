package me.frep.vulcan.spigot.check.impl.movement.elytra;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Elytra", type = 'M', complexType = "Ascending", description = "Invalid ascension pattern.")
public class ElytraM extends AbstractCheck
{
    public ElytraM(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && !this.teleporting()) {
            if (!this.data.getActionProcessor().isBukkitGliding()) {
                return;
            }
            final double deltaY = this.data.getPositionProcessor().getDeltaY();
            if (Math.abs(deltaY) > 0.01) {
                final double modulo = 0.01 % (deltaY % 0.01);
                final boolean exempt = this.isExempt(ExemptType.FLIGHT, ExemptType.SLIME, ExemptType.SLOW_FALLING, ExemptType.LEVITATION, ExemptType.LIQUID, ExemptType.BUBBLE_COLUMN, ExemptType.LIQUID, ExemptType.RIPTIDE, ExemptType.BUKKIT_VELOCITY);
                if (modulo < 1.0E-8 && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deltaY=" + deltaY + " m=" + modulo);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
