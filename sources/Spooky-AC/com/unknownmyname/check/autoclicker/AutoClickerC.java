/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockDig$EnumPlayerDigType
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockDig;
import org.bukkit.entity.Player;

public class AutoClickerC
extends PacketCheck {
    private /* synthetic */ double vl;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ boolean sent;

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInBlockDig) {
            PacketPlayInBlockDig.EnumPlayerDigType digType = ((PacketPlayInBlockDig)packet).c();
            if (digType == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                this.sent = " ".length();
                "".length();
                if (-1 >= 0) {
                    throw null;
                }
            } else if (digType == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                int vl = (int)this.vl;
                if (this.sent) {
                    if (++vl > (0xAC ^ 0xA8)) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(vl), 0.75, " ".length() != 0);
                        "".length();
                        if (2 < 1) {
                            throw null;
                        }
                    }
                } else {
                    this.violations -= Math.min(this.violations + 1.0, 0.1);
                    vl = "".length();
                }
                this.vl = vl;
                "".length();
                if (2 < 2) {
                    throw null;
                }
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.sent = "".length();
        }
    }

    public AutoClickerC() {
        super(Check.CheckType.AUTO_CLICKERC, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.setMaxViolation(0x1E ^ 0x1B);
        this.violations = -1.0;
    }

    static {
        AutoClickerC.I();
    }

    private static String I(String obj, String key) {
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
        I = new String["  ".length()];
        AutoClickerC.I["".length()] = AutoClickerC.I("9/oecX4pOfI=", "buBDq");
        AutoClickerC.I[" ".length()] = AutoClickerC.I("vbWteQ6/WgrPxoxYdFsrAA==", "AhHQw");
    }
}

