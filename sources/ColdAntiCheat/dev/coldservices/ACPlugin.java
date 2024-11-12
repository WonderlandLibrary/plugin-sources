package dev.coldservices;

import org.bukkit.plugin.java.JavaPlugin;

public final class ACPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        CAC.INSTANCE.start(this);
    }

    @Override
    public void onDisable() {
        CAC.INSTANCE.end();
    }
}
