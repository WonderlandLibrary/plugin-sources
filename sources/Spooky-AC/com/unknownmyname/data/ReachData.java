/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.data;

import com.unknownmyname.data.DistanceData;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;

public class ReachData {
    private final /* synthetic */ double extra;
    private final /* synthetic */ PlayerLocation location;
    private final /* synthetic */ double vertical;
    private final /* synthetic */ double reach;
    private final /* synthetic */ PlayerData playerData;
    private final /* synthetic */ double horizontal;
    private final /* synthetic */ DistanceData distanceData;
    private final /* synthetic */ double movement;

    public DistanceData getDistanceData() {
        return this.distanceData;
    }

    public PlayerLocation getLocation() {
        return this.location;
    }

    public double getHorizontal() {
        return this.horizontal;
    }

    public double getMovement() {
        return this.movement;
    }

    public PlayerData getPlayerData() {
        return this.playerData;
    }

    public ReachData(PlayerData playerData, PlayerLocation location, DistanceData distanceData, double movement, double horizontal, double extra, double vertical, double reach) {
        this.playerData = playerData;
        this.location = location;
        this.distanceData = distanceData;
        this.movement = movement;
        this.horizontal = horizontal;
        this.extra = extra;
        this.vertical = vertical;
        this.reach = reach;
    }

    public double getExtra() {
        return this.extra;
    }

    public double getVertical() {
        return this.vertical;
    }

    public double getReach() {
        return this.reach;
    }
}

