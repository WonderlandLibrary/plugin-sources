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

@CheckInfo(name = "Auto Clicker", type = 'O', complexType = "Spikes", description = "Impossible spike in CPS.")
public class AutoClickerO extends AbstractCheck
{
    private final Deque<Long> samples;
    private double lastCps;
    
    public AutoClickerO(final PlayerData data) {
        super(data);
        this.samples = Lists.newLinkedList();
        this.lastCps = -1.0;
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
            if (this.samples.size() == 10) {
                final double cps = MathUtil.getCps(this.samples);
                if (this.lastCps > 0.0) {
                    final double difference = Math.abs(cps - this.lastCps);
                    final double average = (this.lastCps + cps) / 2.0;
                    final boolean invalid = average > 9.25 && difference > 2.8;
                    if (invalid) {
                        if (this.increaseBuffer() > this.MAX_BUFFER) {
                            this.fail("average=" + average + " difference=" + difference);
                        }
                    }
                    else {
                        this.decayBuffer();
                    }
                }
                this.lastCps = cps;
                this.samples.clear();
            }
        }
    }
}
