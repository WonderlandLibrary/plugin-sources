package me.vagdedes.spartan.api;

import me.vagdedes.spartan.k.a.GroundUtils;
import me.vagdedes.spartan.h.Velocity;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.features.syn.ViolationHistory;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.f.SpartanLocation;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.k.c.PunishUtils;
import me.vagdedes.spartan.a.b.Wave;
import me.vagdedes.spartan.features.c.CpsCounter;
import me.vagdedes.spartan.features.c.MiningNotifications;
import me.vagdedes.spartan.a.b.BanManagement;
import java.util.UUID;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.a.a.Config;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.f.TPS;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.features.c.VerboseNotifications;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.a.a.Language;

public class BackgroundAPI
{
    public static String version;
    
    public BackgroundAPI() {
        super();
    }
    
    public static String getVersion() {
        return BackgroundAPI.version;
    }
    
    public static String getMessage(final String s) {
        return Language.getMessage(s);
    }
    
    public static boolean getSetting(final String s) {
        return Settings.canDo(s);
    }
    
    public static boolean hasVerboseEnabled(final Player player) {
        return VerboseNotifications.c(player);
    }
    
    public static int getViolationResetTime() {
        return Settings.b("vl_reset_time");
    }
    
    public static void setVerbose(final Player player, final boolean b) {
        VerboseNotifications.a(player, b, 1);
    }
    
    public static int getPing(final Player player) {
        return LatencyUtils.c(player);
    }
    
    public static double getTPS() {
        return TPS.get();
    }
    
    public static boolean hasPermission(final Player player, final Enums.Permission permission) {
        return PermissionUtils.a(player, permission);
    }
    
    public static boolean isEnabled(final Enums.HackType hackType) {
        return Config.isEnabled(hackType);
    }
    
    public static boolean isSilent(final Enums.HackType hackType) {
        return Config.isSilent(hackType, null);
    }
    
    public static boolean isSilent(final Enums.HackType hackType, final String s) {
        return Config.isSilent(hackType, s);
    }
    
    public static int getVL(final Player player, final Enums.HackType hackType) {
        return VL.a(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static int getVL(final Player player) {
        return VL.o(SpartanBukkit.a(player.getUniqueId()));
    }
    
    public static void setVL(final Player player, final Enums.HackType hackType, final int n) {
        VL.a(SpartanBukkit.a(player.getUniqueId()), hackType, n, false, null);
    }
    
    public static void reloadConfig() {
        Config.a((Player)null, false);
    }
    
    public static void enableCheck(final Enums.HackType hackType) {
        Config.a(hackType, true);
    }
    
    public static void disableCheck(final Enums.HackType hackType) {
        Config.b(hackType, true);
    }
    
    public static void enableSilentChecking(final Player player, final Enums.HackType hackType) {
        VL.c(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static void disableSilentChecking(final Player player, final Enums.HackType hackType) {
        VL.e(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static void enableSilentChecking(final Enums.HackType hackType) {
        Config.c(hackType, true);
    }
    
    public static void disableSilentChecking(final Enums.HackType hackType) {
        Config.d(hackType, true);
    }
    
    public static void cancelCheck(final Player player, final Enums.HackType hackType, final int n) {
        VL.a(SpartanBukkit.a(player.getUniqueId()), hackType, n);
    }
    
    public static void cancelCheckPerVerbose(final Player player, final String s, final int n) {
        VL.b(SpartanBukkit.a(player.getUniqueId()), s, n);
    }
    
    public static void startCheck(final Player player, final Enums.HackType hackType) {
        VL.d(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static void stopCheck(final Player player, final Enums.HackType hackType) {
        VL.b(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static void resetVL() {
        VL.i(false);
    }
    
    public static void resetVL(final Player player) {
        VL.e(SpartanBukkit.a(player.getUniqueId()), true);
    }
    
    public static boolean isBypassing(final Player player) {
        return VL.ae(SpartanBukkit.a(player.getUniqueId()));
    }
    
    public static boolean isBypassing(final Player player, final Enums.HackType hackType) {
        return VL.e(SpartanBukkit.a(player.getUniqueId()), hackType);
    }
    
    public static void banPlayer(final UUID uuid, final String s) {
        BanManagement.a(uuid, "CONSOLE", s, 0L);
    }
    
    public static boolean isBanned(final UUID uuid) {
        return BanManagement.isBanned(uuid);
    }
    
    public static void unbanPlayer(final UUID uuid) {
        BanManagement.a(uuid);
    }
    
    public static String getBanReason(final UUID uuid) {
        return BanManagement.a(uuid, "reason");
    }
    
    public static String getBanPunisher(final UUID uuid) {
        return BanManagement.a(uuid, "punisher");
    }
    
    public static boolean hasMiningNotificationsEnabled(final Player player) {
        return MiningNotifications.c(player);
    }
    
    public static void setMiningNotifications(final Player player, final boolean b) {
        MiningNotifications.b(player, b);
    }
    
    public static int getCPS(final Player player) {
        return CpsCounter.o(SpartanBukkit.a(player.getUniqueId()));
    }
    
    public static UUID[] getBanList() {
        return BanManagement.getBanList();
    }
    
    public static void addToWave(final UUID uuid, final String s) {
        Wave.a(uuid, s);
    }
    
    public static void removeFromWave(final UUID uuid) {
        Wave.b(uuid);
    }
    
    public static void clearWave() {
        Wave.clear();
    }
    
    public static void runWave() {
        Wave.run();
    }
    
    public static UUID[] getWaveList() {
        return Wave.getWaveList();
    }
    
    public static int getWaveSize() {
        return Wave.getSize();
    }
    
    public static boolean isAddedToTheWave(final UUID uuid) {
        return Wave.b(uuid);
    }
    
    public static void warnPlayer(final Player player, final String s) {
        PunishUtils.a(null, SpartanBukkit.a(player.getUniqueId()), s);
    }
    
    public static void reloadPermissionCache() {
        PermissionUtils.clear();
    }
    
    public static void reloadPermissionCache(final Player player) {
        PermissionUtils.a(SpartanBukkit.a(player.getUniqueId()));
    }
    
    public static void addPermission(final Player player, final Enums.Permission permission) {
        PermissionUtils.a(player, permission);
    }
    
    public static void sendClientSidedBlock(final Player player, final Location location, final Material material, final byte b) {
        ClientSidedBlock.a(SpartanBukkit.a(player.getUniqueId()), new SpartanLocation(location), material, b);
    }
    
    public static void destroyClientSidedBlock(final Player player, final Location location) {
        ClientSidedBlock.e(SpartanBukkit.a(player.getUniqueId()), new SpartanLocation(location));
    }
    
    public static void removeClientSidedBlocks(final Player player) {
        ClientSidedBlock.a(SpartanBukkit.a(player.getUniqueId()));
    }
    
    public static boolean containsClientSidedBlock(final Player player, final Location location) {
        return ClientSidedBlock.a(SpartanBukkit.a(player.getUniqueId()), new SpartanLocation(location)) != null;
    }
    
    public static Material getClientSidedBlockMaterial(final Player player, final Location location) {
        return ClientSidedBlock.a(SpartanBukkit.a(player.getUniqueId()), new SpartanLocation(location));
    }
    
    public static byte getClientSidedBlockData(final Player player, final Location location) {
        return ClientSidedBlock.a(SpartanBukkit.a(player.getUniqueId()), new SpartanLocation(location));
    }
    
    public static ViolationHistory getViolationHistory(final String s) {
        return SearchEngine.getViolationHistory(s);
    }
    
    public static String getConfiguredCheckName(final Enums.HackType hackType) {
        return Config.a(hackType);
    }
    
    public static void setConfiguredCheckName(final Enums.HackType hackType, final String s) {
        Config.a(hackType, s, true);
    }
    
    public static void disableVelocityProtection(final Player player, final int n) {
        Velocity.g(SpartanBukkit.a(player.getUniqueId()), 0);
        Velocity.i(SpartanBukkit.a(player.getUniqueId()), n);
    }
    
    public static void setOnGround(final Player player, final int n) {
        GroundUtils.j(SpartanBukkit.a(player.getUniqueId()), n);
    }
    
    static {
        BackgroundAPI.version = "Unknown";
    }
}
