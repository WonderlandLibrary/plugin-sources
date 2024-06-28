/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package com.unknownmyname.check;

import com.unknownmyname.main.Main;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Check {
    private final /* synthetic */ CheckVersion checkVersion;
    protected /* synthetic */ double violations;
    private /* synthetic */ boolean banwave;
    private /* synthetic */ int maxViolation;
    private /* synthetic */ int lastViolation;
    private final /* synthetic */ String friendlyName;
    private final /* synthetic */ CheckType type;
    private final /* synthetic */ String subType;
    private /* synthetic */ long lastCheck;

    public int getLastViolation() {
        return this.lastViolation;
    }

    public void run(final Runnable runnable) {
        new BukkitRunnable(){

            public void run() {
                runnable.run();
            }
        }.runTask((Plugin)Main.getPlugin());
    }

    public String getSubType() {
        return this.subType;
    }

    public Check(CheckType type, String subType, String friendlyName, CheckVersion checkVersion) {
        this.violations = 0.0;
        this.lastViolation = "".length();
        this.maxViolation = 2058211811 + 808207039 - -2123993401 + 1452038692;
        this.lastCheck = 0L;
        this.banwave = "".length();
        this.type = type;
        this.subType = subType;
        this.friendlyName = friendlyName;
        this.checkVersion = checkVersion;
    }

    public CheckType getType() {
        return this.type;
    }

    public CheckVersion getCheckVersion() {
        return this.checkVersion;
    }

    public void setBanwave(boolean banwave) {
        this.banwave = banwave;
    }

    public boolean isBanwave() {
        return this.banwave;
    }

    public String getFriendlyName() {
        return this.friendlyName;
    }

    public void setViolations(double violations) {
        this.violations = violations;
    }

    public int getMaxViolation() {
        return this.maxViolation;
    }

    public void setMaxViolation(int maxViolation) {
        this.maxViolation = maxViolation;
    }

    public void setLastViolation(int lastViolation) {
        this.lastViolation = lastViolation;
    }

    public long getLastCheck() {
        return this.lastCheck;
    }

    public double getViolations() {
        return this.violations;
    }

    public void setLastCheck(long lastCheck) {
        this.lastCheck = lastCheck;
    }

    public static final class CheckVersion
    extends Enum<CheckVersion> {
        public static final /* synthetic */ /* enum */ CheckVersion RELEASE;
        public static final /* synthetic */ /* enum */ CheckVersion EXPERIMENTAL;
        private static final /* synthetic */ CheckVersion[] ENUM$VALUES;
        private static final /* synthetic */ String[] I;

        public static CheckVersion valueOf(String string) {
            return Enum.valueOf(CheckVersion.class, string);
        }

        static {
            CheckVersion.I();
            RELEASE = new CheckVersion();
            EXPERIMENTAL = new CheckVersion();
            CheckVersion[] arrcheckVersion = new CheckVersion["  ".length()];
            arrcheckVersion["".length()] = RELEASE;
            arrcheckVersion[" ".length()] = EXPERIMENTAL;
            ENUM$VALUES = arrcheckVersion;
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

        private static void I() {
            I = new String["  ".length()];
            CheckVersion.I["".length()] = CheckVersion.I("uQi9RqxaePY=", "cutKv");
            CheckVersion.I[" ".length()] = CheckVersion.I("tmJn2jROXQSD3D2IBUIFRA==", "xnYcM");
        }

        public static CheckVersion[] values() {
            CheckVersion[] arrcheckVersion = ENUM$VALUES;
            int n = arrcheckVersion.length;
            CheckVersion[] arrcheckVersion2 = new CheckVersion[n];
            System.arraycopy(arrcheckVersion, "".length(), arrcheckVersion2, "".length(), n);
            return arrcheckVersion2;
        }
    }

    public static final class CheckType
    extends Enum<CheckType> {
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSI;
        public static final /* synthetic */ /* enum */ CheckType FLYC;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERC;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERK;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTG;
        public static final /* synthetic */ /* enum */ CheckType FLYE;
        public static final /* synthetic */ /* enum */ CheckType KILL_AURAE;
        public static final /* synthetic */ /* enum */ CheckType FLYB;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSD;
        public static final /* synthetic */ /* enum */ CheckType SPEEDE;
        public static final /* synthetic */ /* enum */ CheckType TIMERB;
        public static final /* synthetic */ /* enum */ CheckType MISCB;
        public static final /* synthetic */ /* enum */ CheckType FLYA;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSJ;
        public static final /* synthetic */ /* enum */ CheckType PAYLOAD;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSG;
        private static final /* synthetic */ CheckType[] ENUM$VALUES;
        private final /* synthetic */ String suffix;
        public static final /* synthetic */ /* enum */ CheckType SPEEDB;
        public static final /* synthetic */ /* enum */ CheckType KILL_AURAC;
        public static final /* synthetic */ /* enum */ CheckType PHASEA;
        public static final /* synthetic */ /* enum */ CheckType FLYJ;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSB;
        public static final /* synthetic */ /* enum */ CheckType SPEEDC;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSC;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSH;
        public static final /* synthetic */ /* enum */ CheckType SPEEDD;
        public static final /* synthetic */ /* enum */ CheckType VELOCITYB;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTB;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTF;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSA;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSO;
        public static final /* synthetic */ /* enum */ CheckType FLYD;
        public static final /* synthetic */ /* enum */ CheckType SPEEDA;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSE;
        public static final /* synthetic */ /* enum */ CheckType KILL_AURAD;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERD;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERE;
        public static final /* synthetic */ /* enum */ CheckType FLYH;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTC;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSF;
        public static final /* synthetic */ /* enum */ CheckType VELOCITYA;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSK;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSM;
        private static final /* synthetic */ String[] I;
        public static final /* synthetic */ /* enum */ CheckType MISCA;
        public static final /* synthetic */ /* enum */ CheckType FLYI;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERI;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERA;
        public static final /* synthetic */ /* enum */ CheckType KILL_AURAA;
        public static final /* synthetic */ /* enum */ CheckType KILL_AURAB;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERG;
        public static final /* synthetic */ /* enum */ CheckType FLYG;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSL;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERJ;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTA;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTE;
        private final /* synthetic */ String name;
        public static final /* synthetic */ /* enum */ CheckType BADPACKETSN;
        public static final /* synthetic */ /* enum */ CheckType REACHA;
        public static final /* synthetic */ /* enum */ CheckType FLYF;
        public static final /* synthetic */ /* enum */ CheckType AIM_ASSISTD;
        public static final /* synthetic */ /* enum */ CheckType MISCC;
        public static final /* synthetic */ /* enum */ CheckType REACHB;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERH;
        public static final /* synthetic */ /* enum */ CheckType TIMERA;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERINDEV;
        public static final /* synthetic */ /* enum */ CheckType AUTO_CLICKERB;

        private static void I() {
            I = new String[29 + 150 - 89 + 102];
            CheckType.I["".length()] = CheckType.I("\u0015\u0018\t5\b\u0007\u0002\r9\u001d\u0015", "TQDjI");
            CheckType.I[" ".length()] = CheckType.I("\u0017%$\u0002%%%:7v\u0017", "VLICV");
            CheckType.I["  ".length()] = CheckType.l("br6vQkFCWFk=", "ppyNb");
            CheckType.I["   ".length()] = CheckType.I("\u001b:\n9\u0005\t \u000e5\u0010\u0018", "ZsGfD");
            CheckType.I[95 ^ 91] = CheckType.lI("nTpF+6/nIUPPYpWsls/ozQ==", "ZvTbX");
            CheckType.I[30 ^ 27] = CheckType.l("HSn+1tu7SOY=", "owUyX");
            CheckType.I[98 ^ 100] = CheckType.l("nHBsMs7yv8cHgOUGZp3PZA==", "hOZyC");
            CheckType.I[182 ^ 177] = CheckType.lI("d9O7e6lmXd++X3As3TcjaA==", "vKOHN");
            CheckType.I[85 ^ 93] = CheckType.I("-\u0001&\u000f4\u001a", "nnKmU");
            CheckType.I[57 ^ 48] = CheckType.I("\n\u001f%)2\u0018\u0005!%'\u000f", "KVhvs");
            CheckType.I[63 ^ 53] = CheckType.l("0tXBWEMIJGyjTQ+sm+JaLw==", "PFTwP");
            CheckType.I[80 ^ 91] = CheckType.l("VpJKKS+75RI=", "saMaO");
            CheckType.I[13 ^ 1] = CheckType.l("Bw1Q9eCclDooh375Sk4Ghg==", "idacG");
            CheckType.I[50 ^ 63] = CheckType.I("5\u000b\u001a\u0016\u001a\u0007\u000b\u0004#I1", "tbwWi");
            CheckType.I[50 ^ 60] = CheckType.l("O2ECzRdc3Uw=", "qrACe");
            CheckType.I[33 ^ 46] = CheckType.I("(3$.\u0002:) \"\u0017/", "iziqC");
            CheckType.I[160 ^ 176] = CheckType.I("$\u001a\"3\u0005\u0016\u001a<\u0006V#", "esOrv");
            CheckType.I[109 ^ 124] = CheckType.l("ZyQi0A0dW0g=", "lGMCv");
            CheckType.I[162 ^ 176] = CheckType.l("gltOWQYFwrSd8DOBvHwSDA==", "FZoIE");
            CheckType.I[99 ^ 112] = CheckType.lI("yk+Hw1wweTnOMIPgigY0og==", "Nxwhj");
            CheckType.I[130 ^ 150] = CheckType.I("\u0011%5\u001a$&", "RJXxE");
            CheckType.I[4 ^ 17] = CheckType.lI("e4FiZyQTAAcrwXCf+t5tjQ==", "qcxaZ");
            CheckType.I[114 ^ 100] = CheckType.lI("/zy+HxpUqUjAp0pAeH8rkA==", "nQeup");
            CheckType.I[186 ^ 173] = CheckType.lI("YVC0wGOdukw=", "JnyaC");
            CheckType.I[182 ^ 174] = CheckType.lI("M8DD7/rxj3ZRw8CzyRHAnw==", "EdAhs");
            CheckType.I[68 ^ 93] = CheckType.I("*93 \b\u0007%$$.\u0019l\u0005", "kLGOK");
            CheckType.I[170 ^ 176] = CheckType.lI("bRQDZdgd4ag=", "itYOb");
            CheckType.I[12 ^ 23] = CheckType.l("RlvubSyHhlIFIZNcbYcoLw==", "EECqY");
            CheckType.I[42 ^ 54] = CheckType.lI("0qiJDYkIRpYgDbamUzhT6g==", "pDGdS");
            CheckType.I[47 ^ 50] = CheckType.l("9YuJ5WzyAb8=", "qMLID");
            CheckType.I[91 ^ 69] = CheckType.lI("pC0BxWaxaWdh1kF68WcJ3A==", "CfnFJ");
            CheckType.I[164 ^ 187] = CheckType.I(";\u001b'.\u0017\u0016\u00070*1\bN\u0017", "znSAT");
            CheckType.I[74 ^ 106] = CheckType.l("yPBm5McOQcw=", "iRcRx");
            CheckType.I[80 ^ 113] = CheckType.lI("1zhlI17GLr889nn0dxQ+DA==", "NkgQA");
            CheckType.I[148 ^ 182] = CheckType.I("\u0002/0)+/3'-\r1z\u0001", "CZDFh");
            CheckType.I[42 ^ 9] = CheckType.I("1\r\u001e\u0007\u0004\u0006", "rbsee");
            CheckType.I[64 ^ 100] = CheckType.l("WrlmqeMbDu1lHxyJQp+vW34xB+dtC6TM", "vpqKv");
            CheckType.I[97 ^ 68] = CheckType.l("RHdB4tZnalw6WpZIDhIz/G82usVFP9Le", "xKyvR");
            CheckType.I[84 ^ 114] = CheckType.I("\u0000++\f\u00067", "CDFng");
            CheckType.I[79 ^ 104] = CheckType.l("9cKjCOeIzDz9Avmq3w5J9A==", "bGLMA");
            CheckType.I[121 ^ 81] = CheckType.lI("eCSdiPTL+Wd9LNugl2tlrA==", "rWZuC");
            CheckType.I[41 ^ 0] = CheckType.I("9#\u001b ,\u000e", "zLvBM");
            CheckType.I[188 ^ 150] = CheckType.lI("fmDwYc8tdAXTOH3BJhoZ7A==", "FUeMz");
            CheckType.I[8 ^ 35] = CheckType.l("+7+CaP85eFSvjtHsehGyHA==", "BKTwx");
            CheckType.I[26 ^ 54] = CheckType.lI("mn9ysrW8LhU=", "qWZbO");
            CheckType.I[136 ^ 165] = CheckType.lI("FujCFV75xa34mdjGICrOPA==", "NFnVv");
            CheckType.I[56 ^ 22] = CheckType.l("RBfmhgUMc8du+LMGA4k8UA==", "OeJFn");
            CheckType.I[51 ^ 28] = CheckType.lI("TfvLX+WFmt0=", "qFclE");
            CheckType.I[169 ^ 153] = CheckType.I("09:!-2 '-94>$", "qlnnr");
            CheckType.I[69 ^ 116] = CheckType.lI("PMBI0z0RaDvZIyYn7QC3Vg==", "iQeZY");
            CheckType.I[78 ^ 124] = CheckType.I("\u0014\u001a\u0014/\u0004#", "WuyMe");
            CheckType.I[55 ^ 4] = CheckType.l("jWLmDkm+eTOSBN237Cev8A==", "LSzZS");
            CheckType.I[32 ^ 20] = CheckType.lI("Ncy3/u5cy0eez48d5v67ug==", "ktvrP");
            CheckType.I[89 ^ 108] = CheckType.l("GLw2nkc7psQ=", "qbJoA");
            CheckType.I[175 ^ 153] = CheckType.lI("LCP87F6hITj4a8EsbWYDVg==", "tPNxZ");
            CheckType.I[123 ^ 76] = CheckType.I("\"?\t:1\u001c$\u0004v1", "iVeVp");
            CheckType.I[149 ^ 173] = CheckType.lI("JaJC3Cy6dDE=", "LgaIM");
            CheckType.I[9 ^ 48] = CheckType.lI("AeLb7ehdF/KTbUKUkYaCXA==", "TIukp");
            CheckType.I[171 ^ 145] = CheckType.I("\u0000*\"'$>1/k'", "KCNKe");
            CheckType.I[40 ^ 19] = CheckType.I("\u001065\u0000\u000f'", "SYXbn");
            CheckType.I[165 ^ 153] = CheckType.I("\u0003;\u001e#0\t'\u0000.,", "HrRoo");
            CheckType.I[141 ^ 176] = CheckType.lI("M9IaSgcF9umN+W0vpP4Dzg==", "ySdDt");
            CheckType.I[42 ^ 20] = CheckType.l("U+1zAAMVLWA=", "iPZzm");
            CheckType.I[123 ^ 68] = CheckType.lI("Bm8kIF/LWi14FCdWXUtOsw==", "zxIbR");
            CheckType.I[94 ^ 30] = CheckType.lI("+fA6Pez3lXJ2YV44Ig1CvA==", "pTNxW");
            CheckType.I[90 ^ 27] = CheckType.l("I/n8EHt+Pr0=", "xDdJt");
            CheckType.I[116 ^ 54] = CheckType.I("\u0019\f\u0003:\n\u0013\u0010\u001d7\u0010", "REOvU");
            CheckType.I[225 ^ 162] = CheckType.lI("q024fqb1WK4OcukMuMycNA==", "SQmcQ");
            CheckType.I[132 ^ 192] = CheckType.lI("1/QFtvrXXWA=", "nlIMi");
            CheckType.I[107 ^ 46] = CheckType.I(".3'\u001c,/9&\u0018>+", "lrcLm");
            CheckType.I[105 ^ 47] = CheckType.l("3DDtihrRTPlEVdqv/By8ZlP1UgaqNqV9", "bIAxu");
            CheckType.I[52 ^ 115] = CheckType.lI("KaxT0rGwdUQ=", "pKjCJ");
            CheckType.I[3 ^ 75] = CheckType.lI("QH7sjT4/V7ZSuSyJRptEHQ==", "Ccqdl");
            CheckType.I[229 ^ 172] = CheckType.lI("QhO/AcKMdxWOaWY3SnFR78kx3th6hYCa", "hLiQD");
            CheckType.I[108 ^ 38] = CheckType.l("za737HX3IxY=", "FXPKy");
            CheckType.I[68 ^ 15] = CheckType.l("f5I7KvFxOsZXTDybWoFkXQ==", "snuhR");
            CheckType.I[235 ^ 167] = CheckType.lI("dRBcFxwhFn/O8Ehj0simnDfVo/x/8Ggv", "QuRrS");
            CheckType.I[220 ^ 145] = CheckType.I("\u000b\u000b$\u0015\u0007<", "HdIwf");
            CheckType.I[63 ^ 113] = CheckType.l("pG7YizcoNuB9NGWrAo0n8Q==", "cxeAR");
            CheckType.I[11 ^ 68] = CheckType.lI("kfQqbVpcL2enys+fcBqG6Q==", "MsvRI");
            CheckType.I[242 ^ 162] = CheckType.lI("CwBn3i+Kync=", "guufh");
            CheckType.I[125 ^ 44] = CheckType.lI("sRULIy3TuuQ=", "TUGny");
            CheckType.I[98 ^ 48] = CheckType.lI("HqQGu/d/FC4=", "YMTfo");
            CheckType.I[27 ^ 72] = CheckType.l("cudckYPKFmQ=", "Nygxa");
            CheckType.I[195 ^ 151] = CheckType.lI("tW8wJ/H23Ko=", "megmm");
            CheckType.I[124 ^ 41] = CheckType.lI("FOwPnod6c4g=", "UAnrc");
            CheckType.I[94 ^ 8] = CheckType.l("GCpBu0qZy1s=", "KvNbT");
            CheckType.I[222 ^ 137] = CheckType.lI("Rvzk70WqWC8=", "fsADH");
            CheckType.I[116 ^ 44] = CheckType.lI("SbaUTWbvjgwAmw34jvxhGg==", "otvQj");
            CheckType.I[232 ^ 177] = CheckType.lI("Ze3mMIgpvYE=", "RIWZO");
            CheckType.I[241 ^ 171] = CheckType.l("YDfGPP7TmxCOJWCPexFedQ==", "blDzS");
            CheckType.I[231 ^ 188] = CheckType.l("BVk18fR2WThNAhcV88JVUQ==", "QlmNR");
            CheckType.I[53 ^ 105] = CheckType.I("\u000b=\u001c\u0011\u0017<", "HRqsv");
            CheckType.I[106 ^ 55] = CheckType.I("7#-\u0001\u001b6),\u0005\t;", "ubiQZ");
            CheckType.I[10 ^ 84] = CheckType.I(".H\u0017.$Y$", "yeCOT");
            CheckType.I[255 ^ 160] = CheckType.I("/\u0016\u0001\u000b\u0013\u0018", "lylir");
            CheckType.I[212 ^ 180] = CheckType.l("9vMBzs9xXqEBJp3GWRIC7Q==", "TbTCz");
            CheckType.I[166 ^ 199] = CheckType.I("\f/:2\"*1s\u0010", "OCSQI");
            CheckType.I[82 ^ 48] = CheckType.l("mDeaCZFsyVw=", "BSCVS");
            CheckType.I[245 ^ 150] = CheckType.l("jffmezIDFXE=", "nBPgO");
            CheckType.I[246 ^ 146] = CheckType.lI("/WRHlVCyJ4M=", "sPJJy");
            CheckType.I[17 ^ 116] = CheckType.I("'\u0004\u001f\u0010\"\u0007\u000e\u0004\u0012", "jkjfG");
            CheckType.I[38 ^ 64] = CheckType.I("5;+\u0014", "swrVd");
            CheckType.I[7 ^ 96] = CheckType.lI("97ZePbTLh9c=", "yacJt");
            CheckType.I[192 ^ 168] = CheckType.l("rm60fG5iXAWZGMYBb8gg/A==", "WMgSB");
            CheckType.I[79 ^ 38] = CheckType.lI("knl3R1nyznU=", "wZjQm");
            CheckType.I[65 ^ 43] = CheckType.lI("QKvk0KKrxM4mfGBEbZZ7+Q==", "AbGWE");
            CheckType.I[16 ^ 123] = CheckType.lI("BO06VkFYP4g4CkUBOyunYw==", "dMGay");
            CheckType.I[203 ^ 167] = CheckType.l("H3DWCbl/zwo=", "tlgFB");
            CheckType.I[210 ^ 191] = CheckType.I("\u00156\u001aN/", "SZcnk");
            CheckType.I[119 ^ 25] = CheckType.I("\u001a\u0007\u001a\u0015\b:\r\u0001\u0017", "Whocm");
            CheckType.I[85 ^ 58] = CheckType.I("7\u001d\u0011\u0011", "qQHTx");
            CheckType.I[102 ^ 22] = CheckType.I("\u00124<h5", "TXEHp");
            CheckType.I[224 ^ 145] = CheckType.l("tSHU4UZq0wPgLYqaCthhEQ==", "lEJLd");
            CheckType.I[224 ^ 146] = CheckType.l("yzb5HkRXPJg=", "wfSFK");
            CheckType.I[19 ^ 96] = CheckType.l("6XXGLxrqaMs=", "Bejij");
            CheckType.I[13 ^ 121] = CheckType.I("\"\u0005#8?\u0002\u000f8:", "ojVNZ");
            CheckType.I[91 ^ 46] = CheckType.I("\r%:\u0017", "KicPF");
            CheckType.I[218 ^ 172] = CheckType.lI("6ENHi0wkKAo=", "tNjpA");
            CheckType.I[181 ^ 194] = CheckType.l("fAvmQlYqXbKORek2ZO4nFQ==", "jVEIj");
            CheckType.I[40 ^ 80] = CheckType.l("dEk6CG+M62M=", "fmchd");
            CheckType.I[90 ^ 35] = CheckType.l("ywp0pJOPBV0=", "HktyM");
            CheckType.I[238 ^ 148] = CheckType.l("Js67TsBqVMSgar5LDzRO6g==", "NtsgT");
            CheckType.I[245 ^ 142] = CheckType.I("/=:\u001b", "iqcRq");
            CheckType.I[190 ^ 194] = CheckType.I("\u0012\n*E3", "TfSez");
            CheckType.I[84 ^ 41] = CheckType.l("s9J6Gui2WZ2FAdd/SNUv6A==", "izIOo");
            CheckType.I[37 ^ 91] = CheckType.lI("zB8SgVZKx9E=", "YQxCM");
            CheckType.I[114 + 20 - 54 + 47] = CheckType.lI("l7Ev/4IFkTY=", "rmeYN");
            CheckType.I[108 + 26 - 57 + 51] = CheckType.lI("Rq0S3PoqXS6+DmH6K95+jw==", "Qlmqn");
            CheckType.I[125 + 30 - 107 + 81] = CheckType.lI("AM5JvsOFyqE=", "vvcan");
            CheckType.I[10 + 104 - 0 + 16] = CheckType.l("y8kA10f84sQ=", "vLSwO");
            CheckType.I[94 + 27 - 23 + 33] = CheckType.lI("TylrmO/umT4=", "lUcKF");
            CheckType.I[77 + 6 - 21 + 70] = CheckType.I("7\t&\u000b5&", "gAgXp");
            CheckType.I[110 + 77 - 85 + 31] = CheckType.lI("sZTp1rpnNeo=", "ZcLcq");
            CheckType.I[78 + 57 - 91 + 90] = CheckType.lI("PilOdrWFns40PSN9XX6GTQ==", "WsVyV");
            CheckType.I[76 + 29 - 13 + 43] = CheckType.I("\u0002415\u0007\u0010", "QdtpC");
            CheckType.I[118 + 22 - 77 + 73] = CheckType.l("tRF7gzPLLkI=", "ubsvh");
            CheckType.I[127 + 100 - 140 + 50] = CheckType.l("3V5PEOgY8geOVwzRAxD4hw==", "FllKd");
            CheckType.I[115 + 125 - 126 + 24] = CheckType.l("31TtR/3On2Q=", "dqLcN");
            CheckType.I[121 + 70 - 87 + 35] = CheckType.lI("K5hbSHKJV0c=", "tKPeg");
            CheckType.I[97 + 55 - 94 + 82] = CheckType.lI("+B0othIlDSc1ZVPwH1W0rw==", "ZccHw");
            CheckType.I[81 + 12 - 76 + 124] = CheckType.I("\u0003\u0002!<\u0016\u0013", "PRdyR");
            CheckType.I[121 + 136 - 120 + 5] = CheckType.lI("XuH3Gi7fMM8=", "qhdIf");
            CheckType.I[134 + 68 - 113 + 54] = CheckType.I(",\u001f#7$\f\u001585", "apVAA");
            CheckType.I[100 + 38 - 49 + 55] = CheckType.l("Gp3/7zxlGJo=", "rXYfP");
            CheckType.I[2 + 18 - -58 + 67] = CheckType.I("'#\u00007\u000bT\u0017", "tSeRo");
            CheckType.I[14 + 98 - 88 + 122] = CheckType.lI("lIf3y0ROM6lOF4JE8qVP7w==", "gbQbI");
            CheckType.I[68 + 30 - -44 + 5] = CheckType.lI("PJ2IZU6kgeM=", "ypATT");
            CheckType.I[9 + 76 - -10 + 53] = CheckType.lI("AZz7jok5H84=", "KkQso");
            CheckType.I[104 + 4 - 33 + 74] = CheckType.l("iWu7pv0XwOQCq1aXn5nFPA==", "qiEEA");
            CheckType.I[137 + 78 - 107 + 42] = CheckType.I("-/\u001a/08", "yfWjb");
            CheckType.I[57 + 108 - 86 + 72] = CheckType.I("\u0001(>4\u001fu\u0000", "UASQm");
            CheckType.I[18 + 73 - -50 + 11] = CheckType.I("\u0005\u001b-\u0000 ", "JoEeR");
            CheckType.I[69 + 112 - 151 + 123] = CheckType.lI("uVZt6gb5tqg=", "PtoWg");
            CheckType.I[22 + 150 - 119 + 101] = CheckType.l("E0zhQT56YTc=", "HQdxZ");
            CheckType.I[12 + 2 - -102 + 39] = CheckType.I(";\u000e\u0003\u0013\u0017", "tzkve");
            CheckType.I[106 + 116 - 102 + 36] = CheckType.l("2VSenARA9aawkK093bJ34Q==", "sXnBR");
            CheckType.I[20 + 26 - -61 + 50] = CheckType.lI("M/l0PM15JTbxy8wyoL46xw==", "ZdiaY");
            CheckType.I[98 + 89 - 131 + 102] = CheckType.l("EdiCcjdIN4Q/R8n9njXuIA==", "ZQreE");
            CheckType.I[37 + 83 - 43 + 82] = CheckType.lI("EyUhnILKZ3k6pOvfejrqnA==", "LARXe");
            CheckType.I[73 + 45 - 22 + 64] = CheckType.lI("OX0hKHvnKOipMjzAfGSjYQ==", "kBNnj");
            CheckType.I[124 + 67 - 148 + 118] = CheckType.lI("65e/EUFB5bI3l8lpnKjPgw==", "URyZk");
            CheckType.I[74 + 9 - -4 + 75] = CheckType.l("O2CHdtMn8sw=", "HLnRP");
            CheckType.I[78 + 7 - -42 + 36] = CheckType.I("\u0005\u001c\u0013\u000f6q2", "QsdjD");
            CheckType.I[152 + 0 - 5 + 17] = CheckType.I("9\u000e1/6\u0019\u0004*-", "taDYS");
            CheckType.I[29 + 1 - -30 + 105] = CheckType.I("\u0004\u0004$\t0", "IMwJs");
            CheckType.I[150 + 21 - 41 + 36] = CheckType.l("qgqhrraspeqPkUTIi4O52g==", "XvKNB");
            CheckType.I[156 + 160 - 254 + 105] = CheckType.l("Vj6VL0Uqpcc4NCFq6ORK3g==", "EESqP");
            CheckType.I[137 + 159 - 196 + 68] = CheckType.l("MHLEsQDfEXmkYNoUBOGRMg==", "acFur");
            CheckType.I[76 + 130 - 183 + 146] = CheckType.l("7503a2zOBTyUECDchcv9+Q==", "SLpUD");
            CheckType.I[143 + 1 - 104 + 130] = CheckType.I("'\"\u0004\u0011%", "hVltW");
            CheckType.I[129 + 66 - 146 + 122] = CheckType.l("a2l5v3w+ajOFO3yOhYoxhw==", "Ujkar");
            CheckType.I[86 + 75 - 124 + 135] = CheckType.lI("mDGYr7MCDrtIGHxOwlRLCA==", "AtAwp");
            CheckType.I[85 + 40 - 82 + 130] = CheckType.l("bqLlvIMlGs4=", "JePEu");
            CheckType.I[73 + 141 - 196 + 156] = CheckType.lI("wMLXPUa0Q+BzsBZIXAuWpA==", "eFzOj");
            CheckType.I[14 + 85 - 96 + 172] = CheckType.l("ablVUflRB5Q=", "VmnaA");
            CheckType.I[137 + 152 - 215 + 102] = CheckType.I("\u00010\u0002\u0017%", "NDjrW");
            CheckType.I[109 + 157 - 217 + 128] = CheckType.lI("BIz1qOjBzd546Zj8OCQhtA==", "Moqxo");
            CheckType.I[43 + 29 - 34 + 140] = CheckType.lI("QOUq1a+mYi6p5qJfb+5F2Q==", "wztnO");
            CheckType.I[169 + 171 - 217 + 56] = CheckType.lI("bwju97tHByk=", "lQCJh");
            CheckType.I[109 + 56 - 46 + 61] = CheckType.I("\u00135#7\"\u0012?\"30\u0017", "Qtggc");
            CheckType.I[67 + 95 - 17 + 36] = CheckType.lI("A3qfAv9WuLa7uH/WSi2VBg==", "kfzyU");
            CheckType.I[113 + 31 - -28 + 10] = CheckType.l("IXRUpzO3PXc=", "gAywF");
            CheckType.I[171 + 180 - 224 + 56] = CheckType.l("Rz6DU4o11NtmYDNij7xuJQ==", "tpXXr");
            CheckType.I[13 + 19 - -39 + 113] = CheckType.lI("a2nkF4G30tTTjHX/SnQrIQ==", "xejQE");
            CheckType.I[76 + 62 - 17 + 64] = CheckType.lI("fiuvEuL/JGA=", "zGKpu");
            CheckType.I[91 + 172 - 104 + 27] = CheckType.lI("YvBeBW4FrOkvbb8hrrrvbQ==", "iJued");
            CheckType.I[25 + 92 - -38 + 32] = CheckType.I("%$\u0019\u0005\u0004\u0011 \u000b\u001af\"", "cEjqF");
            CheckType.I[109 + 49 - 157 + 187] = CheckType.l("CRmwNPqlNUk=", "RzWCk");
            CheckType.I[133 + 131 - 235 + 160] = CheckType.l("FOR/X+0ChrASfiJ02ox0Cg==", "cGjIT");
            CheckType.I[37 + 78 - 79 + 154] = CheckType.l("gxAP/sUQQNpY3RJuVTqO7Q==", "GGinf");
            CheckType.I[152 + 121 - 262 + 180] = CheckType.I("\u000369\b\u000b", "LBQmy");
        }

        public String getName() {
            return this.name;
        }

        private CheckType(String name, String suffix) {
            this.name = name;
            this.suffix = suffix;
        }

        private static String lI(String obj, String key) {
            try {
                SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x27 ^ 0x2F), "DES");
                Cipher des = Cipher.getInstance("DES");
                des.init("  ".length(), keySpec);
                return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static CheckType[] values() {
            CheckType[] arrcheckType = ENUM$VALUES;
            int n = arrcheckType.length;
            CheckType[] arrcheckType2 = new CheckType[n];
            System.arraycopy(arrcheckType, "".length(), arrcheckType2, "".length(), n);
            return arrcheckType2;
        }

        public static CheckType valueOf(String string) {
            return Enum.valueOf(CheckType.class, string);
        }

        public String getSuffix() {
            return this.suffix;
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
                if (3 > 0) continue;
                throw null;
            }
            return sb.toString();
        }

        static {
            CheckType.I();
            AIM_ASSISTA = new CheckType(I[" ".length()], I["  ".length()]);
            AIM_ASSISTB = new CheckType(I[0x79 ^ 0x7D], I[0x75 ^ 0x70]);
            AIM_ASSISTC = new CheckType(I[0 ^ 7], I[0x4B ^ 0x43]);
            AIM_ASSISTD = new CheckType(I[0xAD ^ 0xA7], I[0x33 ^ 0x38]);
            AIM_ASSISTE = new CheckType(I[0x89 ^ 0x84], I[0 ^ 0xE]);
            AIM_ASSISTF = new CheckType(I[0x68 ^ 0x78], I[0x73 ^ 0x62]);
            AIM_ASSISTG = new CheckType(I[0x28 ^ 0x3B], I[0x83 ^ 0x97]);
            AUTO_CLICKERA = new CheckType(I[0x15 ^ 3], I[6 ^ 0x11]);
            AUTO_CLICKERB = new CheckType(I[0x6D ^ 0x74], I[0xBE ^ 0xA4]);
            AUTO_CLICKERC = new CheckType(I[9 ^ 0x15], I[0xB ^ 0x16]);
            AUTO_CLICKERD = new CheckType(I[0x58 ^ 0x47], I[2 ^ 0x22]);
            AUTO_CLICKERE = new CheckType(I[0x74 ^ 0x56], I[1 ^ 0x22]);
            AUTO_CLICKERINDEV = new CheckType(I[7 ^ 0x22], I[0xE ^ 0x28]);
            AUTO_CLICKERG = new CheckType(I[0x5F ^ 0x77], I[0xE8 ^ 0xC1]);
            AUTO_CLICKERH = new CheckType(I[0xEC ^ 0xC7], I[0xA3 ^ 0x8F]);
            AUTO_CLICKERI = new CheckType(I[0x9C ^ 0xB2], I[0x9E ^ 0xB1]);
            AUTO_CLICKERJ = new CheckType(I[0x7D ^ 0x4C], I[0xA3 ^ 0x91]);
            AUTO_CLICKERK = new CheckType(I[0x81 ^ 0xB5], I[0x54 ^ 0x61]);
            KILL_AURAA = new CheckType(I[0x41 ^ 0x76], I[0x43 ^ 0x7B]);
            KILL_AURAB = new CheckType(I[0x34 ^ 0xE], I[0x57 ^ 0x6C]);
            KILL_AURAC = new CheckType(I[0x89 ^ 0xB4], I[0x1B ^ 0x25]);
            KILL_AURAD = new CheckType(I[0x1F ^ 0x5F], I[0x7F ^ 0x3E]);
            KILL_AURAE = new CheckType(I[0x39 ^ 0x7A], I[0x22 ^ 0x66]);
            BADPACKETSG = new CheckType(I[0x35 ^ 0x73], I[0xC4 ^ 0x83]);
            BADPACKETSI = new CheckType(I[0x4B ^ 2], I[0xE0 ^ 0xAA]);
            BADPACKETSE = new CheckType(I[3 ^ 0x4F], I[0x69 ^ 0x24]);
            BADPACKETSH = new CheckType(I[0x3B ^ 0x74], I[0xE ^ 0x5E]);
            REACHA = new CheckType(I[0x93 ^ 0xC1], I[8 ^ 0x5B]);
            REACHB = new CheckType(I[0x42 ^ 0x17], I[0x46 ^ 0x10]);
            MISCA = new CheckType(I[0x74 ^ 0x2C], I[0x7A ^ 0x23]);
            BADPACKETSL = new CheckType(I[0x2E ^ 0x75], I[0x45 ^ 0x19]);
            BADPACKETSN = new CheckType(I[0x2C ^ 0x72], I[0x24 ^ 0x7B]);
            BADPACKETSD = new CheckType(I[0xB ^ 0x6A], I[0x66 ^ 4]);
            FLYA = new CheckType(I[0x1C ^ 0x78], I[0x1F ^ 0x7A]);
            FLYB = new CheckType(I[0x62 ^ 5], I[0xE6 ^ 0x8E]);
            FLYC = new CheckType(I[0xDF ^ 0xB5], I[0x4A ^ 0x21]);
            FLYD = new CheckType(I[0x3B ^ 0x56], I[0x4B ^ 0x25]);
            FLYE = new CheckType(I[0x2A ^ 0x5A], I[0x37 ^ 0x46]);
            FLYF = new CheckType(I[0xC5 ^ 0xB6], I[0x47 ^ 0x33]);
            FLYG = new CheckType(I[0x64 ^ 0x12], I[0x1A ^ 0x6D]);
            FLYH = new CheckType(I[0 ^ 0x79], I[0x51 ^ 0x2B]);
            FLYI = new CheckType(I[0x6F ^ 0x13], I[0x1B ^ 0x66]);
            FLYJ = new CheckType(I[90 + 110 - 198 + 125], I[75 + 29 - 98 + 122]);
            PAYLOAD = new CheckType(I[65 + 113 - 102 + 54], I[36 + 123 - 52 + 24]);
            PHASEA = new CheckType(I[92 + 47 - 60 + 54], I[74 + 66 - 62 + 56]);
            SPEEDA = new CheckType(I[135 + 72 - 89 + 18], I[26 + 38 - -26 + 47]);
            SPEEDB = new CheckType(I[133 + 58 - 128 + 76], I[34 + 8 - -31 + 67]);
            SPEEDC = new CheckType(I[62 + 111 - 108 + 77], I[141 + 81 - 202 + 123]);
            SPEEDD = new CheckType(I[39 + 99 - 92 + 99], I[98 + 105 - 103 + 46]);
            SPEEDE = new CheckType(I[121 + 66 - 71 + 32], I[90 + 16 - 95 + 138]);
            TIMERA = new CheckType(I[119 + 37 - 37 + 32], I[38 + 7 - -19 + 88]);
            TIMERB = new CheckType(I[119 + 7 - 87 + 115], I[148 + 19 - 154 + 142]);
            VELOCITYA = new CheckType(I[43 + 82 - 68 + 100], I[43 + 118 - 74 + 71]);
            VELOCITYB = new CheckType(I[89 + 9 - 87 + 149], I[102 + 137 - 97 + 19]);
            MISCB = new CheckType(I[119 + 95 - 172 + 121], I[94 + 134 - 161 + 97]);
            MISCC = new CheckType(I[132 + 129 - 118 + 23], I[120 + 88 - 118 + 77]);
            BADPACKETSC = new CheckType(I[34 + 103 - 80 + 112], I[19 + 22 - -80 + 49]);
            BADPACKETSK = new CheckType(I[102 + 59 - 145 + 156], I[42 + 3 - 19 + 147]);
            BADPACKETSA = new CheckType(I[11 + 47 - -26 + 91], I[106 + 11 - 41 + 100]);
            BADPACKETSB = new CheckType(I[73 + 100 - 148 + 153], I[111 + 98 - 144 + 114]);
            BADPACKETSF = new CheckType(I[86 + 164 - 113 + 44], I[14 + 82 - -76 + 10]);
            BADPACKETSJ = new CheckType(I[75 + 13 - -52 + 44], I[95 + 52 - -8 + 30]);
            BADPACKETSM = new CheckType(I[125 + 52 - 143 + 153], I[83 + 64 - 28 + 69]);
            BADPACKETSO = new CheckType(I[78 + 174 - 140 + 78], I[89 + 0 - 24 + 126]);
            CheckType[] arrcheckType = new CheckType[7 ^ 0x47];
            arrcheckType["".length()] = AIM_ASSISTA;
            arrcheckType[" ".length()] = AIM_ASSISTB;
            arrcheckType["  ".length()] = AIM_ASSISTC;
            arrcheckType["   ".length()] = AIM_ASSISTD;
            arrcheckType[191 ^ 187] = AIM_ASSISTE;
            arrcheckType[50 ^ 55] = AIM_ASSISTF;
            arrcheckType[173 ^ 171] = AIM_ASSISTG;
            arrcheckType[148 ^ 147] = AUTO_CLICKERA;
            arrcheckType[168 ^ 160] = AUTO_CLICKERB;
            arrcheckType[74 ^ 67] = AUTO_CLICKERC;
            arrcheckType[182 ^ 188] = AUTO_CLICKERD;
            arrcheckType[12 ^ 7] = AUTO_CLICKERE;
            arrcheckType[7 ^ 11] = AUTO_CLICKERINDEV;
            arrcheckType[132 ^ 137] = AUTO_CLICKERG;
            arrcheckType[110 ^ 96] = AUTO_CLICKERH;
            arrcheckType[185 ^ 182] = AUTO_CLICKERI;
            arrcheckType[11 ^ 27] = AUTO_CLICKERJ;
            arrcheckType[84 ^ 69] = AUTO_CLICKERK;
            arrcheckType[88 ^ 74] = KILL_AURAA;
            arrcheckType[36 ^ 55] = KILL_AURAB;
            arrcheckType[127 ^ 107] = KILL_AURAC;
            arrcheckType[138 ^ 159] = KILL_AURAD;
            arrcheckType[83 ^ 69] = KILL_AURAE;
            arrcheckType[155 ^ 140] = BADPACKETSG;
            arrcheckType[125 ^ 101] = BADPACKETSI;
            arrcheckType[175 ^ 182] = BADPACKETSE;
            arrcheckType[13 ^ 23] = BADPACKETSH;
            arrcheckType[74 ^ 81] = REACHA;
            arrcheckType[127 ^ 99] = REACHB;
            arrcheckType[180 ^ 169] = MISCA;
            arrcheckType[176 ^ 174] = BADPACKETSL;
            arrcheckType[128 ^ 159] = BADPACKETSN;
            arrcheckType[36 ^ 4] = BADPACKETSD;
            arrcheckType[57 ^ 24] = FLYA;
            arrcheckType[228 ^ 198] = FLYB;
            arrcheckType[35 ^ 0] = FLYC;
            arrcheckType[59 ^ 31] = FLYD;
            arrcheckType[127 ^ 90] = FLYE;
            arrcheckType[58 ^ 28] = FLYF;
            arrcheckType[75 ^ 108] = FLYG;
            arrcheckType[54 ^ 30] = FLYH;
            arrcheckType[169 ^ 128] = FLYI;
            arrcheckType[187 ^ 145] = FLYJ;
            arrcheckType[185 ^ 146] = PAYLOAD;
            arrcheckType[102 ^ 74] = PHASEA;
            arrcheckType[179 ^ 158] = SPEEDA;
            arrcheckType[64 ^ 110] = SPEEDB;
            arrcheckType[76 ^ 99] = SPEEDC;
            arrcheckType[191 ^ 143] = SPEEDD;
            arrcheckType[3 ^ 50] = SPEEDE;
            arrcheckType[163 ^ 145] = TIMERA;
            arrcheckType[42 ^ 25] = TIMERB;
            arrcheckType[9 ^ 61] = VELOCITYA;
            arrcheckType[40 ^ 29] = VELOCITYB;
            arrcheckType[20 ^ 34] = MISCB;
            arrcheckType[170 ^ 157] = MISCC;
            arrcheckType[170 ^ 146] = BADPACKETSC;
            arrcheckType[119 ^ 78] = BADPACKETSK;
            arrcheckType[46 ^ 20] = BADPACKETSA;
            arrcheckType[62 ^ 5] = BADPACKETSB;
            arrcheckType[179 ^ 143] = BADPACKETSF;
            arrcheckType[115 ^ 78] = BADPACKETSJ;
            arrcheckType[13 ^ 51] = BADPACKETSM;
            arrcheckType[91 ^ 100] = BADPACKETSO;
            ENUM$VALUES = arrcheckType;
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

}

