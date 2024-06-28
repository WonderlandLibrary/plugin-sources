/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelOutboundHandlerAdapter
 *  io.netty.channel.ChannelPromise
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 */
package com.unknownmyname.injector;

import com.unknownmyname.data.PlayerData;
import com.unknownmyname.main.Main;
import com.unknownmyname.util.SafeReflection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;

public class PacketEncoder
extends ChannelOutboundHandlerAdapter {
    private final /* synthetic */ PlayerData playerData;
    private static final /* synthetic */ String[] I;

    static {
        PacketEncoder.I();
    }

    private static void I() {
        I = new String[" ".length()];
        PacketEncoder.I["".length()] = PacketEncoder.I("7TOgl3X+K7Egz/CEZ8ZczhVTgq9FdbfI3+BtJcnAbBbOnYEeOhzoGA==", "NvvVL");
    }

    public PacketEncoder(PlayerData playerData) {
        this.playerData = playerData;
    }

    public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {
        if (o instanceof PacketPlayOutEntityTeleport) {
            SafeReflection.setOnGround((PacketPlayOutEntityTeleport)o, "".length() != 0);
            "".length();
            if (4 <= 3) {
                throw null;
            }
        } else if (o instanceof PacketPlayOutEntity) {
            SafeReflection.setOnGround((PacketPlayOutEntity)o, "".length() != 0);
        }
        super.write(channelHandlerContext, o, channelPromise);
        try {
            this.playerData.handle((Packet)o, "".length() != 0);
            "".length();
        }
        catch (Throwable var5) {
            Main.getPlugin().getLogger().log(Level.SEVERE, I["".length()], var5);
        }
        if (false) {
            throw null;
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x4B ^ 0x43), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

