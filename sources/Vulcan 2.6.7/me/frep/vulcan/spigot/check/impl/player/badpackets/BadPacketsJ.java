package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'J', complexType = "Held Item Slot", description = "Sent HeldItemSlot while placing.")
public class BadPacketsJ extends AbstractCheck
{
    public BadPacketsJ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isHeldItemSlot()) {
            final boolean placing = this.data.getActionProcessor().isPlacing();
            final boolean exempt = this.isExempt(ExemptType.CLIENT_VERSION, ExemptType.SERVER_VERSION, ExemptType.CREATIVE, ExemptType.SPECTATOR);
            if (!exempt) {
                if (placing) {
                    this.fail();
                }
            }
        }
    }
}
