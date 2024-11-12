package dev.coldservices.check.impl.reach;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.Packet;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientUseEntity;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.mc.PlayerEnums;
import cc.ghast.packet.wrapper.packet.play.client.*;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.ConnectionTracker;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.data.tracker.impl.RotationTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.util.mcp.AxisAlignedBB;
import dev.coldservices.util.mcp.MathHelper;
import dev.coldservices.util.mcp.MovingObjectPosition;
import dev.coldservices.util.mcp.Vec3;
import dev.coldservices.util.player.TrackerEntity;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

@CheckManifest(name = "Reach", type = "A", description = "The best reach check no cap.")
public class ReachA extends Check implements PacketCheck {

    private final List<Packet> hits = new ArrayList<>();
    private TrackerEntity target;
    private boolean mitigate;


    public ReachA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientUseEntity) {
            GPacketPlayClientUseEntity wrapper = (GPacketPlayClientUseEntity) packet;

            if(wrapper.getType() == PlayerEnums.UseType.ATTACK) {
                this.target = data.getEntityTracker().getTrackerMap().getOrDefault(wrapper.getEntityId(), null);
            }
        }

        if(packet instanceof PacketPlayClientFlying) {
            PacketPlayClientFlying wrapper = (PacketPlayClientFlying) packet;
            PositionTracker positionTracker = data.getPositionTracker();

            boolean exempt = this.isExempt(ExemptType.FLIGHT, ExemptType.BOAT, ExemptType.VEHICLE) ||
                    data.getPlayer().getGameMode() != GameMode.SURVIVAL;

            if(exempt) return;

            mitigate = this.buffer.getBuffer() > 2;

            if(target == null) return;

            AxisAlignedBB boundingBox = new AxisAlignedBB(
                    target.getPosX() - 0.4F, target.getPosY() - 0.1F, target.getPosZ() - 0.4F,
                    target.getPosX() + 0.4F, target.getPosY() + 1.9F, target.getPosZ() + 0.4F
            );

            if (this.isExempt(ExemptType.RETARD)) boundingBox = boundingBox.expand(0.03, 0.03, 0.03);

            boolean invalid = checkRange(boundingBox);

            if(invalid) {
                if(this.buffer.increase() > 2) {
                    this.failNoBan("range or hitbox.");
                }
            } else {
                this.buffer.decreaseBy(2);
            }

            target = null;
        }
    }

    public boolean checkRange(AxisAlignedBB bb) {
        RotationTracker rotTracker = data.getRotationTracker();

        MovingObjectPosition ray = rayCast(rotTracker.getYaw(), rotTracker.getPitch(), false, bb);

        return ray == null || ray.hitVec == null;
    }

    private MovingObjectPosition rayCast(final float yaw, final float pitch, final boolean sneak, final AxisAlignedBB bb) {
        final Location position = data.getPositionTracker().getLastLocation();

        final double lastX = position.getX();
        final double lastY = position.getY();
        final double lastZ = position.getZ();

        final Vec3 vec3 = new Vec3(lastX, lastY + this.getEyeHeight(sneak), lastZ);
        final Vec3 vec31 = this.getVectorForRotation(pitch, yaw);
        final Vec3 vec32 = vec3.add(new Vec3(vec31.xCoord * 3.0D, vec31.yCoord * 3.0D, vec31.zCoord * 3.0D));

        return bb.calculateIntercept(vec3, vec32);
    }

    // better not be using fastmath my guy
    private Vec3 getVectorForRotation(final float pitch, final float yaw) {
        final float f = MathHelper.cos(-yaw * 0.017453292F - (float) Math.PI);
        final float f1 = MathHelper.sin(-yaw * 0.017453292F - (float) Math.PI);
        final float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        final float f3 = MathHelper.sin(-pitch * 0.017453292F);

        return new Vec3(f1 * f2, f3, f * f2);
    }

    public float getEyeHeight(final boolean sneak) {
        float f2 = 1.62F;

        if (data.getPlayer().isSleeping()) {
            f2 = 0.2F;
        }

        if (sneak) {
            f2 -= 0.08F;
        }

        return f2;
    }

}
