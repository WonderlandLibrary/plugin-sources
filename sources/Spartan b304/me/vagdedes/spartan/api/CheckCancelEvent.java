package me.vagdedes.spartan.api;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class CheckCancelEvent extends Event implements Cancellable
{
    private Player p;
    private Enums.HackType h;
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public CheckCancelEvent(final Player p2, final Enums.HackType h) {
        super();
        this.p = p2;
        this.h = h;
        this.cancelled = false;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public Enums.HackType getHackType() {
        return this.h;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return CheckCancelEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return CheckCancelEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
