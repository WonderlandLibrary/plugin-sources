package me.vagdedes.spartan.f;

public class CheckLatency
{
    private int ping;
    private long time;
    
    public CheckLatency(final SpartanPlayer spartanPlayer) {
        super();
        this.ping = spartanPlayer.getPing();
        this.time = System.currentTimeMillis();
    }
    
    public int getPing() {
        return this.ping;
    }
    
    public long getTime() {
        return System.currentTimeMillis() - this.time;
    }
}
