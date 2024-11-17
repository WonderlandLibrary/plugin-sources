package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import java.util.Collections;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import com.google.common.collect.Lists;
import me.frep.vulcan.spigot.data.PlayerData;
import java.util.List;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'J', complexType = "Range", description = "Impossible consistency.")
public class AutoClickerJ extends AbstractCheck
{
    private final List<Long> samples;
    
    public AutoClickerJ(final PlayerData data) {
        super(data);
        this.samples = Lists.newArrayList();
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
                Collections.sort(this.samples);
                final long range = this.samples.get(this.samples.size() - 1) - this.samples.get(0);
                if (range < 50L) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("range=" + range);
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
