package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import me.frep.vulcan.spigot.config.Config;
import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import com.google.common.collect.Lists;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.Deque;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'A', complexType = "Limit", description = "Left clicking too quickly.")
public class AutoClickerA extends AbstractCheck
{
    private final Deque<Long> samples;
    private long lastSwing;
    
    public AutoClickerA(final PlayerData data) {
        super(data);
        this.samples = Lists.newLinkedList();
        this.lastSwing = 0L;
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isArmAnimation() && !this.isExempt(ExemptType.AUTOCLICKER_NON_DIG)) {
            final long now = System.currentTimeMillis();
            final long delay = now - this.lastSwing;
            this.samples.add(delay);
            if (this.samples.size() == 20) {
                final double cps = MathUtil.getCps(this.samples);
                if (cps > Config.AUTOCLICKER_A_MAX_CPS && cps > 5.0) {
                    this.fail("cps=" + cps);
                }
                this.samples.clear();
            }
            this.lastSwing = now;
        }
    }
}
