package me.frep.vulcan.api.event;

import org.bukkit.event.HandlerList;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class VulcanRegisterPlayerEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private final Player player;
    private static final HandlerList handlers;
    
    public VulcanRegisterPlayerEvent(final Player player) {
        super(true);
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return VulcanRegisterPlayerEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return VulcanRegisterPlayerEvent.handlers;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    static {
        handlers = new HandlerList();
    }
}
