package com.elevatemc.anticheat.util.config;

import com.elevatemc.anticheat.Hood;
import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.manager.CheckManager;

public class ConfigurationService {
    public ConfigurationService() {
        CheckManager checkManager = HoodPlugin.INSTANCE.getCheckManager();
        
        for (String check : checkManager.getAlphabeticallySortedChecks()) {
            String path = "checks." + check.toLowerCase();
            
            if (!Hood.get().getConfig().contains(path)) {
                Hood.get().getConfig().set(path + ".enabled", true);
                Hood.get().getConfig().set(path + ".auto-ban", false);
                Hood.get().getConfig().set(path + ".max-violations", 15);
            }

        }

        Hood.get().saveConfig();
        Hood.get().reloadConfig();
    }

    public boolean isEnabled(String check) {
        return Hood.get().getConfig().getBoolean("checks." + check.toLowerCase() + ".enabled");
    }

    public boolean isAutoban(String check) {
        return Hood.get().getConfig().getBoolean("checks." + check.toLowerCase() + ".auto-ban");
    }

    public double getMaxViolations(String check) {
        return Hood.get().getConfig().getDouble("checks." + check.toLowerCase() + ".max-violations");
    }

    public void toggleCheckActivation(String check) {
        Hood.get().getConfig().set("checks." + check.toLowerCase() + ".enabled", !isEnabled(check));

        Hood.get().saveConfig();
        Hood.get().reloadConfig();
    }
}