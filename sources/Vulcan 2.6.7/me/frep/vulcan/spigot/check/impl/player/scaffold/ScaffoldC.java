package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'C', complexType = "Sprint", description = "Sprinting while bridging.")
public class ScaffoldC extends AbstractCheck
{
    private long lastPlace;
    
    public ScaffoldC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled()) {
                return;
            }
            final boolean bridging = PlayerUtil.isBridging(event);
            final long delay = System.currentTimeMillis() - this.lastPlace;
            final int sprintingTicks = this.data.getActionProcessor().getSprintingTicks();
            final boolean exempt = this.isExempt(ExemptType.LIQUID, ExemptType.CREATIVE, ExemptType.FLIGHT);
            final boolean invalid = bridging && delay < 300L && sprintingTicks > 5;
            if (invalid && !exempt) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("ticks=" + sprintingTicks + " delay=" + delay);
                }
            }
            else {
                this.decayBuffer();
            }
            this.lastPlace = System.currentTimeMillis();
        }
    }
}
