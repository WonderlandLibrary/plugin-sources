package me.vagdedes.spartan.features.syn;

import org.bukkit.Material;
import me.vagdedes.spartan.Register;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.CommandSender;
import me.vagdedes.spartan.features.a.ConfigurationDiagnostics;
import me.vagdedes.spartan.j.NPC;
import me.vagdedes.spartan.k.c.SynManager;

public class AutomatedConfigurationDiagnostics
{
    private static final int max = 576000;
    private static int time;
    
    public AutomatedConfigurationDiagnostics() {
        super();
    }
    
    public static void run() {
        if (AutomatedConfigurationDiagnostics.time == 0) {
            if (SynManager.s() && NPC.a().length <= 10) {
                AutomatedConfigurationDiagnostics.time = 576000;
                ConfigurationDiagnostics.b(null);
            }
        }
        else {
            --AutomatedConfigurationDiagnostics.time;
        }
    }
    
    public static ItemStack getItem() {
        return new ItemStack(Register.v1_13 ? Material.COMPARATOR : Material.getMaterial("REDSTONE_COMPARATOR"));
    }
    
    static {
        AutomatedConfigurationDiagnostics.time = 576000;
    }
}
