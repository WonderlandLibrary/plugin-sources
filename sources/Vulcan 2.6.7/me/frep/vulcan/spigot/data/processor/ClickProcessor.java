package me.frep.vulcan.spigot.data.processor;

import java.util.Collection;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import me.frep.vulcan.spigot.util.type.EvictingList;
import me.frep.vulcan.spigot.data.PlayerData;

public class ClickProcessor
{
    private final PlayerData data;
    private long lastSwing;
    private long delay;
    private long lastInteractEntity;
    private final EvictingList<Long> samples;
    private double cps;
    private double kurtosis;
    
    public ClickProcessor(final PlayerData data) {
        this.lastSwing = -1L;
        this.lastInteractEntity = System.currentTimeMillis();
        this.samples = new EvictingList<Long>(20);
        this.data = data;
    }
    
    public void handleArmAnimation() {
        if (!this.data.getExemptProcessor().isExempt(ExemptType.AUTOCLICKER_NON_DIG)) {
            final long now = System.currentTimeMillis();
            if (this.lastSwing > 0L) {
                this.delay = now - this.lastSwing;
                this.samples.add(this.delay);
                if (this.samples.isFull()) {
                    this.cps = MathUtil.getCps(this.samples);
                }
            }
            this.lastSwing = now;
        }
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    public long getLastSwing() {
        return this.lastSwing;
    }
    
    public long getDelay() {
        return this.delay;
    }
    
    public long getLastInteractEntity() {
        return this.lastInteractEntity;
    }
    
    public EvictingList<Long> getSamples() {
        return this.samples;
    }
    
    public double getCps() {
        return this.cps;
    }
    
    public double getKurtosis() {
        return this.kurtosis;
    }
    
    public void setLastSwing(final long lastSwing) {
        this.lastSwing = lastSwing;
    }
    
    public void setDelay(final long delay) {
        this.delay = delay;
    }
    
    public void setLastInteractEntity(final long lastInteractEntity) {
        this.lastInteractEntity = lastInteractEntity;
    }
    
    public void setCps(final double cps) {
        this.cps = cps;
    }
    
    public void setKurtosis(final double kurtosis) {
        this.kurtosis = kurtosis;
    }
}
