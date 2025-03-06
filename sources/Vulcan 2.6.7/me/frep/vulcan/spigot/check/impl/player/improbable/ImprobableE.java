package me.frep.vulcan.spigot.check.impl.player.improbable;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Improbable", type = 'E', complexType = "Total", description = "Too many combined violations.")
public class ImprobableE extends AbstractCheck
{
    private long lastFlag;
    
    public ImprobableE(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isFlying()) {
            final int totalViolations = this.data.getTotalViolations();
            if (System.currentTimeMillis() - this.lastFlag >= 60000L) {
                if (totalViolations > Config.IMPROBABLE_E_MAX_VIOLATIONS) {
                    this.fail("totalViolations=" + totalViolations);
                    this.lastFlag = System.currentTimeMillis();
                }
            }
        }
    }
}
