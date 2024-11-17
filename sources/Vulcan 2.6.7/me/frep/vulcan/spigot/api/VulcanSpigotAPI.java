package me.frep.vulcan.spigot.api;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Constructor;
import me.frep.vulcan.spigot.check.manager.CheckManager;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.api.check.ICheckData;
import java.util.Map;
import java.util.Iterator;
import me.frep.vulcan.spigot.check.AbstractCheck;
import me.frep.vulcan.api.check.Check;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.jday.JDay;
import me.frep.vulcan.spigot.util.PlayerUtil;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.api.data.IPlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.api.VulcanAPI;

public class VulcanSpigotAPI implements VulcanAPI
{
    @Override
    public IPlayerData getPlayerData(final Player player) {
        return Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
    }
    
    @Override
    public int getPing(final Player player) {
        return PlayerUtil.getPing(player);
    }
    
    @Override
    public void executeBanWave() {
        JDay.executeBanWave();
    }
    
    @Override
    public boolean isFrozen(final Player player) {
        return ((PlayerData)this.getPlayerData(player)).getPositionProcessor().isFrozen();
    }
    
    @Override
    public double getKurtosis(final Player player) {
        return ((PlayerData)this.getPlayerData(player)).getClickProcessor().getKurtosis();
    }
    
    @Override
    public void setFrozen(final Player player, final boolean frozen) {
        final PlayerData data = (PlayerData)this.getPlayerData(player);
        if (data == null) {
            return;
        }
        data.getPositionProcessor().setFrozen(frozen);
    }
    
    @Override
    public int getSensitivity(final Player player) {
        return ((PlayerData)this.getPlayerData(player)).getRotationProcessor().getSensitivity();
    }
    
    @Override
    public double getCps(final Player player) {
        return ((PlayerData)this.getPlayerData(player)).getClickProcessor().getCps();
    }
    
    @Override
    public int getTransactionPing(final Player player) {
        return (int)((PlayerData)this.getPlayerData(player)).getConnectionProcessor().getTransactionPing();
    }
    
    @Override
    public int getTotalViolations(final Player player) {
        return this.getPlayerData(player).getTotalViolations();
    }
    
    @Override
    public int getCombatViolations(final Player player) {
        return this.getPlayerData(player).getCombatViolations();
    }
    
    @Override
    public int getMovementViolations(final Player player) {
        return this.getPlayerData(player).getMovementViolations();
    }
    
    @Override
    public int getPlayerViolations(final Player player) {
        return this.getPlayerData(player).getPlayerViolations();
    }
    
    @Override
    public double getTps() {
        return ServerUtil.getTPS();
    }
    
    @Override
    public int getTicks() {
        return Vulcan.INSTANCE.getTickManager().getTicks();
    }
    
    @Override
    public int getJoinTicks(final Player player) {
        return this.getPlayerData(player).getJoinTicks();
    }
    
    @Override
    public String getVulcanVersion() {
        return Vulcan.INSTANCE.getPlugin().getDescription().getVersion();
    }
    
    @Override
    public Check getCheck(final Player player, final String checkName, final char checkType) {
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return null;
        }
        for (final AbstractCheck abstractCheck : data.getChecks()) {
            if (abstractCheck.getName().equals(checkName) && Character.toString(abstractCheck.getType()).equals(Character.toString(checkType))) {
                return abstractCheck;
            }
        }
        return null;
    }
    
    @Override
    public Map<String, ICheckData> getCheckData() {
        return Config.CHECK_DATA;
    }
    
    @Override
    public boolean hasAlertsEnabled(final Player player) {
        return Vulcan.INSTANCE.getAlertManager().getAlertsEnabled().contains(player);
    }
    
    @Override
    public boolean isCheckEnabled(final String string) {
        for (final Constructor<?> constructor : CheckManager.CONSTRUCTORS) {
            if (constructor.getClass().getSimpleName().equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String getServerVersion() {
        return ServerUtil.getServerVersion().toString();
    }
    
    @Override
    public Set<String> getChecks() {
        return Config.ENABLED_CHECKS.keySet();
    }
    
    @Override
    public Map<String, Boolean> getEnabledChecks() {
        return Config.ENABLED_CHECKS;
    }
    
    @Override
    public Map<String, Integer> getMaxViolations() {
        return Config.MAX_VIOLATIONS;
    }
    
    @Override
    public Map<String, Integer> getAlertIntervals() {
        return Config.ALERT_INTERVAL;
    }
    
    @Override
    public Map<String, Integer> getMinimumViolationsToNotify() {
        return Config.MINIMUM_VL_TO_NOTIFY;
    }
    
    @Override
    public Map<String, List<String>> getPunishmentCommands() {
        return Config.PUNISHMENT_COMMANDS;
    }
    
    @Override
    public Map<String, Boolean> getPunishableChecks() {
        return Config.PUNISHABLE;
    }
    
    @Override
    public Map<String, Boolean> getBroadcastPunishments() {
        return Config.BROADCAST_PUNISHMENT;
    }
    
    @Override
    public Map<String, Integer> getMaximumPings() {
        return Config.MAXIMUM_PING;
    }
    
    @Override
    public Map<String, Double> getMinimumTps() {
        return Config.MINIMUM_TPS;
    }
    
    @Override
    public Map<String, Integer> getMaxBuffers() {
        return Config.MAX_BUFFERS;
    }
    
    @Override
    public Map<String, Double> getBufferDecays() {
        return Config.BUFFER_DECAYS;
    }
    
    @Override
    public Map<String, Double> getBufferMultiples() {
        return Config.BUFFER_MULTIPLES;
    }
    
    @Override
    public Map<String, Boolean> getHotbarShuffle() {
        return Config.HOTBAR_SHUFFLE;
    }
    
    @Override
    public Map<String, Integer> getHotbarShuffleMinimums() {
        return Config.HOTBAR_SHUFFLE_MINIMUM;
    }
    
    @Override
    public Map<String, Integer> getHotbarShuffleIntervals() {
        return Config.HOTBAR_SHUFFLE_EVERY;
    }
    
    @Override
    public Map<String, Boolean> getRandomRotation() {
        return Config.RANDOM_ROTATION;
    }
    
    @Override
    public Map<String, Integer> getRandomRotationMinimums() {
        return Config.RANDOM_ROTATION_MINIMUM;
    }
    
    @Override
    public Map<String, Integer> getRandomRotationIntervals() {
        return Config.RANDOM_ROTATION_EVERY;
    }
    
    @Override
    public List<Check> getChecks(final Player player) {
        return ((PlayerData)this.getPlayerData(player)).getChecks().stream().map(e -> e).collect(Collectors.toList());
    }
    
    @Override
    public String getClientVersion(final Player player) {
        return PlayerUtil.getClientVersionToString(player);
    }
    
    @Override
    public void toggleAlerts(final Player player) {
        Vulcan.INSTANCE.getAlertManager().toggleAlerts(player);
    }
    
    @Override
    public void toggleVerbose(final Player player) {
        Vulcan.INSTANCE.getAlertManager().toggleVerbose(player);
    }
    
    @Override
    public void flag(final Player player, final String checkName, final String checkType, final String info) {
        Vulcan.INSTANCE.getAlertManager().handleApiAlert(player, checkName, checkType, info);
    }
}
