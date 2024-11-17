package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanJudgementDayEndEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public VulcanJudgementDayEndEvent() {
        super(true);
    }
    
    public HandlerList getHandlers() {
        return VulcanJudgementDayEndEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanJudgementDayEndEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
    
    static {
        handlers = new HandlerList();
    }
}
