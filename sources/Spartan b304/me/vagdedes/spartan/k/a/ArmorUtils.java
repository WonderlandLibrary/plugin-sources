package me.vagdedes.spartan.k.a;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.vagdedes.spartan.f.SpartanPlayer;

public class ArmorUtils
{
    public ArmorUtils() {
        super();
    }
    
    public static a a(final SpartanPlayer spartanPlayer) {
        int n = 0;
        final ItemStack[] armorContents = spartanPlayer.a().getArmorContents();
        for (int length = armorContents.length, i = 0; i < length; ++i) {
            if (armorContents[i] != null) {
                ++n;
            }
        }
        return (n == 0) ? a.c : ((n == 4) ? a.a : a.b);
    }
    
    public static a a(final Player player) {
        int n = 0;
        final ItemStack[] armorContents = player.getInventory().getArmorContents();
        for (int length = armorContents.length, i = 0; i < length; ++i) {
            if (armorContents[i] != null) {
                ++n;
            }
        }
        return (n == 0) ? a.c : ((n == 4) ? a.a : a.b);
    }
    
    public enum a
    {
        a, 
        b, 
        c;
        
        private static final /* synthetic */ a[] a;
        
        public static a[] values() {
            return ArmorUtils.a.a.clone();
        }
        
        public static a valueOf(final String name) {
            return Enum.<a>valueOf(a.class, name);
        }
        
        static {
            a = new a[] { ArmorUtils.a.a, ArmorUtils.a.b, ArmorUtils.a.c };
        }
    }
}
