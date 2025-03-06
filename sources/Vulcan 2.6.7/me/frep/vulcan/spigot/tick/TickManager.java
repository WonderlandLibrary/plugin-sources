package me.frep.vulcan.spigot.tick;

import java.util.Iterator;
import me.frep.vulcan.spigot.jday.JDay;
import me.frep.vulcan.spigot.config.Stats;
import me.frep.vulcan.spigot.config.Config;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.util.ServerUtil;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

public class TickManager implements Runnable
{
    private int ticks;
    private static BukkitTask task;
    
    public void start() {
        assert TickManager.task == null : "TickProcessor has already been started!";
        TickManager.task = Bukkit.getScheduler().runTaskTimerAsynchronously(Vulcan.INSTANCE.getPlugin(), this, 0L, 1L);
    }
    
    public void stop() {
        if (TickManager.task == null) {
            return;
        }
        TickManager.task.cancel();
        TickManager.task = null;
    }
    
    @Override
    public void run() {
        ++this.ticks;
        if (this.ticks % 5 == 0) {
            if (ServerUtil.isHigherThan1_17()) {
                final Iterator<PlayerData> iterator = Vulcan.INSTANCE.getPlayerDataManager().getAllData().iterator();
                PlayerData data = null;
                while (iterator.hasNext()) {
                    data = iterator.next();
                    data.getConnectionProcessor().sendPing();
                }
                Vulcan.INSTANCE.getPlayerDataManager().getAllData().forEach(data2 -> data2.getConnectionProcessor().sendPing());
            }
            else {
                for (final PlayerData data : Vulcan.INSTANCE.getPlayerDataManager().getAllData()) {
                    data.getConnectionProcessor().sendTransaction();
                }
            }
        }
        if (Config.PUNISHMENT_STATISTIC_BROADCAST && this.ticks % Config.PUNISHMENT_STATISTIC_BROADCAST_INTERVAL == 0) {
            Config.PUNISHMENT_STATISTIC_BROADCAST_MESSAGE.forEach(string -> ServerUtil.broadcast(string.replaceAll("%amount%", Integer.toString(Stats.getPunishments()))));
        }
        if (Config.RUN_JUDGEMENT_DAY_AT_INTERVAL && this.ticks % Config.JUDGEMENT_DAY_INTERVAL == 0) {
            JDay.executeBanWave();
        }
        if (this.ticks > 4000 && this.ticks % 500 == 0 && ServerUtil.getTPS() < 1.0 && Config.INCOMPATABILITY_MANAGER) {
            Vulcan.INSTANCE.log("Incompatible Spigot fork detected. Please contact support @ https://discord.com/invite/SCNuwUG");
        }
    }
    
    public int getTicks() {
        return this.ticks;
    }
}
