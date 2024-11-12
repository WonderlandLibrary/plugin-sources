package dev.coldservices.check.impl.scaffold;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientBlockPlace;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientFlying;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientPosition;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.data.tracker.impl.RotationTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.util.mcp.*;
import net.minecraft.server.v1_8_R3.Position;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

@CheckManifest(name = "Scaffold", type = "A", description = "Placed a block without looking at it.")
public class ScaffoldA extends Check implements PacketCheck {

    public ScaffoldA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientBlockPlace) {
            GPacketPlayClientBlockPlace wrapper = (GPacketPlayClientBlockPlace) packet;

            PositionTracker positionTracker = data.getPositionTracker();

            boolean exempt = this.isExempt(ExemptType.FLIGHT) || data.getPlayer().getGameMode() != GameMode.SURVIVAL || wrapper.getPosition().getY() >= positionTracker.getY();

            if(exempt) {
                this.buffer.setBuffer(0);
                return;
            }

            wrapper.getDirection().ifPresent(direction -> {
                BlockPosition block = wrapper.getPosition();
                RotationTracker tracker = data.getRotationTracker();

                double minX = block.getX() - 1;
                double minY = block.getY() - 1;
                double minZ = block.getZ() - 1;
                double maxX = block.getX() + 1;
                double maxY = block.getY() + 1;
                double maxZ = block.getZ() + 1;

                AxisAlignedBB boundingBox = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);

                if(this.isExempt(ExemptType.RETARD)) {
                    boundingBox.expand(0.03, 0.03, 0.03);
                }

                MovingObjectPosition rayTrace = rayCast(tracker.getYaw(), tracker.getPitch(), false, boundingBox);

                if (rayTrace == null || rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.MISS) {
                    if(this.buffer.increase() > 8) {
                        this.failNoBan("");
                        this.executeSetback(true, false);
                    }
                } else {
                    this.buffer.setBuffer(0);
                }
            });
        }
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
