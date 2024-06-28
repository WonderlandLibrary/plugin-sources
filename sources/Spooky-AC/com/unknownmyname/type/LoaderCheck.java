/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.type;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.aimassist.AimA;
import com.unknownmyname.check.aimassist.AimB;
import com.unknownmyname.check.aimassist.AimC;
import com.unknownmyname.check.aimassist.AimD;
import com.unknownmyname.check.aimassist.AimE;
import com.unknownmyname.check.aimassist.AimF;
import com.unknownmyname.check.aimassist.AimG;
import com.unknownmyname.check.autoclicker.AutoClickerA;
import com.unknownmyname.check.autoclicker.AutoClickerB;
import com.unknownmyname.check.autoclicker.AutoClickerC;
import com.unknownmyname.check.autoclicker.AutoClickerD;
import com.unknownmyname.check.autoclicker.AutoClickerE;
import com.unknownmyname.check.autoclicker.AutoClickerF;
import com.unknownmyname.check.autoclicker.AutoClickerG;
import com.unknownmyname.check.autoclicker.AutoClickerH;
import com.unknownmyname.check.autoclicker.AutoClickerI;
import com.unknownmyname.check.autoclicker.AutoClickerJ;
import com.unknownmyname.check.autoclicker.AutoClickerK;
import com.unknownmyname.check.badpackets.BadPacketsA;
import com.unknownmyname.check.badpackets.BadPacketsB;
import com.unknownmyname.check.badpackets.BadPacketsC;
import com.unknownmyname.check.badpackets.BadPacketsD;
import com.unknownmyname.check.badpackets.BadPacketsE;
import com.unknownmyname.check.badpackets.BadPacketsF;
import com.unknownmyname.check.badpackets.BadPacketsG;
import com.unknownmyname.check.badpackets.BadPacketsH;
import com.unknownmyname.check.badpackets.BadPacketsI;
import com.unknownmyname.check.badpackets.BadPacketsJ;
import com.unknownmyname.check.badpackets.BadPacketsK;
import com.unknownmyname.check.badpackets.BadPacketsL;
import com.unknownmyname.check.badpackets.BadPacketsM;
import com.unknownmyname.check.badpackets.BadPacketsN;
import com.unknownmyname.check.badpackets.BadPacketsO;
import com.unknownmyname.check.fly.FlyA;
import com.unknownmyname.check.fly.FlyB;
import com.unknownmyname.check.fly.FlyC;
import com.unknownmyname.check.fly.FlyD;
import com.unknownmyname.check.fly.FlyE;
import com.unknownmyname.check.fly.FlyF;
import com.unknownmyname.check.fly.FlyG;
import com.unknownmyname.check.fly.FlyH;
import com.unknownmyname.check.fly.FlyI;
import com.unknownmyname.check.fly.FlyJ;
import com.unknownmyname.check.killaura.KillAuraA;
import com.unknownmyname.check.killaura.KillAuraB;
import com.unknownmyname.check.killaura.KillAuraC;
import com.unknownmyname.check.killaura.KillAuraD;
import com.unknownmyname.check.killaura.KillAuraE;
import com.unknownmyname.check.misc.MiscA;
import com.unknownmyname.check.misc.MiscB;
import com.unknownmyname.check.misc.MiscC;
import com.unknownmyname.check.phase.PhaseA;
import com.unknownmyname.check.playload.CustomPayloadA;
import com.unknownmyname.check.reach.ReachA;
import com.unknownmyname.check.reach.ReachB;
import com.unknownmyname.check.speed.SpeedA;
import com.unknownmyname.check.speed.SpeedB;
import com.unknownmyname.check.speed.SpeedC;
import com.unknownmyname.check.speed.SpeedD;
import com.unknownmyname.check.speed.SpeedE;
import com.unknownmyname.check.timer.TimerA;
import com.unknownmyname.check.timer.TimerB;
import com.unknownmyname.check.velocity.VelocityA;
import com.unknownmyname.check.velocity.VelocityB;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoaderCheck {
    private /* synthetic */ List<Class<? extends Check>> checkClasses;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public List<Check> loadChecks() {
        ArrayList<Check> checks = new ArrayList<Check>();
        Iterator<Class<? extends Check>> iterator = this.getCheckClasses().iterator();
        "".length();
        if (2 < 2) {
            throw null;
        }
        do {
            if (!iterator.hasNext()) {
                return checks;
            }
            Class<? extends Check> clazz = iterator.next();
            try {
                checks.add(clazz.newInstance());
                "".length();
            }
            catch (Throwable throwable) {
                // empty catch block
                continue;
            }
            if (-1 != -1) break;
        } while (true);
        throw null;
    }

    public LoaderCheck() {
        this.checkClasses = null;
    }

    public void setCheckClasses(List<Class<? extends Check>> checkClasses) {
        this.checkClasses = checkClasses;
    }

    private List<Class<? extends Check>> getCheckClasses() {
        if (this.checkClasses == null) {
            this.checkClasses = new ArrayList<Class<? extends Check>>();
            this.checkClasses.add(AimA.class);
            this.checkClasses.add(AimB.class);
            this.checkClasses.add(AimC.class);
            this.checkClasses.add(AimD.class);
            this.checkClasses.add(AimE.class);
            this.checkClasses.add(AimF.class);
            this.checkClasses.add(AimG.class);
            this.checkClasses.add(AutoClickerA.class);
            this.checkClasses.add(AutoClickerB.class);
            this.checkClasses.add(AutoClickerC.class);
            this.checkClasses.add(AutoClickerD.class);
            this.checkClasses.add(AutoClickerE.class);
            this.checkClasses.add(AutoClickerF.class);
            this.checkClasses.add(AutoClickerG.class);
            this.checkClasses.add(AutoClickerH.class);
            this.checkClasses.add(AutoClickerI.class);
            this.checkClasses.add(AutoClickerJ.class);
            this.checkClasses.add(AutoClickerK.class);
            this.checkClasses.add(BadPacketsA.class);
            this.checkClasses.add(BadPacketsB.class);
            this.checkClasses.add(BadPacketsC.class);
            this.checkClasses.add(BadPacketsD.class);
            this.checkClasses.add(BadPacketsE.class);
            this.checkClasses.add(BadPacketsF.class);
            this.checkClasses.add(BadPacketsG.class);
            this.checkClasses.add(BadPacketsH.class);
            this.checkClasses.add(BadPacketsI.class);
            this.checkClasses.add(BadPacketsJ.class);
            this.checkClasses.add(BadPacketsK.class);
            this.checkClasses.add(BadPacketsL.class);
            this.checkClasses.add(BadPacketsM.class);
            this.checkClasses.add(BadPacketsN.class);
            this.checkClasses.add(BadPacketsO.class);
            this.checkClasses.add(FlyA.class);
            this.checkClasses.add(FlyB.class);
            this.checkClasses.add(FlyC.class);
            this.checkClasses.add(FlyD.class);
            this.checkClasses.add(FlyE.class);
            this.checkClasses.add(FlyF.class);
            this.checkClasses.add(FlyG.class);
            this.checkClasses.add(FlyH.class);
            this.checkClasses.add(FlyI.class);
            this.checkClasses.add(FlyJ.class);
            this.checkClasses.add(KillAuraA.class);
            this.checkClasses.add(KillAuraB.class);
            this.checkClasses.add(KillAuraD.class);
            this.checkClasses.add(KillAuraC.class);
            this.checkClasses.add(KillAuraD.class);
            this.checkClasses.add(KillAuraE.class);
            this.checkClasses.add(MiscA.class);
            this.checkClasses.add(MiscB.class);
            this.checkClasses.add(MiscC.class);
            this.checkClasses.add(PhaseA.class);
            this.checkClasses.add(CustomPayloadA.class);
            this.checkClasses.add(ReachA.class);
            this.checkClasses.add(ReachB.class);
            this.checkClasses.add(SpeedA.class);
            this.checkClasses.add(SpeedB.class);
            this.checkClasses.add(SpeedC.class);
            this.checkClasses.add(SpeedD.class);
            this.checkClasses.add(SpeedE.class);
            this.checkClasses.add(TimerA.class);
            this.checkClasses.add(TimerB.class);
            this.checkClasses.add(VelocityA.class);
            this.checkClasses.add(VelocityB.class);
        }
        return this.checkClasses;
    }
}

