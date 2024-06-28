package me.vagdedes.spartan.features.c;

import me.vagdedes.spartan.j.NPC;
import org.bukkit.OfflinePlayer;
import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.a.a.Language;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.k.c.PermissionUtils;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.f.SpartanPlayer;

public class StaffChat
{
    public StaffChat() {
        super();
    }
    
    public static boolean c(final SpartanPlayer spartanPlayer, String substring) {
        if (!PermissionUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Enums.Permission.staff_chat) || !Settings.canDo("staff_chat") || !substring.startsWith(Language.getMessage("staff_chat_character"))) {
            return false;
        }
        substring = substring.substring(1);
        final String a = ConfigUtils.a(Bukkit.getPlayer(spartanPlayer.getUniqueId()), Language.getMessage("staff_chat_message").replace((CharSequence)"{message}", (CharSequence)substring), (Enums.HackType)null);
        for (final SpartanPlayer spartanPlayer2 : NPC.a()) {
            if (PermissionUtils.a(Bukkit.getPlayer(spartanPlayer2.getUniqueId()), Enums.Permission.staff_chat)) {
                Bukkit.getPlayer(spartanPlayer2.getUniqueId()).sendMessage(a);
            }
        }
        return true;
    }
}
