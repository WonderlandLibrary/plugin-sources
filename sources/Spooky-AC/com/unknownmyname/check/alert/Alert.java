/*
 * Decompiled with CFR 0.146.
 */
package com.unknownmyname.check.alert;

import com.unknownmyname.check.Check;

public class Alert {
    private final /* synthetic */ int vl;
    private final /* synthetic */ long timestamp;
    private final /* synthetic */ String data;
    private final /* synthetic */ Check check;

    public int getVl() {
        return this.vl;
    }

    public Alert(Check check, String data, int vl) {
        this.timestamp = System.currentTimeMillis();
        this.check = check;
        this.data = data;
        this.vl = vl;
    }

    public String getData() {
        return this.data;
    }

    public Check getCheck() {
        return this.check;
    }

    public long getTimestamp() {
        return this.timestamp;
    }
}

