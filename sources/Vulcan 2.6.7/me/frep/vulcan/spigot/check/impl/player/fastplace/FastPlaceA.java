package me.frep.vulcan.spigot.check.impl.player.fastplace;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.util.BlockUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import java.util.HashMap;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.Map;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Fast Place", type = 'A', complexType = "Delay", description = "Placing blocks too quickly.")
public class FastPlaceA extends AbstractCheck
{
    private final Map<Integer, Long> placed;
    private int blocksPlaced;
    
    public FastPlaceA(final PlayerData data) {
        super(data);
        this.placed = new HashMap<Integer, Long>();
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            final boolean exempt = this.isExempt(ExemptType.CANCELLED_BREAK, ExemptType.VEHICLE) || event.isCancelled();
            if (this.data.getPlayer().getItemInHand().getType().toString().contains("HOE")) {
                return;
            }
            if (ServerUtil.isHigherThan1_9() && this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("HOE")) {
                return;
            }
            if (exempt || BlockUtil.isScaffolding(event.getBlock().getType())) {
                return;
            }
            ++this.blocksPlaced;
            this.placed.put(this.blocksPlaced, System.currentTimeMillis());
            if (System.currentTimeMillis() - this.placed.get(1) > 1000L) {
                if (this.placed.size() > Config.FASTPLACE_A_MAX_BLOCKS) {
                    this.fail("amount=" + this.placed.size());
                }
                this.placed.clear();
                this.blocksPlaced = 0;
            }
        }
    }
}
