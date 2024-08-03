package dev.ectasy.api.utils;

import org.bukkit.Bukkit;

public class VersionUtils {

    public static boolean isAbove(int ver){
        String currentVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        int subVersion = Integer.parseInt(currentVersion.replace("v1_", "").replaceAll("_R\\d", ""));
        return subVersion > ver;
    }

}
