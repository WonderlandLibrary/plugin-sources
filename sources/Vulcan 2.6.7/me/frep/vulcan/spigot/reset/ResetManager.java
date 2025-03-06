package me.frep.vulcan.spigot.reset;

import me.frep.vulcan.spigot.check.AbstractCheck;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.api.event.VulcanViolationResetEvent;
import me.frep.vulcan.spigot.config.Config;
import org.bukkit.scheduler.BukkitTask;

public class ResetManager implements Runnable
{
    private static BukkitTask task;
    
    public static void reset() {
        if (Config.VIOLATION_RESET) {
            if (Config.ENABLE_API) {
                final VulcanViolationResetEvent event = new VulcanViolationResetEvent();
                if (ServerUtil.isHigherThan1_13()) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Bukkit.getPluginManager().callEvent(event));
                }
                else {
                    Bukkit.getPluginManager().callEvent(event);
                }
                if (event.isCancelled()) {
                    return;
                }
            }
            Vulcan.INSTANCE.getPlayerDataManager().getAllData().parallelStream().forEach(data -> {
                data.setTotalViolations(0);
                data.setCombatViolations(0);
                data.setMovementViolations(0);
                data.setPlayerViolations(0);
                data.setAutoClickerViolations(0);
                data.setScaffoldViolations(0);
                data.setTimerViolations(0);
                data.getChecks().forEach(check -> {
                    check.setVl(0);
                    check.resetBuffer();
                });
                return;
            });
            if (Config.VIOLATION_RESET_MESSAGE_ENABLED) {
                Vulcan.INSTANCE.getAlertManager().sendMessage(Config.VIOLATION_RESET_MESSAGE);
            }
        }
    }
    
    public void start() {
        assert ResetManager.task == null : "ResetProcessor has already been started!";
        final int RESET_INTERVAL = Config.getInt("violation-reset.interval-in-minutes");
        ResetManager.task = Bukkit.getScheduler().runTaskTimerAsynchronously(Vulcan.INSTANCE.getPlugin(), this, RESET_INTERVAL * 1200, RESET_INTERVAL * 1200);
    }
    
    public void stop() {
        if (ResetManager.task == null) {
            return;
        }
        ResetManager.task.cancel();
        ResetManager.task = null;
    }
    
    @Override
    public void run() {
        reset();
    }
}
