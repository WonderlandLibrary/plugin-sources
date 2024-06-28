/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying
 *  net.minecraft.server.v1_8_R3.PacketPlayInFlying$PacketPlayInLook
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.killaura;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.PacketCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.manager.AlertsManager;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.entity.Player;

public class KillAuraB
extends PacketCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Float lastYaw;
    private /* synthetic */ float yawSpeed;

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
            if (2 >= 1) continue;
            throw null;
        }
        return sb.toString();
    }

    @Override
    public void handle(Player player, PlayerData playerData, Packet packet, long timestamp) {
        if (packet instanceof PacketPlayInFlying.PacketPlayInLook) {
            PacketPlayInFlying.PacketPlayInLook packetPlayInLook = (PacketPlayInFlying.PacketPlayInLook)packet;
            if (this.lastYaw != null && timestamp - playerData.getLastPosition() > 3500L) {
                float changeYaw = Math.abs(this.lastYaw.floatValue() - packetPlayInLook.d());
                if (this.yawSpeed < 45.0f && playerData.getTeleportTicks() > (0x38 ^ 0x3D)) {
                    float nearValue;
                    if (changeYaw > 345.0f && changeYaw < 375.0f) {
                        float nearValue2 = Math.abs(360.0f - (changeYaw + Math.abs(180.0f - Math.abs(packetPlayInLook.d() % 180.0f - this.lastYaw.floatValue() % 180.0f))));
                        if ((double)Math.abs(nearValue2) == 0.0) {
                            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(changeYaw), 1.0, " ".length() != 0);
                            "".length();
                            if (2 != 2) {
                                throw null;
                            }
                        }
                    } else if ((double)changeYaw > 172.5 && (double)changeYaw < 187.5 && (double)Math.abs(nearValue = Math.abs(180.0f - (changeYaw + Math.abs(90.0f - Math.abs(packetPlayInLook.d() % 90.0f - this.lastYaw.floatValue() % 90.0f))))) == 0.0) {
                        AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(changeYaw), 0.75, " ".length() != 0);
                    }
                    this.violations -= Math.min(this.violations + 2.0, 0.01);
                }
                this.yawSpeed *= 3.0f;
                this.yawSpeed += changeYaw;
                this.yawSpeed /= 4.0f;
            }
            this.lastYaw = Float.valueOf(packetPlayInLook.d());
        }
    }

    static {
        KillAuraB.I();
    }

    public KillAuraB() {
        super(Check.CheckType.KILL_AURAB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastYaw = null;
        this.yawSpeed = 360.0f;
        this.setMaxViolation(0x65 ^ 0x6F);
        this.violations = -2.0;
    }

    private static void I() {
        I = new String["  ".length()];
        KillAuraB.I["".length()] = KillAuraB.I("\u0000", "BsDqJ");
        KillAuraB.I[" ".length()] = KillAuraB.I("&\u0010\u000e\u001f\b\u0018\u000b\u0003", "mybsI");
    }
}

