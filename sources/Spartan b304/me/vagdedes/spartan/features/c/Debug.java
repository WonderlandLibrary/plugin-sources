package me.vagdedes.spartan.features.c;

import org.bukkit.entity.Player;
import java.util.Iterator;
import me.vagdedes.spartan.a.a.Language;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;
import java.util.UUID;
import java.util.HashMap;

public class Debug
{
    private static final HashMap<UUID, HashMap<UUID, String>> q;
    
    public Debug() {
        super();
    }
    
    public static boolean a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2, final String str) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final UUID uniqueId2 = spartanPlayer2.getUniqueId();
        return Debug.q.containsKey(uniqueId) && ((HashMap<UUID, String>)Debug.q.get(uniqueId)).containsKey(uniqueId2) && ((String)((HashMap<UUID, String>)Debug.q.get(uniqueId)).get(uniqueId2)).contains("[" + str + "]");
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2) {
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final UUID uniqueId2 = spartanPlayer2.getUniqueId();
        if (Debug.q.containsKey(uniqueId) && ((HashMap<UUID, String>)Debug.q.get(uniqueId)).containsKey(uniqueId2)) {
            ((HashMap<UUID, String>)Debug.q.get(uniqueId)).remove(uniqueId2);
            if (((HashMap<UUID, String>)Debug.q.get(uniqueId)).keySet().size() == 0) {
                Debug.q.remove(uniqueId);
            }
            Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(Language.getMessage("debug_disable_all_message").replace((CharSequence)"{player}", (CharSequence)spartanPlayer2.getName()));
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanPlayer spartanPlayer2, final String replacement) {
        boolean b = false;
        final UUID uniqueId = spartanPlayer.getUniqueId();
        final UUID uniqueId2 = spartanPlayer2.getUniqueId();
        if (!Debug.q.containsKey(uniqueId)) {
            Debug.q.put(uniqueId, new HashMap<UUID, String>());
        }
        if (((HashMap<UUID, String>)Debug.q.get(uniqueId)).containsKey(uniqueId2)) {
            if (((String)((HashMap<UUID, String>)Debug.q.get(uniqueId)).get(uniqueId2)).contains("[" + replacement + "]")) {
                final String replace;
                ((HashMap<UUID, String>)Debug.q.get(uniqueId)).put(uniqueId2, replace = ((String)((HashMap<UUID, String>)Debug.q.get(uniqueId)).get(uniqueId2)).replace("[" + replacement + "]", ""));
                if (replace.equals("")) {
                    ((HashMap<UUID, String>)Debug.q.get(uniqueId)).remove(uniqueId2);
                    if (((HashMap<UUID, String>)Debug.q.get(uniqueId)).keySet().size() == 0) {
                        Debug.q.remove(uniqueId);
                    }
                }
            }
            else {
                ((HashMap<UUID, String>)Debug.q.get(uniqueId)).put(uniqueId2, ((HashMap<UUID, String>)Debug.q.get(uniqueId)).get(uniqueId2) + "[" + replacement + "]");
                b = true;
            }
        }
        else {
            ((HashMap<UUID, String>)Debug.q.get(uniqueId)).put(uniqueId2, "[" + replacement + "]");
            b = true;
        }
        Bukkit.getPlayer(spartanPlayer.getUniqueId()).sendMessage(Language.getMessage(b ? "debug_enable_message" : "debug_disable_message").replace("{player}", spartanPlayer2.getName()).replace((CharSequence)"{type}", (CharSequence)replacement));
    }
    
    public static void f(final SpartanPlayer spartanPlayer, final String s) {
        a(spartanPlayer, "Movement", s);
    }
    
    public static void g(final SpartanPlayer spartanPlayer, final String s) {
        a(spartanPlayer, "Combat", s);
    }
    
    public static void h(final SpartanPlayer spartanPlayer, final String s) {
        a(spartanPlayer, "Misc", s);
    }
    
    private static void a(final SpartanPlayer spartanPlayer, final String s, final String s2) {
        for (final UUID key : Debug.q.keySet()) {
            final HashMap<UUID, String> hashMap = (HashMap<UUID, String>)Debug.q.get(key);
            if (hashMap.containsKey(spartanPlayer.getUniqueId()) && ((String)hashMap.get(spartanPlayer.getUniqueId())).contains("[" + s + "]")) {
                final Player player = Bukkit.getPlayer(key);
                if (player == null || !player.isOnline()) {
                    continue;
                }
                player.sendMessage(Language.getMessage("debug_player_message").replace("{player}", spartanPlayer.getName()).replace("{type}", s).replace((CharSequence)"{info}", (CharSequence)s2.toLowerCase().replaceAll("_", "-")));
            }
        }
    }
    
    public static int size() {
        return Debug.q.size();
    }
    
    static {
        q = new HashMap<UUID, HashMap<UUID, String>>();
    }
}
