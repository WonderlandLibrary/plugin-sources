package me.vagdedes.spartan.checks.f;

import me.vagdedes.spartan.Register;
import org.bukkit.Material;
import me.vagdedes.spartan.f.HackPrevention;
import me.vagdedes.spartan.k.a.MaterialUtils;
import me.vagdedes.spartan.k.a.BlockUtils;
import me.vagdedes.spartan.a.a.Checks;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.j.ClientSidedBlock;
import me.vagdedes.spartan.f.SpartanBlock;
import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.system.Enums;

public class Liquids
{
    private static final Enums.HackType a;
    
    public Liquids() {
        super();
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock, final SpartanBlock spartanBlock2) {
        final Material b = ClientSidedBlock.b(spartanPlayer, spartanBlock.a());
        if (VL.b(spartanPlayer, Liquids.a, false) || !Checks.getBoolean("Liquids.check_block_placing") || BlockUtils.y(b) || b == MaterialUtils.a("lily_pad") || BlockUtils.m(b) || !BlockUtils.h(spartanBlock2.getType()) || !BlockUtils.F(b) || !spartanBlock.getType().isBlock()) {
            return;
        }
        new HackPrevention(spartanPlayer, Liquids.a, "t: place, b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock));
    }
    
    public static void a(final SpartanPlayer spartanPlayer, final SpartanBlock spartanBlock) {
        final Material b = ClientSidedBlock.b(spartanPlayer, spartanBlock.a());
        if (Register.v1_13 || VL.b(spartanPlayer, Liquids.a, false) || !Checks.getBoolean("Liquids.check_block_breaking") || !BlockUtils.h(b)) {
            return;
        }
        new HackPrevention(spartanPlayer, Liquids.a, "t: break, b: " + BlockUtils.a((SpartanPlayer)null, spartanBlock));
    }
    
    static {
        a = Enums.HackType.Liquids;
    }
}
