package me.vagdedes.spartan.checks.combat.a.c;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class CAResultEvent extends Event
{
    private Player p;
    private CAEventListeners.a a;
    private CAEventListeners.b a;
    private double a;
    private double b;
    private static final HandlerList handlers;
    
    public CAResultEvent(final Player p5, final CAEventListeners.a a, final CAEventListeners.b a2, final double a3, final double b) {
        super();
        this.p = p5;
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.b = b;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public CAEventListeners.a a() {
        return this.a;
    }
    
    public CAEventListeners.b a() {
        return this.a;
    }
    
    public double a() {
        return this.a;
    }
    
    public double b() {
        return this.b;
    }
    
    public HandlerList getHandlers() {
        return CAResultEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return CAResultEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
