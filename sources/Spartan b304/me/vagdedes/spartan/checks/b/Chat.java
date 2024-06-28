package me.vagdedes.spartan.checks.b;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.b.PluginUtils;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Chat
{
    private static final Enums.HackType a;
    private static final boolean f;
    
    public Chat() {
        super();
    }
    
    public static void e(final SpartanPlayer spartanPlayer, final String s) {
        if (VL.b(spartanPlayer, Chat.a, false)) {
            return;
        }
        final int length = s.length();
        final int i = (Chat.f || PluginUtils.exists("viaversion") || PluginUtils.exists("protocolsupport")) ? 256 : 100;
        if (length > i) {
            new HackPrevention(spartanPlayer, Chat.a, "t: illegal chat message length, len: " + length + ", lim: " + i);
        }
        else if (PlayerData.ba(spartanPlayer)) {
            new HackPrevention(spartanPlayer, Chat.a, "t: using chat will being dead");
        }
    }
    
    static {
        a = Enums.HackType.Exploits;
        f = (VersionUtils.a() != VersionUtils.a.l && VersionUtils.a() != VersionUtils.a.c && VersionUtils.a() != VersionUtils.a.d && VersionUtils.a() != VersionUtils.a.e && VersionUtils.a() != VersionUtils.a.f);
    }
}
