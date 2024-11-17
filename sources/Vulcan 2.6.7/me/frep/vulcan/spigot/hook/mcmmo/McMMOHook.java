package me.frep.vulcan.spigot.hook.mcmmo;

import com.gmail.nossr50.datatypes.skills.AbilityType;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityDeactivateEvent;
import org.bukkit.event.EventHandler;
import me.frep.vulcan.spigot.data.PlayerData;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.Vulcan;
import com.gmail.nossr50.events.skills.abilities.McMMOPlayerAbilityActivateEvent;
import org.bukkit.event.Listener;

public class McMMOHook implements Listener
{
    @EventHandler
    public void onActivate(final McMMOPlayerAbilityActivateEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data != null) {
            if (event.getAbility() == AbilityType.BERSERK) {
                data.getActionProcessor().setBerserking(true);
            }
        }
    }

    @EventHandler
    public void onDeactivate(final McMMOPlayerAbilityDeactivateEvent event) {
        final Player player = event.getPlayer();
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data != null) {
            if (event.getAbility() == AbilityType.BERSERK) {
                data.getActionProcessor().setBerserking(false);
            }
        }
    }
}
