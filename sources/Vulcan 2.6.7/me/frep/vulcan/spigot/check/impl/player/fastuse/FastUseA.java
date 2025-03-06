package me.frep.vulcan.spigot.check.impl.player.fastuse;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Fast Use", type = 'A', complexType = "Delay", description = "Using items too quickly.")
public class FastUseA extends AbstractCheck
{
    private long startEat;
    
    public FastUseA(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlace()) {
            this.startEat = System.currentTimeMillis();
        }
        else if (packet.isItemConsumeEvent()) {
            final PlayerItemConsumeEvent event = (PlayerItemConsumeEvent)packet.getRawPacket().getRawNMSPacket();
            if (BlockUtil.isKelp(event.getItem().getType())) {
                return;
            }
            final long delay = System.currentTimeMillis() - this.startEat;
            final long flyingDelay = this.data.getConnectionProcessor().getFlyingDelay();
            final boolean invalid = delay < 1000L && flyingDelay < 10L;
            final boolean exempt = this.isExempt(ExemptType.DROPPED_ITEM, ExemptType.PICKED_UP_ITEM);
            if (invalid && !exempt) {
                this.fail("delay=" + delay + " item=" + this.data.getPlayer().getItemInHand().getType() + " delay=" + flyingDelay);
            }
        }
    }
}
