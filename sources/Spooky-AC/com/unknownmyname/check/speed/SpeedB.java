/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.speed;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SpeedB
extends MovementCheck {
    private /* synthetic */ int lastLadderCheck;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ int threshold;
    private /* synthetic */ boolean ladder;

    private static void I() {
        I = new String["  ".length()];
        SpeedB.I["".length()] = SpeedB.I("1/NS7rpQEqA=", "wQQWY");
        SpeedB.I[" ".length()] = SpeedB.l("&\u0012#<-", "ubFYI");
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

    private /* synthetic */ void lambda$0(World world, PlayerLocation playerLocation, PlayerData playerData, double d) {
        Block block = world.getBlockAt((int)Math.floor(playerLocation.getX()), (int)Math.floor(playerLocation.getY() + 1.0), (int)Math.floor(playerLocation.getZ()));
        Material type = block.getType();
        if (type != Material.VINE && type != Material.LADDER) {
            this.ladder = "".length();
            "".length();
            if (false) {
                throw null;
            }
        } else {
            AlertsManager.getInstance().handleViolation(playerData, this, String.valueOf(d));
        }
    }

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        if (to.getY() > from.getY() && !to.getOnGround().booleanValue() && !from.getOnGround().booleanValue()) {
            if (this.ladder) {
                double yDiff = to.getY() - from.getY();
                if (yDiff > 0.118) {
                    int n = this.threshold;
                    this.threshold = n + " ".length();
                    if (n > "  ".length()) {
                        this.threshold = "".length();
                        Object location = to.clone();
                        World world = player.getWorld();
                        this.run(() -> this.lambda$0(world, (PlayerLocation)location, playerData, yDiff));
                        "".length();
                        if (4 < 0) {
                            throw null;
                        }
                    }
                } else {
                    this.threshold = "".length();
                    "".length();
                    if (1 <= -1) {
                        throw null;
                    }
                }
            } else {
                int n = this.lastLadderCheck;
                this.lastLadderCheck = n + " ".length();
                if (n > (0x42 ^ 0x4B)) {
                    this.lastLadderCheck = "".length();
                    Object location = to.clone();
                    World world = player.getWorld();
                    this.run(() -> this.lambda$1(world, (PlayerLocation)location));
                    "".length();
                    if (4 <= 3) {
                        throw null;
                    }
                }
            }
        } else {
            this.ladder = "".length();
        }
    }

    static {
        SpeedB.I();
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
            if (3 > 2) continue;
            throw null;
        }
        return sb.toString();
    }

    public SpeedB() {
        super(Check.CheckType.SPEEDB, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.lastLadderCheck = "".length();
        this.ladder = "".length();
        this.threshold = "".length();
        this.setMaxViolation(0x3B ^ 0x22);
    }

    private /* synthetic */ void lambda$1(World world, PlayerLocation playerLocation) {
        Block block = world.getBlockAt((int)Math.floor(playerLocation.getX()), (int)Math.floor(playerLocation.getY() + 1.0), (int)Math.floor(playerLocation.getZ()));
        Material type = block.getType();
        if (type == Material.VINE || type == Material.LADDER) {
            this.ladder = " ".length();
        }
    }
}

