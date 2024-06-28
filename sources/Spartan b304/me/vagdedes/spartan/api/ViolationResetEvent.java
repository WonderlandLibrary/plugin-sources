package me.vagdedes.spartan.api;

import java.util.Iterator;
import org.bukkit.event.HandlerList;
import me.vagdedes.spartan.system.Enums;
import java.util.ArrayList;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public class ViolationResetEvent extends Event implements Cancellable
{
    private boolean cancelled;
    private ArrayList<Enums.HackType> ignore;
    private static final HandlerList handlers;
    
    public ViolationResetEvent() {
        super();
        this.ignore = new ArrayList<Enums.HackType>(Enums.HackType.values().length);
        this.cancelled = false;
    }
    
    public void setIgnoredChecks(final ArrayList<Enums.HackType> list) {
        if (list != null) {
            final Iterator<Enums.HackType> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.ignoreCheck(iterator.next());
            }
        }
    }
    
    public void ignoreCheck(final Enums.HackType hackType) {
        if (!this.ignore.contains(hackType)) {
            this.ignore.add(hackType);
        }
    }
    
    public ArrayList<Enums.HackType> getIgnoredChecks() {
        return this.ignore;
    }
    
    public int getTime() {
        return API.getViolationResetTime();
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public HandlerList getHandlers() {
        return ViolationResetEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return ViolationResetEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
