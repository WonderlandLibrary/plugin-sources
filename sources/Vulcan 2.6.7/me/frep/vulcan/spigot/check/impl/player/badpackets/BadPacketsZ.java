package me.frep.vulcan.spigot.check.impl.player.badpackets;

import org.bukkit.GameMode;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'Z', complexType = "Spectate", description = "Invalid Spectate Packets.")
public class BadPacketsZ extends AbstractCheck
{
    public BadPacketsZ(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isSpectate()) {
            if (ServerUtil.isLowerThan1_8()) {
                return;
            }
            final boolean invalid = this.data.getPlayer().getGameMode() != GameMode.SPECTATOR && this.data.getActionProcessor().getGameMode() != GameMode.SPECTATOR;
            if (invalid) {
                this.fail();
            }
        }
    }
}
