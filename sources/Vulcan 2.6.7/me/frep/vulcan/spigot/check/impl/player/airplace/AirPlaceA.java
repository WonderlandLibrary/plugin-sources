package me.frep.vulcan.spigot.check.impl.player.airplace;

import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Air Place", type = 'A', complexType = "Invalid", description = "Invalid block placement.", experimental = true)
public class AirPlaceA extends AbstractCheck
{
    public AirPlaceA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            final boolean exempt = this.isExempt(ExemptType.SPECTATOR, ExemptType.CANCELLED_PLACE, ExemptType.CREATIVE);
            if (event.isCancelled() || exempt) {
                return;
            }
            if (BlockUtil.isSlab(event.getBlock().getType()) || BlockUtil.isSand(event.getBlock().getType()) || BlockUtil.isGravel(event.getBlock().getType()) || BlockUtil.isAnvil(event.getBlock().getType()) || BlockUtil.isPath(event.getBlock().getType()) || BlockUtil.isStripped(event.getBlock().getType()) || BlockUtil.isFarmland(event.getBlock().getType())) {
                return;
            }
            if (event.getBlockAgainst().getX() == event.getBlock().getX() && event.getBlockAgainst().getY() == event.getBlock().getY() && event.getBlockAgainst().getZ() == event.getBlock().getZ() && BlockUtil.isAir(event.getBlock().getLocation().add(0.0, 1.0, 0.0).getBlock().getType()) && BlockUtil.isAir(event.getBlock().getLocation().add(0.0, -1.0, 0.0).getBlock().getType()) && BlockUtil.isAir(event.getBlock().getLocation().add(1.0, 0.0, 0.0).getBlock().getType()) && BlockUtil.isAir(event.getBlock().getLocation().add(-1.0, 0.0, 0.0).getBlock().getType()) && BlockUtil.isAir(event.getBlock().getLocation().add(0.0, 0.0, 1.0).getBlock().getType()) && BlockUtil.isAir(event.getBlock().getLocation().add(0.0, 0.0, -1.0).getBlock().getType())) {
                this.fail("block= " + event.getBlock().getType());
            }
        }
    }
}
