package me.frep.vulcan.spigot.check.impl.combat.hitbox;

import me.frep.vulcan.spigot.util.type.Pair;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Hitbox", type = 'A', complexType = "History", description = "Attacked while not looking at target.")
public class HitboxA extends AbstractCheck
{
    private boolean attacking;
    
    public HitboxA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK || !(wrapper.getEntity() instanceof Player)) {
                return;
            }
            this.attacking = true;
        }
        else if (packet.isFlying()) {
            if (this.attacking) {
                final int ticks = Vulcan.INSTANCE.getTickManager().getTicks();
                final int pingTicks = MathUtil.getPingInTicks(this.data);
                final double x = this.data.getPositionProcessor().getX();
                final double z = this.data.getPositionProcessor().getZ();
                final Vector origin = new Vector(x, 0.0, z);
                final double angle = this.data.getCombatProcessor().getTargetLocations().stream().filter(pair -> Math.abs(ticks - pair.getY() - pingTicks) < 3).mapToDouble(pair -> {
                    final Vector targetLocation = pair.getX().toVector().setY(0.0);
                    final Vector direction = targetLocation.clone().subtract(origin);
                    final Vector target = this.getDirection(this.data.getRotationProcessor().getYaw(), this.data.getRotationProcessor().getPitch()).setY(0.0);
                    return (double)direction.angle(target);
                }).min().orElse(-1.0);
                final double distance = this.data.getCombatProcessor().getDistance();
                if (angle == -1.0) {
                    this.decreaseBufferBy(0.025);
                }
                else {
                    final PlayerData targetData = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(this.data.getCombatProcessor().getTrackedPlayer());
                    if (targetData == null) {
                        return;
                    }
                    final boolean targetExempt = targetData.getExemptProcessor().isExempt(ExemptType.VEHICLE, ExemptType.BOAT);
                    double maxAngle = Config.HITBOX_A_MAX_ANGLE;
                    if (System.currentTimeMillis() - this.data.getConnectionProcessor().getLastFast() < 1500L) {
                        maxAngle += 0.125;
                    }
                    final boolean exempt = this.isExempt(ExemptType.CREATIVE, ExemptType.GLIDING, ExemptType.VEHICLE);
                    final float deltaYaw = this.data.getRotationProcessor().getDeltaYaw();
                    if (angle > maxAngle && distance > 1.5 && distance < 10.0 && !exempt && !targetExempt) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("angle=" + angle + " distance=" + distance + " dYaw=" + deltaYaw);
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                }
            }
            this.attacking = false;
        }
    }
    
    private Vector getDirection(final float yaw, final float pitch) {
        final Vector vector = new Vector();
        final double rotX = yaw;
        final double rotY = pitch;
        vector.setY(-Math.sin(Math.toRadians(rotY)));
        final double xz = Math.cos(Math.toRadians(rotY));
        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
        return vector;
    }
}
