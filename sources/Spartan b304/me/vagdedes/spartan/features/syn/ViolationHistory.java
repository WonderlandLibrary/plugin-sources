package me.vagdedes.spartan.features.syn;

import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.features.b.SearchEngine;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import me.vagdedes.spartan.system.Enums;
import java.util.concurrent.ConcurrentHashMap;

public class ViolationHistory
{
    private ConcurrentHashMap<Enums.HackType, Integer> violations;
    private ConcurrentHashMap<Enums.HackType, ArrayList<String>> days;
    
    public ViolationHistory(final ConcurrentHashMap<Enums.HackType, Integer> violations, final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days) {
        super();
        this.violations = violations;
        this.days = days;
    }
    
    public ConcurrentHashMap<Enums.HackType, Integer> getViolations() {
        return this.violations;
    }
    
    public ConcurrentHashMap<Enums.HackType, ArrayList<String>> getDays() {
        return this.days;
    }
    
    public static ArrayList<String> getLore(final Player player, final Player player2, final Enums.HackType hackType, final ViolationHistory violationHistory) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("");
        if (SynManager.s()) {
            final ConcurrentHashMap<Enums.HackType, Integer> violations = violationHistory.getViolations();
            final ConcurrentHashMap<Enums.HackType, ArrayList<String>> days = violationHistory.getDays();
            list.add("§7Considered§8:§a " + (SearchEngine.a((OfflinePlayer)player2) ? "Hacker" : "Legitimate"));
            list.add("§c" + (violations.containsKey(hackType) ? ((int)Integer.valueOf(violations.get((Object)hackType))) : 0) + " §7violation(s) in §c" + (days.containsKey(hackType) ? ((ArrayList<String>)days.get(hackType)).size() : 0) + " §7day(s).");
            return list;
        }
        list.add(Advertisement.a(player, "Violation History Information"));
        return list;
    }
}
