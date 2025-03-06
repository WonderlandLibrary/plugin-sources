package me.frep.vulcan.spigot.check.impl.player.scaffold;

import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import java.util.HashMap;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.Map;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'K', complexType = "Limit", description = "Bridging too quickly.")
public class ScaffoldK extends AbstractCheck
{
    private int lastSneak;
    private final Map<Integer, Long> placed;
    private int blocksPlaced;
    
    public ScaffoldK(final PlayerData data) {
        super(data);
        this.placed = new HashMap<Integer, Long>();
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled() || BlockUtil.isSlab(event.getBlock().getType()) || this.isExempt(ExemptType.SLAB)) {
                return;
            }
            if (PlayerUtil.isBridging(event) && this.ticks() - this.lastSneak > 20) {
                ++this.blocksPlaced;
                this.placed.put(this.blocksPlaced, System.currentTimeMillis());
                if (System.currentTimeMillis() - this.placed.get(1) > 1000L) {
                    final int amount = this.placed.size();
                    if (amount >= Config.SCAFFOLD_K_MAX_BLOCKS) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("amount=" + amount);
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                    this.placed.clear();
                    this.blocksPlaced = 0;
                }
            }
        }
        else if (packet.isEntityAction()) {
            final WrappedPacketInEntityAction wrapper = this.data.getEntityActionWrapper();
            if (wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.START_SNEAKING || wrapper.getAction() == WrappedPacketInEntityAction.PlayerAction.STOP_SNEAKING) {
                this.lastSneak = this.ticks();
            }
        }
    }
}
