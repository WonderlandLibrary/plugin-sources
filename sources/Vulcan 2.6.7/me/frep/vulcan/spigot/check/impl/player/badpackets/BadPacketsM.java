package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'M', complexType = "Post BlockPlace", description = "Post BlockPlace packets.")
public class BadPacketsM extends AbstractCheck
{
    private long lastFlying;
    private boolean sent;
    
    public BadPacketsM(final PlayerData data) {
        super(data);
        this.lastFlying = 0L;
        this.sent = false;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            final boolean exempt = this.isExempt(ExemptType.CREATIVE, ExemptType.SPECTATOR, ExemptType.WEB, ExemptType.LIQUID, ExemptType.EMPTIED_BUCKET);
            final boolean bucket = this.data.getActionProcessor().getSinceStupidBucketEmptyTicks() < 50 || this.data.getPlayer().getItemInHand().getType().toString().contains("BUCKET") || (ServerUtil.isHigherThan1_9() && this.data.getPlayer().getInventory().getItemInOffHand().getType().toString().contains("BUCKET"));
            if (this.sent && !exempt && !bucket) {
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
        else if (packet.isBlockPlace()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            if (delay < 10L) {
                this.sent = true;
            }
        }
    }
}
