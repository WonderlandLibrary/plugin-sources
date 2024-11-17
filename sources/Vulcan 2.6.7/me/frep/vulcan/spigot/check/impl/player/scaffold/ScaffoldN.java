package me.frep.vulcan.spigot.check.impl.player.scaffold;

import org.bukkit.util.Vector;
import org.bukkit.Material;
import me.frep.vulcan.spigot.util.ray.RayTrace;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.MathUtil;
import org.bukkit.block.BlockFace;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import java.util.ArrayList;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.block.Block;
import java.util.List;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'N', complexType = "Expand", description = "Invalid block face.", experimental = true)
public class ScaffoldN extends AbstractCheck
{
    private List<Block> lastPlace;
    
    public ScaffoldN(final PlayerData data) {
        super(data);
        this.lastPlace = new ArrayList<Block>();
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled() || BlockUtil.isScaffolding(event.getBlock().getType())) {
                return;
            }
            final Block block = event.getBlock();
            if (this.lastPlace.contains(event.getBlockAgainst())) {
                final boolean under = event.getBlockPlaced().getY() < this.data.getPositionProcessor().getY();
                final BlockFace face = event.getBlockAgainst().getFace(event.getBlock());
                Material below2;
                if (ServerUtil.isHigherThan1_13()) {
                    below2 = this.data.getPositionProcessor().getBlockBelow2Modern();
                }
                else {
                    below2 = this.data.getPositionProcessor().getBlockBelow2();
                }
                if (below2 == null || face == null) {
                    return;
                }
                if (face == BlockFace.DOWN || face == BlockFace.UP || face == BlockFace.SELF) {
                    return;
                }
                final double distanceX = Math.abs(this.data.getPositionProcessor().getX() - event.getBlockPlaced().getX());
                final double distanceZ = Math.abs(this.data.getPositionProcessor().getZ() - event.getBlockPlaced().getZ());
                final double distanceXZ = MathUtil.hypot(distanceX, distanceZ);
                final int blockX = event.getBlock().getLocation().getBlockX();
                final int blockZ = event.getBlock().getLocation().getBlockZ();
                final int playerX = this.data.getPositionProcessor().getBlockX();
                final int playerZ = this.data.getPositionProcessor().getBlockZ();
                final boolean match = blockX == playerX || blockZ == playerZ;
                final boolean invalid = BlockUtil.isAir(below2) && under && distanceXZ > 1.85 && match;
                final boolean exempt = this.isExempt(ExemptType.FLIGHT, ExemptType.CREATIVE);
                if (invalid && !exempt) {
                    final RayTrace rayTrace = new RayTrace(this.data.getPlayer().getLocation().toVector(), this.getDirection(this.data.getRotationProcessor().getYaw(), this.data.getRotationProcessor().getPitch()));
                    final List<Block> blocks = rayTrace.getBlocks(this.data.getPlayer().getWorld(), 3.0, 1.0);
                    if (!blocks.isEmpty()) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("distanceXZ=" + distanceXZ + " r=" + blocks.size());
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                }
                this.lastPlace.clear();
            }
            this.lastPlace.add(block);
        }
    }
    
    private BlockFace getCardinalDirection(final float yaw) {
        float rot = (yaw - 90.0f) % 360.0f;
        if (rot < 0.0f) {
            rot += 360.0;
        }
        return this.getDirection(rot);
    }
    
    private BlockFace getDirection(final float yaw) {
        if (0.0f <= yaw && yaw < 45.0f) {
            return BlockFace.WEST;
        }
        if (45.0f <= yaw && yaw < 135.0f) {
            return BlockFace.NORTH;
        }
        if (135.0f <= yaw && yaw < 225.0f) {
            return BlockFace.EAST;
        }
        if (225.0f <= yaw && yaw < 315.0f) {
            return BlockFace.SOUTH;
        }
        if (315.0f <= yaw && yaw < 360.0) {
            return BlockFace.WEST;
        }
        return BlockFace.SELF;
    }
    
    public Vector getDirection(final float yaw, final float pitch) {
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
