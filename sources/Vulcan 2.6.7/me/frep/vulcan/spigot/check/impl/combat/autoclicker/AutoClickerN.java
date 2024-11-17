package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.util.type.EvictingList;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'N', complexType = "Deviation Difference", description = "Low deviation difference.")
public class AutoClickerN extends AbstractCheck
{
    private final EvictingList<Long> samples;
    private double lastDeviation;
    
    public AutoClickerN(final PlayerData data) {
        super(data);
        this.samples = new EvictingList<Long>(25);
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
            if (this.samples.isFull()) {
                final double deviation = MathUtil.getStandardDeviation(this.samples);
                final double difference = Math.abs(deviation - this.lastDeviation);
                final double average = Math.abs(deviation + this.lastDeviation) / 2.0;
                if (difference < 0.25 && average < 150.0) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("difference=" + difference + " average=" + average);
                    }
                }
                else {
                    this.decayBuffer();
                }
                this.lastDeviation = deviation;
            }
        }
    }
}
