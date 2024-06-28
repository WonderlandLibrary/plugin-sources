/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check;

import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import org.bukkit.entity.Player;

public abstract class AimCheck
extends Check {
    public abstract void handle(Player var1, PlayerData var2, PlayerLocation var3, PlayerLocation var4, long var5);

    public AimCheck(Check.CheckType subType, String type, String friendlyName, Check.CheckVersion checkVersion) {
        super(subType, type, friendlyName, checkVersion);
    }
}

