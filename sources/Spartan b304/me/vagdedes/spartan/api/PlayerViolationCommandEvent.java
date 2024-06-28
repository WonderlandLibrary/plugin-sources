package me.vagdedes.spartan.api;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class PlayerViolationCommandEvent extends Event implements Cancellable
{
    private Player p;
    private Enums.HackType h;
    private String c;
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public PlayerViolationCommandEvent(final Player p3, final Enums.HackType h, final String c) {
        super();
        this.p = p3;
        this.h = h;
        this.c = c;
        this.cancelled = false;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public Enums.HackType getHackType() {
        return this.h;
    }
    
    public String getCommand() {
        return this.c;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return PlayerViolationCommandEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerViolationCommandEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
