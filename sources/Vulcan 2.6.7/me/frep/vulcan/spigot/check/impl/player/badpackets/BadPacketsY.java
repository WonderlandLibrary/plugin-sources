package me.frep.vulcan.spigot.check.impl.player.badpackets;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Bad Packets", type = 'Y', complexType = "Invalid Position", description = "NaN X/Y/Z values.")
public class BadPacketsY extends AbstractCheck
{
    public BadPacketsY(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final double x = this.data.getPositionProcessor().getX();
            final double y = this.data.getPositionProcessor().getY();
            final double z = this.data.getPositionProcessor().getZ();
            if (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z) || x >= 2.147483647E9 || y >= 2.147483647E9 || z >= 2.147483647E9) {
                this.fail("x=" + x + " y=" + y + " z=" + z);
            }
        }
    }
}
