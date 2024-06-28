/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 */
package com.unknownmyname.check;

import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class EventCheck
extends Check {
    public EventCheck(Check.CheckType subType, String type, String friendlyName, Check.CheckVersion checkVersion) {
        super(subType, type, friendlyName, checkVersion);
    }

    public abstract void handle(Player var1, PlayerData var2, Event var3, long var4);
}

