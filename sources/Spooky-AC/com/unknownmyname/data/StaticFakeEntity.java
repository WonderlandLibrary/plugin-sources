/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.server.v1_8_R3.DataWatcher
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn
 *  net.minecraft.server.v1_8_R3.World
 *  net.minecraft.server.v1_8_R3.WorldServer
 */
package com.unknownmyname.data;

import com.mojang.authlib.GameProfile;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.util.SafeReflection;
import java.util.Collections;
import java.util.UUID;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;

public class StaticFakeEntity {
    private final /* synthetic */ GameProfile gameProfile;
    private /* synthetic */ Entity entity;
    private final /* synthetic */ int oldId;
    private /* synthetic */ int entityId;
    private final /* synthetic */ PlayerLocation location;

    public PlayerLocation getLocation() {
        return this.location;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public GameProfile getGameProfile() {
        return this.gameProfile;
    }

    public int getOldId() {
        return this.oldId;
    }

    public PacketPlayOutEntityTeleport teleport(double x, double y, double z) {
        this.entity.locX = x;
        this.entity.locY = y;
        this.entity.locZ = z;
        this.entity.onGround = " ".length();
        return new PacketPlayOutEntityTeleport(this.entity);
    }

    public PacketPlayOutEntityDestroy destroy() {
        int[] arrn = new int[" ".length()];
        arrn["".length()] = this.entityId;
        return new PacketPlayOutEntityDestroy(arrn);
    }

    public PacketPlayOutNamedEntitySpawn create(WorldServer worldServer) {
        this.entity = new Entity((World)worldServer){

            protected void h() {
            }

            protected void a(NBTTagCompound nbtTagCompound) {
            }

            protected void b(NBTTagCompound nbtTagCompound) {
            }
        };
        this.entityId = this.entity.getId();
        UUID uuid = this.gameProfile.getId();
        int c = (int)Math.floor(this.location.getX() * 32.0);
        int d = (int)Math.floor(this.location.getY() * 32.0);
        int e = (int)Math.floor(this.location.getZ() * 32.0);
        byte f = (byte)(this.location.getYaw() * 256.0f / 360.0f);
        byte g = (byte)(this.location.getPitch() * 256.0f / 360.0f);
        DataWatcher i = this.entity.getDataWatcher();
        i.watch("".length(), (Object)(0x33 ^ 0x13));
        return SafeReflection.spawn(this.entityId, uuid, c, d, e, f, g, "".length(), i, Collections.emptyList());
    }

    public StaticFakeEntity(int oldId, PlayerLocation location, GameProfile gameProfile) {
        this.entityId = -" ".length();
        this.oldId = oldId;
        this.location = location;
        this.gameProfile = gameProfile;
    }

}

