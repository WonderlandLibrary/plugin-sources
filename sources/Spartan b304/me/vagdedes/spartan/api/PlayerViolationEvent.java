package me.vagdedes.spartan.api;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PlayerViolationEvent extends Event implements Cancellable
{
    private Player p;
    private Enums.HackType h;
    private String m;
    private int v;
    private boolean fp;
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public PlayerViolationEvent(final Player p5, final Enums.HackType h, final Integer n, final String m, final boolean fp) {
        super();
        this.p = p5;
        this.h = h;
        this.v = n;
        this.m = m;
        this.fp = fp;
        this.cancelled = false;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public Enums.HackType getHackType() {
        return this.h;
    }
    
    public String getMessage() {
        return this.m;
    }
    
    public int getViolation() {
        return this.v;
    }
    
    public boolean isFalsePositive() {
        return this.fp;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PlayerViolationEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerViolationEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
