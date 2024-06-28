package me.vagdedes.spartan.f;

import me.vagdedes.spartan.k.f.TPS;

public class CheckTPS
{
    private double as;
    private long time;
    
    public CheckTPS() {
        super();
        this.as = TPS.get();
        this.time = System.currentTimeMillis();
    }
    
    public double getTPS() {
        return this.as;
    }
    
    public long getTime() {
        return System.currentTimeMillis() - this.time;
    }
}
