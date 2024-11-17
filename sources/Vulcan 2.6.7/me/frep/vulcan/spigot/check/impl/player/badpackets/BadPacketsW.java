package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'W', complexType = "Post WindowClick", experimental = false, description = "Post WindowClick packets.")
public class BadPacketsW extends AbstractCheck
{
    private long lastFlying;
    private boolean sent;
    
    public BadPacketsW(final PlayerData data) {
        super(data);
        this.lastFlying = 0L;
        this.sent = false;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            final double deltaXZ = this.data.getPositionProcessor().getDeltaXZ();
            if (this.sent) {
                if (delay > 40L && delay < 100L && deltaXZ > 0.1) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail();
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.sent = false;
            }
            this.lastFlying = System.currentTimeMillis();
        }
        else if (packet.isWindowClick()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            if (delay < 10L) {
                this.sent = true;
            }
        }
    }
}
