package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanJudgementDayStartEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public VulcanJudgementDayStartEvent() {
        super(true);
    }
    
    public HandlerList getHandlers() {
        return VulcanJudgementDayStartEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanJudgementDayStartEvent.handlers;
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
