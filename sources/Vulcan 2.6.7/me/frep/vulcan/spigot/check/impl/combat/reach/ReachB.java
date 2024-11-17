package me.frep.vulcan.spigot.check.impl.combat.reach;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.util.Vector;
import me.frep.vulcan.spigot.Vulcan;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Reach", type = 'B', complexType = "Simple", description = "Hit from too far away.")
public class ReachB extends AbstractCheck
{
    private boolean attacking;
    private Player target;
    
    public ReachB(final PlayerData data) {
        super(data);
        this.attacking = false;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final WrappedPacketInUseEntity wrapper = this.data.getUseEntityWrapper();
            if (wrapper.getAction() != WrappedPacketInUseEntity.EntityUseAction.ATTACK || !(wrapper.getEntity() instanceof Player)) {
                this.target = null;
                this.attacking = false;
                return;
            }
            this.attacking = true;
            this.target = (Player)wrapper.getEntity();
        }
        else if (packet.isFlying() && this.attacking && this.target != null) {
            final PlayerData targetData = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(this.target);
            if (targetData == null) {
                return;
            }
            final double x = this.data.getPositionProcessor().getX();
            final double y = this.data.getPositionProcessor().getY();
            final double z = this.data.getPositionProcessor().getZ();
            final Vector origin = new Vector(x, 0.0, z);
            final double targetX = targetData.getPositionProcessor().getX();
            final double targetY = targetData.getPositionProcessor().getY();
            final double targetZ = targetData.getPositionProcessor().getZ();
            final Vector targetVector = new Vector(targetX, 0.0, targetZ);
            final double yDifference = Math.abs(y - targetY);
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            final double distance = origin.distance(targetVector) - 0.565;
            final int groundTicks = this.data.getPositionProcessor().getClientGroundTicks();
            double maxDistance = Config.REACH_B_MAX_REACH + 0.05;
            maxDistance += yDifference * 0.9;
            maxDistance += deltaXZ * 0.66;
            if (this.data.getActionProcessor().isSprinting()) {
                maxDistance += 0.32;
            }
            if (groundTicks < 3) {
                maxDistance += 0.35;
            }
            if (this.data.getActionProcessor().isHasSpeed()) {
                maxDistance += this.data.getActionProcessor().getSpeedAmplifier() * 0.125;
            }
            final double yawDiff = PlayerUtil.getYawDiff(this.data.getPlayer(), targetData.getPlayer());
            if (yawDiff > 100.0) {
                maxDistance += 1.25;
            }
            final boolean exempt = this.isExempt(ExemptType.TELEPORT, ExemptType.JOINED, ExemptType.PLUGIN_LOAD, ExemptType.WORLD_CHANGE, ExemptType.CREATIVE, ExemptType.VEHICLE);
            if (this.isExempt(ExemptType.SERVER_POSITION) || System.currentTimeMillis() - this.data.getConnectionProcessor().getLastFast() < 1500L) {
                ++maxDistance;
            }
            final boolean targetExempt = targetData.getExemptProcessor().isExempt(ExemptType.VEHICLE, ExemptType.BOAT);
            if (distance > maxDistance && distance < 6.0 && !exempt && !targetExempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail(distance + " > " + maxDistance);
                }
            }
            else {
                this.decayBuffer();
            }
            this.attacking = false;
        }
    }
}
