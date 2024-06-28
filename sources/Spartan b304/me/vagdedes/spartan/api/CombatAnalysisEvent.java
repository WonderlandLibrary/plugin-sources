package me.vagdedes.spartan.api;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class CombatAnalysisEvent extends Event
{
    private Player p;
    private CAEventListeners.b a;
    private double a;
    private double b;
    private static final HandlerList handlers;
    
    public CombatAnalysisEvent(final Player p4, final CAEventListeners.b a, final double a2, final double b) {
        super();
        this.p = p4;
        this.a = a;
        this.a = a2;
        this.b = b;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public boolean a() {
        return this.a == CAEventListeners.b.c;
    }
    
    public boolean b() {
        return this.a == CAEventListeners.b.b;
    }
    
    public double a() {
        return this.a;
    }
    
    public double b() {
        return this.b;
    }
    
    public HandlerList getHandlers() {
        return CombatAnalysisEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return CombatAnalysisEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
