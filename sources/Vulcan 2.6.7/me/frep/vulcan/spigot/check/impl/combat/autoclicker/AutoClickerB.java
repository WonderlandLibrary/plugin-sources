package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import com.google.common.collect.Lists;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.Deque;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'B', complexType = "Deviation", description = "Too low standard deviation.")
public class AutoClickerB extends AbstractCheck
{
    private final Deque<Long> samples;
    
    public AutoClickerB(final PlayerData data) {
        super(data);
        this.samples = Lists.newLinkedList();
    }
    
    @Override
    public void handle(final Packet packet) {
        if (packet.isArmAnimation() && !this.isExempt(ExemptType.AUTOCLICKER)) {
            final long delay = this.data.getClickProcessor().getDelay();
            if (delay > 5000L) {
                this.samples.clear();
                return;
            }
            this.samples.add(delay);
            if (this.samples.size() == 50) {
                final double deviation = MathUtil.getStandardDeviation(this.samples);
                final double average = MathUtil.getAverage(this.samples);
                if (deviation < 167.0) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("deviation=" + deviation + " average=" + average);
                    }
                    else {
                        this.decayBuffer();
                    }
                }
                this.samples.clear();
            }
        }
    }
}
