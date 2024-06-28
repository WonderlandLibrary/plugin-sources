package me.vagdedes.spartan.c;

import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.SpartanPlayer;
import wtf.choco.veinminer.api.event.PlayerVeinMineEvent;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.a.a.Compatibility;
import me.vagdedes.spartan.k.b.ReflectionUtils;
import org.bukkit.event.Listener;

public class VeinMiner_New implements Listener
{
    private static boolean enabled;
    
    public VeinMiner_New() {
        super();
    }
    
    public static void reload() {
        if (ReflectionUtils.e("wtf.choco.veinminer.api.event.PlayerVeinMineEvent")) {
            final Compatibility compatibility = new Compatibility("VeinMiner");
            if (!VeinMiner_New.enabled && compatibility.isEnabled() && (PluginUtils.exists("veinminer") || compatibility.c())) {
                Register.enable(new VeinMiner_New(), (Class)VeinMiner_New.class);
                VeinMiner_New.enabled = true;
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
        VeinMiner_New.enabled = false;
    }
}
