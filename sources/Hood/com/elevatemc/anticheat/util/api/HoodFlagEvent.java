package com.elevatemc.anticheat.util.api;

import com.elevatemc.anticheat.base.check.Check;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HoodFlagEvent extends Event implements Cancellable {

    private final Player player;
    private final double vl;
    private final Check check;
    private String name;
    private static final HandlerList handlers;

    public HoodFlagEvent(final Player player, final Check check, final String name, final double vl) {
        this.player = player;
        this.check = check;
        this.name = name;
        this.vl = vl;
    }

    public boolean isCancelled() {
        return false;
    }

    public void setCancelled(boolean b) {
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getVl() {
        return this.vl;
    }

    public Check getCheck() {
        return this.check;
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    public HandlerList getHandlers() {
        return HoodFlagEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return HoodFlagEvent.handlers;
    }

    static {
        handlers = new HandlerList();
    }
}