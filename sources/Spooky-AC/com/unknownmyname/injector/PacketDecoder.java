/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelInboundHandlerAdapter
 *  net.minecraft.server.v1_8_R3.Packet
 */
package com.unknownmyname.injector;

import com.unknownmyname.data.PlayerData;
import com.unknownmyname.main.Main;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.v1_8_R3.Packet;

public class PacketDecoder
extends ChannelInboundHandlerAdapter {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ ChannelHandlerContext lastContext;
    private final /* synthetic */ PlayerData playerData;

    public void sendFakePacket(Packet packet) {
        try {
            super.channelRead(this.lastContext, (Object)packet);
            "".length();
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        if (3 <= 0) {
            throw null;
        }
    }

    private static void I() {
        I = new String[" ".length()];
        PacketDecoder.I["".length()] = PacketDecoder.I("1,3\u0005\t\u0013m.\u0006L\u001f,4\r\u0000\u0012m3\u0007\u000f\u0018 3\u0007\u000bW=;\n\u0007\u00129z", "wMZil");
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
            if (4 >= -1) continue;
            throw null;
        }
        return sb.toString();
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        super.channelRead(channelHandlerContext, o);
        try {
            this.lastContext = channelHandlerContext;
            this.playerData.handle((Packet)o, " ".length() != 0);
            "".length();
        }
        catch (Throwable var4) {
            Main.getPlugin().getLogger().log(Level.SEVERE, I["".length()], var4);
        }
        if (true <= false) {
            throw null;
        }
    }

    static {
        PacketDecoder.I();
    }

    public PacketDecoder(PlayerData playerData) {
        this.playerData = playerData;
        playerData.setPacketDecoder(this);
    }
}

