package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'Y', complexType = "Rotation", description = "Generic rotation analysis heuristic.")
public class AimY extends AbstractCheck
{
    public AimY(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isRotation() && this.hitTicks() < 3) {
            final float lastFuckedYaw = this.data.getRotationProcessor().getLastFuckedPredictedYaw();
            final float fuckedYaw = this.data.getRotationProcessor().getFuckedPredictedYaw();
            final float difference = Math.abs(fuckedYaw - lastFuckedYaw);
            final double distance = this.data.getCombatProcessor().getDistance();
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.DEATH, ExemptType.WORLD_CHANGE) || this.data.getActionProcessor().getSinceTeleportTicks() < 20;
            if (exempt) {
                return;
            }
            if (distance > 0.6 && difference > 20.0f && distance < 10.0) {
                this.fail("diff=" + difference + " dist=" + distance);
            }
        }
    }
}
