package me.vagdedes.spartan.c;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.choco.veinminer.api.PlayerVeinMineEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.k.b.ReflectionUtils;
import org.bukkit.event.Listener;

public class VeinMiner_Old implements Listener
{
    private static boolean enabled;
    
    public VeinMiner_Old() {
        super();
    }
    
    public static void reload() {
        if (ReflectionUtils.e("me.choco.veinminer.api.PlayerVeinMineEvent")) {
            final Compatibility compatibility = new Compatibility("VeinMiner");
            if (!VeinMiner_Old.enabled && compatibility.isEnabled() && (PluginUtils.exists("veinminer") || compatibility.c())) {
                Register.enable(new VeinMiner_Old(), (Class)VeinMiner_Old.class);
                VeinMiner_Old.enabled = true;
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    private void a(final PlayerVeinMineEvent playerVeinMineEvent) {
        final SpartanPlayer spartanPlayer = new SpartanPlayer(playerVeinMineEvent.getPlayer());
        final int n = 20;
        VL.a(spartanPlayer, Enums.HackType.Nuker, n);
        VL.a(spartanPlayer, Enums.HackType.NoSwing, n);
        VL.a(spartanPlayer, Enums.HackType.FastBreak, n);
        VL.a(spartanPlayer, Enums.HackType.BlockReach, n);
        VL.a(spartanPlayer, Enums.HackType.GhostHand, n);
    }
    
    static {
        VeinMiner_Old.enabled = false;
    }
}
