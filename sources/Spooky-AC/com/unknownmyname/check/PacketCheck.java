/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check;

import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

public abstract class PacketCheck
extends Check {
    public abstract void handle(Player var1, PlayerData var2, Packet var3, long var4);

    public PacketCheck(Check.CheckType type, String subType, String friendlyName, Check.CheckVersion checkVersion) {
        super(type, subType, friendlyName, checkVersion);
    }
}

