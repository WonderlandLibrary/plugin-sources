package me.frep.vulcan.spigot.data;

import me.frep.vulcan.spigot.exempt.type.ExemptType;
import java.util.ArrayList;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.data.processor.ConnectionProcessor;
import me.frep.vulcan.spigot.data.processor.VelocityProcessor;
import me.frep.vulcan.spigot.data.processor.RotationProcessor;
import me.frep.vulcan.spigot.data.processor.PositionProcessor;
import me.frep.vulcan.spigot.data.processor.ClickProcessor;
import me.frep.vulcan.spigot.data.processor.ActionProcessor;
import me.frep.vulcan.spigot.data.processor.CombatProcessor;
import me.frep.vulcan.spigot.exempt.ExemptProcessor;
import me.frep.vulcan.spigot.check.AbstractCheck;
import java.util.List;
import io.github.retrooper.packetevents.packetwrappers.play.in.helditemslot.WrappedPacketInHeldItemSlot;
import io.github.retrooper.packetevents.packetwrappers.play.in.entityaction.WrappedPacketInEntityAction;
import io.github.retrooper.packetevents.packetwrappers.play.in.blockdig.WrappedPacketInBlockDig;
import io.github.retrooper.packetevents.packetwrappers.play.in.useentity.WrappedPacketInUseEntity;
import io.github.retrooper.packetevents.packetwrappers.play.in.flying.WrappedPacketInFlying;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import org.bukkit.entity.Player;
import me.frep.vulcan.api.data.IPlayerData;

public class PlayerData implements IPlayerData
{
    private final Player player;
    private ClientVersion clientVersion;
    private String clientBrand;
    private boolean hasSentClientBrand;
    private int totalViolations;
    private int combatViolations;
    private int movementViolations;
    private int playerViolations;
    private int autoClickerViolations;
    private int scaffoldViolations;
    private int timerViolations;
    private final long joinTime;
    private final int joinTicks;
    private long lastClientBrandAlert;
    private long lastPunishment;
    private WrappedPacketInFlying flyingWrapper;
    private WrappedPacketInUseEntity useEntityWrapper;
    private WrappedPacketInBlockDig blockDigWrapper;
    private WrappedPacketInEntityAction entityActionWrapper;
    private WrappedPacketInHeldItemSlot heldItemSlotWrapper;
    private int pendingTransactions;
    private final List<AbstractCheck> checks;
    private final ExemptProcessor exemptProcessor;
    private final CombatProcessor combatProcessor;
    private final ActionProcessor actionProcessor;
    private final ClickProcessor clickProcessor;
    private final PositionProcessor positionProcessor;
    private final RotationProcessor rotationProcessor;
    private final VelocityProcessor velocityProcessor;
    private final ConnectionProcessor connectionProcessor;
    
    public PlayerData(final Player player) {
        this.clientVersion = ClientVersion.UNRESOLVED;
        this.clientBrand = "Unresolved";
        this.hasSentClientBrand = false;
        this.joinTime = System.currentTimeMillis();
        this.joinTicks = Vulcan.INSTANCE.getTickManager().getTicks();
        this.pendingTransactions = 0;
        this.checks = CheckManager.loadChecks(this);
        this.exemptProcessor = new ExemptProcessor(this);
        this.combatProcessor = new CombatProcessor(this);
        this.actionProcessor = new ActionProcessor(this);
        this.clickProcessor = new ClickProcessor(this);
        this.positionProcessor = new PositionProcessor(this);
        this.rotationProcessor = new RotationProcessor(this);
        this.velocityProcessor = new VelocityProcessor(this);
        this.connectionProcessor = new ConnectionProcessor(this);
        this.player = player;
    }
    
    public void sendTransaction(final short id) {
        PlayerUtil.sendTransaction(this.player, id);
    }
    
    public void sendPing(final int id) {
        PlayerUtil.sendPing(this.player, id);
    }
    
    public boolean is1_17() {
        return PlayerUtil.is1_17(this);
    }
    
    public boolean is1_13() {
        return PlayerUtil.is1_13(this);
    }
    
    public boolean is1_9() {
        return PlayerUtil.is1_9(this);
    }
    
    public List<String> getExemptions() {
        final List<String> exempts = new ArrayList<String>();
        for (final ExemptType exemptType : ExemptType.values()) {
            if (this.exemptProcessor.isExempt(exemptType)) {
                exempts.add(exemptType.toString());
            }
        }
        return exempts;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public ClientVersion getClientVersion() {
        return this.clientVersion;
    }
    
    @Override
    public String getClientBrand() {
        return this.clientBrand;
    }
    
    public boolean isHasSentClientBrand() {
        return this.hasSentClientBrand;
    }
    
    @Override
    public int getTotalViolations() {
        return this.totalViolations;
    }
    
    @Override
    public int getCombatViolations() {
        return this.combatViolations;
    }
    
    @Override
    public int getMovementViolations() {
        return this.movementViolations;
    }
    
    @Override
    public int getPlayerViolations() {
        return this.playerViolations;
    }
    
    @Override
    public int getAutoClickerViolations() {
        return this.autoClickerViolations;
    }
    
    @Override
    public int getScaffoldViolations() {
        return this.scaffoldViolations;
    }
    
    @Override
    public int getTimerViolations() {
        return this.timerViolations;
    }
    
    @Override
    public long getJoinTime() {
        return this.joinTime;
    }
    
    @Override
    public int getJoinTicks() {
        return this.joinTicks;
    }
    
    @Override
    public long getLastClientBrandAlert() {
        return this.lastClientBrandAlert;
    }
    
    public long getLastPunishment() {
        return this.lastPunishment;
    }
    
    public WrappedPacketInFlying getFlyingWrapper() {
        return this.flyingWrapper;
    }
    
    public WrappedPacketInUseEntity getUseEntityWrapper() {
        return this.useEntityWrapper;
    }
    
    public WrappedPacketInBlockDig getBlockDigWrapper() {
        return this.blockDigWrapper;
    }
    
    public WrappedPacketInEntityAction getEntityActionWrapper() {
        return this.entityActionWrapper;
    }
    
    public WrappedPacketInHeldItemSlot getHeldItemSlotWrapper() {
        return this.heldItemSlotWrapper;
    }
    
    public int getPendingTransactions() {
        return this.pendingTransactions;
    }
    
    public List<AbstractCheck> getChecks() {
        return this.checks;
    }
    
    public ExemptProcessor getExemptProcessor() {
        return this.exemptProcessor;
    }
    
    public CombatProcessor getCombatProcessor() {
        return this.combatProcessor;
    }
    
    public ActionProcessor getActionProcessor() {
        return this.actionProcessor;
    }
    
    public ClickProcessor getClickProcessor() {
        return this.clickProcessor;
    }
    
    public PositionProcessor getPositionProcessor() {
        return this.positionProcessor;
    }
    
    public RotationProcessor getRotationProcessor() {
        return this.rotationProcessor;
    }
    
    public VelocityProcessor getVelocityProcessor() {
        return this.velocityProcessor;
    }
    
    public ConnectionProcessor getConnectionProcessor() {
        return this.connectionProcessor;
    }
    
    public void setClientVersion(final ClientVersion clientVersion) {
        this.clientVersion = clientVersion;
    }
    
    public void setClientBrand(final String clientBrand) {
        this.clientBrand = clientBrand;
    }
    
    public void setHasSentClientBrand(final boolean hasSentClientBrand) {
        this.hasSentClientBrand = hasSentClientBrand;
    }
    
    public void setTotalViolations(final int totalViolations) {
        this.totalViolations = totalViolations;
    }
    
    public void setCombatViolations(final int combatViolations) {
        this.combatViolations = combatViolations;
    }
    
    public void setMovementViolations(final int movementViolations) {
        this.movementViolations = movementViolations;
    }
    
    public void setPlayerViolations(final int playerViolations) {
        this.playerViolations = playerViolations;
    }
    
    public void setAutoClickerViolations(final int autoClickerViolations) {
        this.autoClickerViolations = autoClickerViolations;
    }
    
    public void setScaffoldViolations(final int scaffoldViolations) {
        this.scaffoldViolations = scaffoldViolations;
    }
    
    public void setTimerViolations(final int timerViolations) {
        this.timerViolations = timerViolations;
    }
    
    public void setLastClientBrandAlert(final long lastClientBrandAlert) {
        this.lastClientBrandAlert = lastClientBrandAlert;
    }
    
    public void setLastPunishment(final long lastPunishment) {
        this.lastPunishment = lastPunishment;
    }
    
    public void setFlyingWrapper(final WrappedPacketInFlying flyingWrapper) {
        this.flyingWrapper = flyingWrapper;
    }
    
    public void setUseEntityWrapper(final WrappedPacketInUseEntity useEntityWrapper) {
        this.useEntityWrapper = useEntityWrapper;
    }
    
    public void setBlockDigWrapper(final WrappedPacketInBlockDig blockDigWrapper) {
        this.blockDigWrapper = blockDigWrapper;
    }
    
    public void setEntityActionWrapper(final WrappedPacketInEntityAction entityActionWrapper) {
        this.entityActionWrapper = entityActionWrapper;
    }
    
    public void setHeldItemSlotWrapper(final WrappedPacketInHeldItemSlot heldItemSlotWrapper) {
        this.heldItemSlotWrapper = heldItemSlotWrapper;
    }
    
    public void setPendingTransactions(final int pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
    }
}
