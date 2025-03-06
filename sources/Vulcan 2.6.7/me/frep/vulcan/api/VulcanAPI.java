package me.frep.vulcan.api;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.List;
import me.frep.vulcan.api.check.Check;
import me.frep.vulcan.api.check.ICheckData;
import java.util.Map;
import me.frep.vulcan.api.data.IPlayerData;
import org.bukkit.entity.Player;

public interface VulcanAPI
{
    void toggleAlerts(final Player p0);
    
    void toggleVerbose(final Player p0);
    
    IPlayerData getPlayerData(final Player p0);
    
    int getPing(final Player p0);
    
    double getKurtosis(final Player p0);
    
    int getTransactionPing(final Player p0);
    
    int getSensitivity(final Player p0);
    
    void executeBanWave();
    
    void setFrozen(final Player p0, final boolean p1);
    
    double getCps(final Player p0);
    
    int getTotalViolations(final Player p0);
    
    boolean isFrozen(final Player p0);
    
    int getMovementViolations(final Player p0);
    
    int getPlayerViolations(final Player p0);
    
    int getCombatViolations(final Player p0);
    
    double getTps();
    
    String getServerVersion();
    
    Map<String, ICheckData> getCheckData();
    
    int getJoinTicks(final Player p0);
    
    String getClientVersion(final Player p0);
    
    boolean hasAlertsEnabled(final Player p0);
    
    Check getCheck(final Player p0, final String p1, final char p2);
    
    String getVulcanVersion();
    
    boolean isCheckEnabled(final String p0);
    
    int getTicks();
    
    List<Check> getChecks(final Player p0);
    
    Map<String, Boolean> getEnabledChecks();
    
    Map<String, Integer> getMaxViolations();
    
    Map<String, Integer> getAlertIntervals();
    
    Map<String, Integer> getMinimumViolationsToNotify();
    
    Map<String, List<String>> getPunishmentCommands();
    
    Map<String, Boolean> getPunishableChecks();
    
    Map<String, Boolean> getBroadcastPunishments();
    
    Map<String, Integer> getMaximumPings();
    
    Map<String, Double> getMinimumTps();
    
    Map<String, Integer> getMaxBuffers();
    
    Map<String, Double> getBufferDecays();
    
    Map<String, Double> getBufferMultiples();
    
    Map<String, Boolean> getHotbarShuffle();
    
    Map<String, Integer> getHotbarShuffleMinimums();
    
    Map<String, Integer> getHotbarShuffleIntervals();
    
    Map<String, Boolean> getRandomRotation();
    
    Map<String, Integer> getRandomRotationMinimums();
    
    Map<String, Integer> getRandomRotationIntervals();
    
    Set<String> getChecks();
    
    void flag(final Player p0, final String p1, final String p2, final String p3);
    
    public static class Factory
    {
        private static VulcanAPI api;
        
        @Nullable
        public static VulcanAPI getApi() {
            return Factory.api;
        }
        
        public static void setApi(final VulcanAPI api) {
            Factory.api = api;
        }
    }
}
