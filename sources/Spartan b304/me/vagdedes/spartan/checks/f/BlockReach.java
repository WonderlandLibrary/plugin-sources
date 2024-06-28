package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.Register;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.f.LatencyUtils;
import me.vagdedes.spartan.k.a.MoveUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import org.bukkit.Material;
import me.vagdedes.spartan.c.MineBomb;
import me.vagdedes.spartan.c.TreeFeller;
import me.vagdedes.spartan.h.Teleport;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class BlockReach
{
    private static final double c = 7.5;
    private static final double an = 16.0;
    private static final Enums.HackType a;
    
    public BlockReach() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (VL.b(spartanPlayer, BlockReach.a, true) || Teleport.E(spartanPlayer) || TreeFeller.b(spartanPlayer, spartanBlock) || MineBomb.B(spartanPlayer) || spartanPlayer.getItemInHand().getType() == Material.FLINT_AND_STEEL || BlockUtils.c(spartanBlock.getType())) {
            return;
        }
        final double a = MoveUtils.a(spartanPlayer.a(), spartanBlock.a());
        if (a >= (LatencyUtils.e(spartanPlayer, 200) ? 15.0 : 7.5) && a <= 16.0) {
            new HackPrevention(spartanPlayer, BlockReach.a, "t: break, d: " + a);
        }
    }
    
    public static void b(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        if (spartanBlock == null || !BlockUtils.F(spartanPlayer, spartanBlock.a()) || VL.b(spartanPlayer, BlockReach.a, true) || Teleport.E(spartanPlayer) || TreeFeller.b(spartanPlayer, spartanBlock) || BlockUtils.c(spartanBlock.getType())) {
            return;
        }
        final double a = MoveUtils.a(spartanPlayer.a(), spartanBlock.a());
        if (a >= (LatencyUtils.e(spartanPlayer, 200) ? 15.0 : 7.5) && a <= 16.0) {
            new HackPrevention(spartanPlayer, BlockReach.a, "t: interact, d: " + a);
        }
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final SpartanBlock spartanBlock2) {
        if (VL.b(spartanPlayer, BlockReach.a, true) || Teleport.E(spartanPlayer) || !BlockUtils.c(spartanPlayer, spartanBlock2.a(), true) || spartanPlayer.getItemInHand().getType() == (Register.v1_13 ? Material.BONE_MEAL : Material.getMaterial("INK_SACK")) || BlockUtils.c(spartanBlock.getType())) {
            return;
        }
        final double d = spartanBlock.a().getY() - spartanPlayer.a().getY();
        final double a = MoveUtils.a(spartanPlayer.a(), spartanBlock.a());
        final double d2 = MoveUtils.a(spartanPlayer.a(), spartanBlock2.a()) + 0.4;
        if (a >= 1.3 && a > d2 && d <= 0.5 && spartanPlayer.getVehicle() == null && a <= 16.0) {
            new HackPrevention(spartanPlayer, BlockReach.a, "t: unusual, d: " + a + ", ab_d: " + d2 + ", ypos: " + d);
        }
        else if (a >= (LatencyUtils.e(spartanPlayer, 200) ? 15.0 : 7.5) && a <= 16.0) {
            new HackPrevention(spartanPlayer, BlockReach.a, "t: place, d: " + a);
        }
    }
    
    static {
        a = Enums.HackType.BlockReach;
    }
}
