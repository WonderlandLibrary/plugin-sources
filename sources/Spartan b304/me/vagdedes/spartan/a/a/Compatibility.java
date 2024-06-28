package me.vagdedes.spartan.a.a;

import me.vagdedes.spartan.c.MythicMobs;
import me.vagdedes.spartan.j.UltimateStatistics;
import me.vagdedes.spartan.c.CrackShotPlus;
import me.vagdedes.spartan.c.GrapplingHook;
import me.vagdedes.spartan.c.AcidRain;
import me.vagdedes.spartan.c.ProjectKorra;
import me.vagdedes.spartan.c.VeinMiner_New;
import me.vagdedes.spartan.c.VeinMiner_Old;
import me.vagdedes.spartan.c.AdvancedAbilities;
import me.vagdedes.spartan.c.MagicSpells;
import me.vagdedes.spartan.c.RealDualWield;
import me.vagdedes.spartan.c.CrackShot;
import me.vagdedes.spartan.c.CraftBook;
import org.bukkit.configuration.file.YamlConfiguration;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.Register;
import java.util.HashMap;
import java.io.File;

public class Compatibility
{
    private static File file;
    private static final HashMap<String, Boolean> d;
    private boolean enabled;
    private boolean a;
    
    public static void clear() {
        Compatibility.d.clear();
    }
    
    public static void create() {
        Compatibility.file = new File(Register.plugin.getDataFolder() + "/compatibility.yml");
        clear();
        for (final String s : new String[] { "AcidRain", "AdvancedAbilities", "CrackShot", "CraftBook", "Essentials", "MagicSpells", "ProtocolLib", "mcMMO_background", "NoHitDelay", "ProjectKorra", "TreeFeller", "VeinMiner", "GrapplingHook", "ViaRewind", "RecentPvPMechanics", "MineBomb", "SmashHit", "SuperPickaxe", "RealDualWield", "UltimateStatistics", "MythicMobs", "ViaVersion" }) {
            ConfigUtils.add(Compatibility.file, s + ".enabled", Boolean.valueOf(true));
            ConfigUtils.add(Compatibility.file, s + ".force", Boolean.valueOf(false));
        }
    }
    
    public static boolean a(final String key) {
        if (Compatibility.d.containsKey(key)) {
            return Boolean.valueOf(Compatibility.d.get((Object)key));
        }
        if (!Compatibility.file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(Compatibility.file);
        if (loadConfiguration == null) {
            return false;
        }
        final boolean boolean1 = loadConfiguration.getBoolean(key);
        Compatibility.d.put(key, Boolean.valueOf(boolean1));
        return boolean1;
    }
    
    public static File getFile() {
        return Compatibility.file;
    }
    
    public Compatibility(final String s) {
        super();
        this.enabled = a(s + ".enabled");
        this.a = a(s + ".force");
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public boolean c() {
        return this.a;
    }
    
    public static void a(final boolean b) {
        CraftBook.reload();
        CrackShot.reload();
        RealDualWield.reload();
        MagicSpells.reload();
        AdvancedAbilities.reload();
        VeinMiner_Old.reload();
        VeinMiner_New.reload();
        ProjectKorra.reload();
        AcidRain.reload();
        GrapplingHook.reload();
        CrackShotPlus.reload();
        UltimateStatistics.d(b);
        MythicMobs.a(b);
    }
    
    static {
        Compatibility.file = new File(Register.plugin.getDataFolder() + "/compatibility.yml");
        d = new HashMap<String, Boolean>(30);
    }
}
