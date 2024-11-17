package me.frep.vulcan.spigot.jday.config;

import org.bukkit.configuration.file.YamlConfiguration;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.File;

public class JDayConfig
{
    private final File userFile;
    private final FileConfiguration userConfig;
    
    public JDayConfig(final String name) {
        this.userFile = new File(Vulcan.INSTANCE.getPlugin().getDataFolder() + File.separator, name + ".yml");
        this.userConfig = YamlConfiguration.loadConfiguration(this.userFile);
    }
    
    public FileConfiguration getConfigFile() {
        return this.userConfig;
    }
    
    public void saveConfigFile() {
        try {
            this.getConfigFile().save(this.userFile);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
