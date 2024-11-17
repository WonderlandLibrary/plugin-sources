package me.frep.vulcan.spigot.check;

import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import java.lang.annotation.Annotation;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.exempt.type.ExemptType;
import java.util.Objects;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.api.check.Check;

public abstract class AbstractCheck implements Check
{
    protected final PlayerData data;
    private int vl;
    private double buffer;
    public final String className;
    public final double MAX_BUFFER;
    public final double BUFFER_DECAY;
    public final double BUFFER_MULTIPLE_ON_FLAG;
    
    public AbstractCheck(final PlayerData data) {
        this.className = this.getClass().getSimpleName();
        this.MAX_BUFFER = Config.MAX_BUFFERS.get(this.className);
        this.BUFFER_DECAY = Config.BUFFER_DECAYS.get(this.className);
        this.BUFFER_MULTIPLE_ON_FLAG = Config.BUFFER_MULTIPLES.get(this.className);
        this.data = data;
    }
    
    public abstract void handle(final Packet p0);
    
    public void fail(final Object info) {
        this.shuffleHotbar();
        this.rotateRandomly();
        this.multiplyBuffer(this.BUFFER_MULTIPLE_ON_FLAG);
        Vulcan.INSTANCE.getAlertExecutor().execute(() -> Vulcan.INSTANCE.getAlertManager().handleAlert(this, this.data, Objects.toString(info)));
    }
    
    public void fail() {
        this.shuffleHotbar();
        this.rotateRandomly();
        this.multiplyBuffer(this.BUFFER_MULTIPLE_ON_FLAG);
        Vulcan.INSTANCE.getAlertExecutor().execute(() -> Vulcan.INSTANCE.getAlertManager().handleAlert(this, this.data, ""));
    }
    
    public void punish() {
        Vulcan.INSTANCE.getAlertExecutor().execute(() -> Vulcan.INSTANCE.getPunishmentManager().handlePunishment(this, this.data));
    }
    
    protected boolean isExempt(final ExemptType... exemptTypes) {
        return this.data.getExemptProcessor().isExempt(exemptTypes);
    }
    
    public int ticks() {
        return Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    public double increaseBuffer() {
        Vulcan.INSTANCE.getAlertManager().handleVerbose(this, this.data);
        return this.buffer = Math.min(10000.0, this.buffer + 1.0);
    }
    
    public double decreaseBufferBy(final double amount) {
        return this.buffer = Math.max(0.0, this.buffer - amount);
    }
    
    public double increaseBufferBy(final double amount) {
        return this.buffer = Math.min(10000.0, this.buffer + amount);
    }
    
    public void resetBuffer() {
        this.buffer = 0.0;
    }
    
    public void multiplyBuffer(final double multiplier) {
        this.buffer *= multiplier;
    }
    
    public void decayBuffer() {
        this.decreaseBufferBy(this.BUFFER_DECAY);
    }
    
    public int hitTicks() {
        return this.data.getCombatProcessor().getHitTicks();
    }
    
    public boolean attacking(final int ticks) {
        return this.data.getCombatProcessor().getHitTicks() < ticks && this.data.getCombatProcessor().getTicksSinceAttack() < ticks * 6;
    }
    
    public CheckInfo getCheckInfo() {
        if (this.getClass().isAnnotationPresent(CheckInfo.class)) {
            return this.getClass().getAnnotation(CheckInfo.class);
        }
        ServerUtil.logError("CheckInfo annotation hasn't been added to the class " + this.className + ".");
        return null;
    }
    
    public void debug(final Object object) {
        ServerUtil.debug(object);
    }
    
    @Override
    public String getCategory() {
        String category = "";
        if (this.getClass().getName().contains("combat")) {
            category = "combat";
        }
        else if (this.getClass().getName().contains("movement")) {
            category = "movement";
        }
        else if (this.getClass().getName().contains("player")) {
            category = "player";
        }
        return category;
    }
    
    public boolean teleporting() {
        return this.data.getActionProcessor().isTeleporting();
    }
    
    public boolean fuckedPosition() {
        return this.data.getPositionProcessor().isFuckedPosition();
    }
    
    @Override
    public String getName() {
        return this.getCheckInfo().name().toLowerCase().replaceAll(" ", "");
    }
    
    @Override
    public char getType() {
        return Character.toLowerCase(this.getCheckInfo().type());
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
    
    @Override
    public int getVl() {
        return this.vl;
    }
    
    @Override
    public int getMaxVl() {
        return Config.MAX_VIOLATIONS.get(this.className);
    }
    
    @Override
    public int getMinimumVlToNotify() {
        return Config.MINIMUM_VL_TO_NOTIFY.get(this.className);
    }
    
    @Override
    public double getMaxBuffer() {
        return this.MAX_BUFFER;
    }
    
    @Override
    public String getDescription() {
        return this.getCheckInfo().description();
    }
    
    @Override
    public String getComplexType() {
        return this.getCheckInfo().complexType();
    }
    
    @Override
    public double getBufferDecay() {
        return this.BUFFER_DECAY;
    }
    
    @Override
    public int getAlertInterval() {
        return Config.ALERT_INTERVAL.get(this.className);
    }
    
    @Override
    public double getBufferMultiple() {
        return this.BUFFER_MULTIPLE_ON_FLAG;
    }
    
    @Override
    public boolean isExperimental() {
        return this.getCheckInfo().experimental();
    }
    
    @Override
    public boolean isPunishable() {
        return Config.PUNISHABLE.get(this.className);
    }
    
    @Override
    public String getDisplayName() {
        return this.getCheckInfo().name();
    }
    
    @Override
    public char getDisplayType() {
        return this.getCheckInfo().type();
    }
    
    public void shuffleHotbar() {
        final boolean shuffleHotbar = Config.HOTBAR_SHUFFLE.get(this.className);
        final int minimumViolationsToShuffleHotbar = Config.HOTBAR_SHUFFLE_MINIMUM.get(this.className);
        final int hotbarShuffleInterval = Config.HOTBAR_SHUFFLE_EVERY.get(this.className);
        if (shuffleHotbar && this.vl > minimumViolationsToShuffleHotbar && this.vl % hotbarShuffleInterval == 0) {
            PlayerUtil.shuffleHotbar(this.data.getPlayer());
        }
    }
    
    public void rotateRandomly() {
        final boolean randomRotation = Config.RANDOM_ROTATION.get(this.className);
        final int minimumViolationsToRotateRandomly = Config.RANDOM_ROTATION_MINIMUM.get(this.className);
        final int randomRotationInterval = Config.RANDOM_ROTATION_EVERY.get(this.className);
        if (randomRotation && this.vl > minimumViolationsToRotateRandomly && this.vl % randomRotationInterval == 0) {
            PlayerUtil.rotateRandomly(this.data.getPlayer());
        }
    }
    
    public boolean elapsed(final int from, final int required) {
        return Vulcan.INSTANCE.getTickManager().getTicks() - from > required;
    }
    
    public PlayerData getData() {
        return this.data;
    }
    
    @Override
    public double getBuffer() {
        return this.buffer;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public double getMAX_BUFFER() {
        return this.MAX_BUFFER;
    }
    
    public double getBUFFER_DECAY() {
        return this.BUFFER_DECAY;
    }
    
    public double getBUFFER_MULTIPLE_ON_FLAG() {
        return this.BUFFER_MULTIPLE_ON_FLAG;
    }
    
    @Override
    public void setVl(final int vl) {
        this.vl = vl;
    }
    
    @Override
    public void setBuffer(final double buffer) {
        this.buffer = buffer;
    }
}
