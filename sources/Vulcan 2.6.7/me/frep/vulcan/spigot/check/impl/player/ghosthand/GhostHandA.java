package me.frep.vulcan.spigot.check.impl.player.ghosthand;

import java.util.List;
import me.frep.vulcan.spigot.config.Config;
import java.util.function.Predicate;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.Material;
import java.util.ArrayList;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Ghost Hand", type = 'A', complexType = "Bed", description = "Invalid bed break.")
public class GhostHandA extends AbstractCheck
{
    public GhostHandA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockBreakEvent()) {
            final BlockBreakEvent event = (BlockBreakEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.getBlock().getType().toString().contains("BED") && !event.getBlock().getType().toString().equalsIgnoreCase("BEDROCK")) {
                BlockFace direction = null;
                if (this.data.getPositionProcessor().getY() < event.getBlock().getY()) {
                    return;
                }
                if (event.getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock().getType().toString().contains("BED")) {
                    direction = BlockFace.SOUTH;
                }
                if (event.getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock().getType().toString().contains("BED")) {
                    direction = BlockFace.WEST;
                }
                if (event.getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock().getType().toString().contains("BED")) {
                    direction = BlockFace.NORTH;
                }
                if (event.getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock().getType().toString().contains("BED")) {
                    direction = BlockFace.EAST;
                }
                if (direction == null) {
                    return;
                }
                final List<Material> blocks = new ArrayList<Material>();
                switch (direction) {
                    case SOUTH: {
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, 2.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, 1.0).getBlock().getType());
                        final boolean invalid68 = blocks.stream().allMatch(Material::isSolid) && blocks.stream().allMatch(Material::isOccluding);
                        blocks.remove(0);
                        blocks.remove(0);
                        final boolean invalid69 = blocks.stream().allMatch(BlockUtil::isSolidGlass);
                        if (!invalid68 && !invalid69) {
                            break;
                        }
                        this.fail("direction=" + direction + " blocks=" + blocks);
                        if (Config.GHOSTHAND_A_CANCEL) {
                            event.setCancelled(true);
                            break;
                        }
                        break;
                    }
                    case NORTH: {
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, -2.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, -1.0).getBlock().getType());
                        final boolean invalid70 = blocks.stream().allMatch(Material::isSolid) && blocks.stream().allMatch(Material::isOccluding);
                        blocks.remove(0);
                        blocks.remove(0);
                        final boolean invalid71 = blocks.stream().allMatch(BlockUtil::isSolidGlass);
                        if (!invalid70 && !invalid71) {
                            break;
                        }
                        this.fail("direction=" + direction + " blocks=" + blocks);
                        if (Config.GHOSTHAND_A_CANCEL) {
                            event.setCancelled(true);
                            break;
                        }
                        break;
                    }
                    case EAST: {
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(2.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 1.0, 0.0).getBlock().getType());
                        final boolean invalid72 = blocks.stream().allMatch(Material::isSolid) && blocks.stream().allMatch(Material::isOccluding);
                        blocks.remove(0);
                        blocks.remove(0);
                        final boolean invalid73 = blocks.stream().allMatch(BlockUtil::isSolidGlass);
                        if (!invalid72 && !invalid73) {
                            break;
                        }
                        this.fail("direction=" + direction + " blocks=" + blocks);
                        if (Config.GHOSTHAND_A_CANCEL) {
                            event.setCancelled(true);
                            break;
                        }
                        break;
                    }
                    case WEST: {
                        blocks.add(event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, -1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, 1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 0.0, -1.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-2.0, 0.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType());
                        blocks.add(event.getBlock().getLocation().add(-1.0, 1.0, 0.0).getBlock().getType());
                        final boolean invalid74 = blocks.stream().allMatch(Material::isSolid) && blocks.stream().allMatch(Material::isOccluding);
                        blocks.remove(0);
                        blocks.remove(0);
                        final boolean invalid75 = blocks.stream().allMatch(BlockUtil::isSolidGlass);
                        if (!invalid74 && !invalid75) {
                            break;
                        }
                        this.fail("direction=" + direction + " blocks=" + blocks);
                        if (Config.GHOSTHAND_A_CANCEL) {
                            event.setCancelled(true);
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
}
