/*
 * Decompiled with CFR 0.146.
 * 
 * Could not load the following classes:
 *  org.bukkit.scheduler.BukkitRunnable
 */
package com.unknownmyname.runnable;

import com.unknownmyname.check.alert.Alert;
import java.util.Queue;
import org.bukkit.scheduler.BukkitRunnable;

public class LogsRunnable
extends BukkitRunnable {
    private final /* synthetic */ Queue<Alert> alerts;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void run() {
        "".length();
        if (0 < 4) ** GOTO lbl7
        throw null;
lbl-1000: // 1 sources:
        {
            if (this.alerts.size() != 0) continue;
            this.cancel();
lbl7: // 3 sources:
            ** while (this.alerts.size() >= 0)
        }
lbl8: // 1 sources:
    }

    public LogsRunnable(Queue<Alert> alerts) {
        this.alerts = alerts;
    }
}

