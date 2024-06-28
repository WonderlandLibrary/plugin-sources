package me.vagdedes.spartan.api;

import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.system.Enums;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class CheckSilentToggleEvent extends Event implements Cancellable
{
    private Enums.HackType ht;
    private Enums.ToggleAction ta;
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public CheckSilentToggleEvent(final Enums.HackType ht, final Enums.ToggleAction ta) {
        super();
        this.ht = ht;
        this.ta = ta;
        this.cancelled = false;
    }
    
    public Enums.HackType getHackType() {
        return this.ht;
    }
    
    public Enums.ToggleAction getToggleAction() {
        return this.ta;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return CheckSilentToggleEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return CheckSilentToggleEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
