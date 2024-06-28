/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.data;

import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class ClientVersion
extends Enum<ClientVersion> {
    public static final /* synthetic */ /* enum */ ClientVersion VERSION1_8;
    public static final /* synthetic */ /* enum */ ClientVersion VERSION1_7;
    public static final /* synthetic */ /* enum */ ClientVersion VERSION_UNSUPPORTED;
    private final /* synthetic */ String name;
    private static final /* synthetic */ String[] I;
    private static final /* synthetic */ ClientVersion[] ENUM$VALUES;

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

    private ClientVersion(String name) {
        this.name = name;
    }

    public static ClientVersion[] values() {
        ClientVersion[] arrclientVersion = ENUM$VALUES;
        int n = arrclientVersion.length;
        ClientVersion[] arrclientVersion2 = new ClientVersion[n];
        System.arraycopy(arrclientVersion, "".length(), arrclientVersion2, "".length(), n);
        return arrclientVersion2;
    }

    private static void I() {
        I = new String[0x8C ^ 0x8A];
        ClientVersion.I["".length()] = ClientVersion.I("V5GH19ZURk/1XRAMwMoZEQ==", "CrWBu");
        ClientVersion.I[" ".length()] = ClientVersion.l("wOl", "FaTCt");
        ClientVersion.I["  ".length()] = ClientVersion.I("Xa797rgRqiIdFZjud80n3w==", "hXRdl");
        ClientVersion.I["   ".length()] = ClientVersion.l("bGi", "SiQHw");
        ClientVersion.I[180 ^ 176] = ClientVersion.I("v4xHrYLo7i1kpC187ivE8scQurnKjG3S", "izJXH");
        ClientVersion.I[74 ^ 79] = ClientVersion.l("\u001c$\u00068*>$", "IJmVE");
    }

    public String getName() {
        return this.name;
    }

    public static ClientVersion valueOf(String string) {
        return Enum.valueOf(ClientVersion.class, string);
    }

    static {
        ClientVersion.I();
        VERSION1_7 = new ClientVersion(I[" ".length()]);
        VERSION1_8 = new ClientVersion(I["   ".length()]);
        VERSION_UNSUPPORTED = new ClientVersion(I[0x82 ^ 0x87]);
        ClientVersion[] arrclientVersion = new ClientVersion["   ".length()];
        arrclientVersion["".length()] = VERSION1_7;
        arrclientVersion[" ".length()] = VERSION1_8;
        arrclientVersion["  ".length()] = VERSION_UNSUPPORTED;
        ENUM$VALUES = arrclientVersion;
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
            if (3 > 0) continue;
            throw null;
        }
        return sb.toString();
    }
}

