package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanGhostBlockEvent extends Event implements Cancellable
{
    private final Player player;
    private boolean cancelled;
    private final long timestamp;
    private static final HandlerList handlers;
    
    public VulcanGhostBlockEvent(final Player player) {
        super(true);
        this.timestamp = System.currentTimeMillis();
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return VulcanGhostBlockEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanGhostBlockEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    static {
        handlers = new HandlerList();
    }
}
