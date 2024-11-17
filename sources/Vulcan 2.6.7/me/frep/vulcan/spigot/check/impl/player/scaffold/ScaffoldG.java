package me.frep.vulcan.spigot.check.impl.player.scaffold;

import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.BlockUtil;
import org.bukkit.event.block.BlockPlaceEvent;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Scaffold", type = 'G', complexType = "Speed", experimental = false, description = "Bridging too quickly.")
public class ScaffoldG extends AbstractCheck
{
    private long lastPlace;
    
    public ScaffoldG(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlaceEvent()) {
            final BlockPlaceEvent event = (BlockPlaceEvent)packet.getRawPacket().getRawNMSPacket();
            if (event.isCancelled() || BlockUtil.isSlab(event.getBlock().getType()) || this.isExempt(ExemptType.SLAB)) {
                return;
            }
            final boolean bridging = PlayerUtil.isBridging(event);
            if (bridging) {
                final long delay = System.currentTimeMillis() - this.lastPlace;
                final float angle = this.data.getRotationProcessor().getMovementAngle();
                final double acceleration = this.data.getPositionProcessor().getAcceleration();
                final boolean exempt = BlockUtil.isScaffolding(event.getBlock());
                final boolean invalid = acceleration < 0.001 && acceleration > 0.0 && delay < 300L && angle < 2.0f;
                if (invalid && !exempt) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("angle=" + angle + " delay=" + delay + " acceleration=" + acceleration);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.lastPlace = System.currentTimeMillis();
            }
        }
    }
}
