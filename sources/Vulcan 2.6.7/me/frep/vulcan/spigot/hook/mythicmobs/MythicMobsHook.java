package me.frep.vulcan.spigot.hook.mythicmobs;

import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import io.lumine.xikage.mythicmobs.MythicMobs;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class MythicMobsHook implements Listener
{
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player)event.getEntity();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        if (!Vulcan.INSTANCE.isMythicMobsLatest()) {
            if (MythicMobs.inst().getAPIHelper().isMythicMob(event.getDamager())) {
                data.getActionProcessor().setSinceMythicMobTicks(0);
            }
        }
    }
}
