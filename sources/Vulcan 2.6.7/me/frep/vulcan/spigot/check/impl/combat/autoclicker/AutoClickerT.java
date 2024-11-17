package me.frep.vulcan.spigot.check.impl.combat.autoclicker;

import me.frep.vulcan.spigot.config.Config;
import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.util.type.EvictingList;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Auto Clicker", type = 'T', complexType = "Kurtosis", description = "Too low kurtosis")
public class AutoClickerT extends AbstractCheck
{
    private final EvictingList<Long> samples;
    
    public AutoClickerT(final PlayerData data) {
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
                final double kurtosis = MathUtil.getKurtosis(this.samples) / 1000.0;
                this.data.getClickProcessor().setKurtosis(kurtosis);
                if (kurtosis < Config.AUTOCLICKER_T_MIN_KURTOSIS) {
                    if (this.increaseBuffer() > this.MAX_BUFFER) {
                        this.fail("kurtosis=" + kurtosis);
                    }
                }
                else {
                    this.decayBuffer();
                }
            }
        }
    }
}
