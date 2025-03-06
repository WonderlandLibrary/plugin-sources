package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanEnableAlertsEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private final Player player;
    private final long timestamp;
    private static final HandlerList handlers;
    
    public VulcanEnableAlertsEvent(final Player player) {
        this.timestamp = System.currentTimeMillis();
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return VulcanEnableAlertsEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanEnableAlertsEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    static {
        handlers = new HandlerList();
    }
}
