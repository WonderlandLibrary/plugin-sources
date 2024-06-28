/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.bukkit.ChatColor
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.entity.Player
 */
package com.unknownmyname.manager;

import com.google.common.collect.Maps;
import com.unknownmyname.check.Check;
import com.unknownmyname.manager.OptionsManager;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CheckManager {
    private static final /* synthetic */ String[] I;
    private /* synthetic */ Map<Check.CheckType, Boolean> checks;
    private static /* synthetic */ CheckManager instance;

    static {
        CheckManager.I();
    }

    public static CheckManager getInstance() {
        CheckManager checkManager;
        if (instance == null) {
            checkManager = instance = new CheckManager();
            "".length();
            if (false >= true) {
                throw null;
            }
        } else {
            checkManager = instance;
        }
        CheckManager checkManager2 = checkManager;
        return checkManager2;
    }

    private static void I() {
        I = new String[0xA7 ^ 0xA1];
        CheckManager.I["".length()] = CheckManager.I("DlDCPPfpq7M6ZxGREwlqQGQpGwDQ2HqPvdzEiZjbMo0=", "zHmoT");
        CheckManager.I[" ".length()] = CheckManager.l("w\n\f\u0003%<I\f\u00075w\u000b\u0001\u0003(w\r\r\u0015'5\u0005\u0001\u0002h", "WidfF");
        CheckManager.I["  ".length()] = CheckManager.lI("4nRTvvfFw4PR1UFe9/8loQ==", "LsKgB");
        CheckManager.I["   ".length()] = CheckManager.l("c\u0012\"+\u000e!\u0012(", "MwLJl");
        CheckManager.I[59 ^ 63] = CheckManager.l("\u0013\u000e /;\u0004\u001f5)#^", "pfELP");
        CheckManager.I[15 ^ 10] = CheckManager.l("\\?=\u0002\u001a\u001e?7", "rZScx");
    }

    public Map<Check.CheckType, Boolean> getChecks() {
        return this.checks;
    }

    public void disableType(Check.CheckType type, Player executor) {
        if (this.validate(type)) {
            this.checks.put(type, "".length() != 0);
            if (executor != null) {
                executor.sendMessage((Object)ChatColor.RED + type.getName() + I[" ".length()]);
                this.saveChecks();
            }
        }
    }

    private static String lI(String obj, String key) {
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

    private boolean validate(Check.CheckType type) {
        return this.checks.containsKey((Object)type);
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x82 ^ 0x8A), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void enable() {
        this.checks = Maps.newConcurrentMap();
        this.loadChecks();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void loadChecks() {
        var4_1 = Check.CheckType.values();
        var3_2 = var4_1.length;
        var2_3 = "".length();
        "".length();
        if (2 > 1) ** GOTO lbl12
        throw null;
lbl-1000: // 1 sources:
        {
            type = var4_1[var2_3];
            this.checks.put(type, OptionsManager.getInstance().getConfiguration().getBoolean(CheckManager.I["  ".length()] + type.getName() + CheckManager.I["   ".length()]));
            ++var2_3;
lbl12: // 2 sources:
            ** while (var2_3 < var3_2)
        }
lbl13: // 1 sources:
    }

    public boolean enabled(Check.CheckType type) {
        return this.checks.get((Object)type);
    }

    public void enableType(Check.CheckType type, Player executor) {
        if (this.validate(type)) {
            this.checks.put(type, " ".length() != 0);
            if (executor != null) {
                executor.sendMessage((Object)ChatColor.GREEN + type.getName() + I["".length()]);
                this.saveChecks();
            }
        }
    }

    public void disable() {
        this.saveChecks();
        this.checks = null;
        instance = null;
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
            if (4 > 2) continue;
            throw null;
        }
        return sb.toString();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void saveChecks() {
        var4_1 = Check.CheckType.values();
        var3_2 = var4_1.length;
        var2_3 = "".length();
        "".length();
        if (2 == 2) ** GOTO lbl11
        throw null;
lbl-1000: // 1 sources:
        {
            type = var4_1[var2_3];
            OptionsManager.getInstance().getConfiguration().set(CheckManager.I[90 ^ 94] + type.getName() + CheckManager.I[36 ^ 33], (Object)this.enabled(type));
            ++var2_3;
lbl11: // 2 sources:
            ** while (var2_3 < var3_2)
        }
lbl12: // 1 sources:
        OptionsManager.saveConfig();
    }
}

