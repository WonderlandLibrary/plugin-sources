package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'I', complexType = "Entity Action", description = "Sent EntityAction while attacking.")
public class BadPacketsI extends AbstractCheck
{
    public BadPacketsI(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isUseEntity()) {
            final boolean invalid = this.data.getActionProcessor().isSendingAction();
            final boolean exempt = this.isExempt(ExemptType.SERVER_VERSION, ExemptType.CLIENT_VERSION, ExemptType.FAST, ExemptType.SPECTATOR, ExemptType.DEATH);
            if (invalid) {
                if (!exempt) {
                    this.fail();
                }
            }
        }
    }
}
