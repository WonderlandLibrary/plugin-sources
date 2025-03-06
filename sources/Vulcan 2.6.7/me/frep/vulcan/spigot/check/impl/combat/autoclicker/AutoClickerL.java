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

@CheckInfo(name = "Auto Clicker", type = 'L', complexType = "Kurtosis Difference", description = "Similar kurtosis values.")
public class AutoClickerL extends AbstractCheck
{
    private final Deque<Long> samples;
    private double lastKurtosis;
    
    public AutoClickerL(final PlayerData data) {
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
                final double kurtosis = MathUtil.getKurtosis(this.samples) / 1000.0;
                final double difference = Math.abs(kurtosis - this.lastKurtosis);
                if (difference < 8.35) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("difference=" + difference);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.lastKurtosis = kurtosis;
                this.samples.clear();
            }
        }
    }
}
