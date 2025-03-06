package me.frep.vulcan.spigot.check.impl.player.improbable;

import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Improbable", type = 'D', complexType = "Auto Clicker", description = "Too many combined autoclicker violations.")
public class ImprobableD extends AbstractCheck
{
    private long lastFlag;
    
    public ImprobableD(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isArmAnimation() && !this.isExempt(ExemptType.AUTOCLICKER)) {
            final int autoClickerViolations = this.data.getAutoClickerViolations();
            if (System.currentTimeMillis() - this.lastFlag >= 60000L) {
                if (autoClickerViolations > Config.IMPROBABLE_D_MAX_VIOLATIONS) {
                    this.fail("autoClickerViolations=" + autoClickerViolations);
                    this.lastFlag = System.currentTimeMillis();
                }
            }
        }
    }
}
