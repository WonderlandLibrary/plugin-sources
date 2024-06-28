package me.vagdedes.spartan.checks.c;

import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.system.VL;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ImpossibleInventory
{
    private static final Enums.HackType a;
    
    public ImpossibleInventory() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final ClickType clickType) {
        a(spartanPlayer, clickType);
        a(spartanPlayer, itemStack);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final ClickType clickType) {
        final int k = k(spartanPlayer);
        if (VL.b(spartanPlayer, ImpossibleInventory.a, true) || k == 0 || clickType.isCreativeAction() || clickType.isKeyboardClick() || PlayerData.b(spartanPlayer, true) || PlayerData.d(spartanPlayer, 0) || PlayerData.aV(spartanPlayer) || ((BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(1.0, -1.0, 0.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(-1.0, -1.0, 0.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 1.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, -1.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(1.0, -1.0, 1.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(-1.0, -1.0, -1.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(-1.0, -1.0, 1.0)) || BlockUtils.j(spartanPlayer, spartanPlayer.a().b(1.0, -1.0, -1.0))) && k != 3 && k != 5)) {
            return;
        }
        new HackPrevention(spartanPlayer, ImpossibleInventory.a, "t: closed inventory, c: " + k);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final ItemStack itemStack) {
        if (VL.b(spartanPlayer, ImpossibleInventory.a, true) || itemStack.getType() == Material.AIR) {
            return;
        }
        final SpartanLocation a = spartanPlayer.a();
        if (a.a().getType() == MaterialUtils.a("nether_portal") || a.b(0.0, 1.0, 0.0).a().getType() == MaterialUtils.a("nether_portal")) {
            new HackPrevention(spartanPlayer, ImpossibleInventory.a, "t: portal inventory");
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanLocation spartanLocation) {
        final int k = k(spartanPlayer);
        if (VL.b(spartanPlayer, ImpossibleInventory.a, true) || PlayerData.b(spartanPlayer, true) || k == 0 || !PlayerData.aI(spartanPlayer) || PlayerData.aV(spartanPlayer)) {
            return;
        }
        new HackPrevention(spartanPlayer, ImpossibleInventory.a, "t: cursor usage, c: " + k, spartanLocation, 0, false);
    }
    
    private static int k(final SpartanPlayer spartanPlayer) {
        return (Checks.getBoolean("ImpossibleInventory.check_sneaking") && spartanPlayer.isSneaking()) ? 1 : ((Checks.getBoolean("ImpossibleInventory.check_sprinting") && spartanPlayer.isSprinting()) ? 2 : ((Checks.getBoolean("ImpossibleInventory.check_sleeping") && spartanPlayer.isSleeping()) ? 3 : ((Checks.getBoolean("ImpossibleInventory.check_walking") && PlayerData.aJ(spartanPlayer)) ? 4 : ((Checks.getBoolean("ImpossibleInventory.check_dead") && spartanPlayer.isDead()) ? 5 : ((Checks.getBoolean("ImpossibleInventory.check_sprint_jumping") && PlayerData.aM(spartanPlayer)) ? 6 : ((Checks.getBoolean("ImpossibleInventory.check_walk_jumping") && PlayerData.aN(spartanPlayer)) ? 7 : 0))))));
    }
    
    static {
        a = Enums.HackType.ImpossibleInventory;
    }
}
