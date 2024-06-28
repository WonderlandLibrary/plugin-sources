package me.vagdedes.spartan.h;

import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.k.a.GroundUtils;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import me.vagdedes.spartan.a.a.Settings;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.PlayerData;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;

public class Building
{
    public Building() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final Action action) {
        if (PlayerData.aR(spartanPlayer) || spartanBlock == null) {
            return;
        }
        final double a = MoveUtils.a(spartanPlayer.a(), spartanBlock.a());
        if (action == Action.RIGHT_CLICK_BLOCK && spartanPlayer.a().getY() >= spartanBlock.a().getY() + 0.5 && a >= 0.5 && a <= 3.0) {
            boolean b = false;
            if (spartanPlayer.getItemInHand().getItemMeta() != null && spartanPlayer.getItemInHand().getType().isBlock()) {
                b = true;
            }
            else if (Register.v1_9 && spartanPlayer.a().getItemInOffHand().getItemMeta() != null && spartanPlayer.a().getItemInOffHand().getType().isBlock()) {
                b = true;
            }
            if (b) {
                final SpartanLocation a2 = spartanPlayer.a();
                final SpartanLocation a3 = spartanBlock.a();
                final SpartanLocation b2 = a3.b().b(0.0, 1.0, 0.0);
                if (a2.getBlockX() == a3.getBlockX() && a2.getBlockZ() == a3.getBlockZ() && !BlockUtils.f(spartanPlayer, b2) && (spartanPlayer.isSneaking() || !BlockUtils.F(spartanPlayer, a3)) && a > 2.0 && a < 2.5) {
                    CooldownUtils.d(spartanPlayer, "building=protection", 60);
                }
            }
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final boolean b) {
        if (PlayerData.aR(spartanPlayer)) {
            return;
        }
        final SpartanLocation a = spartanBlock.a();
        final SpartanLocation a2 = spartanPlayer.a();
        final SpartanLocation b2 = a2.b().b(0.0, -1.0, 0.0);
        int n = 0;
        if (!PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) && ClientSidedBlock.a(spartanPlayer, b2) == null) {
            if (b2.getBlockX() == a.getBlockX() && b2.getBlockY() == a.getBlockY() && b2.getBlockZ() == a.getBlockZ()) {
                n = 1;
            }
            else if (!BlockUtils.c(spartanPlayer, false, 0.3, -1.0, 0.3)) {
                n = 2;
            }
        }
        if (n == 1 && b && ClientSidedBlock.a(spartanPlayer, a.b().b(0.0, -1.0, 0.0)) == null) {
            if (Settings.canDo("Protections.disallowed_building")) {
                Teleport.E(spartanPlayer);
                a2.setPitch(0.0f);
                Bukkit.getPlayer(spartanPlayer.getUniqueId()).teleport(new Location(a2.getWorld(), a2.getX(), a2.getY(), a2.getZ(), a2.getYaw(), a2.getPitch()));
                CooldownUtils.d(spartanPlayer, "building=protection", 10);
            }
            else {
                CooldownUtils.d(spartanPlayer, "building=protection", 60);
            }
        }
        else if (n != 0) {
            CooldownUtils.d(spartanPlayer, "building=protection", 60);
            if (!b) {
                GroundUtils.j(spartanPlayer, 8);
            }
        }
    }
    
    public static boolean E(final SpartanPlayer spartanPlayer) {
        return !CooldownUtils.g(spartanPlayer, "building=protection") && VL.a(spartanPlayer, Enums.HackType.ImpossibleActions) == 0 && VL.a(spartanPlayer, Enums.HackType.FastPlace) == 0;
    }
}
