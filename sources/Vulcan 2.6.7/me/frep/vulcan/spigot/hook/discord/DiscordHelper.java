package me.frep.vulcan.spigot.hook.discord;

import java.util.logging.Level;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Color;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.util.ServerUtil;
import java.text.DecimalFormat;
import me.frep.vulcan.spigot.config.Stats;
import me.frep.vulcan.spigot.util.PlayerUtil;
import java.util.Iterator;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Collection;
import java.util.ArrayList;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import me.joshb.discordbotapi.server.DiscordBotAPI;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.AbstractCheck;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.config.Config;
import java.util.List;

public class DiscordHelper
{
    public static String COMMAND_PREFIX;
    public static String ALERTS_CHANNEL_ID;
    public static String PUNISHMENT_CHANNEL_ID;
    public static String ALERTS_FORMAT_COLOR;
    public static String PUNISHMENT_FORMAT_COLOR;
    public static String ALERTS_FORMAT_TITLE;
    public static String PUNISHMENT_FORMAT_TITLE;
    public static String ALERTS_FORMAT_DESCRIPTION;
    public static String PUNISHMENT_FORMAT_DESCRIPTION;
    public static String ALERTS_FORMAT_IMAGE;
    public static String PUNISHMENT_FORMAT_IMAGE;
    public static String ALERTS_FORMAT_FOOTER;
    public static String PUNISHMENT_FORMAT_FOOTER;
    private static List<String> ALERTS_FORMAT_CONTENT;
    private static List<String> PUNISHMENT_FORMAT_CONTENT;
    private static DiscordHelper instance;
    
    public DiscordHelper() {
        (DiscordHelper.instance = this).updateConfig();
    }
    
    public void updateConfig() {
        try {
            DiscordHelper.ALERTS_CHANNEL_ID = Config.getString("hooks.discord.alerts-channel-id");
            DiscordHelper.PUNISHMENT_CHANNEL_ID = Config.getString("hooks.discord.punishment-channel-id");
            DiscordHelper.COMMAND_PREFIX = Config.getString("hooks.discord.command-prefix");
            DiscordHelper.ALERTS_FORMAT_COLOR = Config.getString("hooks.discord.alerts-format.color");
            DiscordHelper.ALERTS_FORMAT_TITLE = Config.getString("hooks.discord.alerts-format.title");
            DiscordHelper.ALERTS_FORMAT_DESCRIPTION = Config.getString("hooks.discord.alerts-format.description");
            DiscordHelper.ALERTS_FORMAT_IMAGE = Config.getString("hooks.discord.alerts-format.image");
            DiscordHelper.ALERTS_FORMAT_CONTENT = Config.getStringList("hooks.discord.alerts-format.content");
            DiscordHelper.ALERTS_FORMAT_FOOTER = Config.getString("hooks.discord.alerts-format.footer");
            DiscordHelper.PUNISHMENT_FORMAT_COLOR = Config.getString("hooks.discord.punishment-format.color");
            DiscordHelper.PUNISHMENT_FORMAT_TITLE = Config.getString("hooks.discord.punishment-format.title");
            DiscordHelper.PUNISHMENT_FORMAT_DESCRIPTION = Config.getString("hooks.discord.punishment-format.description");
            DiscordHelper.PUNISHMENT_FORMAT_IMAGE = Config.getString("hooks.discord.punishment-format.image");
            DiscordHelper.PUNISHMENT_FORMAT_CONTENT = Config.getStringList("hooks.discord.punishment-format.content");
            DiscordHelper.PUNISHMENT_FORMAT_FOOTER = Config.getString("hooks.discord.punishment-format.footer");
        }
        catch (final Exception e) {
            Bukkit.getLogger().severe("Error while loading Vulcan's configuration file!");
            e.printStackTrace();
        }
    }
    
    public void sendDiscordAlertMessage(final AbstractCheck check, final PlayerData playerData, final String info) {
        if (DiscordHelper.ALERTS_CHANNEL_ID.equals("channel_id_here") || !Config.getBoolean("hooks.discord.enable-hook")) {
            return;
        }
        final TextChannel textChannel = DiscordBotAPI.getJDA().getTextChannelById(DiscordHelper.ALERTS_CHANNEL_ID);
        textChannel.sendMessage(this.getAlertMessage(check, playerData, info)).queue();
    }
    
    public void sendDiscordPunishMessage(final AbstractCheck check, final PlayerData playerData) {
        if (DiscordHelper.PUNISHMENT_CHANNEL_ID.equals("channel_id_here") || !Config.getBoolean("hooks.discord.enable-hook")) {
            return;
        }
        final TextChannel textChannel = DiscordBotAPI.getJDA().getTextChannelById(DiscordHelper.PUNISHMENT_CHANNEL_ID);
        textChannel.sendMessage(this.getPunishmentMessage(check, playerData)).queue();
    }
    
    private MessageEmbed getAlertMessage(final AbstractCheck check, final PlayerData playerData, final String info) {
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(this.getColor(true));
        embedBuilder.setTitle(this.formatAlert(DiscordHelper.ALERTS_FORMAT_TITLE, check, playerData, info));
        embedBuilder.setDescription(this.formatAlert(DiscordHelper.ALERTS_FORMAT_DESCRIPTION, check, playerData, info));
        embedBuilder.setThumbnail(this.formatAlert(DiscordHelper.ALERTS_FORMAT_IMAGE, check, playerData, info));
        final List<String> formatContent = new ArrayList<String>(DiscordHelper.ALERTS_FORMAT_CONTENT);
        for (final String content : formatContent) {
            final String[] contentSplit = content.split("\\|");
            if (content.startsWith("break")) {
                final boolean b = Boolean.parseBoolean(contentSplit[1]);
                embedBuilder.addBlankField(b);
            }
            else {
                final String value = this.formatAlert(contentSplit[1], check, playerData, info);
                final boolean b2 = Boolean.parseBoolean(contentSplit[2]);
                embedBuilder.addField(contentSplit[0], value, b2);
            }
        }
        embedBuilder.setTimestamp(new Date().toInstant());
        return embedBuilder.build();
    }
    
    private MessageEmbed getPunishmentMessage(final AbstractCheck check, final PlayerData playerData) {
        final EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(this.getColor(false));
        embedBuilder.setTitle(this.formatPunishment(DiscordHelper.PUNISHMENT_FORMAT_TITLE, check, playerData));
        embedBuilder.setDescription(this.formatPunishment(DiscordHelper.PUNISHMENT_FORMAT_DESCRIPTION, check, playerData));
        embedBuilder.setThumbnail(this.formatPunishment(DiscordHelper.PUNISHMENT_FORMAT_IMAGE, check, playerData));
        final List<String> formatContent = new ArrayList<String>(DiscordHelper.PUNISHMENT_FORMAT_CONTENT);
        for (final String content : formatContent) {
            final String[] contentSplit = content.split("\\|");
            if (content.startsWith("break")) {
                final boolean b = Boolean.parseBoolean(contentSplit[1]);
                embedBuilder.addBlankField(b);
            }
            else {
                final String value = this.formatPunishment(contentSplit[1], check, playerData);
                final boolean b2 = Boolean.parseBoolean(contentSplit[2]);
                embedBuilder.addField(contentSplit[0], value, b2);
            }
        }
        embedBuilder.setTimestamp(new Date().toInstant());
        return embedBuilder.build();
    }
    
    private String formatAlert(final String string, final AbstractCheck check, final PlayerData data, final String info) {
        return string.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%info%", info).replaceAll("%max-vl%", Integer.toString(check.getMaxVl())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%total-punishments%", Integer.toString(Stats.getPunishments())).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%x%", Integer.toString(data.getPlayer().getLocation().getBlockX())).replaceAll("%y%", Integer.toString(data.getPlayer().getLocation().getBlockY())).replaceAll("%z%", Integer.toString(data.getPlayer().getLocation().getBlockZ())).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%tps%", new DecimalFormat("##.##").format(ServerUtil.getTPS())).replaceAll("%ping%", String.valueOf(PlayerUtil.getPing(data.getPlayer()))).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate("*") : "").replaceAll("%vl%", Integer.toString(check.getVl())).replaceAll("%type%", Character.toString(check.getCheckInfo().type()));
    }
    
    private String formatPunishment(final String string, final AbstractCheck check, final PlayerData data) {
        return string.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%total-punishments%", Integer.toString(Stats.getPunishments())).replaceAll("%max-vl%", Integer.toString(check.getMaxVl())).replaceAll("%x%", Integer.toString(data.getPlayer().getLocation().getBlockX())).replaceAll("%y%", Integer.toString(data.getPlayer().getLocation().getBlockY())).replaceAll("%z%", Integer.toString(data.getPlayer().getLocation().getBlockZ())).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%tps%", new DecimalFormat("##.##").format(ServerUtil.getTPS())).replaceAll("%ping%", String.valueOf(PlayerUtil.getPing(data.getPlayer()))).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate("*") : "").replaceAll("%vl%", Integer.toString(check.getVl())).replaceAll("%type%", Character.toString(check.getCheckInfo().type()));
    }
    
    private int getColor(final boolean alerts) {
        final int color = Color.BLACK.asRGB();
        String mode;
        if (alerts) {
            mode = "hooks.discord.alerts-format.color";
        }
        else {
            mode = "hooks.discord.punishment-format.color";
        }
        try {
            if (Config.isColor(mode)) {
                return Config.getColor(mode).asRGB();
            }
            if (Config.isString(mode)) {
                final String colorString = Config.getString(mode);
                try {
                    return java.awt.Color.decode(colorString).getRGB();
                }
                catch (final Exception ignored) {
                    final Color c = (Color)Class.forName("org.bukkit.Color").getField(colorString).get(null);
                    return c.asRGB();
                }
            }
            return Config.getInt(mode);
        }
        catch (final ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            Vulcan.INSTANCE.getPlugin().getLogger().log(Level.SEVERE, "Error while parsing color for discord hook!");
            return color;
        }
    }
    
    public static DiscordHelper getInstance() {
        return DiscordHelper.instance;
    }
}
