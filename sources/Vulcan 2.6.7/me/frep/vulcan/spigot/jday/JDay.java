package me.frep.vulcan.spigot.jday;

import org.bukkit.entity.Player;
import java.util.Iterator;
import me.frep.vulcan.api.event.VulcanJudgementDayEndEvent;
import org.bukkit.plugin.Plugin;
import java.util.function.Consumer;
import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.event.Event;
import me.frep.vulcan.api.event.VulcanJudgementDayStartEvent;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.jday.config.JDayConfig;
import me.frep.vulcan.spigot.Vulcan;

public class JDay
{
    public static void executeBanWave() {
        Vulcan.INSTANCE.getJudgementExecutor().execute(() -> {
            final JDayConfig pending = new JDayConfig("jday-pending");
            final JDayConfig banned = new JDayConfig("jday-banned");
            if (pending.getConfigFile().getConfigurationSection("PendingUsers") != null && pending.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false) != null) {
                if (Config.ENABLE_API) {
                    Bukkit.getPluginManager().callEvent(new VulcanJudgementDayStartEvent());
                }
                Config.JDAY_STARTED_BROADCAST.forEach(ServerUtil::broadcast);
                int total = 0;
                Iterator<String> iterator = pending.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false).iterator();
                while (iterator.hasNext()) {
                    final String string = iterator.next();
                    final String name = pending.getConfigFile().getString("PendingUsers." + string + ".Name");
                    final String uuid = pending.getConfigFile().getString("PendingUsers." + string + ".UUID");
                    final String reason = pending.getConfigFile().getString("PendingUsers." + string + ".Reason");
                    final String executedBy = pending.getConfigFile().getString("PendingUsers." + string + ".ExecutedBy");
                    final String wasOnline = pending.getConfigFile().getString("PendingUsers." + string + ".wasOnline");
                    final String date = pending.getConfigFile().getString("PendingUsers." + string + ".Date");
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Config.JDAY_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(command.replaceAll("%player%", name))));
                    Config.JDAY_BROADCAST.forEach(broadcast -> ServerUtil.broadcast(broadcast.replaceAll("%player%", name)));
                    banned.getConfigFile().set("BannedUsers." + string + ".Name", name);
                    banned.getConfigFile().set("BannedUsers." + string + ".UUID", uuid);
                    banned.getConfigFile().set("BannedUsers." + string + ".Date", date);
                    banned.getConfigFile().set("BannedUsers." + string + ".Reason", reason);
                    banned.getConfigFile().set("BannedUsers." + string + ".ExecutedBy", executedBy);
                    banned.getConfigFile().set("BannedUsers." + string + ".wasOnline", wasOnline);
                    banned.saveConfigFile();
                    ++total;
                    try {
                        Thread.sleep(Config.JDAY_COOLDOWN);
                    }
                    catch (final InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (Config.ENABLE_API) {
                    Bukkit.getPluginManager().callEvent(new VulcanJudgementDayEndEvent());
                }
                final int finalTotal = total;
                Config.JDAY_END_BROADCAST.forEach(broadcast -> ServerUtil.broadcast(broadcast.replaceAll("%amount%", Integer.toString(finalTotal))));
                pending.getConfigFile().set("PendingUsers", null);
                pending.saveConfigFile();
                Vulcan.INSTANCE.getPlugin().saveConfig();
            }
        });
    }
    
    public static boolean isInBanWave(final Player player) {
        final JDayConfig pending = new JDayConfig("jday-pending");
        if (pending.getConfigFile().getConfigurationSection("PendingUsers") == null) {
            return false;
        }
        final Iterator iterator = pending.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false).iterator();
        if (iterator.hasNext()) {
            final String string = (String)iterator.next();
            return string.equalsIgnoreCase(player.getUniqueId().toString());
        }
        return false;
    }
    
    public static int getAmountToBan() {
        final JDayConfig pending = new JDayConfig("jday-pending");
        int count = 0;
        if (pending.getConfigFile().getConfigurationSection("PendingUsers") == null) {
            return 0;
        }
        for (final String string : pending.getConfigFile().getConfigurationSection("PendingUsers").getKeys(false)) {
            ++count;
        }
        return count;
    }
}
