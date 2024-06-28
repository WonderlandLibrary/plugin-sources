/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.playload;

import com.google.common.collect.ImmutableMap;
import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import org.bukkit.entity.Player;

public class CustomPayloadA
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private final /* synthetic */ Map<String, String> map;

    public Map<String, String> getMap() {
        return this.map;
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInCustomPayload packetPlayInCustomPayload;
        String channel;
        String type;
        if (packet instanceof PacketPlayInCustomPayload && (type = this.map.get(channel = (packetPlayInCustomPayload = (PacketPlayInCustomPayload)packet).a())) != null) {
            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(type) + I[0xD ^ 0x17] + packetPlayInCustomPayload.a(), 1.0, "".length() != 0);
            AlertsManager.getInstance().handleBan(playerData, this, " ".length() != 0);
        }
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x1F ^ 0x17), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        CustomPayloadA.I();
    }

    public CustomPayloadA() {
        super(Check.CheckType.PAYLOAD, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.map = new ImmutableMap.Builder().put((Object)I["  ".length()], (Object)I["   ".length()]).put((Object)I[0x5C ^ 0x58], (Object)I[0xB8 ^ 0xBD]).put((Object)I[0xA7 ^ 0xA1], (Object)I[0xAA ^ 0xAD]).put((Object)I[0xBB ^ 0xB3], (Object)I[0x5D ^ 0x54]).put((Object)I[0xBB ^ 0xB1], (Object)I[0x77 ^ 0x7C]).put((Object)I[0x78 ^ 0x74], (Object)I[0x99 ^ 0x94]).put((Object)I[0x70 ^ 0x7E], (Object)I[0x2F ^ 0x20]).put((Object)I[0x4E ^ 0x5E], (Object)I[0x21 ^ 0x30]).put((Object)I[0x99 ^ 0x8B], (Object)I[0x99 ^ 0x8A]).put((Object)I[0x53 ^ 0x47], (Object)I[0xB1 ^ 0xA4]).put((Object)I[0x48 ^ 0x5E], (Object)I[0x98 ^ 0x8F]).put((Object)I[0xB7 ^ 0xAF], (Object)I[0x9E ^ 0x87]).build();
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
            if (3 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    private static void I() {
        I = new String[0x38 ^ 0x23];
        CustomPayloadA.I["".length()] = CustomPayloadA.I("\u0006", "GJxiW");
        CustomPayloadA.I[" ".length()] = CustomPayloadA.I("05!\t\u001b\u0007", "sYHlu");
        CustomPayloadA.I["  ".length()] = CustomPayloadA.l("QDd1+nvCN0ckkqQ8J8T4ew==", "RsOZY");
        CustomPayloadA.I["   ".length()] = CustomPayloadA.l("KN1wJ+2KTotVjJDiVwnU0g==", "tWiiM");
        CustomPayloadA.I[29 ^ 25] = CustomPayloadA.l("cWTvA/EWXqM0HG06NEodognSKijjYmNZ", "enuEQ");
        CustomPayloadA.I[168 ^ 173] = CustomPayloadA.lI("CsJn4yE5njVE2sDPuZcupg==", "LAeKJ");
        CustomPayloadA.I[165 ^ 163] = CustomPayloadA.l("8x7NTFk8AT43/tQ09gikmA==", "NRqxz");
        CustomPayloadA.I[51 ^ 52] = CustomPayloadA.lI("g1+g/x44+eOh+3ziPHq4fQ==", "EcSts");
        CustomPayloadA.I[65 ^ 73] = CustomPayloadA.l("TsCrIBqHMlBGaOmPPQe8kw==", "RvTsa");
        CustomPayloadA.I[14 ^ 7] = CustomPayloadA.l("vkfWT99VMW7lh+Kuz+mMyA==", "JctFF");
        CustomPayloadA.I[136 ^ 130] = CustomPayloadA.I("\u000f#*\"", "lLIIE");
        CustomPayloadA.I[171 ^ 160] = CustomPayloadA.l("OtuX2I4eFdGWnqkrUwpY1Q==", "yVXpq");
        CustomPayloadA.I[106 ^ 102] = CustomPayloadA.lI("jYEI1MUD3BI=", "lVhBG");
        CustomPayloadA.I[176 ^ 189] = CustomPayloadA.I("5,97\u001aG\u000470", "gIXTr");
        CustomPayloadA.I[154 ^ 148] = CustomPayloadA.I("\u0002\n6;#", "poWXK");
        CustomPayloadA.I[11 ^ 4] = CustomPayloadA.I("\u000b\u0004\u0004-\u001fy,\n*", "YaeNw");
        CustomPayloadA.I[49 ^ 33] = CustomPayloadA.I("\u0016\u000b", "qlTHz");
        CustomPayloadA.I[163 ^ 178] = CustomPayloadA.lI("IWOyu30oDoobZg+x8ItehQ==", "WfEyM");
        CustomPayloadA.I[112 ^ 98] = CustomPayloadA.I("\u0012\f\u001e8+\u001c>\u0018%\u000b\u0001\u001c\u0003\u000e7\u0001\u0012\u001f?", "qymLD");
        CustomPayloadA.I[72 ^ 91] = CustomPayloadA.I("412\n+\u0005b\u0001\r0\u0013,6", "vBBaY");
        CustomPayloadA.I[71 ^ 83] = CustomPayloadA.l("3KwU3YIm0lzaNPXLUhttYg==", "dwzOH");
        CustomPayloadA.I[191 ^ 170] = CustomPayloadA.I("\t)\u0011\u000418z\"\u0003*.4\u0015", "KZaoC");
        CustomPayloadA.I[103 ^ 113] = CustomPayloadA.l("t/LbaiGrhNq52UhJNLyg7Q==", "lKJJe");
        CustomPayloadA.I[112 ^ 103] = CustomPayloadA.l("03KtmWUz0P1f7mxN+MrwmQ==", "qJxBH");
        CustomPayloadA.I[94 ^ 70] = CustomPayloadA.lI("ijJBhZknozE=", "tzezP");
        CustomPayloadA.I[18 ^ 11] = CustomPayloadA.lI("Qh631Trbq93J4T1F9fFukw==", "DVjwx");
        CustomPayloadA.I[3 ^ 25] = CustomPayloadA.l("OFROe+PWncU=", "ETtTn");
    }

    private static String lI(String obj, String key) {
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
}

