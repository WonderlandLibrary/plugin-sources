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

@CheckInfo(name = "Auto Clicker", type = 'C', complexType = "Rounded", description = "Rounded CPS values.")
public class AutoClickerC extends AbstractCheck
{
    private final Deque<Long> samples;
    
    public AutoClickerC(final PlayerData data) {
        super(data);
        this.samples =  Lists.newLinkedList();
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
            if (this.samples.size() == 20) {
                final double cps = MathUtil.getCps(this.samples);
                final double difference = Math.abs(Math.round(cps) - cps);
                if (difference < 0.08) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("difference=" + difference);
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
