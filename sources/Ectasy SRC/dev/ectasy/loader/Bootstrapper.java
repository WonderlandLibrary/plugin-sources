package dev.ectasy.loader;

import dev.ectasy.Ectasy;
import org.bukkit.plugin.java.JavaPlugin;

public class Bootstrapper extends JavaPlugin {

    @Override
    public void onEnable(){
        new Ectasy(this).onEnable();
    }

}
