/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.velocity;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class VelocityA
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int ticks;

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 5 ^ 0xD), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public VelocityA() {
        super(Check.CheckType.VELOCITYA, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.ticks = "".length();
        this.setMaxViolation(0x51 ^ 0x54);
        this.violations = -3.0;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (!(packet instanceof PacketPlayInFlying)) return;
        PlayerLocation from = playerData.getLastLastLocation();
        PlayerLocation to = playerData.getLocation();
        if (playerData.getVerticalVelocityTicks() <= playerData.getMoveTicks() || !(playerData.getLastVelY() > 0.0)) return;
        double y = to.getY() - from.getY();
        if (y > 0.0) {
            double d;
            y = Math.ceil(y * 8000.0) / 8000.0;
            if (d < 0.41999998688697815 && playerData.getLastLastLocation().getOnGround().booleanValue() && from.getOnGround().booleanValue() && !to.getOnGround().booleanValue() && MathUtil.onGround(from.getY()) && !MathUtil.onGround(to.getY())) {
                double d2;
                double percentage = y / playerData.getLastVelY();
                if (d2 < 0.995) {
                    AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(percentage), 1.0);
                }
            }
            playerData.setLastVelY(0.0);
            this.ticks = "".length();
            "".length();
            if (4 != 2) return;
            throw null;
        }
        if (!playerData.hasLag(timestamp - 100L) && !playerData.hasFast(timestamp - 100L)) {
            int n = this.ticks;
            this.ticks = n + " ".length();
            if (n > playerData.getMaxPingTicks() * "  ".length() + "   ".length()) {
                playerData.setLastVelY(0.0);
                this.ticks = "".length();
                AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(0x34 ^ 0x50), 1.0);
                "".length();
                if (0 < 3) return;
                throw null;
            }
        }
        this.violations -= Math.min(this.violations + 1.5, 0.01);
    }

    private static void I() {
        I = new String["  ".length()];
        VelocityA.I["".length()] = VelocityA.I("jVYe64IbZFs=", "lNTYA");
        VelocityA.I[" ".length()] = VelocityA.l("rUOocs0/bes/+qQqq8SoFw==", "enENt");
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

    static {
        VelocityA.I();
    }
}

