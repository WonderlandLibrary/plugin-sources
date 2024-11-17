package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import me.frep.vulcan.api.check.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanPunishEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private final Player player;
    private final Check check;
    private static final HandlerList handlers;
    
    public VulcanPunishEvent(final Player player, final Check check) {
        super(true);
        this.player = player;
        this.check = check;
    }
    
    public HandlerList getHandlers() {
        return VulcanPunishEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanPunishEvent.handlers;
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
    
    static {
        handlers = new HandlerList();
    }
}
