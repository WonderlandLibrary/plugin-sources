/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.check.badpackets;

import com.unknownmyname.check.Check;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BadPacketsC
extends Check {
    private static final /* synthetic */ String[] I;

    private static void I() {
        I = new String["  ".length()];
        BadPacketsC.I["".length()] = BadPacketsC.I("9", "xBvFK");
        BadPacketsC.I[" ".length()] = BadPacketsC.l("Vj+HIu4RqJlMyaqL/QoI2A==", "HcYri");
    }

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
            if (-1 != 3) continue;
            throw null;
        }
        return sb.toString();
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

    public BadPacketsC() {
        super(Check.CheckType.BADPACKETSC, I["".length()], I[" ".length()], Check.CheckVersion.RELEASE);
        this.violations = -1.0;
        this.setMaxViolation("  ".length());
    }

    static {
        BadPacketsC.I();
    }
}

