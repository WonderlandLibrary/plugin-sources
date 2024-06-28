/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package com.unknownmyname.manager;

import com.unknownmyname.check.Check;
import com.unknownmyname.check.alert.Alert;
import com.unknownmyname.data.PlayerData;
import com.unknownmyname.main.Main;
import com.unknownmyname.manager.CheckManager;
import com.unknownmyname.manager.OptionsManager;
import com.unknownmyname.manager.PlayerManager;
import com.unknownmyname.manager.PunishmentManager;
import com.unknownmyname.util.BukkitUtils;
import java.security.Key;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AlertsManager {
    private final /* synthetic */ ExecutorService executorService;
    private static final /* synthetic */ String CERTAINTY;
    public static /* synthetic */ ArrayList<Player> cooldown;
    private static final /* synthetic */ String NAME;
    private static final /* synthetic */ String DEBUG;
    public static /* synthetic */ AlertsManager instance;
    private final /* synthetic */ SimpleDateFormat simpleDateFormat;
    private static final /* synthetic */ String CERTAINTYEXP;
    private static final /* synthetic */ String ALERT;
    private static final /* synthetic */ String[] I;

    public void handleAlert(final PlayerData playerData, Check check, int vl) {
        if (CheckManager.getInstance().enabled(check.getType())) {
            if (cooldown.contains((Object)playerData.getPlayer())) {
                return;
            }
            if (check.getCheckVersion() == Check.CheckVersion.RELEASE) {
                PlayerManager.getInstance().getPlayers().values().stream().filter(PlayerData::isAlerts).map(PlayerData::getPlayer).forEach(player -> player.sendMessage(this.getFormattedAlert(playerData.getPlayer().getName(), String.valueOf(String.valueOf(check.getFriendlyName())) + I[0x34 ^ 0x17] + check.getSubType(), String.valueOf(vl))));
                cooldown.add(playerData.getPlayer());
                new BukkitRunnable(){

                    public void run() {
                        cooldown.remove((Object)playerData.getPlayer());
                    }
                }.runTaskLaterAsynchronously((Plugin)Main.getInstance(), 15L);
                "".length();
                if (2 < 2) {
                    throw null;
                }
            } else {
                PlayerManager.getInstance().getPlayers().values().stream().filter(PlayerData::isAlerts).map(PlayerData::getPlayer).forEach(player -> player.sendMessage(this.getFormattedExpir(playerData.getPlayer().getName(), String.valueOf(String.valueOf(check.getFriendlyName())) + I[0xB1 ^ 0x95] + check.getSubType(), String.valueOf(vl))));
                cooldown.add(playerData.getPlayer());
                new BukkitRunnable(){

                    public void run() {
                        cooldown.remove((Object)playerData.getPlayer());
                    }
                }.runTaskLaterAsynchronously((Plugin)Main.getInstance(), 15L);
            }
        }
    }

    public AlertsManager() {
        this.executorService = Executors.newSingleThreadExecutor();
        this.simpleDateFormat = new SimpleDateFormat(I[0x97 ^ 0x9F]);
        cooldown = new ArrayList();
    }

    public static AlertsManager getInstance() {
        AlertsManager alertsManager;
        if (instance == null) {
            alertsManager = instance = new AlertsManager();
            "".length();
            if (-1 == 3) {
                throw null;
            }
        } else {
            alertsManager = instance;
        }
        AlertsManager alertsManager2 = alertsManager;
        return alertsManager2;
    }

    public void handleViolation(PlayerData playerData, Check check, String data) {
        this.handleViolation(playerData, check, data, 1.0);
    }

    public void handleViolation(final PlayerData playerData, Check check, String data, double vl, boolean disguise) {
        Main.getInstance();
        Main.listCheat().add(playerData.getPlayer());
        if (CheckManager.getInstance().enabled(check.getType())) {
            BukkitUtils.log(playerData.getPlayer(), I[0x4C ^ 0x59] + new SimpleDateFormat(I[0x95 ^ 0x83]).format(new Date()) + I[0x7E ^ 0x69] + check.getFriendlyName() + I[0xAE ^ 0xB6] + check.getSubType() + I[0x2E ^ 0x37]);
        }
        new BukkitRunnable(){

            public void run() {
                Main.getInstance();
                Main.listCheat().remove((Object)playerData.getPlayer());
            }
        }.runTaskLaterAsynchronously((Plugin)Main.getInstance(), 600L);
        Main.getInstance();
        Main.lastCheck.put(playerData.getPlayer().getUniqueId(), String.valueOf(check.getFriendlyName()) + I[0x7A ^ 0x60] + check.getSubType());
        this.executorService.submit(() -> this._handleViolation(playerData, check, data, vl, disguise));
    }

    private static String lI(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x4F ^ 0x47), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleBan(PlayerData playerData, Check check, boolean disguise) {
        if (!(!CheckManager.getInstance().enabled(check.getType()) || !OptionsManager.getInstance().isAutoBan() || check.getCheckVersion() != Check.CheckVersion.RELEASE || playerData.isBanned() || playerData.isDebug() || OptionsManager.getInstance().isBypassEnabled() && playerData.getPlayer().hasPermission(OptionsManager.getInstance().getBypassPermission()))) {
            if (disguise) {
                playerData.setSpoofBan(" ".length() != 0);
                playerData.setSpoofBanCheck(check);
                "".length();
                if (true < true) {
                    throw null;
                }
            } else {
                this._handleBan(playerData, check);
            }
        }
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
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
            if (2 > 1) continue;
            throw null;
        }
        return sb.toString();
    }

    public String getFormMaxCps(Player player) {
        return ChatColor.translateAlternateColorCodes((char)(0x15 ^ 0x33), (String)ALERT.replace(I[0x73 ^ 0x60], NAME).replace(I[0x38 ^ 0x2C], (CharSequence)player));
    }

    public void handleExport(PlayerData playerData, Alert alert) {
        if (playerData.getQueuedAlerts().size() > (0xB7 ^ 0xA3)) {
            playerData.getQueuedAlerts().clear();
        }
        playerData.getQueuedAlerts().add(alert);
    }

    public String getFormattedAlert(String player, String check, String vl) {
        return ChatColor.translateAlternateColorCodes((char)(0x78 ^ 0x5E), (String)ALERT.replace(I[0xB8 ^ 0xB1], NAME).replace(I[0x1A ^ 0x10], player).replace(I[0xB5 ^ 0xBE], CERTAINTY).replace(I[0xB ^ 7], check).replace(I[0xA2 ^ 0xAF], vl));
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

    public void handleViolation(PlayerData playerData, Check check, String data, double vl) {
        this.handleViolation(playerData, check, data, vl, "".length() != 0);
    }

    static {
        AlertsManager.I();
        NAME = OptionsManager.getInstance().getAnticheatName();
        CERTAINTY = OptionsManager.getInstance().getAlertCertainty();
        CERTAINTYEXP = OptionsManager.getInstance().getAlertExp();
        ALERT = OptionsManager.getInstance().getAlertMessage();
        DEBUG = I["".length()] + (Object)ChatColor.WHITE + I[" ".length()] + (Object)ChatColor.GRAY + I["  ".length()] + (Object)ChatColor.WHITE + I["   ".length()] + I[0xAB ^ 0xAF] + I[0x98 ^ 0x9D] + (Object)ChatColor.GRAY + I[5 ^ 3] + I[0x61 ^ 0x66];
    }

    private void _handleViolation(PlayerData playerData, Check check, String data, double vl, boolean disguise) {
        if (playerData.isEnabled()) {
            check.setViolations(check.getViolations() + vl);
            int currentViolation = (int)Math.floor(check.getViolations());
            if (check.getViolations() > 0.0) {
                this.handleDebug(playerData, check, check.getViolations(), data);
            }
            if (currentViolation > 0) {
                if (currentViolation > check.getLastViolation()) {
                    Alert alert = new Alert(check, data, currentViolation);
                    if (disguise) {
                        if (playerData.getSpoofedAlerts().size() > (0x17 ^ 0x12)) {
                            playerData.getSpoofedAlerts().poll();
                        }
                        playerData.getSpoofedAlerts().add(alert);
                        playerData.setCheckSpoofing(" ".length() != 0);
                        "".length();
                        if (2 != 2) {
                            throw null;
                        }
                    } else {
                        this.handleAlert(playerData, check, currentViolation);
                    }
                    if (check.getViolations() >= (double)check.getMaxViolation()) {
                        this.handleBan(playerData, check, disguise);
                        String name = playerData.getPlayer().getName();
                        OptionsManager.getInstance().getBanConfiguration().set(String.valueOf(name) + I[0x5F ^ 0x44], (Object)String.valueOf(playerData.getPlayer().getUniqueId()));
                        OptionsManager.getInstance().getBanConfiguration().set(String.valueOf(name) + I[0x75 ^ 0x69], (Object)String.valueOf(this.simpleDateFormat.format(new Date(alert.getTimestamp()))));
                        OptionsManager.getInstance().getBanConfiguration().set(String.valueOf(name) + I[0x2E ^ 0x33], (Object)String.valueOf(String.valueOf(check.getType().getName()) + I[0x19 ^ 7] + check.getSubType()));
                        OptionsManager.getInstance().getBanConfiguration().set(String.valueOf(name) + I[0x56 ^ 0x49], (Object)String.valueOf(String.valueOf(alert.getData()) + I[0x18 ^ 0x38] + alert.getVl() + I[0xA9 ^ 0x88] + playerData.getPing() + I[0x2C ^ 0xE]));
                    }
                    this.handleExport(playerData, alert);
                }
                check.setLastViolation(currentViolation);
            }
        }
    }

    public String getFormattedExpir(String player, String check, String vl) {
        return ChatColor.translateAlternateColorCodes((char)(0x4C ^ 0x6A), (String)ALERT.replace(I[0x2C ^ 0x22], NAME).replace(I[0x42 ^ 0x4D], player).replace(I[7 ^ 0x17], CERTAINTYEXP).replace(I[0x1C ^ 0xD], check).replace(I[0x54 ^ 0x46], vl));
    }

    private static void I() {
        I = new String[0x90 ^ 0xB6];
        AlertsManager.I["".length()] = AlertsManager.I("yW+5F2kfYGPxqHDqp28Amz20Eyny1+Zj", "iynEC");
        AlertsManager.I[" ".length()] = AlertsManager.I("rwoFXylSd8o=", "BWXBe");
        AlertsManager.I["  ".length()] = AlertsManager.I("XehAaCxCouY=", "mFvKl");
        AlertsManager.I["   ".length()] = AlertsManager.I("jgLXH0QyEe4e30/mCcu6Jw==", "EDDWT");
        AlertsManager.I[139 ^ 143] = AlertsManager.l("\u00f4\u007f", "SFzgb");
        AlertsManager.I[94 ^ 91] = AlertsManager.lI("81c+t+Xk2yk=", "ZNdNh");
        AlertsManager.I[136 ^ 142] = AlertsManager.l("$Q", "yqTkx");
        AlertsManager.I[172 ^ 171] = AlertsManager.I("QOzBQGHAsfA=", "ytAxM");
        AlertsManager.I[168 ^ 160] = AlertsManager.lI("Qc6cLLHo4OA=", "hRszl");
        AlertsManager.I[164 ^ 173] = AlertsManager.I("cDfp8FxvDR8dDxzW8TCbJA==", "USrxB");
        AlertsManager.I[128 ^ 138] = AlertsManager.lI("B0//KxhD/9BMW5D7Y17PWA==", "aHpLa");
        AlertsManager.I[184 ^ 179] = AlertsManager.lI("rf/baBNcEUfDUAbwwaf7VQ==", "AQviu");
        AlertsManager.I[29 ^ 17] = AlertsManager.lI("IB+JHVMgPhU=", "goIyS");
        AlertsManager.I[83 ^ 94] = AlertsManager.lI("jj+3nCfq2es=", "ujlXS");
        AlertsManager.I[4 ^ 10] = AlertsManager.l("\u001a9#\u0010\u0016\b1,", "aIQup");
        AlertsManager.I[41 ^ 38] = AlertsManager.lI("AybRoL7lsurCmjelGTy8tg==", "hTqVe");
        AlertsManager.I[103 ^ 119] = AlertsManager.I("YQG67dDPhjzaGQGEhgdQ1g==", "mqdZe");
        AlertsManager.I[68 ^ 85] = AlertsManager.I("6vznyDe19Gw=", "SJAql");
        AlertsManager.I[208 ^ 194] = AlertsManager.lI("HlKHkLbNCUs=", "MOZLv");
        AlertsManager.I[51 ^ 32] = AlertsManager.l(":4:\u000f!(<5", "ADHjG");
        AlertsManager.I[25 ^ 13] = AlertsManager.l("\u001d)5\u0018\u0010\u0003+$", "fYYyi");
        AlertsManager.I[143 ^ 154] = AlertsManager.lI("Tj9OwdQ9j7U=", "SwswB");
        AlertsManager.I[94 ^ 72] = AlertsManager.l("%(~\t#n5(d\u0006)v<)T2?", "ALQDn");
        AlertsManager.I[100 ^ 115] = AlertsManager.I("JBXe/zV9LRI=", "MeoQB");
        AlertsManager.I[142 ^ 150] = AlertsManager.l("z", "ZUBpE");
        AlertsManager.I[50 ^ 43] = AlertsManager.lI("PtnKIHYKbwE=", "sySWJ");
        AlertsManager.I[118 ^ 108] = AlertsManager.l("r", "RYlNZ");
        AlertsManager.I[57 ^ 34] = AlertsManager.l("K<\u0002?0", "eIwVT");
        AlertsManager.I[74 ^ 86] = AlertsManager.lI("xUtccj/S4Qk=", "tdVgq");
        AlertsManager.I[61 ^ 32] = AlertsManager.I("yQ3kvmzwh2I=", "vxBTX");
        AlertsManager.I[142 ^ 144] = AlertsManager.lI("kVWoMFXlk6Y=", "TVGTL");
        AlertsManager.I[134 ^ 153] = AlertsManager.l("_\u000f\u0007;\b", "qkfOi");
        AlertsManager.I[36 ^ 4] = AlertsManager.lI("qkKiqN2Ox9U=", "ncPRC");
        AlertsManager.I[128 ^ 161] = AlertsManager.lI("oDMuy9wa19M=", "HvIsB");
        AlertsManager.I[7 ^ 37] = AlertsManager.lI("LyWmS+ZQDeU=", "BgFqJ");
        AlertsManager.I[99 ^ 64] = AlertsManager.lI("Y+qAHBXapuY=", "oTahH");
        AlertsManager.I[226 ^ 198] = AlertsManager.lI("N7ADiwfi8sI=", "hgtKj");
        AlertsManager.I[109 ^ 72] = AlertsManager.lI("W/9xTp1jwPM=", "WlSxi");
    }

    public void _handleBan(PlayerData playerData, Check check) {
        PunishmentManager.getInstance().insertBan(playerData, check);
    }

    public void handleDebug(PlayerData playerData, Check check, double vl, String data) {
        PlayerManager.getInstance().getPlayers().values().stream().filter(PlayerData::isDebug).map(PlayerData::getPlayer).forEach(player -> {
            Object[] arrobject = new Object[0x12 ^ 0x17];
            arrobject["".length()] = playerData.getPlayer().getName();
            arrobject[" ".length()] = check.getType().getName();
            arrobject["  ".length()] = new DecimalFormat(I[0x40 ^ 0x65]).format(vl);
            arrobject["   ".length()] = data;
            player.sendMessage(String.format(DEBUG, arrobject));
        });
    }

}

