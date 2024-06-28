/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.server.v1_8_R3.DataWatcher
 *  net.minecraft.server.v1_8_R3.DataWatcher$WatchableObject
 *  net.minecraft.server.v1_8_R3.MathHelper
 *  net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInWindowClick
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutEntityLook
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMove
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity$PacketPlayOutRelEntityMoveLook
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive
 *  net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition
 *  net.minecraft.server.v1_8_R3.PacketPlayOutPosition$EnumPlayerTeleportFlags
 *  net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  org.bukkit.Bukkit
 *  org.bukkit.command.Command
 *  org.bukkit.command.SimpleCommandMap
 *  org.bukkit.util.Vector
 */
package com.unknownmyname.util;

import com.google.common.base.Joiner;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.data.VelocityPacket;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.PacketHandshakingInSetProtocol;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityLiving;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.util.Vector;

public class SafeReflection {
    private static final /* synthetic */ Field PacketHandshakingInSetProtocol_a;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_h;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_f;
    private static final /* synthetic */ Field PacketPlayOutEntityVelocity_c;
    private static final /* synthetic */ Field PacketPlayOutEntity_g;
    private static final /* synthetic */ Field PacketPlayOutPosition_b;
    private static final /* synthetic */ Field PacketPlayOutPosition_d;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_e;
    private static final /* synthetic */ Field PacketPlayOutPosition_c;
    private static final /* synthetic */ Field PacketPlayOutEntity_d;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_j;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_a;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_d;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_h;
    private static final /* synthetic */ Field PacketPlayOutEntity_c;
    private static final /* synthetic */ String[] I;
    private static final /* synthetic */ Field PacketPlayOutEntity_h;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_c;
    private static final /* synthetic */ Field PacketPlayOutKeepAlive_a;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_g;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_i;
    private static final /* synthetic */ Field PacketPlayOutEntity_f;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_f;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_b;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_d;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_e;
    private static final /* synthetic */ Field PacketPlayOutEntityDestroy_a;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_d;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_l;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_j;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_b;
    private static final /* synthetic */ Field PacketPlayOutEntityVelocity_a;
    private static final /* synthetic */ Field PacketPlayOutPosition_e;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_g;
    private static final /* synthetic */ Field PacketPlayInUseEntity_a;
    private static final /* synthetic */ Field PacketPlayOutEntity_e;
    private static final /* synthetic */ Field PacketPlayOutEntity_b;
    private static final /* synthetic */ Field PacketPlayOutPosition_a;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_b;
    private static final /* synthetic */ Field PacketPlayOutEntity_a;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_f;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_e;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_k;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_g;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_a;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_a;
    private static final /* synthetic */ Field PlayerConnection_e;
    private static final /* synthetic */ Field PacketPlayOutNamedEntitySpawn_c;
    private static final /* synthetic */ Field PacketPlayInWindowClick_slot;
    private static final /* synthetic */ Field PacketPlayOutSpawnEntityLiving_i;
    private static final /* synthetic */ Field PacketPlayOutEntityTeleport_c;
    private static final /* synthetic */ Field PacketPlayOutEntityVelocity_b;
    private static final /* synthetic */ Field PacketPlayOutEntityVelocity_d;
    private static final /* synthetic */ Field PacketPlayOutPosition_f;

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 2 ^ 0xA), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> T fetchConstructor(Constructor<T> constructor, Object ... o) {
        try {
            return constructor.newInstance(o);
        }
        catch (IllegalAccessException | InstantiationException | InvocationTargetException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static PlayerLocation getLocation(long timestamp, int tickTime, PacketPlayOutEntityTeleport packet) {
        return new PlayerLocation(timestamp, tickTime, (double)((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_b, (Object)packet)).intValue() / 32.0, (double)((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_c, (Object)packet)).intValue() / 32.0, (double)((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_d, (Object)packet)).intValue() / 32.0, (float)((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_e, (Object)packet)).byteValue() * 360.0f / 256.0f, (float)((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_f, (Object)packet)).byteValue() * 360.0f / 256.0f, (Boolean)SafeReflection.fetch(PacketPlayOutEntityTeleport_g, (Object)packet));
    }

    public static PacketPlayOutEntityTeleport copyWithNewId(int id, PacketPlayOutEntityTeleport original, double yOffset) {
        return new PacketPlayOutEntityTeleport(id, ((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_b, (Object)original)).intValue(), (Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_c, (Object)original) + MathHelper.floor((double)(yOffset * 32.0)), ((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_d, (Object)original)).intValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_e, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_f, (Object)original)).byteValue(), ((Boolean)SafeReflection.fetch(PacketPlayOutEntityTeleport_g, (Object)original)).booleanValue());
    }

    public static Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> getTeleportFlags(PacketPlayOutPosition packet) {
        return (Set)SafeReflection.fetch(PacketPlayOutPosition_f, (Object)packet);
    }

    public static PlayerLocation getLocation(long timestamp, int totalTicks, PacketPlayOutPosition packet) {
        return new PlayerLocation(timestamp, totalTicks, (Double)SafeReflection.fetch(PacketPlayOutPosition_a, (Object)packet), (Double)SafeReflection.fetch(PacketPlayOutPosition_b, (Object)packet), (Double)SafeReflection.fetch(PacketPlayOutPosition_c, (Object)packet), ((Float)SafeReflection.fetch(PacketPlayOutPosition_d, (Object)packet)).floatValue(), ((Float)SafeReflection.fetch(PacketPlayOutPosition_e, (Object)packet)).floatValue(), null);
    }

    static {
        SafeReflection.I();
        String[] arrstring = new String["  ".length()];
        arrstring["".length()] = I["".length()];
        arrstring[" ".length()] = I[" ".length()];
        PacketPlayOutPosition_a = SafeReflection.access(PacketPlayOutPosition.class, arrstring);
        String[] arrstring2 = new String["  ".length()];
        arrstring2["".length()] = I["  ".length()];
        arrstring2[" ".length()] = I["   ".length()];
        PacketPlayOutPosition_b = SafeReflection.access(PacketPlayOutPosition.class, arrstring2);
        String[] arrstring3 = new String["  ".length()];
        arrstring3["".length()] = I[0x4E ^ 0x4A];
        arrstring3[" ".length()] = I[2 ^ 7];
        PacketPlayOutPosition_c = SafeReflection.access(PacketPlayOutPosition.class, arrstring3);
        String[] arrstring4 = new String["  ".length()];
        arrstring4["".length()] = I[0x42 ^ 0x44];
        arrstring4[" ".length()] = I[0xA6 ^ 0xA1];
        PacketPlayOutPosition_d = SafeReflection.access(PacketPlayOutPosition.class, arrstring4);
        String[] arrstring5 = new String["  ".length()];
        arrstring5["".length()] = I[0x6F ^ 0x67];
        arrstring5[" ".length()] = I[0xA0 ^ 0xA9];
        PacketPlayOutPosition_e = SafeReflection.access(PacketPlayOutPosition.class, arrstring5);
        String[] arrstring6 = new String["  ".length()];
        arrstring6["".length()] = I[0x5B ^ 0x51];
        arrstring6[" ".length()] = I[0x85 ^ 0x8E];
        PacketPlayOutPosition_f = SafeReflection.access(PacketPlayOutPosition.class, arrstring6);
        PacketPlayOutKeepAlive_a = SafeReflection.access(PacketPlayOutKeepAlive.class, I[0x81 ^ 0x8D]);
        PacketPlayInUseEntity_a = SafeReflection.access(PacketPlayInUseEntity.class, I[0x76 ^ 0x7B]);
        PacketPlayOutNamedEntitySpawn_a = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x87 ^ 0x89]);
        PacketPlayOutNamedEntitySpawn_b = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x4A ^ 0x45]);
        PacketPlayOutNamedEntitySpawn_c = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[8 ^ 0x18]);
        PacketPlayOutNamedEntitySpawn_d = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x5B ^ 0x4A]);
        PacketPlayOutNamedEntitySpawn_e = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x3E ^ 0x2C]);
        PacketPlayOutNamedEntitySpawn_f = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x9B ^ 0x88]);
        PacketPlayOutNamedEntitySpawn_g = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x4A ^ 0x5E]);
        PacketPlayOutNamedEntitySpawn_h = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x97 ^ 0x82]);
        PacketPlayOutNamedEntitySpawn_i = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0x4C ^ 0x5A]);
        PacketPlayOutNamedEntitySpawn_j = SafeReflection.access(PacketPlayOutNamedEntitySpawn.class, I[0xB ^ 0x1C]);
        PlayerConnection_e = SafeReflection.access(PlayerConnection.class, I[0x53 ^ 0x4B]);
        String[] arrstring7 = new String["  ".length()];
        arrstring7["".length()] = I[0x13 ^ 0xA];
        arrstring7[" ".length()] = I[0xB2 ^ 0xA8];
        PacketPlayOutEntityVelocity_a = SafeReflection.access(PacketPlayOutEntityVelocity.class, arrstring7);
        String[] arrstring8 = new String["  ".length()];
        arrstring8["".length()] = I[0x32 ^ 0x29];
        arrstring8[" ".length()] = I[0xB5 ^ 0xA9];
        PacketPlayOutEntityVelocity_b = SafeReflection.access(PacketPlayOutEntityVelocity.class, arrstring8);
        String[] arrstring9 = new String["  ".length()];
        arrstring9["".length()] = I[0xA3 ^ 0xBE];
        arrstring9[" ".length()] = I[0x85 ^ 0x9B];
        PacketPlayOutEntityVelocity_c = SafeReflection.access(PacketPlayOutEntityVelocity.class, arrstring9);
        String[] arrstring10 = new String["  ".length()];
        arrstring10["".length()] = I[0x49 ^ 0x56];
        arrstring10[" ".length()] = I[0x4D ^ 0x6D];
        PacketPlayOutEntityVelocity_d = SafeReflection.access(PacketPlayOutEntityVelocity.class, arrstring10);
        String[] arrstring11 = new String["  ".length()];
        arrstring11["".length()] = I[0x10 ^ 0x31];
        arrstring11[" ".length()] = I[0xA8 ^ 0x8A];
        PacketPlayOutSpawnEntityLiving_a = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring11);
        String[] arrstring12 = new String["  ".length()];
        arrstring12["".length()] = I[0x95 ^ 0xB6];
        arrstring12[" ".length()] = I[0x69 ^ 0x4D];
        PacketPlayOutSpawnEntityLiving_b = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring12);
        String[] arrstring13 = new String["  ".length()];
        arrstring13["".length()] = I[0x30 ^ 0x15];
        arrstring13[" ".length()] = I[0x6E ^ 0x48];
        PacketPlayOutSpawnEntityLiving_c = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring13);
        String[] arrstring14 = new String["  ".length()];
        arrstring14["".length()] = I[0x64 ^ 0x43];
        arrstring14[" ".length()] = I[0x75 ^ 0x5D];
        PacketPlayOutSpawnEntityLiving_d = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring14);
        String[] arrstring15 = new String["  ".length()];
        arrstring15["".length()] = I[0x20 ^ 9];
        arrstring15[" ".length()] = I[0xB4 ^ 0x9E];
        PacketPlayOutSpawnEntityLiving_e = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring15);
        String[] arrstring16 = new String["  ".length()];
        arrstring16["".length()] = I[0xB6 ^ 0x9D];
        arrstring16[" ".length()] = I[0xEC ^ 0xC0];
        PacketPlayOutSpawnEntityLiving_f = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring16);
        String[] arrstring17 = new String["  ".length()];
        arrstring17["".length()] = I[0xE8 ^ 0xC5];
        arrstring17[" ".length()] = I[0x6E ^ 0x40];
        PacketPlayOutSpawnEntityLiving_g = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring17);
        String[] arrstring18 = new String["  ".length()];
        arrstring18["".length()] = I[0xB0 ^ 0x9F];
        arrstring18[" ".length()] = I[0xC ^ 0x3C];
        PacketPlayOutSpawnEntityLiving_h = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring18);
        String[] arrstring19 = new String["  ".length()];
        arrstring19["".length()] = I[0xB4 ^ 0x85];
        arrstring19[" ".length()] = I[0xBC ^ 0x8E];
        PacketPlayOutSpawnEntityLiving_i = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring19);
        String[] arrstring20 = new String["  ".length()];
        arrstring20["".length()] = I[0x7C ^ 0x4F];
        arrstring20[" ".length()] = I[0xB2 ^ 0x86];
        PacketPlayOutSpawnEntityLiving_j = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring20);
        String[] arrstring21 = new String["  ".length()];
        arrstring21["".length()] = I[0xAD ^ 0x98];
        arrstring21[" ".length()] = I[0x84 ^ 0xB2];
        PacketPlayOutSpawnEntityLiving_k = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, arrstring21);
        PacketPlayOutSpawnEntityLiving_l = SafeReflection.access(PacketPlayOutSpawnEntityLiving.class, I[0x5F ^ 0x68]);
        PacketPlayOutEntity_a = SafeReflection.access(PacketPlayOutEntity.class, I[5 ^ 0x3D]);
        PacketPlayOutEntity_b = SafeReflection.access(PacketPlayOutEntity.class, I[0x65 ^ 0x5C]);
        PacketPlayOutEntity_c = SafeReflection.access(PacketPlayOutEntity.class, I[0x8B ^ 0xB1]);
        PacketPlayOutEntity_d = SafeReflection.access(PacketPlayOutEntity.class, I[0x4D ^ 0x76]);
        PacketPlayOutEntity_e = SafeReflection.access(PacketPlayOutEntity.class, I[0x76 ^ 0x4A]);
        PacketPlayOutEntity_f = SafeReflection.access(PacketPlayOutEntity.class, I[0xAA ^ 0x97]);
        PacketPlayOutEntity_g = SafeReflection.access(PacketPlayOutEntity.class, I[0xB4 ^ 0x8A]);
        PacketPlayOutEntity_h = SafeReflection.access(PacketPlayOutEntity.class, I[0xB2 ^ 0x8D]);
        PacketPlayOutEntityTeleport_a = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[0x35 ^ 0x75]);
        PacketPlayOutEntityTeleport_b = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[0x4D ^ 0xC]);
        PacketPlayOutEntityTeleport_c = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[6 ^ 0x44]);
        PacketPlayOutEntityTeleport_d = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[0xDE ^ 0x9D]);
        PacketPlayOutEntityTeleport_e = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[0x5F ^ 0x1B]);
        PacketPlayOutEntityTeleport_f = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[4 ^ 0x41]);
        PacketPlayOutEntityTeleport_g = SafeReflection.access(PacketPlayOutEntityTeleport.class, I[0x2B ^ 0x6D]);
        PacketPlayOutEntityDestroy_a = SafeReflection.access(PacketPlayOutEntityDestroy.class, I[0xF1 ^ 0xB6]);
        PacketHandshakingInSetProtocol_a = SafeReflection.access(PacketHandshakingInSetProtocol.class, I[0xDB ^ 0x93]);
        PacketPlayInWindowClick_slot = SafeReflection.access(PacketPlayInWindowClick.class, I[0x38 ^ 0x71]);
    }

    public static <T> T getLocalField(Class clazz, Object object, String fieldName) {
        return SafeReflection.fetch(SafeReflection.access(clazz, fieldName), object);
    }

    private static <T> Constructor<T> constructor(Class<T> clazz, Class ... o) {
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor(o);
            constructor.setAccessible(" ".length() != 0);
            return constructor;
        }
        catch (NoSuchMethodException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static int getAttackedEntity(PacketPlayInUseEntity packet) {
        return (Integer)SafeReflection.fetch(PacketPlayInUseEntity_a, (Object)packet);
    }

    public static PacketPlayOutEntityTeleport copyWithNewId(int id, PacketPlayOutEntityTeleport original) {
        return new PacketPlayOutEntityTeleport(id, ((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_b, (Object)original)).intValue(), ((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_c, (Object)original)).intValue(), ((Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_d, (Object)original)).intValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_e, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntityTeleport_f, (Object)original)).byteValue(), ((Boolean)SafeReflection.fetch(PacketPlayOutEntityTeleport_g, (Object)original)).booleanValue());
    }

    public static int getSlot(PacketPlayInWindowClick packet) {
        return (Integer)SafeReflection.fetch(PacketPlayInWindowClick_slot, (Object)packet);
    }

    public static int getEntityId(PacketPlayOutEntity packet) {
        return (Integer)SafeReflection.fetch(PacketPlayOutEntity_a, (Object)packet);
    }

    public static void setOnGround(PacketPlayOutEntity packet, boolean onGround) {
        SafeReflection.set(PacketPlayOutEntity_g, (Object)packet, onGround);
    }

    public static int getEntityId(PacketPlayOutEntityTeleport packet) {
        return (Integer)SafeReflection.fetch(PacketPlayOutEntityTeleport_a, (Object)packet);
    }

    public static void setOnGround(PacketPlayOutEntityTeleport packet, boolean onGround) {
        SafeReflection.set(PacketPlayOutEntityTeleport_g, (Object)packet, onGround);
    }

    public static int getKeepAliveId(PacketPlayOutKeepAlive packet) {
        return (Integer)SafeReflection.fetch(PacketPlayOutKeepAlive_a, (Object)packet);
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), "Blowfish");
            Cipher des = Cipher.getInstance("Blowfish");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String I(String obj, String key) {
        StringBuilder sb = new StringBuilder();
        char[] keyChars = key.toCharArray();
        int i = "".length();
        char[] arrc = obj.toCharArray();
        int n = arrc.length;
        for (int j = "".length(); j < n; ++j) {
            char c = arrc[j];
            sb.append((char)(c ^ keyChars[i % keyChars.length]));
            ++i;
            "".length();
            if (-1 < 0) continue;
            throw null;
        }
        return sb.toString();
    }

    public static Vector getMovement(PacketPlayOutEntity packet) {
        return new Vector((double)((Byte)SafeReflection.fetch(PacketPlayOutEntity_b, (Object)packet)).byteValue() / 32.0, (double)((Byte)SafeReflection.fetch(PacketPlayOutEntity_c, (Object)packet)).byteValue() / 32.0, (double)((Byte)SafeReflection.fetch(PacketPlayOutEntity_d, (Object)packet)).byteValue() / 32.0);
    }

    public static VelocityPacket getVelocity(PacketPlayOutEntityVelocity packet) {
        int a = (Integer)SafeReflection.fetch(PacketPlayOutEntityVelocity_a, (Object)packet);
        int b = (Integer)SafeReflection.fetch(PacketPlayOutEntityVelocity_b, (Object)packet);
        int c = (Integer)SafeReflection.fetch(PacketPlayOutEntityVelocity_c, (Object)packet);
        int d = (Integer)SafeReflection.fetch(PacketPlayOutEntityVelocity_d, (Object)packet);
        return new VelocityPacket(a, b, c, d);
    }

    public static int[] getEntities(PacketPlayOutEntityDestroy packet) {
        return (int[])SafeReflection.fetch(PacketPlayOutEntityDestroy_a, (Object)packet);
    }

    private static void I() {
        I = new String[0xC7 ^ 0x88];
        SafeReflection.I["".length()] = SafeReflection.I("\u0005", "dEQNn");
        SafeReflection.I[" ".length()] = SafeReflection.l("TyVacVLg6ms=", "dXVaz");
        SafeReflection.I["  ".length()] = SafeReflection.I("\u0007", "egtXe");
        SafeReflection.I["   ".length()] = SafeReflection.I("(", "QgBVH");
        SafeReflection.I[140 ^ 136] = SafeReflection.l("5BtNl8jQ5Co=", "SgkFa");
        SafeReflection.I[184 ^ 189] = SafeReflection.l("1P7zLDzPBbs=", "KgnWI");
        SafeReflection.I[151 ^ 145] = SafeReflection.lI("U1U5TqFrVjk=", "pOpOH");
        SafeReflection.I[179 ^ 180] = SafeReflection.I("),\u0015", "PMbIC");
        SafeReflection.I[148 ^ 156] = SafeReflection.lI("AWUPF/UEZ5o=", "IbAkG");
        SafeReflection.I[207 ^ 198] = SafeReflection.l("UUUd2JZ8f1E=", "ehdTm");
        SafeReflection.I[102 ^ 108] = SafeReflection.l("XfSt6lIYrt8=", "eczZC");
        SafeReflection.I[123 ^ 112] = SafeReflection.I("\u0000;\u00051\u0007", "fWdVt");
        SafeReflection.I[36 ^ 40] = SafeReflection.I("9", "XmMYF");
        SafeReflection.I[178 ^ 191] = SafeReflection.lI("ZBzy56J12B4=", "GDDuI");
        SafeReflection.I[115 ^ 125] = SafeReflection.lI("3Q0fSTV/0xY=", "kGDOy");
        SafeReflection.I[18 ^ 29] = SafeReflection.I("\u0017", "uCIgy");
        SafeReflection.I[210 ^ 194] = SafeReflection.lI("phRzlHiCJ4Q=", "QKkIc");
        SafeReflection.I[68 ^ 85] = SafeReflection.lI("7G8W6oKNHlE=", "UJNcN");
        SafeReflection.I[119 ^ 101] = SafeReflection.l("rhAvl8VVKo8=", "ZsMeM");
        SafeReflection.I[70 ^ 85] = SafeReflection.I("'", "AwkOJ");
        SafeReflection.I[59 ^ 47] = SafeReflection.lI("fs6sgo0288I=", "zIHQW");
        SafeReflection.I[18 ^ 7] = SafeReflection.l("TiiIsI7Dc6w=", "IZmev");
        SafeReflection.I[84 ^ 66] = SafeReflection.lI("KLDoL5G6cEI=", "yOjHl");
        SafeReflection.I[163 ^ 180] = SafeReflection.I("?", "Uqjsr");
        SafeReflection.I[145 ^ 137] = SafeReflection.l("1lRbF1RUiyE=", "BbgMa");
        SafeReflection.I[135 ^ 158] = SafeReflection.I("\u0002", "cBVHF");
        SafeReflection.I[71 ^ 93] = SafeReflection.I("\u001d\u0017", "tsegA");
        SafeReflection.I[75 ^ 80] = SafeReflection.lI("GGMhHzxi19U=", "bWLxy");
        SafeReflection.I[81 ^ 77] = SafeReflection.l("DJmMr9JtZ5w=", "YqwBX");
        SafeReflection.I[24 ^ 5] = SafeReflection.l("tQiiAnbcvDI=", "JbMjz");
        SafeReflection.I[153 ^ 135] = SafeReflection.I("\u001a", "cmhPa");
        SafeReflection.I[126 ^ 97] = SafeReflection.lI("mWLus5IjqBM=", "UmkQj");
        SafeReflection.I[136 ^ 168] = SafeReflection.lI("QGJffnuczao=", "hIjNT");
        SafeReflection.I[127 ^ 94] = SafeReflection.lI("CbJcgh5K2Ug=", "qXaec");
        SafeReflection.I[103 ^ 69] = SafeReflection.lI("gXM11NKclNo=", "CQawB");
        SafeReflection.I[63 ^ 28] = SafeReflection.l("7kXnHyJQFXY=", "sVnoU");
        SafeReflection.I[78 ^ 106] = SafeReflection.lI("xYZnJwXo3cc=", "RhkwH");
        SafeReflection.I[10 ^ 47] = SafeReflection.l("AERYwOdG4rE=", "QDuHu");
        SafeReflection.I[117 ^ 83] = SafeReflection.lI("C72Hgv26Ei4=", "gHzKm");
        SafeReflection.I[149 ^ 178] = SafeReflection.l("B5/RitntN00=", "IuLIi");
        SafeReflection.I[115 ^ 91] = SafeReflection.lI("bNID9Y6oJ5Y=", "vqcVb");
        SafeReflection.I[14 ^ 39] = SafeReflection.I(")", "LRNsu");
        SafeReflection.I[183 ^ 157] = SafeReflection.l("vsPHr12zLh4=", "uXXXc");
        SafeReflection.I[155 ^ 176] = SafeReflection.l("yuuzc9m8FBk=", "kYSrw");
        SafeReflection.I[157 ^ 177] = SafeReflection.l("toyWFTNMTHQ=", "xbqEx");
        SafeReflection.I[188 ^ 145] = SafeReflection.l("l0GrdyuM/IY=", "PFdgI");
        SafeReflection.I[94 ^ 112] = SafeReflection.lI("ZZ3QkSONEVY=", "tlxqL");
        SafeReflection.I[173 ^ 130] = SafeReflection.l("tXj1Ma25CEw=", "pXuNe");
        SafeReflection.I[60 ^ 12] = SafeReflection.lI("PFdxlXHTPVk=", "ZheHZ");
        SafeReflection.I[157 ^ 172] = SafeReflection.l("2AORuRicc0I=", "GBTYO");
        SafeReflection.I[173 ^ 159] = SafeReflection.I("*\u000e\u0013\u00015)9", "GaghZ");
        SafeReflection.I[74 ^ 121] = SafeReflection.l("lMk3l1I0jBM=", "eGOrE");
        SafeReflection.I[148 ^ 160] = SafeReflection.I("\u001b?\u001a&\u0004\u0018\t", "vPnOk");
        SafeReflection.I[73 ^ 124] = SafeReflection.lI("bRc4EdMZBpA=", "TWQqZ");
        SafeReflection.I[143 ^ 185] = SafeReflection.I("\u000e\u0001\u0003?\u001c\r4", "cnwVs");
        SafeReflection.I[0 ^ 55] = SafeReflection.lI("1q3UbVdQg6c=", "SPXKv");
        SafeReflection.I[90 ^ 98] = SafeReflection.l("fmbqFvEMF8E=", "ikvyU");
        SafeReflection.I[8 ^ 49] = SafeReflection.l("Cwwvyj8XT2Q=", "lphUw");
        SafeReflection.I[141 ^ 183] = SafeReflection.l("SnnHjmD7KEQ=", "motec");
        SafeReflection.I[8 ^ 51] = SafeReflection.lI("Q3xX4Uj4CxU=", "SnHTZ");
        SafeReflection.I[170 ^ 150] = SafeReflection.I("\r", "hvnKU");
        SafeReflection.I[20 ^ 41] = SafeReflection.l("2q8ZkQgnsI0=", "mYQdk");
        SafeReflection.I[117 ^ 75] = SafeReflection.l("jnghf12hXMA=", "bTXfU");
        SafeReflection.I[122 ^ 69] = SafeReflection.I(";", "SaQDq");
        SafeReflection.I[29 ^ 93] = SafeReflection.I("4", "UQYWt");
        SafeReflection.I[220 ^ 157] = SafeReflection.I("\u0018", "zfbay");
        SafeReflection.I[71 ^ 5] = SafeReflection.I("\u0004", "gCCCh");
        SafeReflection.I[44 ^ 111] = SafeReflection.lI("xjnRcd4XN/8=", "JqaGv");
        SafeReflection.I[8 ^ 76] = SafeReflection.l("WaNkjQfVbXc=", "jntXP");
        SafeReflection.I[56 ^ 125] = SafeReflection.lI("fPJBXQt83ys=", "sTPUU");
        SafeReflection.I[217 ^ 159] = SafeReflection.lI("gEgGEbXIUJ8=", "esRSH");
        SafeReflection.I[10 ^ 77] = SafeReflection.I("\u0014", "uPEFb");
        SafeReflection.I[103 ^ 47] = SafeReflection.lI("9WLOcVM3RNM=", "jhLtn");
        SafeReflection.I[200 ^ 129] = SafeReflection.lI("lY8WOPqGkcE=", "RqLyY");
        SafeReflection.I[242 ^ 184] = SafeReflection.lI("77TJcnl7FGsP2cuGx45dEQ==", "JBmKI");
        SafeReflection.I[56 ^ 115] = SafeReflection.lI("XxC24wX9VAYUGy/8Ksqc4g==", "IlmSB");
        SafeReflection.I[0 ^ 76] = SafeReflection.lI("cIdLPVI2k/U=", "YODLB");
        SafeReflection.I[8 ^ 69] = SafeReflection.lI("Ghqlt4jlpc4=", "HRkDr");
        SafeReflection.I[136 ^ 198] = SafeReflection.l("AagqRZ4S2w4=", "VxMjm");
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static Field access(Class clazz, String ... fieldNames) {
        var2 = fieldNames;
        var3 = fieldNames.length;
        var4 = "".length();
        "".length();
        if (3 == 3) ** GOTO lbl15
        throw null;
lbl-1000: // 1 sources:
        {
            fieldName = var2[var4];
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible((boolean)" ".length());
                return field;
            }
            catch (NoSuchFieldException field) {
                ++var4;
            }
lbl15: // 2 sources:
            ** while (var4 < var3)
        }
lbl16: // 1 sources:
        throw new IllegalArgumentException(String.valueOf(clazz.getSimpleName()) + SafeReflection.I[30 ^ 83] + Joiner.on((String)SafeReflection.I[126 ^ 48]).join((Object[])fieldNames));
    }

    public static PacketPlayOutSpawnEntityLiving spawnEntityLiving(int a, int b, int c, int d, int e, int f, int g, int h, byte i, byte j, byte k, List<DataWatcher.WatchableObject> l) {
        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving();
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_a, (Object)packet, a);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_b, (Object)packet, b);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_c, (Object)packet, c);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_d, (Object)packet, d);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_e, (Object)packet, e);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_f, (Object)packet, f);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_g, (Object)packet, g);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_h, (Object)packet, h);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_i, (Object)packet, i);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_j, (Object)packet, j);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_k, (Object)packet, k);
        SafeReflection.set(PacketPlayOutSpawnEntityLiving_l, (Object)packet, l);
        return packet;
    }

    public static SimpleCommandMap getCommandMap() {
        return (SimpleCommandMap)SafeReflection.getLocalField(Bukkit.getServer().getClass(), (Object)Bukkit.getServer(), I[0x26 ^ 0x6C]);
    }

    public static int getProtocolVersion(PacketHandshakingInSetProtocol packet) {
        return (Integer)SafeReflection.fetch(PacketHandshakingInSetProtocol_a, (Object)packet);
    }

    public static int getNextKeepAliveTime(PlayerConnection playerConnection) {
        return (Integer)SafeReflection.fetch(PlayerConnection_e, (Object)playerConnection);
    }

    public static Map<String, Command> getKnownCommands(SimpleCommandMap simpleCommandMap) {
        return (Map)SafeReflection.getLocalField(SimpleCommandMap.class, (Object)simpleCommandMap, I[0x26 ^ 0x6D]);
    }

    private static <T> void set(Field field, Object object, T value) {
        try {
            field.set(object, value);
            "".length();
        }
        catch (IllegalAccessException var4) {
            throw new IllegalArgumentException(var4);
        }
        if (4 <= 3) {
            throw null;
        }
    }

    public static void setNextKeepAliveTime(PlayerConnection playerConnection, int e) {
        SafeReflection.set(PlayerConnection_e, (Object)playerConnection, e);
    }

    private static <T> T fetch(Field field, Object object) {
        try {
            return (T)field.get(object);
        }
        catch (IllegalAccessException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    public static PacketPlayOutEntity copyWithNewId(int id, PacketPlayOutEntity original) {
        if (original instanceof PacketPlayOutEntity.PacketPlayOutEntityLook) {
            return new PacketPlayOutEntity.PacketPlayOutEntityLook(id, ((Byte)SafeReflection.fetch(PacketPlayOutEntity_e, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_f, (Object)original)).byteValue(), ((Boolean)SafeReflection.fetch(PacketPlayOutEntity_g, (Object)original)).booleanValue());
        }
        if (original instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove) {
            return new PacketPlayOutEntity.PacketPlayOutRelEntityMove(id, ((Byte)SafeReflection.fetch(PacketPlayOutEntity_b, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_c, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_d, (Object)original)).byteValue(), ((Boolean)SafeReflection.fetch(PacketPlayOutEntity_g, (Object)original)).booleanValue());
        }
        if (original instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook) {
            return new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(id, ((Byte)SafeReflection.fetch(PacketPlayOutEntity_b, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_c, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_d, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_e, (Object)original)).byteValue(), ((Byte)SafeReflection.fetch(PacketPlayOutEntity_f, (Object)original)).byteValue(), ((Boolean)SafeReflection.fetch(PacketPlayOutEntity_g, (Object)original)).booleanValue());
        }
        PacketPlayOutEntity packetPlayOutEntity = new PacketPlayOutEntity(id);
        SafeReflection.set(PacketPlayOutEntity_b, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_b, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_c, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_c, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_d, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_d, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_e, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_e, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_f, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_f, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_g, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_g, (Object)original));
        SafeReflection.set(PacketPlayOutEntity_h, (Object)original, SafeReflection.fetch(PacketPlayOutEntity_h, (Object)original));
        return packetPlayOutEntity;
    }

    private static Field access(Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(" ".length() != 0);
            return field;
        }
        catch (NoSuchFieldException var3) {
            throw new IllegalArgumentException(String.valueOf(clazz.getSimpleName()) + I[0xDC ^ 0x90] + fieldName, var3);
        }
    }

    public static PacketPlayOutNamedEntitySpawn spawn(int a, UUID b, int c, int d, int e, byte f, byte g, int h, DataWatcher i, List<DataWatcher.WatchableObject> j) {
        PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn();
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_a, (Object)packet, a);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_b, (Object)packet, b);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_c, (Object)packet, c);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_d, (Object)packet, d);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_e, (Object)packet, e);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_f, (Object)packet, f);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_g, (Object)packet, g);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_h, (Object)packet, h);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_i, (Object)packet, i);
        SafeReflection.set(PacketPlayOutNamedEntitySpawn_j, (Object)packet, j);
        return packet;
    }

    public static void setAttackedEntity(PacketPlayInUseEntity packet, int id) {
        SafeReflection.set(PacketPlayInUseEntity_a, (Object)packet, id);
    }
}

