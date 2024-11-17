package me.frep.vulcan.spigot.check.impl.combat.hitbox;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.Vulcan;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Hitbox", type = 'B', complexType = "Simple", description = "Attacked while not looking at target.")
public class HitboxB extends AbstractCheck
{
    private boolean attacking;
    private Player target;
    
    public HitboxB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK || !(wrapper.getEntity() instanceof Player)) {
                return;
            }
            this.target = (Player)wrapper.getEntity();
            this.attacking = true;
        }
        else if (packet.isFlying()) {
            if (this.attacking && this.target != null) {
                final PlayerData targetData = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(this.target);
                if (targetData == null) {
                    return;
                }
                final double deltaX = targetData.getPositionProcessor().getX() - this.data.getPositionProcessor().getX();
                final double deltaZ = targetData.getPositionProcessor().getZ() - this.data.getPositionProcessor().getZ();
                final float yaw = this.data.getRotationProcessor().getYaw();
                final double directionX = -Math.sin(yaw * 3.1415927f / 180.0f) * 1.0 * 0.5;
                final double directionZ = Math.cos(yaw * 3.1415927f / 180.0f) * 1.0 * 0.5;
                final Vector direction = new Vector(directionX, 0.0, directionZ);
                final Vector positionDifference = new Vector(deltaX, 0.0, deltaZ);
                final double distance = this.data.getCombatProcessor().getDistance();
                final double angle = Math.toDegrees(positionDifference.angle(direction));
                double maxAngle = Config.HITBOX_B_MAX_ANGLE;
                double maxDistance = 1.25;
                if (System.currentTimeMillis() - this.data.getConnectionProcessor().getLastFast() < 1500L) {
                    maxAngle += 0.125;
                    maxDistance += 0.1;
                }
                final boolean targetExempt = targetData.getExemptProcessor().isExempt(ExemptType.VEHICLE, ExemptType.BOAT);
                final boolean exempt = this.isExempt(ExemptType.CREATIVE, ExemptType.CREATIVE, ExemptType.VEHICLE);
                if (angle > maxAngle && distance > 1.3 && distance < 10.0 && !exempt && !targetExempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("angle=" + angle + " distance=" + distance);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
            this.attacking = false;
        }
    }
}
