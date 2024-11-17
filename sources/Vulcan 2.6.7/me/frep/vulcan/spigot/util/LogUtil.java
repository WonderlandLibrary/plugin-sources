package me.frep.vulcan.spigot.util;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import java.text.DateFormat;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Date;
import me.frep.vulcan.spigot.config.Config;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import me.frep.vulcan.spigot.Vulcan;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.AbstractCheck;
import java.util.List;

public final class LogUtil
{
    public static List<String> leakFaggots;
    
    public static void logAlert(final AbstractCheck check, final PlayerData data, final String info) {
        try {
            final File dataFolder = Vulcan.INSTANCE.getPlugin().getDataFolder();
            final Calendar calendar = Calendar.getInstance();
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            final File violationsFile = new File(Vulcan.INSTANCE.getPlugin().getDataFolder(), "violations.txt");
            if (!violationsFile.exists()) {
                violationsFile.createNewFile();
            }
            final FileWriter fw = new FileWriter(violationsFile, true);
            final PrintWriter pw = new PrintWriter(fw);
            final String cps = MathUtil.trim(data.getClickProcessor().getCps());
            final DateFormat date = new SimpleDateFormat("MM-dd-yyyy");
            final int maxVl = Config.MAX_VIOLATIONS.get(check.getClass().getSimpleName());
            pw.println("[" + date.format(new Date()) + "] [" + sdf.format(calendar.getTime()) + "] " + Config.LOG_FILE_ALERT_MESSAGE.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%max-vl%", Integer.toString(maxVl)).replaceAll("%cps%", cps).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%x%", Double.toString(data.getPositionProcessor().getX())).replaceAll("%y%", Double.toString(data.getPositionProcessor().getY())).replaceAll("%z%", Double.toString(data.getPositionProcessor().getZ())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%tps%", new DecimalFormat("##.##").format(ServerUtil.getTPS())).replaceAll("%ping%", Integer.toString(PlayerUtil.getPing(data.getPlayer()))).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate("*") : "").replaceAll("%vl%", Integer.toString(check.getVl())).replaceAll("%type%", Character.toString(check.getCheckInfo().type())) + " " + info);
            final long length = violationsFile.length() / 1000L;
            if (length > Config.MAX_LOGS_FILE_SIZE) {
                final FileInputStream in = new FileInputStream(Vulcan.INSTANCE.getPlugin().getDataFolder() + File.separator + "violations.txt");
                final File logsFolder = new File(Vulcan.INSTANCE.getPlugin().getDataFolder() + File.separator + "logs");
                if (!logsFolder.exists()) {
                    logsFolder.mkdir();
                }
                final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(logsFolder + File.separator + dateFormat.format(new Date()) + ".zip"));
                out.putNextEntry(new ZipEntry("violations.txt"));
                final byte[] b = new byte[1024];
                int count;
                while ((count = in.read(b)) > 0) {
                    out.write(b, 0, count);
                }
                out.close();
                in.close();
                violationsFile.delete();
            }
            pw.flush();
            pw.close();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void logPunishment(final AbstractCheck check, final PlayerData data) {
        try {
            final File dataFolder = Vulcan.INSTANCE.getPlugin().getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            final File violationsFile = new File(Vulcan.INSTANCE.getPlugin().getDataFolder(), "punishments.txt");
            if (!violationsFile.exists()) {
                violationsFile.createNewFile();
            }
            final FileWriter fw = new FileWriter(violationsFile, true);
            final PrintWriter pw = new PrintWriter(fw);
            pw.println("--------------[ " + data.getPlayer().getName() + " was punished for " + StringUtils.capitalize(check.getDisplayName()) + " (Type " + Character.toUpperCase(check.getDisplayType()) + ") ]--------------");
            pw.println("");
            pw.println("Location:");
            pw.println(" - World: " + data.getPlayer().getWorld().getName());
            pw.println(" - X: " + data.getPositionProcessor().getX());
            pw.println(" - Y: " + data.getPositionProcessor().getY());
            pw.println(" - Z: " + data.getPositionProcessor().getZ());
            pw.println("");
            pw.println("Information:");
            pw.println(" - UUID: " + data.getPlayer().getUniqueId());
            pw.println(" - Keep Alive Ping: " + data.getConnectionProcessor().getKeepAlivePing());
            pw.println(" - Transaction Ping: " + data.getConnectionProcessor().getTransactionPing());
            pw.println(" - Server TPS: " + new DecimalFormat("##.##").format(ServerUtil.getTPS()));
            pw.println(" - Sensitivity: " + data.getRotationProcessor().getSensitivity());
            pw.println(" - Gamemode: " + data.getPlayer().getGameMode());
            pw.println(" - Client Brand: " + data.getClientBrand());
            pw.println(" - Vehicle: " + data.getPlayer().getVehicle());
            pw.println(" - Version: " + PlayerUtil.getClientVersionToString(data.getPlayer()));
            pw.println(" - Last Damage: " + data.getVelocityProcessor().getTransactionFlyingTicks() / 20.0 + " seconds ago");
            pw.println("");
            pw.println("Total Violations: " + data.getTotalViolations());
            pw.println(" - Combat Violations: " + data.getCombatViolations());
            pw.println(" - Movement Violations: " + data.getMovementViolations());
            pw.println(" - Player Violations: " + data.getPlayerViolations());
            pw.println("");
            pw.println("Misc Information:");
            pw.println(" - Since Teleport Ticks: " + data.getActionProcessor().getSinceTeleportTicks());
            pw.println(" - Since Riptide Ticks: " + data.getPositionProcessor().getSinceRiptideTicks());
            pw.println(" - Flying Delay: " + data.getConnectionProcessor().getFlyingDelay());
            pw.println(" - Since Velocity Ticks: " + data.getVelocityProcessor().getTransactionFlyingTicks());
            pw.println(" - Since Firework Ticks: " + data.getPositionProcessor().getSinceFireworkTicks());
            pw.println(" - Ticks Existed: " + data.getActionProcessor().getTicksExisted());
            pw.println(" - Since Vehicle Ticks: " + data.getPositionProcessor().getSinceVehicleTicks());
            pw.println(" - Joined: " + (System.currentTimeMillis() - data.getJoinTime()) / 1000L + " seconds ago");
            pw.println("");
            pw.println("All Violations:");
            for (final AbstractCheck check2 : data.getChecks()) {
                if (check2.getVl() > 0) {
                    pw.println(" - " + StringUtils.capitalize(check2.getDisplayName()) + " (Type " + Character.toUpperCase(check2.getType()) + ") [VL: " + check2.getVl() + "] (" + check2.getCheckInfo().description() + ")");
                }
            }
            pw.println("");
            if (ServerUtil.isHigherThan1_13()) {
                if (data.getPositionProcessor().getNearbyBlocksModern() != null) {
                    pw.println("Blocks Nearby: " + data.getPositionProcessor().getNearbyBlocksModern());
                }
            }
            else if (data.getPositionProcessor().getNearbyBlocks() != null) {
                pw.println("Blocks Nearby: " + data.getPositionProcessor().getNearbyBlocks());
            }
            if (data.getPositionProcessor().getNearbyEntities() != null) {
                pw.println("Entities Nearby: " + data.getPositionProcessor().getNearbyEntities());
            }
            pw.println("Potion Effects: " + data.getPlayer().getActivePotionEffects());
            pw.println("---------------------------------------------------------------------------");
            pw.println("");
            pw.println("");
            pw.flush();
            pw.close();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getURLReturn(final String url) {
        try {
            final HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible;VAPIv3)");
            connection.setRequestProperty("Accept", "*/*");
            final InputStream inputStream = connection.getInputStream();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        }
        catch (final Exception ex) {
            return "";
        }
    }
    
    private LogUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    static {
        (LogUtil.leakFaggots = new ArrayList<String>()).add("12345");
        LogUtil.leakFaggots.add("12503");
        LogUtil.leakFaggots.add("21816");
        LogUtil.leakFaggots.add("5548");
    }
}
