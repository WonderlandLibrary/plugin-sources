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

@CheckInfo(name = "Auto Clicker", type = 'G', complexType = "Outliers", description = "Too low outliers.")
public class AutoClickerG extends AbstractCheck
{
    private final Deque<Long> samples;
    
    public AutoClickerG(final PlayerData data) {
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
                final int outliers = (int)this.samples.stream().filter(l -> l > 150L).count();
                final double average = MathUtil.getAverage(this.samples);
                if (outliers < 3) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("outliers=" + outliers + " average=" + average);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.samples.clear();
            }
        }
    }
}
