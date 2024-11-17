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

@CheckInfo(name = "Auto Clicker", type = 'P', complexType = "Identical", description = "Identical statistical values.")
public class AutoClickerP extends AbstractCheck
{
    private final Deque<Long> samples;
    private double lastDeviation;
    private double lastSkewness;
    private double lastKurtosis;
    
    public AutoClickerP(final PlayerData data) {
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
            if (this.samples.size() == 15) {
                final double deviation = MathUtil.getStandardDeviation(this.samples);
                final double skewness = MathUtil.getSkewness(this.samples);
                final double kurtosis = MathUtil.getKurtosis(this.samples);
                if (deviation == this.lastDeviation && skewness == this.lastSkewness && kurtosis == this.lastKurtosis) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail();
                    }
                }
                else {
                    this.resetBuffer();
                }
                this.lastDeviation = deviation;
                this.lastSkewness = skewness;
                this.lastKurtosis = kurtosis;
                this.samples.clear();
            }
        }
    }
}
