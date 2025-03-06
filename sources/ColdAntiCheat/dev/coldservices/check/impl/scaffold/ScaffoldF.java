package dev.coldservices.check.impl.scaffold;

import ac.artemis.packet.spigot.wrappers.GPacket;
import cc.ghast.packet.nms.EnumDirection;
import cc.ghast.packet.wrapper.bukkit.BlockPosition;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientBlockPlace;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.data.tracker.impl.PositionTracker;
import dev.coldservices.data.tracker.impl.RotationTracker;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.util.mcp.AxisAlignedBB;
import dev.coldservices.util.mcp.MathHelper;
import dev.coldservices.util.mcp.MovingObjectPosition;
import dev.coldservices.util.mcp.Vec3;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

@CheckManifest(name = "Scaffold", type = "F", description = "Checks if the player interacted the below of the block")
public class ScaffoldF extends Check implements PacketCheck {

    public ScaffoldF(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayClientBlockPlace) {
            GPacketPlayClientBlockPlace wrapper = (GPacketPlayClientBlockPlace) packet;

            wrapper.getDirection().ifPresent(enumDirection -> {
                PositionTracker tracker = data.getPositionTracker();
                RotationTracker rotationTracker = data.getRotationTracker();

                BlockPosition position = wrapper.getPosition();
                BlockPosition positionHigher = new BlockPosition(position.getX(), position.getY() + 1, position.getZ());
                World world = data.getPlayer().getWorld();

                Material type2 = world.getBlockAt(new Location(world, positionHigher.getX(), positionHigher.getY(), positionHigher.getZ())).getType();
                Material type = world.getBlockAt(new Location(world, position.getX(), position.getY(), position.getZ())).getType();

                boolean invalidPosition = tracker.getY() >= position.getY();

                double minX = positionHigher.getX() - 1;
                double minY = positionHigher.getY() - 1;
                double minZ = positionHigher.getZ() - 1;
                double maxX = positionHigher.getX() + 1;
                double maxY = positionHigher.getY() + 1;
                double maxZ = positionHigher.getZ() + 1;

                AxisAlignedBB boundingBox = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);

                if(this.isExempt(ExemptType.RETARD)) {
                    boundingBox.expand(0.03, 0.03, 0.03);
                }

                MovingObjectPosition rayTrace = rayCast(rotationTracker.getYaw(), rotationTracker.getPitch(), false, boundingBox);

                if(rayTrace == null) return;

                boolean invalidPlacement = rayTrace.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK;

                if(type != Material.AIR && type2 != Material.AIR && invalidPosition && invalidPlacement && !tracker.isAirBelow()) {
                    this.failNoBan("impossible interact / downwards scaffold");
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
