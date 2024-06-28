package me.vagdedes.spartan.api;

import me.vagdedes.spartan.Register;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class SpartanReloadEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private static final HandlerList handlers;
    
    public SpartanReloadEvent() {
        super();
        this.cancelled = false;
    }
    
    public Plugin getPlugin() {
        return Register.plugin;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return SpartanReloadEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return SpartanReloadEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
