package me.vagdedes.spartan.features.syn;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.k.e.ClickableMessage;
import me.vagdedes.spartan.k.f.ErrorUtils;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.a.SpartanBukkit;
import me.vagdedes.spartan.k.c.SynManager;
import org.bukkit.entity.Player;
import java.util.UUID;
import java.util.HashMap;

public class Advertisement
{
    private static final HashMap<UUID, String> q;
    
    public Advertisement() {
        super();
    }
    
    public static void clear() {
        Advertisement.q.clear();
    }
    
    public static String a(final Player player, final String str) {
        if (!SynManager.s() && player != null) {
            a(player, str);
        }
        return "§7Get §e" + str + " §7with a §6Syn Membership§7.";
    }
    
    public static String d() {
        return "§cExecute the command '/spartan syn' to learn more.";
    }
    
    private static void a(final Player player, final String str) {
        final UUID uniqueId = player.getUniqueId();
        boolean b = false;
        final String string = "[" + str + "]";
        if (!Advertisement.q.containsKey(uniqueId)) {
            Advertisement.q.put(uniqueId, string);
            b = true;
        }
        else {
            final String str2 = (String)Advertisement.q.get(uniqueId);
            if (!str2.contains(string)) {
                Advertisement.q.put(uniqueId, str2 + string);
                b = true;
            }
        }
        if (b) {
            final SpartanPlayer a = SpartanBukkit.a(uniqueId);
            final String s = "syn-membership=notification";
            if (CooldownUtils.g(a, s)) {
                final String d = ErrorUtils.d("Spartan is a constantly maintained plugin with its updates & support remaining free of charge. For this reason, it has a membership that offers advanced moderation features & system advancements. If you wish to further support the development of this plugin, please click this message. Alternatively, you can execute the command '/spartan syn'.");
                if (d != null) {
                    ClickableMessage.a(player, d, "Syn Membership", "/spartan syn", true);
                    CooldownUtils.d(a, s, 2);
                }
            }
        }
    }
    
    static {
        q = new HashMap<UUID, String>();
    }
}
