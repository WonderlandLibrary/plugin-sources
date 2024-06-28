package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.k.d.TimeUtils;
import me.vagdedes.spartan.k.d.StringUtils;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.ChatColor;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.k.c.SynManager;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import me.vagdedes.spartan.k.a.MaterialUtils;
import org.bukkit.inventory.ItemStack;

public class DailyProgress
{
    private int w;
    private int A;
    private int B;
    private int C;
    private static DailyProgress a;
    private static DailyProgress b;
    private static String h;
    
    public DailyProgress(final int w, final int a, final int c, final int b) {
        super();
        this.w = w;
        this.A = a;
        this.B = b;
        this.C = c;
    }
    
    public int d() {
        return this.w;
    }
    
    public int e() {
        return this.B;
    }
    
    public int f() {
        return this.A;
    }
    
    public int g() {
        return this.C;
    }
    
    public static void refresh() {
        DailyProgress.h = null;
        DailyProgress.a = null;
        DailyProgress.b = null;
    }
    
    public static ItemStack getItem() {
        return new ItemStack(MaterialUtils.a("exp_bottle"));
    }
    
    public static ArrayList<String> a(final Player player, final boolean b) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        if (SynManager.s()) {
            if (!SearchEngine.d()) {
                list.add(ChatColor.GRAY + StringUtils.getClearColorString(Language.getMessage("disabled_log_saving")).replace((CharSequence)"[Spartan] ", (CharSequence)""));
            }
            else if (b) {
                final String substring = TimeUtils.a(0, 0, 0).toString().substring(0, 10);
                if (DailyProgress.h == null || !DailyProgress.h.equals(substring)) {
                    DailyProgress.a = SearchEngine.a(0);
                    DailyProgress.b = SearchEngine.a(-1);
                    DailyProgress.h = substring;
                }
                list.add("§2Today§8:");
                list.add("§7Logs§8:§a " + DailyProgress.a.g());
                list.add("§7Violations§8:§e " + DailyProgress.a.d());
                list.add("§7False Positives§8:§e " + DailyProgress.a.e());
                list.add("§7Punishments§8:§c " + DailyProgress.a.f());
                list.add("");
                list.add("§4Yesterday§8:");
                list.add("§7Logs§8:§a " + DailyProgress.b.g());
                list.add("§7Violations§8:§e " + DailyProgress.b.d());
                list.add("§7False Positives§8:§e " + DailyProgress.b.e());
                list.add("§7Punishments§8:§c " + DailyProgress.b.f());
            }
            else {
                list.add("§7Click to load the information.");
            }
            return list;
        }
        list.add(Advertisement.a(player, "Daily Progress Statistics"));
        list.add(Advertisement.d());
        return list;
    }
    
    static {
        DailyProgress.a = null;
        DailyProgress.b = null;
        DailyProgress.h = null;
    }
}
