package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanViolationResetEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public HandlerList getHandlers() {
        return VulcanViolationResetEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanViolationResetEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    static {
        handlers = new HandlerList();
    }
}
