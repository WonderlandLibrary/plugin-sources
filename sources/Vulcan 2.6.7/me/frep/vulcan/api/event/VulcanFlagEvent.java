package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import me.frep.vulcan.api.check.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanFlagEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private final Player player;
    private final Check check;
    private final String info;
    private final long timestamp;
    private static final HandlerList handlers;
    
    public VulcanFlagEvent(final Player player, final Check check, final String info) {
        super(true);
        this.timestamp = System.currentTimeMillis();
        this.player = player;
        this.check = check;
        this.info = info;
    }
    
    public HandlerList getHandlers() {
        return VulcanFlagEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanFlagEvent.handlers;
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
    
    public String getInfo() {
        return this.info;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
    
    static {
        handlers = new HandlerList();
    }
}
