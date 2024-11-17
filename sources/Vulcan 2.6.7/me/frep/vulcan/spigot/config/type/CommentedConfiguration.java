package me.frep.vulcan.spigot.config.type;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import org.bukkit.Bukkit;
import java.io.Reader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.Objects;
import org.bukkit.configuration.ConfigurationSection;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import org.bukkit.configuration.InvalidConfigurationException;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.configuration.file.YamlConfiguration;

public final class CommentedConfiguration extends YamlConfiguration
{
    private final Map<String, String> configComments;
    private boolean creationFailure;
    
    public CommentedConfiguration() {
        this.configComments = new HashMap<String, String>();
        this.creationFailure = false;
    }
    
    public void syncWithConfig(final File file, final InputStream resource, final String... ignoredSections) throws IOException {
        if (this.creationFailure) {
            return;
        }
        final CommentedConfiguration cfg = loadConfiguration(resource);
        if (this.syncConfigurationSection(cfg, cfg.getConfigurationSection(""), Arrays.asList(ignoredSections)) && file != null) {
            this.save(file);
        }
    }
    
    public void setComment(final String path, final String comment) {
        if (comment == null) {
            this.configComments.remove(path);
        }
        else {
            this.configComments.put(path, comment);
        }
    }
    
    public String getComment(final String path) {
        return this.getComment(path, null);
    }
    
    public String getComment(final String path, final String def) {
        return this.configComments.getOrDefault(path, def);
    }
    
    public boolean containsComment(final String path) {
        return this.getComment(path) != null;
    }
    
    public boolean hasFailed() {
        return this.creationFailure;
    }
    
    @Override
    public void loadFromString(final String contents) throws InvalidConfigurationException {
        super.loadFromString(contents);
        final String[] lines = contents.split("\n");
        int currentIndex = 0;
        StringBuilder comments = new StringBuilder();
        String currentSection = "";
        while (currentIndex < lines.length) {
            if (isComment(lines[currentIndex])) {
                comments.append(lines[currentIndex]).append("\n");
            }
            else if (isNewSection(lines[currentIndex])) {
                currentSection = getSectionPath(this, lines[currentIndex], currentSection);
                if (comments.length() > 1) {
                    this.setComment(currentSection, comments.toString().substring(0, comments.length() - 1));
                }
                comments = new StringBuilder();
            }
            ++currentIndex;
        }
    }
    
    @Override
    public String saveToString() {
        this.options().header(null);
        final List<String> lines = new ArrayList<String>(Arrays.asList(super.saveToString().split("\n")));
        int currentIndex = 0;
        String currentSection = "";
        while (currentIndex < lines.size()) {
            final String line = lines.get(currentIndex);
            if (isNewSection(line)) {
                currentSection = getSectionPath(this, line, currentSection);
                if (this.containsComment(currentSection)) {
                    lines.add(currentIndex, this.getComment(currentSection));
                    ++currentIndex;
                }
            }
            ++currentIndex;
        }
        final StringBuilder contents = new StringBuilder();
        for (final String line2 : lines) {
            contents.append("\n").append(line2);
        }
        return (contents.length() == 0) ? "" : contents.substring(1);
    }
    
    private boolean syncConfigurationSection(final CommentedConfiguration commentedConfig, final ConfigurationSection section, final List<String> ignoredSections) {
        boolean changed = false;
        for (final String key : section.getKeys(false)) {
            final String path = section.getCurrentPath().isEmpty() ? key : (section.getCurrentPath() + "." + key);
            if (section.isConfigurationSection(key)) {
                final Stream<String> stream = ignoredSections.stream();
                final String obj = path;
                Objects.requireNonNull(obj);
                final boolean isIgnored = stream.anyMatch(obj::contains);
                final boolean containsSection = this.contains(path);
                if (!containsSection || !isIgnored) {
                    changed = (this.syncConfigurationSection(commentedConfig, section.getConfigurationSection(key), ignoredSections) || changed);
                }
            }
            else if (!this.contains(path)) {
                this.set(path, section.get(key));
                changed = true;
            }
            if (commentedConfig.containsComment(path) && !commentedConfig.getComment(path).equals(this.getComment(path))) {
                this.setComment(path, commentedConfig.getComment(path));
                changed = true;
            }
        }
        if (changed) {
            correctIndexes(section, this.getConfigurationSection(section.getCurrentPath()));
        }
        return changed;
    }
    
    private CommentedConfiguration flagAsFailed() {
        this.creationFailure = true;
        return this;
    }
    
    public static CommentedConfiguration loadConfiguration(final File file) {
        try {
            final FileInputStream stream = new FileInputStream(file);
            return loadConfiguration(new InputStreamReader(stream, StandardCharsets.UTF_8));
        }
        catch (final FileNotFoundException ex) {
            Bukkit.getLogger().warning("File " + file.getName() + " doesn't exist.");
            return new CommentedConfiguration().flagAsFailed();
        }
    }
    
    public static CommentedConfiguration loadConfiguration(final InputStream inputStream) {
        return loadConfiguration(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
    }
    
    public static CommentedConfiguration loadConfiguration(final Reader reader) {
        final CommentedConfiguration config = new CommentedConfiguration();
        try (final BufferedReader bufferedReader = (BufferedReader)((reader instanceof BufferedReader) ? reader : new BufferedReader(reader))) {
            final StringBuilder contents = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contents.append(line).append('\n');
            }
            config.loadFromString(contents.toString());
        }
        catch (final IOException | InvalidConfigurationException ex) {
            config.flagAsFailed();
            ex.printStackTrace();
        }
        return config;
    }
    
    private static boolean isNewSection(final String line) {
        final String trimLine = line.trim();
        return trimLine.contains(": ") || trimLine.endsWith(":");
    }
    
    private static String getSectionPath(final CommentedConfiguration commentedConfig, final String line, final String currentSection) {
        String newSection = line.trim().split(": ")[0];
        if (newSection.endsWith(":")) {
            newSection = newSection.substring(0, newSection.length() - 1);
        }
        if (newSection.startsWith(".")) {
            newSection = newSection.substring(1);
        }
        if (newSection.startsWith("'") && newSection.endsWith("'")) {
            newSection = newSection.substring(1, newSection.length() - 1);
        }
        if (!currentSection.isEmpty() && commentedConfig.contains(currentSection + "." + newSection)) {
            newSection = currentSection + "." + newSection;
        }
        else {
            String parentSection = currentSection;
            while (!parentSection.isEmpty() && !commentedConfig.contains((parentSection = getParentPath(parentSection)) + "." + newSection)) {}
            newSection = (parentSection.trim().isEmpty() ? newSection : (parentSection + "." + newSection));
        }
        return newSection;
    }
    
    private static boolean isComment(final String line) {
        final String trimLine = line.trim();
        return trimLine.startsWith("CRAFTBUKKIT_ARE_FUCKING_IDIOTS");
    }
    
    private static String getParentPath(final String path) {
        return path.contains(".") ? path.substring(0, path.lastIndexOf(46)) : "";
    }
    
    private static void correctIndexes(final ConfigurationSection section, final ConfigurationSection target) {
        final List<Pair<String, Object>> sectionMap = getSectionMap(section);
        final List<Pair<String, Object>> correctOrder = new ArrayList<Pair<String, Object>>();
        for (final Pair<String, Object> entry : sectionMap) {
            correctOrder.add(new Pair<>((entry).key, target.get(entry.key)));
        }
        clearConfiguration(target);
        for (final Pair<String, Object> entry : correctOrder) {
            target.set(entry.key, entry.value);
        }
    }
    
    private static List<Pair<String, Object>> getSectionMap(final ConfigurationSection section) {
        final List<Pair<String, Object>> list = new ArrayList<Pair<String, Object>>();
        for (final String key : section.getKeys(false)) {
            list.add(new Pair<String, Object>(key, section.get(key)));
        }
        return list;
    }
    
    private static void clearConfiguration(final ConfigurationSection section) {
        for (final String key : section.getKeys(false)) {
            section.set(key, null);
        }
    }
    
    private static class Pair<K, V>
    {
        private final K key;
        private final V value;
        
        Pair(final K key, final V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public boolean equals(final Object obj) {
            return obj instanceof Pair && this.key.equals(((Pair)obj).key) && this.value.equals(((Pair)obj).value);
        }
        
        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
    }
}
