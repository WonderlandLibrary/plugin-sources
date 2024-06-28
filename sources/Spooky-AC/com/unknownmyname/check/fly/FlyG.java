/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.fly;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.function.Predicate;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class FlyG
extends MovementCheck {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Double lastY;

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
            if (2 < 4) continue;
            throw null;
        }
        return sb.toString();
    }

    static {
        FlyG.I();
    }

    public FlyG() {
        super(Check.CheckType.FLYG, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
        this.lastY = null;
        this.violations = -1.0;
        this.setMaxViolation(27 + 192 - 90 + 171);
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (this.lastY != null && to.getY() == this.lastY.doubleValue() && from.getY() > this.lastY && playerData.getTeleportTicks() > playerData.getPingTicks() * "  ".length()) {
            if (player.isFlying()) {
                return;
            }
            World world = player.getWorld();
            Cuboid cuboidAbove = new Cuboid(playerData.getLocation()).move(0.0, 1.0, 0.0).expand(0.5, 0.5, 0.5);
            this.run(() -> {
                if (cuboidAbove.checkBlocks(world, type -> {
                    boolean bl;
                    if (type == Material.AIR) {
                        bl = " ".length();
                        "".length();
                        if (4 <= 1) {
                            throw null;
                        }
                    } else {
                        bl = "".length();
                    }
                    return bl;
                })) {
                    AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                }
            });
        }
        this.lastY = from.getY();
    }

    private static void I() {
        I = new String["   ".length()];
        FlyG.I["".length()] = FlyG.I("\u0013", "TstgR");
        FlyG.I[" ".length()] = FlyG.l("yFJt+/rhIn4=", "SMolE");
        FlyG.I["  ".length()] = FlyG.l("bkBPgUvhUTE=", "HzHkd");
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
}

