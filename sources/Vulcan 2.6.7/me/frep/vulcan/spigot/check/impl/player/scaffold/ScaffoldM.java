package me.frep.vulcan.spigot.check.impl.player.scaffold;

import org.bukkit.block.BlockFace;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'M', complexType = "Expand", description = "Invalid block face.")
public class ScaffoldM extends AbstractCheck
{
    public ScaffoldM(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled() || BlockUtil.isScaffolding(event.getBlock().getType())) {
                return;
            }
            final boolean below = event.getBlockPlaced().getY() < this.data.getPositionProcessor().getY();
            final double distanceX = Math.abs(this.data.getPositionProcessor().getX() - event.getBlockPlaced().getX());
            final double distanceZ = Math.abs(this.data.getPositionProcessor().getZ() - event.getBlockPlaced().getZ());
            final double distanceXZ = MathUtil.hypot(distanceX, distanceZ);
            final BlockFace face = event.getBlockAgainst().getFace(event.getBlock());
            final BlockFace direction = this.getCardinalDirection(this.data.getRotationProcessor().getYaw());
            final boolean invalid = below && distanceXZ > 1.0 && distanceXZ < 6.0 && face == direction;
            if (invalid) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("[1] face=" + face + " direction=" + direction + " distance=" + distanceXZ);
                }
            }
            else {
                this.decayBuffer();
            }
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
}
