package me.frep.vulcan.spigot.hook.crackshot;

import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.entity.Player;
import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import org.bukkit.event.Listener;

public class CrackShotHook implements Listener
{
    @EventHandler
    public void onDamage(final WeaponDamageEntityEvent event) {
        if (event.getVictim() instanceof Player) {
            final Player player = (Player)event.getVictim();
            final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
            if (data != null) {
                data.getActionProcessor().handleCrackshotDamage();
            }
        }
    }
}
