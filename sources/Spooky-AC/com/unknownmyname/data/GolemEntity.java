/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityIronGolem
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  net.minecraft.server.v1_8_R3.World
 *  net.minecraft.server.v1_8_R3.WorldServer
 */
package com.unknownmyname.data;

import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.util.SafeReflection;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.World;
import net.minecraft.server.v1_8_R3.WorldServer;

public class GolemEntity {
    private final /* synthetic */ PlayerData playerData;
    private /* synthetic */ int entityId;

    public PlayerData getPlayerData() {
        return this.playerData;
    }

    public GolemEntity(PlayerData playerData) {
        this.entityId = -" ".length();
        this.playerData = playerData;
    }

    public void handle(PlayerData otherData, Packet packet) {
        int entityId;
        PacketPlayOutEntityTeleport packetPlayOutEntityTeleport;
        if (packet instanceof PacketPlayOutEntity) {
            PacketPlayOutEntity packetPlayOutEntity = (PacketPlayOutEntity)packet;
            int entityId2 = SafeReflection.getEntityId(packetPlayOutEntity);
            if (entityId2 == this.playerData.getEntityPlayer().getId()) {
                otherData.getEntityPlayer().playerConnection.sendPacket((Packet)SafeReflection.copyWithNewId(this.entityId, packetPlayOutEntity));
                "".length();
                if (true < false) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayOutEntityTeleport && (entityId = SafeReflection.getEntityId(packetPlayOutEntityTeleport = (PacketPlayOutEntityTeleport)packet)) == this.playerData.getEntityPlayer().getId()) {
            otherData.getEntityPlayer().playerConnection.sendPacket((Packet)SafeReflection.copyWithNewId(this.entityId, packetPlayOutEntityTeleport));
        }
    }

    public PacketPlayOutSpawnEntityLiving createPacket() {
        WorldServer worldServer = (WorldServer)this.playerData.getEntityPlayer().world;
        EntityIronGolem entity = new EntityIronGolem((World)worldServer);
        this.entityId = entity.getId();
        entity.setHealth(Float.NaN);
        entity.locX = this.playerData.getLocation().getX();
        entity.locY = this.playerData.getLocation().getY();
        entity.locZ = this.playerData.getLocation().getZ();
        entity.motX = this.playerData.getEntityPlayer().motX;
        entity.motY = this.playerData.getEntityPlayer().motY;
        entity.motZ = this.playerData.getEntityPlayer().motZ;
        entity.pitch = this.playerData.getLocation().getPitch();
        entity.yaw = this.playerData.getLocation().getYaw();
        entity.aK = this.playerData.getEntityPlayer().aK;
        entity.setInvisible(" ".length() != 0);
        entity.k(" ".length() != 0);
        return new PacketPlayOutSpawnEntityLiving((EntityLiving)entity);
    }

    public int getEntityId() {
        return this.entityId;
    }
}

