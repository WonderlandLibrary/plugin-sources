package me.frep.vulcan.spigot.check.impl.combat.killaura;

import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'C', complexType = "Head Snap", experimental = false, description = "Invalid acceleration.")
public class KillAuraC extends AbstractCheck
{
    public KillAuraC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && this.hitTicks() < 3) {
            final double acceleration = this.data.getPositionProcessor().getAcceleration();
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final boolean validTarget = this.data.getCombatProcessor().getTarget() instanceof Player;
            final boolean invalid = acceleration < 0.001 && deltaYaw > 10.0f && deltaPitch > 26.5 && validTarget && deltaXZ > 0.0;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("accel=" + acceleration + " deltaYaw=" + deltaYaw);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
