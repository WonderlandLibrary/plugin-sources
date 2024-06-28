/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.check.phase;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.MovementCheck;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.data.PlayerLocation;
import com.unknownmyname.manager.AlertsManager;
import com.unknownmyname.util.Cuboid;
import com.unknownmyname.util.MaterialList;
import com.unknownmyname.util.MathUtil;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PhaseA
extends MovementCheck {
    private static final /* synthetic */ String[] I;

    @Override
    public void handle(Player player, PlayerData playerData, PlayerLocation from, PlayerLocation to, long timestamp) {
        double[] arrd = new double["  ".length()];
        arrd["".length()] = to.getX() - from.getX();
        arrd[" ".length()] = to.getZ() - from.getZ();
        double distanceSquared = MathUtil.hypotSquared(arrd);
        if (playerData.getSneaking() != null && playerData.getSneaking() != false || Math.abs(to.getY() - from.getY()) > 2.0 && distanceSquared < 1.0 || playerData.getSuffocationTicks() < (0x61 ^ 0x75)) {
            if (!playerData.isSpawnedIn() || distanceSquared > Math.pow(50.0, 2.0)) {
                return;
            }
            if (Math.abs(from.getY() - to.getY()) > (double)player.getWorld().getMaxHeight()) {
                AlertsManager.getInstance().handleViolation(playerData, this, I["  ".length()]);
                return;
            }
            Cuboid fromCuboid = new Cuboid(from).expand(0.2999, 0.0, 0.2999);
            Cuboid toCuboid = new Cuboid(to).expand(0.2999, 0.0, 0.2999);
            Cuboid between = from.to(to);
            World world = player.getWorld();
            this.run(() -> {
                HashSet<Block> blocks = new HashSet<Block>(between.getBlocks(world));
                blocks.removeAll(fromCuboid.getBlocks(world));
                blocks.addAll(toCuboid.getBlocks(world));
                if (!Cuboid.checkBlocks(blocks, type -> {
                    boolean bl;
                    if (MaterialList.INPASSABLE.contains(type)) {
                        bl = "".length();
                        "".length();
                        if (4 <= -1) {
                            throw null;
                        }
                    } else {
                        bl = " ".length();
                    }
                    return bl;
                })) {
                    AlertsManager.getInstance().handleViolation(playerData, this, I["   ".length()]);
                    player.teleport(from.toLocation(world));
                }
            });
        }
    }

    static {
        PhaseA.I();
    }

    public PhaseA() {
        super(Check.CheckType.PHASEA, I["".length()], I[" ".length()], Check.CheckVersion.EXPERIMENTAL);
    }

    private static void I() {
        I = new String[0x11 ^ 0x15];
        PhaseA.I["".length()] = PhaseA.I("gxrymyi8azg=", "BlOZJ");
        PhaseA.I[" ".length()] = PhaseA.l("\u001d\f52\u0013", "MdTAv");
        PhaseA.I["  ".length()] = PhaseA.I("F4vCN3pCoOu2a5mpi7GKxA==", "lCcdp");
        PhaseA.I["   ".length()] = PhaseA.l("", "wYAKV");
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
            if (-1 < 0) continue;
            throw null;
        }
        return sb.toString();
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
}

