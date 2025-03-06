package me.frep.vulcan.spigot.util;

import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import java.util.logging.Level;
import me.frep.vulcan.spigot.Vulcan;
import io.github.retrooper.packetevents.PacketEvents;

public final class ServerUtil
{
    private static final boolean higherThan1_7;
    private static final boolean higherThan1_8;
    private static final boolean higherThan1_9;
    private static final boolean lowerThan1_13;
    private static final boolean lowerThan1_8;
    private static final boolean lowerThan1_9;
    private static final boolean higherThan1_13;
    private static final boolean higherThan1_16;
    private static final boolean higherThan1_12;
    private static final boolean higherThan1_11;
    private static final boolean higherThan1_17;
    private static final boolean higherThan1_14;
    private static final boolean higherThan1_18;
    private static final boolean higherThan1_19;
    
    public static double getTPS() {
        return Math.min(20.0, PacketEvents.get().getServerUtils().getTPS());
    }
    
    public static void logError(final String message) {
        Vulcan.INSTANCE.getLogger().log(Level.INFO, ColorUtil.translate(message));
    }
    
    public static void log(final String message) {
        Vulcan.INSTANCE.getLogger().log(Level.INFO, ColorUtil.translate(message));
    }
    
    public static ServerVersion getServerVersion() {
        return PacketEvents.get().getServerUtils().getVersion();
    }
    
    public static boolean isHigherThan1_17() {
        return ServerUtil.higherThan1_17;
    }
    
    public static boolean isHigherThan1_19() {
        return ServerUtil.higherThan1_19;
    }
    
    public static boolean isHigherThan1_14() {
        return ServerUtil.higherThan1_14;
    }
    
    public static boolean isHigherThan1_11() {
        return ServerUtil.higherThan1_11;
    }
    
    public static boolean isHigherThan1_7() {
        return ServerUtil.higherThan1_7;
    }
    
    public static boolean isLowerThan1_9() {
        return ServerUtil.lowerThan1_9;
    }
    
    public static boolean isHigherThan1_8() {
        return ServerUtil.higherThan1_8;
    }
    
    public static boolean isHigherThan1_13() {
        return ServerUtil.higherThan1_13;
    }
    
    public static boolean isHigherThan1_16() {
        return ServerUtil.higherThan1_16;
    }
    
    public static boolean isHigherThan1_9() {
        return ServerUtil.higherThan1_9;
    }
    
    public static boolean isLowerThan1_13() {
        return ServerUtil.lowerThan1_13;
    }
    
    public static boolean isLowerThan1_8() {
        return ServerUtil.lowerThan1_8;
    }
    
    public static boolean isHigherThan1_18() {
        return ServerUtil.higherThan1_18;
    }
    
    public static void debug(final Object object) {
        if (Config.DEBUG) {
            broadcast("&c[!] &7" + object);
        }
    }
    
    public static void dispatchCommand(final String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ColorUtil.translate(command));
    }
    
    public static void broadcast(final String message) {
        Bukkit.broadcastMessage(ColorUtil.translate(message));
    }
    
    public static boolean isHigherThan1_12() {
        return ServerUtil.higherThan1_12;
    }
    
    private ServerUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    static {
        higherThan1_7 = getServerVersion().isHigherThan(ServerVersion.v_1_7_10);
        higherThan1_8 = getServerVersion().isHigherThanOrEquals(ServerVersion.v_1_8);
        higherThan1_9 = getServerVersion().isHigherThanOrEquals(ServerVersion.v_1_9);
        lowerThan1_13 = getServerVersion().isLowerThan(ServerVersion.v_1_13);
        lowerThan1_8 = getServerVersion().isLowerThan(ServerVersion.v_1_8);
        lowerThan1_9 = getServerVersion().isOlderThan(ServerVersion.v_1_9);
        higherThan1_13 = getServerVersion().isHigherThan(ServerVersion.v_1_13);
        higherThan1_16 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_16);
        higherThan1_12 = getServerVersion().isHigherThanOrEquals(ServerVersion.v_1_12);
        higherThan1_11 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_11);
        higherThan1_17 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_17);
        higherThan1_14 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_14);
        higherThan1_18 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_18);
        higherThan1_19 = getServerVersion().isNewerThanOrEquals(ServerVersion.v_1_19);
    }
}
