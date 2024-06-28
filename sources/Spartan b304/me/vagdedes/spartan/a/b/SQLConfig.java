package me.vagdedes.spartan.a.b;

import me.vagdedes.spartan.k.c.ConfigUtils;
import me.vagdedes.spartan.features.b.SQLLogs;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import me.vagdedes.spartan.Register;
import java.util.HashMap;

public class SQLConfig
{
    private static final HashMap<String, String> d;
    
    public SQLConfig() {
        super();
    }
    
    public static String getHost() {
        if (SQLConfig.d.containsKey("host")) {
            return SQLConfig.d.get("host");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("host");
        SQLConfig.d.put("host", string);
        return string;
    }
    
    public static String getUser() {
        if (SQLConfig.d.containsKey("user")) {
            return SQLConfig.d.get("user");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("user");
        SQLConfig.d.put("user", string);
        return string;
    }
    
    public static String getPassword() {
        if (SQLConfig.d.containsKey("password")) {
            return SQLConfig.d.get("password");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("password");
        SQLConfig.d.put("password", string);
        return string;
    }
    
    public static String getDatabase() {
        if (SQLConfig.d.containsKey("database")) {
            return SQLConfig.d.get("database");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("database");
        SQLConfig.d.put("database", string);
        return string;
    }
    
    public static String getTable() {
        if (SQLConfig.d.containsKey("table")) {
            return SQLConfig.d.get("table");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("table");
        SQLConfig.d.put("table", string);
        return string;
    }
    
    public static String getPort() {
        if (SQLConfig.d.containsKey("port")) {
            return SQLConfig.d.get("port");
        }
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        if (!file.exists()) {
            create();
        }
        final YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(file);
        if (loadConfiguration == null) {
            return "";
        }
        final String string = loadConfiguration.getString("port");
        SQLConfig.d.put("port", string);
        return string;
    }
    
    public static void clear() {
        SQLConfig.d.clear();
    }
    
    public static void create() {
        clear();
        SQLLogs.disconnect(true);
        final File file = new File(Register.plugin.getDataFolder() + "/mysql.yml");
        ConfigUtils.add(file, "host", "");
        ConfigUtils.add(file, "user", "");
        ConfigUtils.add(file, "password", "");
        ConfigUtils.add(file, "database", "");
        ConfigUtils.add(file, "table", "spartan_logs");
        ConfigUtils.add(file, "port", "3306");
    }
    
    static {
        d = new HashMap<String, String>(6);
    }
}
