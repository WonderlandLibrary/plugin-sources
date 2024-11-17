package me.frep.vulcan.spigot.config;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.Date;
import me.frep.vulcan.spigot.Vulcan;
import java.io.File;
import me.frep.vulcan.spigot.config.type.CommentedConfiguration;

public final class Stats
{
    private static final String fileName = "stats";
    private static CommentedConfiguration config;
    private static File file;
    
    public static void save() {
        try {
            Stats.config.save(Stats.file);
        }
        catch (final Exception exception) {
            final File newFile = new File(Vulcan.INSTANCE.getPlugin().getDataFolder(), "stats.broken." + new Date().getTime());
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "Could not save: stats.yml");
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "Regenerating file and renaming the current file to: " + newFile.getName());
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "You can try fixing the file with a yaml parser online!");
            Stats.file.renameTo(newFile);
            initialize();
        }
    }
    
    public static void reload() {
        try {
            Stats.config.load(Stats.file);
        }
        catch (final Exception exception) {
            final File newFile = new File(Vulcan.INSTANCE.getPlugin().getDataFolder(), "stats.broken." + new Date().getTime());
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "Could not save: stats.yml");
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "Regenerating file and renaming the current file to: " + newFile.getName());
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "You can try fixing the file with a yaml parser online!");
            Stats.file.renameTo(newFile);
            initialize();
        }
    }
    
    public static void initialize() {
        if (!Vulcan.INSTANCE.getPlugin().getDataFolder().exists()) {
            Vulcan.INSTANCE.getPlugin().getDataFolder().mkdir();
        }
        Stats.file = new File(Vulcan.INSTANCE.getPlugin().getDataFolder(), "stats.yml");
        if (!Stats.file.exists()) {
            Stats.file.getParentFile().mkdirs();
            copy(Vulcan.INSTANCE.getPlugin().getResource("stats.yml"), Stats.file);
        }
        Stats.config = CommentedConfiguration.loadConfiguration(Stats.file);
        try {
            Stats.config.syncWithConfig(Stats.file, Vulcan.INSTANCE.getPlugin().getResource("stats.yml"), "NONE");
        }
        catch (final Exception ex) {}
    }
    
    private static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
    
    public static int getInt(final String path) {
        return Stats.config.getInt(path);
    }
    
    public static int getPunishments() {
        return getInt("punishments");
    }
    
    public static void set(final String path, final Object object) {
        Stats.config.set(path, object);
        save();
        reload();
    }
    
    private Stats() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
