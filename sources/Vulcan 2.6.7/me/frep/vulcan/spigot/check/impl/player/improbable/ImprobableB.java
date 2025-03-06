package me.frep.vulcan.spigot.check.impl.player.improbable;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Improbable", type = 'B', complexType = "Movement", experimental = false, description = "Too many combined movement violations.")
public class ImprobableB extends AbstractCheck
{
    private long lastFlag;
    
    public ImprobableB(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isPosition()) {
            final int movementViolations = this.data.getMovementViolations();
            if (System.currentTimeMillis() - this.lastFlag >= 60000L) {
                if (movementViolations > Config.IMPROBABLE_B_MAX_VIOLATIONS) {
                    this.fail("movementViolations=" + movementViolations);
                    this.lastFlag = System.currentTimeMillis();
                }
            }
        }
    }
}
