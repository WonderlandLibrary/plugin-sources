package me.frep.vulcan.spigot.check.impl.combat.killaura;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Kill Aura", type = 'B', complexType = "Acceleration", experimental = false, description = "Invalid acceleration.")
public class KillAuraB extends AbstractCheck
{
    public KillAuraB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition() && this.hitTicks() < 3) {
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double acceleration = this.data.getPositionProcessor().getAcceleration();
            final float baseSpeed = PlayerUtil.getBaseSpeed(this.data, 0.21f);
            final long swingDelay = this.data.getClickProcessor().getDelay();
            final boolean sprinting = this.data.getActionProcessor().isSprinting();
            final boolean validTarget = this.data.getCombatProcessor().getTarget() != null && this.data.getCombatProcessor().getTarget() instanceof Player;
            final boolean validVersion = PlayerUtil.getClientVersion(this.data.getPlayer()).isLowerThan(ClientVersion.v_1_9);
            final boolean exempt = this.isExempt(ExemptType.SOUL_SAND);
            final boolean invalid = acceleration < 0.0025 && sprinting && deltaXZ > baseSpeed && swingDelay < 500L && validTarget && validVersion;
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("a=" + acceleration);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
