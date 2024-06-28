package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.k.f.VersionUtils;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.h.BlockPlace;
import me.vagdedes.spartan.k.g.MillisUtils;
import me.vagdedes.spartan.k.c.LagManagement;
import org.bukkit.potion.PotionEffectType;
import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.k.d.MathUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import org.bukkit.GameMode;
import me.vagdedes.spartan.f.SpartanLocation;
import me.vagdedes.spartan.k.g.AttemptUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.PlayerData;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanBlock;
import org.bukkit.event.block.Action;
import me.vagdedes.spartan.k.g.CooldownUtils;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class ImpossibleActions
{
    private static final Enums.HackType a;
    private static final boolean c;
    
    public ImpossibleActions() {
        super();
    }
    
    public static void j(final SpartanPlayer spartanPlayer) {
        CooldownUtils.d(spartanPlayer, ImpossibleActions.a.toString() + "=interact", 20);
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final Action action, final SpartanBlock spartanBlock) {
        if (VL.b(spartanPlayer, ImpossibleActions.a, true) || !Checks.getBoolean("ImpossibleActions.check_actions") || !CooldownUtils.g(spartanPlayer, ImpossibleActions.a.toString() + "=interact") || (action != Action.LEFT_CLICK_AIR && action != Action.LEFT_CLICK_BLOCK && action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        int n = 0;
        if (spartanPlayer.isSleeping()) {
            n = 1;
        }
        else if (PlayerData.aZ(spartanPlayer)) {
            n = 2;
        }
        else if (PlayerData.aI(spartanPlayer)) {
            n = 3;
        }
        if (n != 0) {
            final String replaceAll = action.toString().toLowerCase().replaceAll("_", "-");
            if (spartanBlock == null) {
                new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: interact, a: " + replaceAll + ", c: " + n);
            }
            else {
                new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: interact, a: " + replaceAll + ", c: " + n + ", b: " + BlockUtils.a(spartanPlayer, spartanBlock));
            }
        }
    }
    
    public static void r(final SpartanPlayer spartanPlayer) {
        if (VL.b(spartanPlayer, ImpossibleActions.a, true) || PlayerData.aF(spartanPlayer)) {
            return;
        }
        final SpartanLocation a = spartanPlayer.a();
        final double n = 0.9375;
        final double n2 = a.getY() - a.getBlockY();
        final double n3 = (n2 < n) ? 0.0 : (1.0 - n2);
        if ((BlockUtils.f(spartanPlayer, a) || a.a().getType() == Material.CACTUS) && !BlockUtils.f(spartanPlayer, a.b().b(0.0, 1.0, 0.0)) && BlockUtils.g(spartanPlayer, true, 0.3, n3, 0.3) && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || spartanPlayer.isOnGround()) && n2 != n && a.b().b(0.0, -0.5, 0.0).a().getType() == Material.CACTUS && AttemptUtils.b(spartanPlayer, ImpossibleActions.a.toString() + "=cactus", 20) >= 5 && CooldownUtils.g(spartanPlayer, ImpossibleActions.a.toString() + "=cactus")) {
            new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: cactus", null, 0, false, 1.0);
            CooldownUtils.d(spartanPlayer, ImpossibleActions.a.toString() + "=cactus", 2);
        }
    }
    
    public static void d(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        final SpartanLocation a = spartanBlock.a();
        final GameMode gameMode;
        if (VL.b(spartanPlayer, ImpossibleActions.a, true) || spartanBlock.getType() == Material.FIRE || !spartanBlock.getType().isBlock() || PlayerData.aR(spartanPlayer) || ((gameMode = spartanPlayer.getGameMode()) != GameMode.SURVIVAL && gameMode != GameMode.ADVENTURE)) {
            return;
        }
        final SpartanLocation a2 = spartanPlayer.a();
        if ((AttemptUtils.f(spartanPlayer, ImpossibleActions.a.toString() + "=block=y") ? ((double)(a.getBlockY() - AttemptUtils.a(spartanPlayer, ImpossibleActions.a.toString() + "=block=y"))) : 0.0) > 0.0) {
            AttemptUtils.m(spartanPlayer, ImpossibleActions.a.toString() + "=scaffold=custom");
        }
        if (a.getBlockY() == a2.getBlockY() - 1) {
            final SpartanLocation c = MoveUtils.c(spartanPlayer);
            final double b = MathUtils.b(a2.a().a(), a);
            final boolean b2 = BlockUtils.I(spartanPlayer, spartanPlayer.a()) && !Register.v1_13;
            final int integer = Checks.getInteger("ImpossibleActions.cancel_seconds");
            final int n = (integer > 60) ? 60 : integer;
            if (c != null) {
                final double b3 = MathUtils.b(spartanPlayer.a(), c);
                final int a3 = PlayerData.a(spartanPlayer, PotionEffectType.JUMP);
                if (Checks.getBoolean("ImpossibleActions.check_tower") && b3 <= 0.15 && a3 <= 1 && BlockUtils.a(spartanPlayer, a, spartanBlock.getType(), 0.1) && BlockUtils.a(spartanPlayer, a.b().b(0.0, -1.0, 0.0), spartanBlock.getType(), 0.1) && BlockUtils.a(spartanPlayer, a.b().b(0.0, -2.0, 0.0), spartanBlock.getType(), 0.1)) {
                    final int a4 = LagManagement.a(spartanPlayer, (a3 != -1 && !PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0)) ? 7 : 5);
                    final String a5 = BlockUtils.a(spartanPlayer, spartanBlock);
                    if (AttemptUtils.b(spartanPlayer, ImpossibleActions.a.toString() + "=tower=normal", 20) >= a4) {
                        new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: tower(normal), b: " + a5, n * 20);
                    }
                    if (MillisUtils.a(spartanPlayer, ImpossibleActions.a.toString() + "=tower") <= 300L && AttemptUtils.b(spartanPlayer, ImpossibleActions.a.toString() + "=tower=constant", 20) >= a4 - 1) {
                        new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: tower(constant), b: " + a5, n * 20);
                    }
                    MillisUtils.o(spartanPlayer, ImpossibleActions.a.toString() + "=tower");
                }
            }
            final SpartanLocation b4 = a.b().b(0.0, -1.0, 0.0);
            if (Checks.getBoolean("ImpossibleActions.check_scaffold") && b <= 1.0 && (!BlockUtils.f(spartanPlayer, b4) || b4.a().getType() == spartanPlayer.getItemInHand().getType()) && (PlayerData.i(spartanPlayer, 0.0, 0.0, 0.0) || PlayerData.i(spartanPlayer, 0.0, -1.0, 0.0) || spartanPlayer.isOnGround() || b2)) {
                final double i = MoveUtils.i(spartanPlayer);
                final boolean b5 = MoveUtils.p(spartanPlayer) >= 5;
                final boolean b6 = BlockPlace.E(spartanPlayer) && MoveUtils.g(spartanPlayer) <= 400L;
                final double f = LagManagement.f(spartanPlayer, MoveUtils.a(spartanPlayer, b2 ? 0.16 : (b6 ? 0.48 : (b5 ? 0.18 : 0.22)), b2 ? 8.0 : 4.0, PotionEffectType.SPEED));
                final boolean b7 = i >= f;
                int n2;
                if (b7) {
                    n2 = AttemptUtils.b(spartanPlayer, ImpossibleActions.a.toString() + "=scaffold=custom", 20);
                }
                else {
                    n2 = AttemptUtils.a(spartanPlayer, ImpossibleActions.a.toString() + "=scaffold=custom");
                }
                final int b8 = AttemptUtils.b(spartanPlayer, ImpossibleActions.a.toString() + "=scaffold=normal", 12);
                if (b7) {
                    if (b8 >= 3) {
                        new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: scaffold(normal), b: " + BlockUtils.a(spartanPlayer, spartanBlock) + ", d: " + i + ", l: " + f + ", h: " + b, n * 20);
                    }
                    else if (n2 >= 3) {
                        new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: scaffold(custom), b: " + BlockUtils.a(spartanPlayer, spartanBlock) + ", d: " + i + ", l: " + f + ", h: " + b, n * 20);
                    }
                }
            }
            AttemptUtils.c(spartanPlayer, ImpossibleActions.a.toString() + "=block=y", a.getBlockY());
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (ImpossibleActions.c || VL.b(spartanPlayer, ImpossibleActions.a, true) || ClientSidedBlock.a(spartanPlayer, spartanBlock.a()) != null || !PlayerData.aH(spartanPlayer) || !Checks.getBoolean("ImpossibleActions.check_item_usage") || BlockUtils.E(spartanPlayer, spartanBlock.a())) {
            return;
        }
        new HackPrevention(spartanPlayer, ImpossibleActions.a, "t: item usage, e: break, b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock));
    }
    
    static {
        a = Enums.HackType.ImpossibleActions;
        c = (VersionUtils.a() == VersionUtils.a.l || VersionUtils.a() == VersionUtils.a.c);
    }
}
