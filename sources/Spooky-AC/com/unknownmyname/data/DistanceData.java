/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.data;

import com.unknownmyname.util.Cuboid;

public class DistanceData {
    private final /* synthetic */ double y;
    private final /* synthetic */ Cuboid hitbox;
    private final /* synthetic */ double dist;
    private final /* synthetic */ double z;
    private final /* synthetic */ double x;

    public DistanceData(Cuboid hitbox, double x, double z, double y, double dist) {
        this.hitbox = hitbox;
        this.x = x;
        this.z = z;
        this.y = y;
        this.dist = dist;
    }

    public double getZ() {
        return this.z;
    }

    public double getX() {
        return this.x;
    }

    public double getDist() {
        return this.dist;
    }

    public double getY() {
        return this.y;
    }

    public Cuboid getHitbox() {
        return this.hitbox;
    }
}

