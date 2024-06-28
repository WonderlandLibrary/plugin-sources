package me.vagdedes.spartan.api;

import me.vagdedes.spartan.features.syn.ViolationHistory;
import org.bukkit.Material;
import org.bukkit.Location;
import java.util.UUID;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;

public class API
{
    public API() {
        super();
    }
    
    public static String getVersion() {
        return BackgroundAPI.getVersion();
    }
    
    public static String getMessage(final String s) {
        return BackgroundAPI.getMessage(s);
    }
    
    public static boolean getSetting(final String s) {
        return BackgroundAPI.getSetting(s);
    }
    
    public static boolean hasVerboseEnabled(final Player player) {
        return BackgroundAPI.hasVerboseEnabled(player);
    }
    
    public static int getViolationResetTime() {
        return BackgroundAPI.getViolationResetTime();
    }
    
    public static void setVerbose(final Player player, final boolean b) {
        BackgroundAPI.setVerbose(player, b);
    }
    
    public static int getPing(final Player player) {
        return BackgroundAPI.getPing(player);
    }
    
    public static double getTPS() {
        return BackgroundAPI.getTPS();
    }
    
    public static boolean hasPermission(final Player player, final Enums.Permission permission) {
        return BackgroundAPI.hasPermission(player, permission);
    }
    
    public static boolean isEnabled(final Enums.HackType hackType) {
        return BackgroundAPI.isEnabled(hackType);
    }
    
    public static boolean isSilent(final Enums.HackType hackType) {
        return BackgroundAPI.isSilent(hackType);
    }
    
    public static int getVL(final Player player, final Enums.HackType hackType) {
        return BackgroundAPI.getVL(player, hackType);
    }
    
    public static int getVL(final Player player) {
        return BackgroundAPI.getVL(player);
    }
    
    public static void setVL(final Player player, final Enums.HackType hackType, final int n) {
        BackgroundAPI.setVL(player, hackType, n);
    }
    
    public static void reloadConfig() {
        BackgroundAPI.reloadConfig();
    }
    
    public static void enableCheck(final Enums.HackType hackType) {
        BackgroundAPI.enableCheck(hackType);
    }
    
    public static void disableCheck(final Enums.HackType hackType) {
        BackgroundAPI.disableCheck(hackType);
    }
    
    public static void enableSilentChecking(final Player player, final Enums.HackType hackType) {
        BackgroundAPI.enableSilentChecking(player, hackType);
    }
    
    public static void disableSilentChecking(final Player player, final Enums.HackType hackType) {
        BackgroundAPI.disableSilentChecking(player, hackType);
    }
    
    public static void enableSilentChecking(final Enums.HackType hackType) {
        BackgroundAPI.enableSilentChecking(hackType);
    }
    
    public static void disableSilentChecking(final Enums.HackType hackType) {
        BackgroundAPI.disableSilentChecking(hackType);
    }
    
    public static void cancelCheck(final Player player, final Enums.HackType hackType, final int n) {
        BackgroundAPI.cancelCheck(player, hackType, n);
    }
    
    public static void cancelCheckPerVerbose(final Player player, final String s, final int n) {
        BackgroundAPI.cancelCheckPerVerbose(player, s, n);
    }
    
    public static void startCheck(final Player player, final Enums.HackType hackType) {
        BackgroundAPI.startCheck(player, hackType);
    }
    
    public static void stopCheck(final Player player, final Enums.HackType hackType) {
        BackgroundAPI.stopCheck(player, hackType);
    }
    
    public static void resetVL() {
        BackgroundAPI.resetVL();
    }
    
    public static void resetVL(final Player player) {
        BackgroundAPI.resetVL(player);
    }
    
    public static boolean isBypassing(final Player player) {
        return BackgroundAPI.isBypassing(player);
    }
    
    public static boolean isBypassing(final Player player, final Enums.HackType hackType) {
        return BackgroundAPI.isBypassing(player, hackType);
    }
    
    public static void banPlayer(final UUID uuid, final String s) {
        BackgroundAPI.banPlayer(uuid, s);
    }
    
    public static boolean isBanned(final UUID uuid) {
        return BackgroundAPI.isBanned(uuid);
    }
    
    public static void unbanPlayer(final UUID uuid) {
        BackgroundAPI.unbanPlayer(uuid);
    }
    
    public static String getBanReason(final UUID uuid) {
        return BackgroundAPI.getBanReason(uuid);
    }
    
    public static String getBanPunisher(final UUID uuid) {
        return BackgroundAPI.getBanPunisher(uuid);
    }
    
    public static boolean hasMiningNotificationsEnabled(final Player player) {
        return BackgroundAPI.hasMiningNotificationsEnabled(player);
    }
    
    public static void setMiningNotifications(final Player player, final boolean b) {
        BackgroundAPI.setMiningNotifications(player, b);
    }
    
    public static int getCPS(final Player player) {
        return BackgroundAPI.getCPS(player);
    }
    
    public static UUID[] getBanList() {
        return BackgroundAPI.getBanList();
    }
    
    public static void addToWave(final UUID uuid, final String s) {
        BackgroundAPI.addToWave(uuid, s);
    }
    
    public static void removeFromWave(final UUID uuid) {
        BackgroundAPI.removeFromWave(uuid);
    }
    
    public static void clearWave() {
        BackgroundAPI.clearWave();
    }
    
    public static void runWave() {
        BackgroundAPI.runWave();
    }
    
    public static UUID[] getWaveList() {
        return BackgroundAPI.getWaveList();
    }
    
    public static int getWaveSize() {
        return BackgroundAPI.getWaveSize();
    }
    
    public static boolean isAddedToTheWave(final UUID uuid) {
        return BackgroundAPI.isAddedToTheWave(uuid);
    }
    
    public static void warnPlayer(final Player player, final String s) {
        BackgroundAPI.warnPlayer(player, s);
    }
    
    public static void reloadPermissionCache() {
        BackgroundAPI.reloadPermissionCache();
    }
    
    public static void reloadPermissionCache(final Player player) {
        BackgroundAPI.reloadPermissionCache(player);
    }
    
    public static void addPermission(final Player player, final Enums.Permission permission) {
        BackgroundAPI.addPermission(player, permission);
    }
    
    public static void sendClientSidedBlock(final Player player, final Location location, final Material material, final byte b) {
        BackgroundAPI.sendClientSidedBlock(player, location, material, b);
    }
    
    public static void destroyClientSidedBlock(final Player player, final Location location) {
        BackgroundAPI.destroyClientSidedBlock(player, location);
    }
    
    public static void removeClientSidedBlocks(final Player player) {
        BackgroundAPI.removeClientSidedBlocks(player);
    }
    
    public static boolean containsClientSidedBlock(final Player player, final Location location) {
        return BackgroundAPI.containsClientSidedBlock(player, location);
    }
    
    public static Material getClientSidedBlockMaterial(final Player player, final Location location) {
        return BackgroundAPI.getClientSidedBlockMaterial(player, location);
    }
    
    public static byte getClientSidedBlockData(final Player player, final Location location) {
        return BackgroundAPI.getClientSidedBlockData(player, location);
    }
    
    public static ViolationHistory getViolationHistory(final String s) {
        return BackgroundAPI.getViolationHistory(s);
    }
    
    public static void disableVelocityProtection(final Player player, final int n) {
        BackgroundAPI.disableVelocityProtection(player, n);
    }
    
    public static String getConfiguredCheckName(final Enums.HackType hackType) {
        return BackgroundAPI.getConfiguredCheckName(hackType);
    }
    
    public static void setConfiguredCheckName(final Enums.HackType hackType, final String s) {
        BackgroundAPI.setConfiguredCheckName(hackType, s);
    }
    
    public static void setOnGround(final Player player, final int n) {
        BackgroundAPI.setOnGround(player, n);
    }
}
