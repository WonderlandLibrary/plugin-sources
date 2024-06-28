/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.data;

public class VelocityPacket {
    private final /* synthetic */ int y;
    private final /* synthetic */ int x;
    private final /* synthetic */ int entityId;
    private final /* synthetic */ int z;

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public VelocityPacket(int entityId, int x, int y, int z) {
        this.entityId = entityId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return this.x;
    }
}

