package me.vagdedes.spartan.h;

import me.vagdedes.spartan.k.g.AttemptUtils;
import me.vagdedes.spartan.features.a.DefaultConfiguration;
import me.vagdedes.spartan.system.VL;
import me.vagdedes.spartan.system.Enums;
import me.vagdedes.spartan.f.SpartanPlayer;

public class LowViolation
{
    private SpartanPlayer a;
    private Enums.HackType b;
    private String k;
    
    public LowViolation(final SpartanPlayer a, final Enums.HackType b, final String k) {
        super();
        this.a = a;
        this.b = b;
        this.k = k;
    }
    
    public boolean q() {
        return VL.a(this.a, this.b) > DefaultConfiguration.f(this.b) || AttemptUtils.b(this.a, "low-violation=protection=" + this.b.toString() + "=" + this.k, 60) >= 2;
    }
}
