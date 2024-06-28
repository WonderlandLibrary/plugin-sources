/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.autoclicker;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayInBlockPlace;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class AutoClickerA
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int ticks;
    private /* synthetic */ boolean place;
    private /* synthetic */ Queue<Integer> intervals;
    private /* synthetic */ boolean swung;

    private static void I() {
        I = new String["  ".length()];
        AutoClickerA.I["".length()] = AutoClickerA.I("jT8C8YxqKps=", "kdGlJ");
        AutoClickerA.I[" ".length()] = AutoClickerA.I("DTIEcPneUq23EOHpGP5K2w==", "TZZLn");
    }

    public AutoClickerA() {
        super(Check.CheckType.AUTO_CLICKERA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.intervals = new ConcurrentLinkedQueue<Integer>();
        this.ticks = "".length();
        this.swung = "".length();
        this.place = "".length();
        this.setMaxViolation(0x96 ^ 0x9C);
        this.violations = -6.0;
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying) {
            if (this.swung && !playerData.isSwingDigging() && !this.place) {
                if (this.ticks < (0x9D ^ 0x95)) {
                    this.intervals.add(this.ticks);
                    if (this.intervals.size() >= (0x93 ^ 0xBB)) {
                        double deviation = MathUtil.deviation(this.intervals);
                        this.violations += (0.325 - deviation) * 2.0 + 0.675;
                        if (this.violations < -6.0) {
                            this.violations = -6.0;
                        }
                        if (deviation < 0.325) {
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(deviation), 0.0);
                        }
                        this.intervals.clear();
                    }
                }
                this.ticks = "".length();
            }
            this.place = "".length();
            this.swung = "".length();
            this.ticks += " ".length();
            "".length();
            if (1 >= 2) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInArmAnimation) {
            this.swung = " ".length();
            "".length();
            if (false) {
                throw null;
            }
        } else if (packet instanceof PacketPlayInBlockPlace) {
            this.place = " ".length();
        }
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0xB9 ^ 0xB1), "DES");
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
        AutoClickerA.I();
    }
}

