/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check;

import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.ReachData;
import org.bukkit.entity.Player;

public abstract class ReachCheck
extends Check {
    public abstract void handle(Player var1, PlayerData var2, ReachData var3, long var4);

    public ReachCheck(Check.CheckType subType, String type, String friendlyName, Check.CheckVersion checkVersion) {
        super(subType, type, friendlyName, checkVersion);
    }
}

