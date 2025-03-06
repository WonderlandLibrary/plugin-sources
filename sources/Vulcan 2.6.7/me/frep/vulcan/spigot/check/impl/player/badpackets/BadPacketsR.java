package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'R', complexType = "Post HeldItemSlot", experimental = false, description = "Post HeldItemSlot packets.")
public class BadPacketsR extends AbstractCheck
{
    private long lastFlying;
    private boolean sent;
    
    public BadPacketsR(final PlayerData data) {
        super(data);
        this.lastFlying = 0L;
        this.sent = false;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            final boolean exempt = this.isExempt(ExemptType.CREATIVE, ExemptType.SPECTATOR);
            if (this.sent && !exempt) {
                if (delay > 40L && delay < 100L) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("delay=" + delay);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.sent = false;
            }
            this.lastFlying = System.currentTimeMillis();
        }
        else if (packet.isHeldItemSlot()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            if (delay < 10L) {
                this.sent = true;
            }
        }
    }
}
