package me.vagdedes.spartan.checks.c;

import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.c.LagManagement;
import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.system.VL;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class InventoryClicks
{
    private static final Enums.HackType a;
    
    public InventoryClicks() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final ItemStack itemStack, final ClickType clickType) {
        if (VL.b(spartanPlayer, InventoryClicks.a, true) || clickType.isCreativeAction() || clickType.isKeyboardClick() || itemStack.getItemMeta() == null || !PlayerData.d(spartanPlayer, 8) || BlockUtils.E(itemStack.getType())) {
            return;
        }
        final boolean shiftClick = clickType.isShiftClick();
        final String replaceAll = itemStack.getType().toString().toLowerCase().replaceAll("_", "-");
        final long a = MillisUtils.a(spartanPlayer, InventoryClicks.a.toString() + "=time");
        if (shiftClick && !PlayerData.aI(spartanPlayer) && VL.a(spartanPlayer, InventoryClicks.a) > 0) {
            if (AttemptUtils.b(spartanPlayer, InventoryClicks.a.toString() + "=shift", 5) > LagManagement.a(spartanPlayer, 10)) {
                new HackPrevention(spartanPlayer, InventoryClicks.a, "t: shift-click, i: " + replaceAll);
            }
        }
        else if (!shiftClick && MillisUtils.hasTimer(a)) {
            if (a < 50L) {
                if (AttemptUtils.b(spartanPlayer, InventoryClicks.a.toString() + "=fast", 5) >= 2) {
                    new HackPrevention(spartanPlayer, InventoryClicks.a, "t: normal, speed: fast, ms: " + a + ", i: " + replaceAll);
                }
            }
            else if (a < 100L) {
                if (AttemptUtils.b(spartanPlayer, InventoryClicks.a.toString() + "=average", 5) >= 4) {
                    new HackPrevention(spartanPlayer, InventoryClicks.a, "t: normal, speed: average, ms: " + a + ", i: " + replaceAll);
                }
            }
            else if (a < 150L && AttemptUtils.b(spartanPlayer, InventoryClicks.a.toString() + "=slow", 10) >= 8) {
                new HackPrevention(spartanPlayer, InventoryClicks.a, "t: normal, speed: slow, ms: " + a + ", i: " + replaceAll);
            }
        }
        MillisUtils.o(spartanPlayer, InventoryClicks.a.toString() + "=time");
    }
    
    static {
        a = Enums.HackType.InventoryClicks;
    }
}
