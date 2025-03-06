package me.frep.vulcan.spigot.check.data;

import java.util.List;
import me.frep.vulcan.api.check.ICheckData;

public class CheckData implements ICheckData
{
    private boolean enabled;
    private boolean punishable;
    private boolean broadcastPunishment;
    private boolean hotbarShuffle;
    private boolean randomRotation;
    private int maxViolations;
    private int alertInterval;
    private int minimumVlToNotify;
    private int maximumPing;
    private int randomRotationMinimumVl;
    private int hotbarShuffleMinimumVl;
    private int randomRotationInterval;
    private int hotbarShuffleInterval;
    private double minimumTps;
    private double maxBuffer;
    private double bufferDecay;
    private double bufferMultiple;
    private List<String> punishmentCommands;
    private String checkName;
    
    public CheckData(final String checkName) {
        this.checkName = checkName;
    }
    
    @Override
    public String toString() {
        return "{checkName=" + this.checkName + " enabled=" + this.enabled + " punishable=" + this.punishable + " broadcastPunishment=" + this.broadcastPunishment + "}";
    }
    
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    
    @Override
    public boolean isPunishable() {
        return this.punishable;
    }
    
    @Override
    public boolean isBroadcastPunishment() {
        return this.broadcastPunishment;
    }
    
    @Override
    public boolean isHotbarShuffle() {
        return this.hotbarShuffle;
    }
    
    @Override
    public boolean isRandomRotation() {
        return this.randomRotation;
    }
    
    @Override
    public int getMaxViolations() {
        return this.maxViolations;
    }
    
    @Override
    public int getAlertInterval() {
        return this.alertInterval;
    }
    
    @Override
    public int getMinimumVlToNotify() {
        return this.minimumVlToNotify;
    }
    
    @Override
    public int getMaxPing() {
        return this.maximumPing;
    }
    
    @Override
    public int getMinimumVlToRandomlyRotate() {
        return this.randomRotationMinimumVl;
    }
    
    @Override
    public int getMinimumVlToShuffleHotbar() {
        return this.hotbarShuffleMinimumVl;
    }
    
    @Override
    public double getMinimumTps() {
        return this.minimumTps;
    }
    
    @Override
    public double getMaxBuffer() {
        return this.maxBuffer;
    }
    
    @Override
    public double getBufferDecay() {
        return this.bufferDecay;
    }
    
    @Override
    public double getBufferMultiple() {
        return this.bufferMultiple;
    }
    
    @Override
    public List<String> getPunishmentCommands() {
        return this.punishmentCommands;
    }
    
    @Override
    public int getRandomRotationInterval() {
        return this.randomRotationInterval;
    }
    
    public int getMaximumPing() {
        return this.maximumPing;
    }
    
    public int getRandomRotationMinimumVl() {
        return this.randomRotationMinimumVl;
    }
    
    public int getHotbarShuffleMinimumVl() {
        return this.hotbarShuffleMinimumVl;
    }
    
    public int getHotbarShuffleInterval() {
        return this.hotbarShuffleInterval;
    }
    
    public String getCheckName() {
        return this.checkName;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void setPunishable(final boolean punishable) {
        this.punishable = punishable;
    }
    
    public void setBroadcastPunishment(final boolean broadcastPunishment) {
        this.broadcastPunishment = broadcastPunishment;
    }
    
    public void setHotbarShuffle(final boolean hotbarShuffle) {
        this.hotbarShuffle = hotbarShuffle;
    }
    
    public void setRandomRotation(final boolean randomRotation) {
        this.randomRotation = randomRotation;
    }
    
    public void setMaxViolations(final int maxViolations) {
        this.maxViolations = maxViolations;
    }
    
    public void setAlertInterval(final int alertInterval) {
        this.alertInterval = alertInterval;
    }
    
    public void setMinimumVlToNotify(final int minimumVlToNotify) {
        this.minimumVlToNotify = minimumVlToNotify;
    }
    
    public void setMaximumPing(final int maximumPing) {
        this.maximumPing = maximumPing;
    }
    
    public void setRandomRotationMinimumVl(final int randomRotationMinimumVl) {
        this.randomRotationMinimumVl = randomRotationMinimumVl;
    }
    
    public void setHotbarShuffleMinimumVl(final int hotbarShuffleMinimumVl) {
        this.hotbarShuffleMinimumVl = hotbarShuffleMinimumVl;
    }
    
    public void setRandomRotationInterval(final int randomRotationInterval) {
        this.randomRotationInterval = randomRotationInterval;
    }
    
    public void setHotbarShuffleInterval(final int hotbarShuffleInterval) {
        this.hotbarShuffleInterval = hotbarShuffleInterval;
    }
    
    public void setMinimumTps(final double minimumTps) {
        this.minimumTps = minimumTps;
    }
    
    public void setMaxBuffer(final double maxBuffer) {
        this.maxBuffer = maxBuffer;
    }
    
    public void setBufferDecay(final double bufferDecay) {
        this.bufferDecay = bufferDecay;
    }
    
    public void setBufferMultiple(final double bufferMultiple) {
        this.bufferMultiple = bufferMultiple;
    }
    
    public void setPunishmentCommands(final List<String> punishmentCommands) {
        this.punishmentCommands = punishmentCommands;
    }
    
    public void setCheckName(final String checkName) {
        this.checkName = checkName;
    }
}
