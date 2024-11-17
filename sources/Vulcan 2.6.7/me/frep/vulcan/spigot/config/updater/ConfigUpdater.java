package me.frep.vulcan.spigot.config.updater;

import java.util.Set;
import java.util.HashMap;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import java.util.Iterator;
import org.bukkit.configuration.ConfigurationSection;
import java.io.IOException;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;
import org.yaml.snakeyaml.Yaml;
import java.util.Collection;
import java.util.ArrayList;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import org.bukkit.configuration.file.YamlConfiguration;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.File;
import org.bukkit.plugin.Plugin;

public final class ConfigUpdater
{
    public static void update(final Plugin plugin, final String resourceName, final File toUpdate, final List<String> ignoredSections) throws IOException {
        final BufferedReader newReader = new BufferedReader(new InputStreamReader(plugin.getResource(resourceName), StandardCharsets.UTF_8));
        final List<String> newLines = newReader.lines().collect(Collectors.toList());
        newReader.close();
        final FileConfiguration oldConfig = YamlConfiguration.loadConfiguration(toUpdate);
        final FileConfiguration newConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(plugin.getResource(resourceName), StandardCharsets.UTF_8));
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(toUpdate), StandardCharsets.UTF_8));
        final List<String> ignoredSectionsArrayList = new ArrayList<String>(ignoredSections);
        ignoredSectionsArrayList.removeIf(ignoredSection -> !newConfig.isConfigurationSection(ignoredSection));
        final Yaml yaml = new Yaml();
        final Map<String, String> comments = parseComments(newLines, ignoredSectionsArrayList, oldConfig, yaml);
        write(newConfig, oldConfig, comments, ignoredSectionsArrayList, writer, yaml);
    }
    
    private static void write(final FileConfiguration newConfig, final FileConfiguration oldConfig, final Map<String, String> comments, final List<String> ignoredSections, final BufferedWriter writer, final Yaml yaml) throws IOException {
    Label_0012:
        for (final String key : newConfig.getKeys(true)) {
            final String[] keys = key.split("\\.");
            final String actualKey = keys[keys.length - 1];
            final String comment = comments.remove(key);
            final StringBuilder prefixBuilder = new StringBuilder();
            final int indents = keys.length - 1;
            appendPrefixSpaces(prefixBuilder, indents);
            final String prefixSpaces = prefixBuilder.toString();
            if (comment != null) {
                writer.write(comment);
            }
            for (final String ignoredSection : ignoredSections) {
                if (key.startsWith(ignoredSection)) {
                    continue Label_0012;
                }
            }
            final Object newObj = newConfig.get(key);
            final Object oldObj = oldConfig.get(key);
            if (newObj instanceof ConfigurationSection && oldObj instanceof ConfigurationSection) {
                writeSection(writer, actualKey, prefixSpaces, (ConfigurationSection)oldObj);
            }
            else if (newObj instanceof ConfigurationSection) {
                writeSection(writer, actualKey, prefixSpaces, (ConfigurationSection)newObj);
            }
            else if (oldObj != null) {
                write(oldObj, actualKey, prefixSpaces, yaml, writer);
            }
            else {
                write(newObj, actualKey, prefixSpaces, yaml, writer);
            }
        }
        final String danglingComments = comments.get(null);
        if (danglingComments != null) {
            writer.write(danglingComments);
        }
        writer.close();
    }
    
    private static void write(Object obj, final String actualKey, final String prefixSpaces, final Yaml yaml, final BufferedWriter writer) throws IOException {
        if (obj instanceof ConfigurationSerializable) {
            writer.write(prefixSpaces + actualKey + ": " + yaml.dump(((ConfigurationSerializable)obj).serialize()));
        }
        else if (obj instanceof String || obj instanceof Character) {
            if (obj instanceof String) {
                final String s = (String)obj;
                obj = s.replace("\n", "\\n");
            }
            writer.write(prefixSpaces + actualKey + ": " + yaml.dump(obj));
        }
        else if (obj instanceof List) {
            writeList((List)obj, actualKey, prefixSpaces, yaml, writer);
        }
        else {
            writer.write(prefixSpaces + actualKey + ": " + yaml.dump(obj));
        }
    }
    
    private static void writeSection(final BufferedWriter writer, final String actualKey, final String prefixSpaces, final ConfigurationSection section) throws IOException {
        if (section.getKeys(false).isEmpty()) {
            writer.write(prefixSpaces + actualKey + ": {}");
        }
        else {
            writer.write(prefixSpaces + actualKey + ":");
        }
        writer.write("\n");
    }
    
    private static void writeList(final List list, final String actualKey, final String prefixSpaces, final Yaml yaml, final BufferedWriter writer) throws IOException {
        writer.write(getListAsString(list, actualKey, prefixSpaces, yaml));
    }
    
    private static String getListAsString(final List list, final String actualKey, final String prefixSpaces, final Yaml yaml) {
        final StringBuilder builder = new StringBuilder(prefixSpaces).append(actualKey).append(":");
        if (list.isEmpty()) {
            builder.append(" []\n");
            return builder.toString();
        }
        builder.append("\n");
        for (int i = 0; i < list.size(); ++i) {
            final Object o = list.get(i);
            if (o instanceof String) {
                final String s = String.valueOf(o);
                builder.append(prefixSpaces).append("  - \"").append(s).append("\"");
            }
            else if (o instanceof Character) {
                builder.append(prefixSpaces).append("  - '").append(o).append("'");
            }
            else if (o instanceof List) {
                builder.append(prefixSpaces).append("  - ").append(yaml.dump(o));
            }
            else {
                builder.append(prefixSpaces).append("  - ").append(o);
            }
            if (i != list.size()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
    
    private static Map<String, String> parseComments(final List<String> lines, final List<String> ignoredSections, final FileConfiguration oldConfig, final Yaml yaml) {
        final Map<String, String> comments = new HashMap<String, String>();
        final StringBuilder builder = new StringBuilder();
        final StringBuilder keyBuilder = new StringBuilder();
        int lastLineIndentCount = 0;
    Label_0038:
        for (final String line : lines) {
            if (line != null && line.trim().startsWith("-")) {
                continue;
            }
            if (line == null || line.trim().equals("") || line.trim().startsWith("#")) {
                builder.append(line).append("\n");
            }
            else {
                lastLineIndentCount = setFullKey(keyBuilder, line, lastLineIndentCount);
                for (final String ignoredSection : ignoredSections) {
                    if (keyBuilder.toString().equals(ignoredSection)) {
                        final Object value = oldConfig.get(keyBuilder.toString());
                        if (value instanceof ConfigurationSection) {
                            appendSection(builder, (ConfigurationSection)value, new StringBuilder(getPrefixSpaces(lastLineIndentCount)), yaml);
                            continue Label_0038;
                        }
                        continue Label_0038;
                    }
                }
                if (keyBuilder.length() <= 0) {
                    continue;
                }
                comments.put(keyBuilder.toString(), builder.toString());
                builder.setLength(0);
            }
        }
        if (builder.length() > 0) {
            comments.put(null, builder.toString());
        }
        return comments;
    }
    
    private static void appendSection(final StringBuilder builder, final ConfigurationSection section, final StringBuilder prefixSpaces, final Yaml yaml) {
        builder.append((CharSequence)prefixSpaces).append(getKeyFromFullKey(section.getCurrentPath())).append(":");
        final Set<String> keys = section.getKeys(false);
        if (keys.isEmpty()) {
            builder.append(" {}\n");
            return;
        }
        builder.append("\n");
        prefixSpaces.append("  ");
        for (final String key : keys) {
            final Object value = section.get(key);
            final String actualKey = getKeyFromFullKey(key);
            if (value instanceof ConfigurationSection) {
                appendSection(builder, (ConfigurationSection)value, prefixSpaces, yaml);
                prefixSpaces.setLength(prefixSpaces.length() - 2);
            }
            else if (value instanceof List) {
                builder.append(getListAsString((List)value, actualKey, prefixSpaces.toString(), yaml));
            }
            else {
                builder.append(prefixSpaces.toString()).append(actualKey).append(": ").append(yaml.dump(value));
            }
        }
    }
    
    private static int countIndents(final String s) {
        int spaces = 0;
        for (final char c : s.toCharArray()) {
            if (c != ' ') {
                break;
            }
            ++spaces;
        }
        return spaces / 2;
    }
    
    private static void removeLastKey(final StringBuilder keyBuilder) {
        String temp = keyBuilder.toString();
        final String[] keys = temp.split("\\.");
        if (keys.length == 1) {
            keyBuilder.setLength(0);
            return;
        }
        temp = temp.substring(0, temp.length() - keys[keys.length - 1].length() - 1);
        keyBuilder.setLength(temp.length());
    }
    
    private static String getKeyFromFullKey(final String fullKey) {
        final String[] keys = fullKey.split("\\.");
        return keys[keys.length - 1];
    }
    
    private static int setFullKey(final StringBuilder keyBuilder, final String configLine, final int lastLineIndentCount) {
        final int currentIndents = countIndents(configLine);
        final String key = configLine.trim().split(":")[0];
        if (keyBuilder.length() == 0) {
            keyBuilder.append(key);
        }
        else if (currentIndents == lastLineIndentCount) {
            removeLastKey(keyBuilder);
            if (keyBuilder.length() > 0) {
                keyBuilder.append(".");
            }
            keyBuilder.append(key);
        }
        else if (currentIndents > lastLineIndentCount) {
            keyBuilder.append(".").append(key);
        }
        else {
            for (int difference = lastLineIndentCount - currentIndents, i = 0; i < difference + 1; ++i) {
                removeLastKey(keyBuilder);
            }
            if (keyBuilder.length() > 0) {
                keyBuilder.append(".");
            }
            keyBuilder.append(key);
        }
        return currentIndents;
    }
    
    private static String getPrefixSpaces(final int indents) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indents; ++i) {
            builder.append("  ");
        }
        return builder.toString();
    }
    
    private static void appendPrefixSpaces(final StringBuilder builder, final int indents) {
        builder.append(getPrefixSpaces(indents));
    }
    
    private ConfigUpdater() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
