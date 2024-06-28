/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  com.mongodb.MongoCredential
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.configuration.file.YamlConfigurationOptions
 */
package com.unknownmyname.manager;

import com.mongodb.MongoCredential;
import com.unknownmyname.main.Main;
import java.io.File;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

public class OptionsManager {
    private /* synthetic */ String modPermission;
    private static final /* synthetic */ String[] I;
    private /* synthetic */ String banSeparated;
    private /* synthetic */ String logsPlayer;
    private /* synthetic */ String banMessage;
    private /* synthetic */ String alertMessage;
    private /* synthetic */ boolean bypassEnabled;
    private /* synthetic */ String logsResult;
    private static /* synthetic */ YamlConfiguration configuration;
    private /* synthetic */ List<String> banCommands;
    private /* synthetic */ boolean mongoEnabled;
    public static /* synthetic */ String HEADER;
    private /* synthetic */ String alertExperimental;
    private static /* synthetic */ OptionsManager instance;
    private /* synthetic */ String anticheatName;
    private /* synthetic */ String alertCertainty;
    private /* synthetic */ String bypassPermission;
    private /* synthetic */ YamlConfiguration banConfiguration;
    private /* synthetic */ String mongoDatabase;
    private /* synthetic */ MongoCredential mongoCredential;
    private /* synthetic */ File banFile;
    private /* synthetic */ boolean autoBan;
    private static /* synthetic */ File file;
    private /* synthetic */ boolean banAnnouncement;
    private /* synthetic */ boolean mongoAuthEnabled;
    private /* synthetic */ String logsSeparated;
    private /* synthetic */ String mongoHost;
    private /* synthetic */ int mongoPort;

    public String getAnticheatName() {
        return this.anticheatName;
    }

    public String getSm() {
        return this.banSeparated;
    }

    public String getAlertCertainty() {
        return this.alertCertainty;
    }

    private static void I() {
        I = new String[112 + 13 - 30 + 49];
        OptionsManager.I["".length()] = OptionsManager.I("qJ4gYjc1V9Ct2CUrXltLOnBy/GzaZH/mi6BLZDF/UXy8oR2w3vLlcdL4Q1n0pJolLqMaznxkr61yTpCLQOxVxL23jZQBfhF+azMafZoKU6U=", "gklBY");
        OptionsManager.I[" ".length()] = OptionsManager.I("BfRpED+fPdo=", "OgmUS");
        OptionsManager.I["  ".length()] = OptionsManager.l("hXUtPhECVwJR+RO/cQBmWzivOeYbdZ9e", "UXula");
        OptionsManager.I["   ".length()] = OptionsManager.l("pTqHOPc1i7JucUew7o2bOQ==", "qsGiV");
        OptionsManager.I[148 ^ 144] = OptionsManager.I("0FW6win5Vn7VOSfz5LyhNiKokdRw4WLF", "jPdJG");
        OptionsManager.I[90 ^ 95] = OptionsManager.l("JjrrIePDPl/Ma0D8EJv7CQ==", "IVUVi");
        OptionsManager.I[79 ^ 73] = OptionsManager.l("I3clU/6eYl0Gwmls4aZyYtg5VcSFI9bq", "DmVZI");
        OptionsManager.I[67 ^ 68] = OptionsManager.I("AuzVAwqcRYX0szELjw7gjA==", "tzqrO");
        OptionsManager.I[134 ^ 142] = OptionsManager.I("hou2LDutWRvBBHCexJxmHMEEcJ7EnGYcwQRwnsScZhy9WBacmko5Dw==", "MosQb");
        OptionsManager.I[166 ^ 175] = OptionsManager.lI("'\u00044\u0004\r5F2\u0013\u000b2\t8\u0018\r?", "FhQvy");
        OptionsManager.I[14 ^ 4] = OptionsManager.I("wpgnUOmet8C3BF4acVnkIQ==", "aiJBb");
        OptionsManager.I[95 ^ 84] = OptionsManager.l("WvcGTRXjYo3Xns25IOQp5UAg17oK8Bmp", "oufXa");
        OptionsManager.I[98 ^ 110] = OptionsManager.lI("Jx\":\u001d\u0004;o1\u001fL:<:\u0014\u000b", "lOOSz");
        OptionsManager.I[31 ^ 18] = OptionsManager.lI("\u00068/30\u0014z'$7\u00145-$", "gTJAD");
        OptionsManager.I[68 ^ 74] = OptionsManager.l("jJFlQhaVUi2OTg5f4le3mQlRrnr7r4uYJtmX1YOkbXJ+i6MznItBT3WKpWYdIgwxvz1xHstW9aXlcNai44aELA==", "SKTpt");
        OptionsManager.I[142 ^ 129] = OptionsManager.lI("9!\u00021\u0002+c\u0017&\u00045$\u00140\u001f7#", "XMgCv");
        OptionsManager.I[163 ^ 179] = OptionsManager.I("Mv1YSaGeRZPG71McB6RMpQ==", "lmaHn");
        OptionsManager.I[4 ^ 21] = OptionsManager.l("VH4MsNcUm1oMTv/y+fSc+w==", "GhZCg");
        OptionsManager.I[144 ^ 130] = OptionsManager.I("4vewhASJu49XoDJBY8+ny6/s9yPXrmAYcUsO+OLj9sQ=", "lfLjR");
        OptionsManager.I[15 ^ 28] = OptionsManager.lI("85\u00047z71\u001975=1", "ZTjDT");
        OptionsManager.I[140 ^ 152] = OptionsManager.lI("\u274eXPAk*XP\u001d98\u000bV\u001a/7\u0016\u0013\u001cn;\u0001V^w\n\b\u0019\u0017% 95^+w", "YxvxN");
        OptionsManager.I[148 ^ 129] = OptionsManager.lI("\u0010$\r?A\u0001 \u0013-\u001d\u00131\u0006(", "rEcLo");
        OptionsManager.I[165 ^ 179] = OptionsManager.I("opvQ7GhZtrmR92wVWvyF9pH3bBVa/IX2kfdsFVr8hfaR92wVWvyF9pH3bBVa/IX2/bimBymwFyk=", "sqXEk");
        OptionsManager.I[63 ^ 40] = OptionsManager.l("M9xv6azqXk1OlTks2cb3pw==", "rMayI");
        OptionsManager.I[94 ^ 70] = OptionsManager.I("3eva/+dLvXoApCufkMujoH7n9jb1Iq5R", "SjixB");
        OptionsManager.I[161 ^ 184] = OptionsManager.l("pYIJKRxlGgrMFsNMdL7OKZk5l2N3tvzROc4RHzsMgxA=", "hBGsQ");
        OptionsManager.I[153 ^ 131] = OptionsManager.I("j3kNIxmfuUqZMpq7mcaFUvsF/gh7eRMOcBQpIijakCU=", "cBhAm");
        OptionsManager.I[6 ^ 29] = OptionsManager.l("LDfTTaaZC0/dvFi8/9CkHQ==", "ItIUf");
        OptionsManager.I[12 ^ 16] = OptionsManager.I("HE49FzWjuf3qCnrCFim6AK4bayfMvxrD", "SzKGB");
        OptionsManager.I[102 ^ 123] = OptionsManager.I("Gsj8FnHc+6r/kp8ltdnvs2ytvownxotN", "qAKae");
        OptionsManager.I[120 ^ 102] = OptionsManager.I("U184NFr4OtyGS/LRG5BhBA==", "bBfMz");
        OptionsManager.I[16 ^ 15] = OptionsManager.l("V7ZR9u2ZAISMmtWdbfLPc5mQX+zAvQyX", "Gpjki");
        OptionsManager.I[123 ^ 91] = OptionsManager.I("QILODnuuqngVFapRgiu4KygFwvFe9DBL", "KkyBV");
        OptionsManager.I[160 ^ 129] = OptionsManager.l("bOdzD7aaEs6lHm1APsf9LA==", "NdTAZ");
        OptionsManager.I[165 ^ 135] = OptionsManager.I("qqBL47TfdlLQ+H1S/2cVuj5IM9MbnwqFgSE/MDyJ66w=", "fETBF");
        OptionsManager.I[110 ^ 77] = OptionsManager.lI("\n2\u001882\u000f \tw=\u0001=\u000b6~\u000f&\u00181~\u001b \t+>\u000f>\t", "nSlYP");
        OptionsManager.I[123 ^ 95] = OptionsManager.l("L3PZiJRXGTmVpJdyO9veCA==", "ARWjY");
        OptionsManager.I[148 ^ 177] = OptionsManager.I("hgeDRp90QfZW6LFgVyEvdanIF9rvsqOO9k+4DzhNKYw=", "aEcNt");
        OptionsManager.I[176 ^ 150] = OptionsManager.l("aSn4lTSwItc=", "JEzrY");
        OptionsManager.I[137 ^ 174] = OptionsManager.lI("\u0014'.8 >\u0016\u0002y2*;", "GWAWK");
        OptionsManager.I[164 ^ 140] = OptionsManager.I("voYH3mJehWY=", "lGXYs");
        OptionsManager.I[41 ^ 0] = OptionsManager.I("wFjNKwmVshhz4Myyb13Job7O3waXV8ag", "hPVKp");
        OptionsManager.I[36 ^ 14] = OptionsManager.lI("\r\u001b *h\u0011\u0018& #\u0013\u0018(>5", "atGYF");
        OptionsManager.I[133 ^ 174] = OptionsManager.I("SySRvnW/UtcfY6iDLbi3ARuCtRNJwGBN", "CWPJu");
        OptionsManager.I[167 ^ 139] = OptionsManager.I("LMF+uVpCnxBkfMQYnlJV/Q==", "XjhyU");
        OptionsManager.I[67 ^ 110] = OptionsManager.lI("VYf\u001a6\u0018\u000f%\n(PLqI-\u000b\u001c*\u001c|", "pjFaU");
        OptionsManager.I[188 ^ 146] = OptionsManager.lI("\u0007\b\u0013\u0015h\u0018\u0002\u0004\u00074\n\u0013\u0011\u0002", "kgtfF");
        OptionsManager.I[34 ^ 13] = OptionsManager.lI("i\"@\u001f_biK__biK__biK__biK__biK__biK__biK", "ODfrr");
        OptionsManager.I[147 ^ 163] = OptionsManager.lI("2#58- a3/+'.9$-*", "SOPJY");
        OptionsManager.I[58 ^ 11] = OptionsManager.lI("OP89H\u001c\u00148$\u000f", "igQJh");
        OptionsManager.I[89 ^ 107] = OptionsManager.l("xtvnGbfpOABamH11jEPJuf98XSDDwZgI", "ZsOCe");
        OptionsManager.I[106 ^ 89] = OptionsManager.I("sDj10LY7I39GD2TitubZvYzYUezDsU5a", "Gourg");
        OptionsManager.I[41 ^ 29] = OptionsManager.l("aY1wyt2xjIgB3u1xMZA6cw==", "jBUZP");
        OptionsManager.I[10 ^ 63] = OptionsManager.l("/wR2IyVUc93K5sa9NQIWrxuL6Oe5UbIVezbi6wJNUGQkWFGUxqy2NAmkObAyzfqD9QCS0nVN/KIKj3AVRgGvPw==", "MJBSg");
        OptionsManager.I[62 ^ 8] = OptionsManager.l("ScViXmymdeirFNKH1hEQOqNS7mJ7h0wA", "lduAz");
        OptionsManager.I[189 ^ 138] = OptionsManager.I("O+pmRlmYY6GprqbUm5FK9Q==", "ouDRT");
        OptionsManager.I[188 ^ 132] = OptionsManager.lI("\u0011;(\tx\u00105+\u00177\u001d>5", "sZFzV");
        OptionsManager.I[150 ^ 175] = OptionsManager.l("1Y9oAvOXZKnU7SZHQyGbKB5M77dXrJWCcMfWIcG1kmY=", "ezIpe");
        OptionsManager.I[41 ^ 19] = OptionsManager.lI("\t#\u0004\u0014{\u0006'\u0019\u00144\f'", "kBjgU");
        OptionsManager.I[136 ^ 179] = OptionsManager.I("61AWwnzJsJx0eKI3Q/kjFgjYb6tyb1FyE7HHFaAbUvw+azZZzDQC3w==", "HwcDv");
        OptionsManager.I[112 ^ 76] = OptionsManager.lI("#\u000f\u001b\u0003`2\u000b\u0005\u0011< \u001a\u0010\u0014", "AnupN");
        OptionsManager.I[169 ^ 148] = OptionsManager.lI("@!H\u0002BKjCB", "fGnoo");
        OptionsManager.I[82 ^ 108] = OptionsManager.I("N5qA6rp6Iogxb7cmcEFdBw==", "mTwDd");
        OptionsManager.I[22 ^ 41] = OptionsManager.I("r54qLWpwRILC+6Is5cOFxBbQFRLiA6Mo", "xieAd");
        OptionsManager.I[46 ^ 110] = OptionsManager.lI("0\u0015\u001d\f/5\u0007\fC ;\u001a\u000e\u0002c1\u001a\b\u000f!1\u0010", "TtimM");
        OptionsManager.I[36 ^ 101] = OptionsManager.I("cMXhH5bT3g2XubwPsgGWGqWXZxZh9mLq", "IWYPV");
        OptionsManager.I[47 ^ 109] = OptionsManager.l("DAWunrqct4h+qdlLx1t1ng==", "SbkEF");
        OptionsManager.I[224 ^ 163] = OptionsManager.I("tMtcwu3wwn8kJi75VWdG/f2Quf0BGaF0", "hrHwl");
        OptionsManager.I[89 ^ 29] = OptionsManager.lI("7\u0016\u001d&'2\u0004\fi(<\u0019\u000e(k7\u0016\u001d&'2\u0004\f", "SwiGE");
        OptionsManager.I[233 ^ 172] = OptionsManager.I("JpjnmlrwBajiEvBGkO//WQ==", "cZQEG");
        OptionsManager.I[198 ^ 128] = OptionsManager.lI("\u0001\u0019<-\u0005\u0004\u000b-b\n\n\u0016/#I\u0004\r<$I\u0000\u0016).\u000b\u0000\u001c", "exHLg");
        OptionsManager.I[254 ^ 185] = OptionsManager.I("wO55wmrJ25Lv+cLtbO2hNtL/XhydZyFpVFwN3PzNhhw=", "JAtKk");
        OptionsManager.I[16 ^ 88] = OptionsManager.I("+3GAX/7yxBxRESoLNPETJw==", "tmfKE");
        OptionsManager.I[207 ^ 134] = OptionsManager.lI("1\u0000\u0018\u0014\u00144\u0012\t[\u001b:\u000f\u000b\u001aX4\u0014\u0018\u001dX%\u0000\u001f\u0006\u0001:\u0013\b", "Ualuv");
        OptionsManager.I[210 ^ 152] = OptionsManager.lI("", "MaJkd");
        OptionsManager.I[82 ^ 25] = OptionsManager.lI("\u0005\n\u000b7\u001f\u0006\u001c\u00105\u0018\u0006A\u001b#\u0006\u0014\u001c\nt\u0013\u001b\u000e\u001b6\u0013\u0011", "uoyZv");
        OptionsManager.I[32 ^ 108] = OptionsManager.lI("\u0016\u001f6\u001e\n\u0015\t-\u001c\r\u0015T&\n\u0013\u0007\t7]\u0013\u0003\b)\u001a\u0010\u0015\u0013+\u001d", "fzDsc");
        OptionsManager.I[49 ^ 124] = OptionsManager.I("cFcr7KYTgR7HUD9KlzUQxw==", "XDtpM");
        OptionsManager.I[245 ^ 187] = OptionsManager.l("y/tx2JxSbijX5H0d5QuN9vZCNVLNLeRmyK4cLm7iykM=", "CNGSO");
        OptionsManager.I[210 ^ 157] = OptionsManager.l("KJSlnUTV7oCkud5lv7qP26tsNr4NTSZR1e+l+NSyfxI=", "evjOH");
        OptionsManager.I[237 ^ 189] = OptionsManager.lI(";\u00105\u0002\u0001,\u0001 \u0004\u0019v99\f++\u000b9\u0012\u001ex;~\u0004\u00049\u001a<\u0004\u000e", "XxPaj");
        OptionsManager.I[118 ^ 39] = OptionsManager.l("yQWYq2CfanCoG4tWfVo+fJ9++/sUshE5xZDeFQVRVwQ=", "TonSx");
        OptionsManager.I[91 ^ 9] = OptionsManager.l("6f7u176Cr4fBSBBsxrDV9CBLxOaSlK2y2j5paWkcAq0=", "oKecF");
        OptionsManager.I[2 ^ 81] = OptionsManager.I("e/KzcSow1cu+IGk3tmAi7OPI98MguNrIywu4yqIMduU=", "dbDTC");
        OptionsManager.I[38 ^ 114] = OptionsManager.l("qO+5OVcsUWunQtzwHZe/VRDMAv0FmBCrfVXJ3KM9wCk=", "RcoIh");
        OptionsManager.I[68 ^ 17] = OptionsManager.I("JGrzc5CwhegUhgPC+ypNV0Z2dN0HfK8mwbcxrFjUPiNN5FDKl+KZAg==", "ufxhg");
        OptionsManager.I[43 ^ 125] = OptionsManager.l("lV87tIr+3ZRj3F+9bfZKmQJ/kwIt9bBMGpAoucdVLZnG8YcIGhXc7Q==", "DRBnl");
        OptionsManager.I[95 ^ 8] = OptionsManager.l("ZDuE5PsUSF3aSUjSMdUTXHNg3ZBQArVPmYR8Khrt7H73UTdVbVEMqw==", "RpSiz");
        OptionsManager.I[34 ^ 122] = OptionsManager.lI("\u00110)!\u0011\u0006!<'\t\\\u001996\u001514%!\u0011\u0017*l\u0006T\u00176- \u0016\u0017<", "rXLBz");
        OptionsManager.I[103 ^ 62] = OptionsManager.l("G7X5+6G4NBHVUM2TDu8MhwnMz1spRxht0OM+rX6R7+QOV5THSpEgAQ==", "FUVcB");
        OptionsManager.I[94 ^ 4] = OptionsManager.l("R/o/fscYeyLZhYzV8NETCkXYqIcH5qWpLKCue2g979jGVsLg3mKy2g==", "gZvij");
        OptionsManager.I[109 ^ 54] = OptionsManager.l("uX117UM/axYQE9UVY6BZq3ZSWZR8/fTb4M9TzBEaDlKzI6o09+O+wQ==", "MCfep");
        OptionsManager.I[106 ^ 54] = OptionsManager.l("b5exvDCKE1sD6fo1v2xAfhvFIxUazhTsbLO192SzqtjnxBJ2Kg9grA==", "yrnbt");
        OptionsManager.I[253 ^ 160] = OptionsManager.lI("\t\u001c\u0003\t:\u001e\r\u0016\u000f\"D5\u0013\u001e>)\u0018\u000f\t:\u000f\u0006F#\u007f\u000f\u001a\u0007\b=\u000f\u0010", "jtfjQ");
        OptionsManager.I[96 ^ 62] = OptionsManager.l("3DSd4t9V+U5lGeIPZeZwZ8PtYxWhzeQ/0OI+wxX7yPpRl8aFR3W8Pg==", "LcCXG");
        OptionsManager.I[192 ^ 159] = OptionsManager.l("K4Pcr7pzejRHjMcd9uMbDHBaIlV+aHjQH1FZoM5cIbRyawBw+uGaLA==", "spHHX");
        OptionsManager.I[45 ^ 77] = OptionsManager.lI(", +\u00043;1>\u0002+a\n/\u0003\b.+%\u0002,<f+\t9-$+\u0003", "OHNgX");
        OptionsManager.I[115 ^ 18] = OptionsManager.lI("%,)(\u00192=<.\u0001h\u0002 2R\u0007j)%\u0013$()/", "FDLKr");
        OptionsManager.I[90 ^ 56] = OptionsManager.l("2TCPCK+UA9Mjj8b26jCHFfsbrmXd7X/ZhsY/Y5S+e5Q=", "aEeoW");
        OptionsManager.I[117 ^ 22] = OptionsManager.lI("\u0005\r\u0017\u000f\u0013\u0012\u001c\u0002\t\u000bH#\u001e\u0015X%\n\u001f\u000e\u0011\b\u0000\u0016B\u001d\b\u0004\u0010\u0000\u001d\u0002", "ferlx");
        OptionsManager.I[234 ^ 142] = OptionsManager.I("u9ExgqqGgoLPMt0wecXNL/mmxgLDlS8IuPlJLvpbyQM=", "RZYiA");
        OptionsManager.I[225 ^ 132] = OptionsManager.lI("9\u0000$:).\u00111<1t.- b\u001fF$7#8\u0004$=", "ZhAYB");
        OptionsManager.I[122 ^ 28] = OptionsManager.I("+/gWDvYCIufqYGGOWVOjwpNnkuKDNoGLzOwcJqPji+U=", "qgVHE");
        OptionsManager.I[44 ^ 75] = OptionsManager.I("Mu6otllMSmz6h6MwjKpYPTpaqa4O170xjgq0LNnMVOA=", "RkmFK");
        OptionsManager.I[5 ^ 109] = OptionsManager.lI("\u000f8\u0006!:\u0018)\u0013'\"B\u0016\u000f;q$~\u0006,0\u000e<\u0006&", "lPcBQ");
        OptionsManager.I[79 ^ 38] = OptionsManager.l("sZn5nO/oX82o4SM2FUTFKD208TG5lqoEJRgR4jYOIFs=", "MeHFA");
        OptionsManager.I[76 ^ 38] = OptionsManager.l("yWNvJKPCCYTiWEap59VoPkGk1xUFlCwVjc6/q3AOmms=", "bqEKU");
        OptionsManager.I[23 ^ 124] = OptionsManager.lI("0\u001b\u00163\u0011'\n\u00035\t}:\u001d&\u001f=\u0007\u001c\"\u0003}\u0016\u001d1\u0018?\u0016\u0017", "SssPz");
        OptionsManager.I[193 ^ 173] = OptionsManager.l("mbYeNhnbVivVGIddf05xqX0I8CUcRJu89+OCoDjUe8g=", "goBMa");
        OptionsManager.I[13 ^ 96] = OptionsManager.lI("\u0006$\u0003\b\u0005\u00115\u0016\u000e\u001dK\u0007\u000f\u0007\u0002$9\u0014\nN'b\u0003\u0005\u000f\u0007 \u0003\u000f", "eLfkn");
        OptionsManager.I[125 ^ 19] = OptionsManager.lI(":\f\u001c3.-\u001d\t56w/\u0010<)\u0018\u0011\u000b1e\u001aJ\u001c>$;\b\u001c4", "YdyPE");
        OptionsManager.I[171 ^ 196] = OptionsManager.I("eaonyjV59HOtOe/d/vq6xURQjjd1EI+l/RUEbJp3JBM=", "tGPtH");
        OptionsManager.I[214 ^ 166] = OptionsManager.lI(")+\u00124\u001a>:\u00072\u0002d\b\u001e;\u001d\u000b6\u00056Q\u000fm\u00129\u0010(/\u00123", "JCwWq");
        OptionsManager.I[108 ^ 29] = OptionsManager.l("qG2IzBRU/6R3Eky1HzTCOBcoA2WGlnyiIAyRi9H59Jc=", "ivsoC");
        OptionsManager.I[26 ^ 104] = OptionsManager.I("SNW/iw3Kg8yD43Cdz3vvQ9P5doORKxSXwIoOLgTluhs=", "yrpXw");
        OptionsManager.I[59 ^ 72] = OptionsManager.lI("/*\u0012'%8;\u0007!=b\u0010\u0012%-$b6j+\"#\u0015(+(", "LBwDN");
        OptionsManager.I[104 ^ 28] = OptionsManager.l("rzTcFp0P7VvNzNiL8rwbJAE+o8z7PCz0l0h3nz2Fo5M=", "mfIzZ");
        OptionsManager.I[13 ^ 120] = OptionsManager.I("DszCDWxjbwIal7ui5x4h6Ie5o31VL996TALj15MGUbg=", "zKuvX");
        OptionsManager.I[194 ^ 180] = OptionsManager.I("ua6+yahlITg/0JdsTkezbrW9F24dLm2R2aG3bfisch0=", "OxolI");
        OptionsManager.I[177 ^ 198] = OptionsManager.I("Dnjx91qtmqzCvepyiUIfdC6Phgiwb5NnZEZMDwMb1hw=", "VEZpQ");
        OptionsManager.I[28 ^ 100] = OptionsManager.I("1W2Pm5aMLGZ31V1YliT5iCe/iWH/+r9T9/7jFYu/x7c=", "MNUbP");
        OptionsManager.I[54 ^ 79] = OptionsManager.I("MUS3sNJv9D6jkJQtQtHRA7iSrP/bgmijuIIhe0y6IwI=", "SMAtp");
        OptionsManager.I[38 ^ 92] = OptionsManager.I("qqQ7vTJA4iDOu1OvmjhTqBl1pd6Jtg8T7QBcDhmZHjw=", "HSKrm");
        OptionsManager.I[250 ^ 129] = OptionsManager.I("f+pwqd7Tlkb6zgMP3EmE9YrqYHJk3x9CoGYO8K78a0o=", "pUjxV");
        OptionsManager.I[15 ^ 115] = OptionsManager.lI("\t<\b/\u0006\u001e-\u001d)\u001eD\u0002\b \u0002\t=\u00195M+z\b\"\f\b8\b(", "jTmLm");
        OptionsManager.I[185 ^ 196] = OptionsManager.l("Z0UqZFGHzz321iz5Z18IMVX7IeMAqtCrj8k9Na5ZrZA=", "NyLHk");
        OptionsManager.I[103 ^ 25] = OptionsManager.lI("7.#$\u000e ?6\"\u0016z\u00054.\u0011=%'+\u0016t\u0007h\"\u000b5$*\"\u0001", "TFFGe");
        OptionsManager.I[60 + 89 - 93 + 71] = OptionsManager.I("h+SIfdC/kC7I+DmKdmRxhE1rE4P94k4gG/w7yB94hog=", "TOyoI");
        OptionsManager.I[123 + 94 - 144 + 55] = OptionsManager.I("09/cP0Eak57DXn7y0R1ayCUTBVchpoTKAoSh8kM0aXY=", "PBlbE");
        OptionsManager.I[61 + 16 - 52 + 104] = OptionsManager.l("wa+HiHFPVZHoWQ4pGl66dBVXYSyfemBopun0IqbZZQE=", "CQHEC");
        OptionsManager.I[38 + 114 - 33 + 11] = OptionsManager.lI(",8\u0004\u0010.;)\u0011\u00166a\u0019\u000f\u0005$#9\u0005#*<p ] !1\u0003\u001f +", "OPasE");
        OptionsManager.I[21 + 96 - 91 + 105] = OptionsManager.l("nVbnevdbEP2xcBhv2/djSFWgbHR+pPS4YubgV8XNKNs=", "REmLs");
        OptionsManager.I[121 + 125 - 131 + 17] = OptionsManager.lI("\u001b\u0011$\u0010\u0006\f\u00001\u0016\u001eV)(\u001d\n+\t.\u001c\u000bX;o\u0016\u0003\u0019\u001b-\u0016\t", "xyAsm");
        OptionsManager.I[123 + 18 - 9 + 1] = OptionsManager.I("FPfkTMSgMhsBchDcESxLvFi2dg96ToiSC9Vx75kEh7jNEDF37L8IgA==", "JzouL");
        OptionsManager.I[24 + 73 - 36 + 73] = OptionsManager.lI("\u000b?6\f\u0019\u001c.#\n\u0001F\u001e=\u0019\u0013\u0004>7&\u001c\u001c2!\u000e\u0011\u001cw\u0011A\u0017\u000661\u0003\u0017\f", "hWSor");
        OptionsManager.I[107 + 123 - 146 + 51] = OptionsManager.lI("-!+!\":0>':`\u0007!\u0011%!>n\u0003g+'/ %+-", "NINBI");
        OptionsManager.I[52 + 80 - 121 + 125] = OptionsManager.I("XhA8KbnS2+9sgQVqMFF/6v2KcIADH5G+4R3ezS0wlSA=", "YKFHE");
        OptionsManager.I[24 + 63 - -43 + 7] = OptionsManager.I("apJHUqaC4Uu7AgWIeV4Ee5EcVtfxb/0APsizWmhVU0nFEZMNgtn7zg==", "YIodY");
        OptionsManager.I[88 + 102 - 164 + 112] = OptionsManager.l("PidZUJteYB04XRa7GNjwPyPvgg6iFOhXVDHzyyS415z8Ee0RoAJRKA==", "fZwMA");
        OptionsManager.I[10 + 117 - 81 + 93] = OptionsManager.lI("))\b->>8\u001d+&d\u0012\b<#/3.<49)\b<u\bo\b 4(-\b*", "JAmNU");
        OptionsManager.I[54 + 4 - -18 + 64] = OptionsManager.l("Lc5REgHKcE2YO+vVK+JSHCDZdZJAo1YObZzkhUsViGQ=", "jfRPf");
        OptionsManager.I[63 + 113 - 53 + 18] = OptionsManager.l("NFmo9wtWdOBPmkvWwnzWKEwX1eF1jpxXvkQa/Sy8f8o=", "JWROi");
        OptionsManager.I[66 + 7 - -16 + 53] = OptionsManager.l("hgr/lUA8pcI+yRWLfWxlXoknj8JWQFn1DDvhZJpk7PQ=", "GlNRm");
        OptionsManager.I[13 + 9 - -40 + 81] = OptionsManager.I("G8bb5LYwivqgLXgvgasqfg/RAbf3OJSb9r1jDAbgt24=", "VGHOI");
    }

    public String getLogsSepearted() {
        return this.logsSeparated;
    }

    public String getModPermission() {
        return this.modPermission;
    }

    public boolean isBanAnnouncement() {
        return this.banAnnouncement;
    }

    private static String I(String obj, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8")), 0x98 ^ 0x90), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init("  ".length(), keySpec);
            return new String(des.doFinal(Base64.getDecoder().decode(obj.getBytes("UTF-8"))), "UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static {
        OptionsManager.I();
        HEADER = I["".length()];
    }

    public OptionsManager() {
        configuration = new YamlConfiguration();
        this.banConfiguration = new YamlConfiguration();
    }

    public File getFile() {
        return file;
    }

    public MongoCredential getMongoCredential() {
        return this.mongoCredential;
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
            if (4 >= 4) continue;
            throw null;
        }
        return sb.toString();
    }

    public <T> List<T> readList(Object o) {
        List<Object> list;
        if (o instanceof List) {
            list = (List<Object>)o;
            "".length();
            if (2 < 2) {
                throw null;
            }
        } else {
            Object[] arrobject = new Object[" ".length()];
            arrobject["".length()] = o;
            list = Arrays.asList(arrobject);
        }
        return list;
    }

    public String getBanMessage() {
        return this.banMessage;
    }

    public void enable() {
        this.setupConfig();
        this.readConfig();
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    private void setupConfig() {
        Main plugin = Main.getPlugin();
        file = new File(plugin.getDataFolder(), I[0xA0 ^ 0x87]);
        this.loadConfig();
        configuration.options().header(HEADER);
        configuration.options().copyDefaults(" ".length() != 0).copyHeader(" ".length() != 0);
        configuration.addDefault(I[0x85 ^ 0xAD], (Object)I[0x8E ^ 0xA7]);
        configuration.addDefault(I[0xB ^ 0x21], (Object)I[0x25 ^ 0xE]);
        configuration.addDefault(I[0x83 ^ 0xAF], (Object)I[0x82 ^ 0xAF]);
        configuration.addDefault(I[0xB4 ^ 0x9A], (Object)I[0x88 ^ 0xA7]);
        configuration.addDefault(I[0x4E ^ 0x7E], (Object)I[0x25 ^ 0x14]);
        configuration.addDefault(I[0x88 ^ 0xBA], (Object)I[0xD ^ 0x3E]);
        configuration.addDefault(I[0xF0 ^ 0xC4], (Object)I[0xBE ^ 0x8B]);
        configuration.addDefault(I[0x7B ^ 0x4D], (Object)I[0xB8 ^ 0x8F]);
        String[] arrstring = new String[" ".length()];
        arrstring["".length()] = I[0xBC ^ 0x85];
        configuration.addDefault(I[0 ^ 0x38], (Object)arrstring);
        configuration.addDefault(I[0xB2 ^ 0x88], (Object)I[0x60 ^ 0x5B]);
        configuration.addDefault(I[0x26 ^ 0x1A], (Object)I[0x5C ^ 0x61]);
        configuration.addDefault(I[0xB1 ^ 0x8F], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x76 ^ 0x49], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x11 ^ 0x51], (Object)("".length() != 0));
        configuration.addDefault(I[0xEA ^ 0xAB], (Object)I[0xF8 ^ 0xBA]);
        configuration.addDefault(I[0x6E ^ 0x2D], (Object)(7040 + 18124 - 1719 + 3572));
        configuration.addDefault(I[0x62 ^ 0x26], (Object)I[0xCC ^ 0x89]);
        configuration.addDefault(I[0x3D ^ 0x7B], (Object)("".length() != 0));
        configuration.addDefault(I[0xD9 ^ 0x9E], (Object)I[0x68 ^ 0x20]);
        configuration.addDefault(I[0x2C ^ 0x65], (Object)I[0x6F ^ 0x25]);
        configuration.addDefault(I[0xF6 ^ 0xBD], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x2E ^ 0x62], (Object)I[0x6C ^ 0x21]);
        configuration.addDefault(I[0xE8 ^ 0xA6], (Object)(" ".length() != 0));
        configuration.addDefault(I[0 ^ 0x4F], (Object)("".length() != 0));
        configuration.addDefault(I[0xCB ^ 0x9B], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xCC ^ 0x9D], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x10 ^ 0x42], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x36 ^ 0x65], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xEA ^ 0xBE], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x26 ^ 0x73], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x72 ^ 0x24], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x56 ^ 1], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x64 ^ 0x3C], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xD2 ^ 0x8B], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xD6 ^ 0x8C], (Object)("".length() != 0));
        configuration.addDefault(I[0xE0 ^ 0xBB], (Object)(" ".length() != 0));
        configuration.addDefault(I[4 ^ 0x58], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xFA ^ 0xA7], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x7E ^ 0x20], (Object)("".length() != 0));
        configuration.addDefault(I[0x56 ^ 9], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x15 ^ 0x75], (Object)(" ".length() != 0));
        configuration.addDefault(I[3 ^ 0x62], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x70 ^ 0x12], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xF1 ^ 0x92], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xF0 ^ 0x94], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x57 ^ 0x32], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x49 ^ 0x2F], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x78 ^ 0x1F], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x12 ^ 0x7A], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x47 ^ 0x2E], (Object)("".length() != 0));
        configuration.addDefault(I[0xD ^ 0x67], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xF5 ^ 0x9E], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xC2 ^ 0xAE], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x2B ^ 0x46], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x46 ^ 0x28], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x72 ^ 0x1D], (Object)(" ".length() != 0));
        configuration.addDefault(I[8 ^ 0x78], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xCF ^ 0xBE], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x2F ^ 0x5D], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x6B ^ 0x18], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x49 ^ 0x3D], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x43 ^ 0x36], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x22 ^ 0x54], (Object)(" ".length() != 0));
        configuration.addDefault(I[9 ^ 0x7E], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x2F ^ 0x57], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x70 ^ 9], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x47 ^ 0x3D], (Object)(" ".length() != 0));
        configuration.addDefault(I[0x3D ^ 0x46], (Object)("".length() != 0));
        configuration.addDefault(I[0x26 ^ 0x5A], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xFD ^ 0x80], (Object)(" ".length() != 0));
        configuration.addDefault(I[0xE9 ^ 0x97], (Object)("".length() != 0));
        configuration.addDefault(I[15 + 84 - -5 + 23], (Object)("".length() != 0));
        configuration.addDefault(I[3 + 127 - 94 + 92], (Object)(" ".length() != 0));
        configuration.addDefault(I[30 + 88 - 17 + 28], (Object)(" ".length() != 0));
        configuration.addDefault(I[36 + 110 - 120 + 104], (Object)(" ".length() != 0));
        configuration.addDefault(I[43 + 108 - 80 + 60], (Object)(" ".length() != 0));
        configuration.addDefault(I[21 + 98 - 14 + 27], (Object)(" ".length() != 0));
        configuration.addDefault(I[38 + 65 - 97 + 127], (Object)(" ".length() != 0));
        configuration.addDefault(I[112 + 53 - 38 + 7], (Object)(" ".length() != 0));
        configuration.addDefault(I[81 + 123 - 121 + 52], (Object)("".length() != 0));
        configuration.addDefault(I[8 + 77 - -25 + 26], (Object)("".length() != 0));
        configuration.addDefault(I[26 + 118 - 76 + 69], (Object)(" ".length() != 0));
        configuration.addDefault(I[77 + 22 - -13 + 26], (Object)(" ".length() != 0));
        configuration.addDefault(I[129 + 41 - 166 + 135], (Object)(" ".length() != 0));
        configuration.addDefault(I[109 + 25 - 112 + 118], (Object)("".length() != 0));
        configuration.addDefault(I[99 + 38 - 46 + 50], (Object)("".length() != 0));
        configuration.addDefault(I[130 + 31 - 25 + 6], (Object)(" ".length() != 0));
        configuration.addDefault(I[19 + 45 - -39 + 40], (Object)("".length() != 0));
        OptionsManager.saveConfig();
    }

    public static void saveConfig() {
        try {
            configuration.save(file);
            "".length();
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (true < false) {
            throw null;
        }
    }

    public boolean isBypassEnabled() {
        return this.bypassEnabled;
    }

    public boolean isMongoEnabled() {
        return this.mongoEnabled;
    }

    public String getBypassPermission() {
        return this.bypassPermission;
    }

    public void disable() {
        OptionsManager.saveConfig();
    }

    public String getPlayerLogs() {
        return this.logsPlayer;
    }

    public String getAlertExp() {
        return this.alertExperimental;
    }

    public String getAlertMessage() {
        return this.alertMessage;
    }

    public File getBanFile() {
        return this.banFile;
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

    public int getMongoPort() {
        return this.mongoPort;
    }

    public static OptionsManager getInstance() {
        OptionsManager optionsManager;
        if (instance == null) {
            optionsManager = instance = new OptionsManager();
            "".length();
            if (3 != 3) {
                throw null;
            }
        } else {
            optionsManager = instance;
        }
        OptionsManager optionsManager2 = optionsManager;
        return optionsManager2;
    }

    public boolean isMongoAuthEnabled() {
        return this.mongoAuthEnabled;
    }

    public String getMongoDatabase() {
        return this.mongoDatabase;
    }

    public YamlConfiguration getBanConfiguration() {
        return this.banConfiguration;
    }

    public String getPlayerLogsResult() {
        return this.logsResult;
    }

    public String getMongoHost() {
        return this.mongoHost;
    }

    public boolean isAutoBan() {
        return this.autoBan;
    }

    public List<String> getBanCommands() {
        return this.banCommands;
    }

    private void loadConfig() {
        try {
            configuration.load(file);
            "".length();
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (3 <= -1) {
            throw null;
        }
    }

    private void readConfig() {
        this.anticheatName = configuration.getString(I[" ".length()], I["  ".length()]);
        this.logsPlayer = configuration.getString(I["   ".length()], I[2 ^ 6]);
        this.logsResult = configuration.getString(I[0x36 ^ 0x33], I[0x7B ^ 0x7D]);
        this.logsSeparated = configuration.getString(I[0x21 ^ 0x26], I[0x76 ^ 0x7E]);
        this.alertCertainty = configuration.getString(I[0xAA ^ 0xA3], I[1 ^ 0xB]);
        this.alertExperimental = configuration.getString(I[0xB8 ^ 0xB3], I[0x59 ^ 0x55]);
        this.alertMessage = configuration.getString(I[0x2E ^ 0x23], I[0x9F ^ 0x91]);
        this.modPermission = configuration.getString(I[0xE ^ 1], I[0x6F ^ 0x7F]);
        String[] arrstring = new String[" ".length()];
        arrstring["".length()] = I[0x42 ^ 0x50];
        this.banCommands = this.readList(configuration.get(I[0xB9 ^ 0xA8], (Object)arrstring));
        this.banMessage = configuration.getString(I[0x22 ^ 0x31], I[0x8A ^ 0x9E]);
        this.banSeparated = configuration.getString(I[0x8C ^ 0x99], I[0x49 ^ 0x5F]);
        this.autoBan = configuration.getBoolean(I[0x97 ^ 0x80], " ".length() != 0);
        this.banAnnouncement = configuration.getBoolean(I[0x84 ^ 0x9C], " ".length() != 0);
        this.bypassEnabled = configuration.getBoolean(I[0xB5 ^ 0xAC], " ".length() != 0);
        this.bypassPermission = configuration.getString(I[0x45 ^ 0x5F], I[0x1C ^ 7]);
        this.mongoEnabled = configuration.getBoolean(I[0x2E ^ 0x32], "".length() != 0);
        this.mongoHost = configuration.getString(I[0x14 ^ 9], I[9 ^ 0x17]);
        this.mongoPort = configuration.getInt(I[0x84 ^ 0x9B], 8194 + 10856 - 14737 + 22704);
        this.mongoDatabase = configuration.getString(I[0x34 ^ 0x14], I[0x92 ^ 0xB3]);
        this.mongoAuthEnabled = configuration.getBoolean(I[0x1B ^ 0x39], "".length() != 0);
        if (this.mongoAuthEnabled) {
            String username = configuration.getString(I[0x10 ^ 0x33], I[0x50 ^ 0x74]);
            String password = configuration.getString(I[0x86 ^ 0xA3], I[0x3B ^ 0x1D]);
            this.mongoCredential = MongoCredential.createCredential((String)username, (String)this.mongoDatabase, (char[])password.toCharArray());
        }
    }
}

