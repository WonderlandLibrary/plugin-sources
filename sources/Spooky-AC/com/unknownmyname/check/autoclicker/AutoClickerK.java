/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity
 *  net.minecraft.server.v1_8_R3.PacketPlayInUseEntity$EnumEntityUseAction
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.entity.Player;

public class AutoClickerK
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Long lastFlying;

    public AutoClickerK() {
        super(Check.CheckType.AUTO_CLICKERK, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastFlying = null;
        this.setMaxViolation(0x8D ^ 0x87);
        this.violations = -0.5;
    }

    static {
        AutoClickerK.I();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        PacketPlayInUseEntity packetPlayInUseEntity;
        if (packet instanceof PacketPlayInFlying) {
            if (this.lastFlying != null) {
                if (timestamp - this.lastFlying > 40L && timestamp - this.lastFlying < 800L) {
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf((timestamp - this.lastFlying) * 2L), 0.25);
                    "".length();
                    if (-1 == 3) {
                        throw null;
                    }
                } else {
                    this.violations -= Math.min(this.violations + 0.5, 0.025);
                }
                this.lastFlying = null;
                "".length();
                if (-1 >= 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInUseEntity && (packetPlayInUseEntity = (PacketPlayInUseEntity)packet).a() == PacketPlayInUseEntity.EnumEntityUseAction.ATTACK) {
            long lastFlying = playerData.getLastFlying();
            if (timestamp - lastFlying < 10L) {
                this.lastFlying = lastFlying;
                "".length();
                if (4 < 2) {
                    throw null;
                }
            } else {
                this.violations -= Math.min(this.violations + 0.5, 0.025);
            }
        }
    }

    private static void I() {
        I = new String["  ".length()];
        AutoClickerK.I["".length()] = AutoClickerK.I("/sVns7y/VZs=", "TVsEc");
        AutoClickerK.I[" ".length()] = AutoClickerK.I("F8Xi95Sv04IfT65YZhBkxw==", "tLwly");
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x94 ^ 0x9C), "DES");
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

