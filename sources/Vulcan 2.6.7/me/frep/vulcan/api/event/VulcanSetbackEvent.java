package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import me.frep.vulcan.api.check.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanSetbackEvent extends Event implements Cancellable
{
    private final Player player;
    private boolean cancelled;
    private final Check check;
    private final long timestamp;
    private static final HandlerList handlers;
    
    public VulcanSetbackEvent(final Player player, final Check check) {
        super(true);
        this.timestamp = System.currentTimeMillis();
        this.player = player;
        this.check = check;
    }
    
    public HandlerList getHandlers() {
        return VulcanSetbackEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanSetbackEvent.handlers;
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
    
    public Check getCheck() {
        return this.check;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    static {
        handlers = new HandlerList();
    }
}
