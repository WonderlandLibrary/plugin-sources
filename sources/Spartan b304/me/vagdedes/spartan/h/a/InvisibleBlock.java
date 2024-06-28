package me.vagdedes.spartan.h.a;

import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.k.c.PunishUtils;
import java.util.UUID;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;

public class InvisibleBlock
{
    public InvisibleBlock() {
        super();
    }
    
    public static void G(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, "invisible-block", 40);
    }
    
    public static void d(final UUID uuid) {
        CooldownUtils.b(uuid, "invisible-block", 40);
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final double n) {
        if (!CooldownUtils.g(spartanPlayer, "invisible-block") || n > 0.5 || PunishUtils.bg(spartanPlayer) || !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) {
            return;
        }
        H(spartanPlayer);
    }
    
    public static void H(final SpartanPlayer spartanPlayer) {
        if (!BlockUtils.J(spartanPlayer, spartanPlayer.a().b(0.0, -0.6, 0.0))) {
            ClientSidedBlock.f(spartanPlayer, spartanPlayer.a());
            ClientSidedBlock.f(spartanPlayer, spartanPlayer.a().b(0.0, 1.0, 0.0));
            ClientSidedBlock.f(spartanPlayer, spartanPlayer.a().b(0.0, -1.0, 0.0));
        }
    }
}
