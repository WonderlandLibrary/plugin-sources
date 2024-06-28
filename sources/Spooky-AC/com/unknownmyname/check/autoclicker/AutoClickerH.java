/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.BlockPosition
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.MaterialList;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerH
extends PacketCheck {
    private /* synthetic */ int combo;
    private /* synthetic */ boolean swing;
    private /* synthetic */ boolean placed;
    private /* synthetic */ Integer lastPlaceTicks;
    private /* synthetic */ int placeTicks;
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            this.swing = "".length();
            if (this.placed) {
                this.placeTicks += " ".length();
                "".length();
                if (true <= false) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.swing = " ".length();
            "".length();
            if (-1 != -1) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            PacketPlayInBlockPlace packetPlayInBlockPlace = (PacketPlayInBlockPlace)packet;
            if (this.swing && packetPlayInBlockPlace.a().getX() == -" ".length() && (packetPlayInBlockPlace.a().getY() == -" ".length() || packetPlayInBlockPlace.a().getY() == 140 + 221 - 135 + 29) && packetPlayInBlockPlace.a().getZ() == -" ".length() && packetPlayInBlockPlace.d() == 0.0f && packetPlayInBlockPlace.e() == 0.0f && packetPlayInBlockPlace.f() == 0.0f && packetPlayInBlockPlace.getFace() == 74 + 51 - 15 + 145 && packetPlayInBlockPlace.getItemStack() != null && MaterialList.canPlace(packetPlayInBlockPlace.getItemStack())) {
                this.placed = " ".length();
                this.placeTicks = "".length();
                "".length();
                if (2 == 0) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig packetPlayInBlockDig = (PacketPlayInBlockDig)packet;
            if (this.placed && packetPlayInBlockDig.c() == PacketPlayInBlockDig.EnumPlayerDigType.RELEASE_USE_ITEM) {
                if (this.lastPlaceTicks != null && this.lastPlaceTicks == this.placeTicks && this.placeTicks < (0x67 ^ 0x62)) {
                    int n = this.combo;
                    this.combo = n + " ".length();
                    if (n > (0xAE ^ 0xA4)) {
                        AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                        this.combo = "".length();
                        "".length();
                        if (!true) {
                            throw null;
                        }
                    }
                } else {
                    this.combo = "".length();
                }
                this.lastPlaceTicks = this.placeTicks;
                this.placeTicks = "".length();
            }
        }
    }

    static {
        AutoClickerH.I();
    }

    public AutoClickerH() {
        super(Check.CheckType.AUTO_CLICKERH, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.swing = "".length();
        this.placed = "".length();
        this.placeTicks = "".length();
        this.lastPlaceTicks = null;
        this.combo = "".length();
        this.violations = -1.0;
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x33 ^ 0x3B), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private static void I() {
        I = new String["   ".length()];
        AutoClickerH.I["".length()] = AutoClickerH.I("dpF1Q+OrCf4=", "UZEGT");
        AutoClickerH.I[" ".length()] = AutoClickerH.l("7\u0002::X5\u001b'6\u0013\u0013\u0005", "vwNUx");
        AutoClickerH.I["  ".length()] = AutoClickerH.lI("rI9hg5veLqk=", "GzPiW");
    }

    private static String l(String obj, String key) {
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
            if (true) continue;
            throw null;
        }
        return sb.toString();
    }
}

