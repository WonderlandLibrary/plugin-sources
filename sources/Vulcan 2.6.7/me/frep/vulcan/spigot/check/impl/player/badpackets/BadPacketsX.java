package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'X', complexType = "Post ArmAnimation", experimental = false, description = "Post ArmAnimation packets.")
public class BadPacketsX extends AbstractCheck
{
    private long lastFlying;
    private boolean sent;
    
    public BadPacketsX(final PlayerData data) {
        super(data);
        this.lastFlying = 0L;
        this.sent = false;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            if (this.isExempt(ExemptType.CLIENT_VERSION, ExemptType.SERVER_VERSION)) {
                return;
            }
            if (this.sent) {
                if (delay > 40L && delay < 100L) {
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
        else if (packet.isArmAnimation()) {
            final long delay = System.currentTimeMillis() - this.lastFlying;
            if (delay < 10L) {
                this.sent = true;
            }
        }
    }
}
