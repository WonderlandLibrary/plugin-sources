package me.vagdedes.spartan.checks.combat.a.c;

import me.vagdedes.spartan.f.SpartanPlayer;
import me.vagdedes.spartan.checks.combat.a.c.a.CAEventListeners;
import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class CACombatEvent extends Event
{
    private Player a;
    private Player b;
    private static final HandlerList handlers;
    
    public CACombatEvent(final Player a, final Player b) {
        super();
        this.a = a;
        this.b = b;
    }
    
    public CAEventListeners.b a() {
        return CAEventListeners.a(new SpartanPlayer(this.a), new SpartanPlayer(this.b));
    }
    
    public Player getDamager() {
        return this.a;
    }
    
    public Player a() {
        return this.b;
    }
    
    public HandlerList getHandlers() {
        return CACombatEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return CACombatEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
