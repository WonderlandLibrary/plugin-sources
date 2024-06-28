package me.vagdedes.spartan.g;

import me.vagdedes.spartan.a.a.Settings;

public class IDs
{
    public IDs() {
        super();
    }
    
    public static String spigot() {
        return "66556";
    }
    
    public static String nonce() {
        return "-6666666666";
    }
    
    public static String resource() {
        return "25638";
    }
    
    public static String site() {
        return "https://vagdedes.com/spartan/verify.php?id=&nonce=";
    }
    
    public static String f() {
        return String.valueOf(Settings.getString("syn_id"));
    }
    
    public static String g() {
        return String.valueOf(Settings.getString("patreon_name"));
    }
}
