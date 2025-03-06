package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.util.type.EvictingList;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'S', complexType = "Distinct", description = "Checks for amount of distinct delays.")
public class AutoClickerS extends AbstractCheck
{
    private final EvictingList<Long> samples;
    
    public AutoClickerS(final PlayerData data) {
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
                final int distinct = MathUtil.getDistinct(this.samples);
                if (distinct < 6) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("distinct=" + distinct);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
