/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  io.netty.channel.Channel
 *  io.netty.channel.ChannelHandler
 *  io.netty.channel.ChannelPipeline
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  net.minecraft.server.v1_8_R3.NetworkManager
 *  net.minecraft.server.v1_8_R3.PlayerConnection
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.manager;

import com.google.common.collect.Maps;
import com.unknownmyname.check.Check;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.injector.PacketDecoder;
import com.unknownmyname.injector.PacketEncoder;
import com.unknownmyname.main.Main;
import com.unknownmyname.type.LoaderCheck;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.NetworkManager;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {
    private static final /* synthetic */ String[] I;
    private static /* synthetic */ PlayerManager instance;
    private final /* synthetic */ Main plugin;
    private final /* synthetic */ Map<UUID, PlayerData> players;

    public PlayerManager(Main plugin) {
        this.players = Maps.newConcurrentMap();
        this.plugin = plugin;
    }

    static {
        PlayerManager.I();
    }

    public static PlayerManager getInstance() {
        return instance;
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

    public Map<UUID, PlayerData> getPlayers() {
        return this.players;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static void enable(Main plugin) {
        PlayerManager.instance = new PlayerManager(plugin);
        var2_1 = Bukkit.getOnlinePlayers().iterator();
        "".length();
        if (true > false) ** GOTO lbl9
        throw null;
lbl-1000: // 1 sources:
        {
            player = (Player)var2_1.next();
            PlayerManager.instance.inject(player);
lbl9: // 2 sources:
            ** while (var2_1.hasNext())
        }
lbl10: // 1 sources:
    }

    public void uninject(Player player) {
        PlayerData playerData = this.players.remove(player.getUniqueId());
        if (playerData != null) {
            playerData.setEnabled("".length() != 0);
            PlayerConnection playerConnection = playerData.getEntityPlayer().playerConnection;
            if (playerConnection != null && !playerConnection.isDisconnected()) {
                Channel channel = playerConnection.networkManager.channel;
                try {
                    channel.pipeline().remove(I[0xAE ^ 0xAA]);
                    channel.pipeline().remove(I[0x1C ^ 0x19]);
                    "".length();
                }
                catch (Throwable throwable) {
                    // empty catch block
                }
                if (4 == 1) {
                    throw null;
                }
            }
        }
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x79 ^ 0x71), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void inject(Player player) {
        PlayerData playerData = new PlayerData(player, this.plugin.getTypeLoader().loadChecks());
        Channel channel = playerData.getEntityPlayer().playerConnection.networkManager.channel;
        this.players.put(player.getUniqueId(), playerData);
        if (channel != null) {
            channel.pipeline().addBefore(I["".length()], I[" ".length()], (ChannelHandler)new PacketEncoder(playerData));
            channel.pipeline().addBefore(I["  ".length()], I["   ".length()], (ChannelHandler)new PacketDecoder(playerData));
        }
    }

    public PlayerData getPlayer(Player player) {
        return this.players.get(player.getUniqueId());
    }

    private static void I() {
        I = new String[0x1A ^ 0x1C];
        PlayerManager.I["".length()] = PlayerManager.I("H9WjahtxU5ylHPYYNbjU1Q==", "VVteD");
        PlayerManager.I[" ".length()] = PlayerManager.l("\u00077\b\u001c\u0010\\7\u0014\n\f\u00157\b", "qRzic");
        PlayerManager.I["  ".length()] = PlayerManager.lI("NkxfOmtgTltqNaLHqMfBhg==", "TNmmb");
        PlayerManager.I["   ".length()] = PlayerManager.I("837Isf9cejU0JGtyhVokEg==", "qiTtw");
        PlayerManager.I[62 ^ 58] = PlayerManager.I("ZmNsAi5DJ2/q7C3LoiI6xQ==", "azRVN");
        PlayerManager.I[181 ^ 176] = PlayerManager.I("qx+CVwaDPEgoiEbV/YLIfg==", "SDkMY");
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
            if (4 > -1) continue;
            throw null;
        }
        return sb.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static void disable() {
        var1 = Bukkit.getOnlinePlayers().iterator();
        "".length();
        if (0 < 3) ** GOTO lbl8
        throw null;
lbl-1000: // 1 sources:
        {
            player = (Player)var1.next();
            PlayerManager.instance.uninject(player);
lbl8: // 2 sources:
            ** while (var1.hasNext())
        }
lbl9: // 1 sources:
        PlayerManager.instance.getClass();
        PlayerManager.instance = null;
    }

    public Main getPlugin() {
        return this.plugin;
    }
}

