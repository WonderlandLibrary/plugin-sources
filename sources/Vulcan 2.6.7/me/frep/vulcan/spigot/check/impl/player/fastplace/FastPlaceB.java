package me.frep.vulcan.spigot.check.impl.player.fastplace;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Fast Place", type = 'B', complexType = "Delay", description = "Placing blocks too quickly.", experimental = true)
public class FastPlaceB extends AbstractCheck
{
    private int blocksPlaced;
    
    public FastPlaceB(final PlayerData data) {
        super(data);
        this.blocksPlaced = 0;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isBlockPlace()) {
            if (System.currentTimeMillis() - this.data.getConnectionProcessor().getLastFlying() > 55L) {
                this.blocksPlaced = 0;
            }
            ++this.blocksPlaced;
        }
        else if (packet.isFlying()) {
            final long flyingDelay = this.data.getConnectionProcessor().getFlyingDelay();
            if (this.blocksPlaced > Config.FASTPLACE_B_MAX_BLOCKS && flyingDelay < 105L) {
                if (this.increaseBuffer() > this.MAX_BUFFER) {
                    this.fail("blocks=" + this.blocksPlaced + " delay=" + flyingDelay);
                }
            }
            else {
                this.decayBuffer();
            }
            this.blocksPlaced = 0;
        }
    }
}
