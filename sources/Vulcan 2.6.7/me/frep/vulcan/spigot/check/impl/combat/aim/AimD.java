package me.frep.vulcan.spigot.check.impl.combat.aim;

import org.bukkit.entity.Entity;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'D', complexType = "Straight", experimental = false, description = "Invalid pitch change.")
public class AimD extends AbstractCheck
{
    public AimD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.attacking(3)) {
            final float pitch = this.data.getRotationProcessor().getPitch();
            final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
            final float deltaPitch = this.data.getRotationProcessor().getDeltaPitch();
            final double distance = this.data.getCombatProcessor().getDistance();
            final Entity entity = this.data.getCombatProcessor().getTarget();
            if (entity == null || !(entity instanceof Player)) {
                return;
            }
            final Player target = (Player)entity;
            final PlayerData targetData = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(target);
            if (targetData == null) {
                return;
            }
            final boolean invalid = deltaYaw > 2.75f && deltaPitch == 0.0f && Math.abs(pitch) < 65.0f && distance > 1.0 && targetData.getPositionProcessor().getDeltaXZ() > 0.03;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("deltaYaw=" + deltaYaw + " deltaPitch=" + deltaPitch);
                }
            }
            else {
                this.decayBuffer();
            }
        }
    }
}
