package me.frep.vulcan.spigot.check.impl.player.improbable;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Improbable", type = 'C', complexType = "Player", description = "Too many combined player violations.")
public class ImprobableC extends AbstractCheck
{
    private long lastFlag;
    
    public ImprobableC(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final int playerViolations = this.data.getPlayerViolations();
            if (System.currentTimeMillis() - this.lastFlag >= 60000L) {
                if (playerViolations > Config.IMPROBABLE_C_MAX_VIOLATIONS) {
                    this.fail("playerViolations=" + playerViolations);
                    this.lastFlag = System.currentTimeMillis();
                }
            }
        }
    }
}
