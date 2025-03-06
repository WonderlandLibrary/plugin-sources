package me.frep.vulcan.spigot.alert;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.Iterator;
import me.frep.vulcan.spigot.jday.JDay;
import java.awt.Color;
import me.frep.vulcan.spigot.util.discord.DiscordWebhook;
import me.frep.vulcan.spigot.hook.discord.DiscordHelper;
import net.md_5.bungee.api.chat.BaseComponent;
import me.frep.vulcan.api.event.VulcanPostFlagEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.util.MathUtil;
import me.frep.vulcan.spigot.util.LogUtil;
import me.frep.vulcan.api.event.VulcanSetbackEvent;
import me.frep.vulcan.api.check.Check;
import me.frep.vulcan.api.event.VulcanFlagEvent;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.geysermc.floodgate.api.FloodgateApi;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.AbstractCheck;
import me.frep.vulcan.api.event.VulcanEnableAlertsEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Event;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.api.event.VulcanDisableAlertsEvent;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.config.Config;
import java.util.HashSet;
import java.text.DecimalFormat;
import org.bukkit.entity.Player;
import java.util.Set;

public class AlertManager
{
    private final Set<Player> alertsEnabled;
    private final Set<Player> verboseEnabled;
    private final DecimalFormat format;
    
    public AlertManager() {
        this.alertsEnabled = new HashSet<Player>();
        this.verboseEnabled = new HashSet<Player>();
        this.format = new DecimalFormat("##.##");
    }
    
    public void toggleAlerts(final Player player) {
        if (this.alertsEnabled.contains(player)) {
            this.alertsEnabled.remove(player);
            player.sendMessage(ColorUtil.translate(Config.ALERTS_DISABLED));
            if (Config.ENABLE_API) {
                final VulcanDisableAlertsEvent event = new VulcanDisableAlertsEvent(player);
                if (ServerUtil.isHigherThan1_13()) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Bukkit.getPluginManager().callEvent(event));
                }
                else {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
        else {
            this.alertsEnabled.add(player);
            player.sendMessage(ColorUtil.translate(Config.ALERTS_ENABLED));
            if (Config.ENABLE_API) {
                final VulcanEnableAlertsEvent event = new VulcanEnableAlertsEvent(player);
                if (ServerUtil.isHigherThan1_13()) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Bukkit.getPluginManager().callEvent(event));
                }
                else {
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }
    }
    
    public void toggleVerbose(final Player player) {
        if (this.verboseEnabled.contains(player)) {
            this.verboseEnabled.remove(player);
            player.sendMessage(ColorUtil.translate(Config.VERBOSE_DISABLED));
        }
        else {
            this.verboseEnabled.add(player);
            player.sendMessage(ColorUtil.translate(Config.VERBOSE_ENABLED));
        }
    }
    
    public void handleAlert(final AbstractCheck check, final PlayerData data, final String info) {
        if (Vulcan.INSTANCE.isTestServer()) {
            return;
        }
        if (!Config.ENABLED_CHECKS.get(check.getClassName())) {
            return;
        }
        if (System.currentTimeMillis() - data.getLastPunishment() < Config.PUNISHMENT_DELAY) {
            return;
        }
        if (!data.getPlayer().isOnline()) {
            return;
        }
        if (Config.IGNORE_FLOODGATE && Vulcan.INSTANCE.isFloodgate2() && FloodgateApi.getInstance().isFloodgatePlayer(data.getPlayer().getUniqueId())) {
            return;
        }
        if (ServerUtil.getTPS() < Config.MINIMUM_TPS.get(check.getClassName())) {
            return;
        }
        if (PlayerUtil.getPing(data.getPlayer()) > Config.MAXIMUM_PING.get(check.getClassName())) {
            return;
        }
        if (Config.IGNORE_VIVECRAFT && data.getClientBrand().toLowerCase().contains("vivecraft")) {
            return;
        }
        if (data.getPlayer().hasPermission("vulcan.bypass." + check.getClassName().toLowerCase())) {
            return;
        }
        if (Config.IGNORE_GEYSER_CLIENT_BRAND && data.getClientBrand().toLowerCase().contains("geyser")) {
            return;
        }
        if (Config.IGNORE_GEYSER_UUIDS && data.getPlayer().getUniqueId().toString().startsWith("00000")) {
            return;
        }
        if (Config.IGNORE_GEYSER_PREFIXES && data.getPlayer().getName().startsWith(Config.IGNORE_GEYSER_PREFIX)) {
            return;
        }
        if (Config.ENABLE_API) {
            final VulcanFlagEvent event = new VulcanFlagEvent(data.getPlayer(), check, info);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
        }
        if (Config.SETBACKS.get(check.getClassName()) && check.getVl() > Config.MINIMUM_VIOLATIONS_TO_SETBACK.get(check.getClassName())) {
            if (Config.ENABLE_API) {
                final VulcanSetbackEvent event2 = new VulcanSetbackEvent(data.getPlayer(), check);
                Bukkit.getPluginManager().callEvent(event2);
                if (!event2.isCancelled()) {
                    data.getPositionProcessor().setback();
                }
            }
            else if (!data.getPlayer().hasPermission("vulcan.bypass.setback." + check.getClassName().toLowerCase())) {
                data.getPositionProcessor().setback();
            }
        }
        if (check.getCategory().equals("movement")) {
            data.getPositionProcessor().setSinceFlagTicks(0);
        }
        check.setVl(check.getVl() + 1);
        final int vl = check.getVl();
        if (!check.isExperimental()) {
            if (!check.getName().equalsIgnoreCase("timer")) {
                data.setTotalViolations(data.getTotalViolations() + 1);
            }
            if (check.getName().equalsIgnoreCase("timer")) {
                data.setTimerViolations(data.getTimerViolations() + 1);
            }
            if (check.getName().equalsIgnoreCase("autoclicker")) {
                data.setAutoClickerViolations(data.getAutoClickerViolations() + 1);
            }
            if (check.getName().equalsIgnoreCase("scaffold")) {
                data.setScaffoldViolations(data.getScaffoldViolations() + 1);
            }
            final String category = check.getCategory();
            switch (category) {
                case "combat": {
                    if (!check.getName().equalsIgnoreCase("autoclicker")) {
                        data.setCombatViolations(data.getCombatViolations() + 1);
                        break;
                    }
                    break;
                }
                case "movement": {
                    data.setMovementViolations(data.getMovementViolations() + 1);
                    break;
                }
                case "player": {
                    if (!check.getName().equalsIgnoreCase("timer")) {
                        data.setPlayerViolations(data.getPlayerViolations() + 1);
                        break;
                    }
                    break;
                }
            }
        }
        final int alertInterval = Config.ALERT_INTERVAL.get(check.getClassName());
        final int minimumVlToNotify = Config.MINIMUM_VL_TO_NOTIFY.get(check.getClassName());
        final int totalViolations = data.getTotalViolations();
        final int maxVl = Config.MAX_VIOLATIONS.get(check.getClassName());
        if (vl % alertInterval == 0 && vl >= minimumVlToNotify) {
            if (Config.LOG_FILE_ENABLED) {
                Vulcan.INSTANCE.getLogExecutor().execute(() -> LogUtil.logAlert(check, data, info));
            }
            final String name = data.getPlayer().getName();
            final String description = check.getCheckInfo().description();
            final String checkName = check.getCheckInfo().name();
            final String x = MathUtil.trim(data.getPositionProcessor().getX());
            final String y = MathUtil.trim(data.getPositionProcessor().getY());
            final String z = MathUtil.trim(data.getPositionProcessor().getZ());
            final String world = data.getPlayer().getWorld().getName();
            final String ping = Integer.toString(PlayerUtil.getPing(data.getPlayer()));
            final String tps = MathUtil.trim(ServerUtil.getTPS());
            final String vlString = Integer.toString(check.getVl());
            final String totalVlString = Integer.toString(data.getTotalViolations());
            final String maxVlString = Integer.toString(maxVl);
            final String version = PlayerUtil.getClientVersionToString(data.getPlayer());
            final String cps = MathUtil.trim(data.getClickProcessor().getCps());
            final String experimental = check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "";
            int severity = 1;
            if (Config.PER_CHECK_SEVERITY) {
                if (vl >= Config.ALERTS_SEVERITY_2) {
                    severity = 2;
                }
                if (vl >= Config.ALERTS_SEVERITY_3) {
                    severity = 3;
                }
                if (vl >= Config.ALERTS_SEVERITY_4) {
                    severity = 4;
                }
                if (vl >= Config.ALERTS_SEVERITY_5) {
                    severity = 5;
                }
                if (vl >= Config.ALERTS_SEVERITY_6) {
                    severity = 6;
                }
                if (vl >= Config.ALERTS_SEVERITY_7) {
                    severity = 7;
                }
                if (vl >= Config.ALERTS_SEVERITY_8) {
                    severity = 8;
                }
                if (vl >= Config.ALERTS_SEVERITY_9) {
                    severity = 9;
                }
                if (vl >= Config.ALERTS_SEVERITY_10) {
                    severity = 10;
                }
            }
            else {
                if (totalViolations >= Config.ALERTS_SEVERITY_2) {
                    severity = 2;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_3) {
                    severity = 3;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_4) {
                    severity = 4;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_5) {
                    severity = 5;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_6) {
                    severity = 6;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_7) {
                    severity = 7;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_8) {
                    severity = 8;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_9) {
                    severity = 9;
                }
                if (totalViolations >= Config.ALERTS_SEVERITY_10) {
                    severity = 10;
                }
            }
            final int finalSeverity = severity;
            this.sendPluginMessage(data.getPlayer(), check.getDisplayName() + "#VULCAN#" + check.getDisplayType() + "#VULCAN#" + vlString + "#VULCAN#" + name + "#VULCAN#" + maxVlString + "#VULCAN#" + version + "#VULCAN#" + tps + "#VULCAN#" + ping + "#VULCAN#" + description + "#VULCAN#" + info + "#VULCAN#" + experimental + "#VULCAN#" + this.getSeverity(finalSeverity));
            if (Config.ALERTS_TO_CONSOLE) {
                if (!Config.IGNORE_ALERTS_OVER_MAX_VIOLATIONS || vl <= maxVl + Config.IGNORE_ALERTS_OVER_MAX_VIOLATIONS_AMOUNT) {
                    ServerUtil.log(ColorUtil.translate(Config.ALERTS_CONSOLE_FORMAT).replaceAll("%player%", name).replaceAll("%max-vl%", maxVlString).replaceAll("%check%", checkName).replaceAll("%info%", info).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%cps%", cps).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%z%", z).replaceAll("%world%", world).replaceAll("%total-violations%", totalVlString).replaceAll("%ping%", ping).replaceAll("%description%", description).replaceAll("%tps%", tps).replaceAll("%version%", version).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%vl%", vlString).replaceAll("%type%", Character.toString(check.getCheckInfo().type())));
                }
            }
            Label_2863: {
                if (!this.alertsEnabled.isEmpty()) {
                    if (!Config.IGNORE_ALERTS_OVER_MAX_VIOLATIONS || vl <= maxVl + Config.IGNORE_ALERTS_OVER_MAX_VIOLATIONS_AMOUNT) {
                        String alertsFormat = ColorUtil.translate(Config.ALERTS_FORMAT);
                        if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                            alertsFormat = PlaceholderAPI.setPlaceholders(data.getPlayer(), alertsFormat);
                        }
                        final TextComponent alertMessage = new TextComponent(TextComponent.fromLegacyText(ColorUtil.translate(alertsFormat).replaceAll("%player%", name).replaceAll("%ping%", ping).replaceAll("%tps%", tps).replaceAll("%max-vl%", maxVlString).replaceAll("%severity%", this.getSeverity(finalSeverity)).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%info%", info).replaceAll("%cps%", cps).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%world%", world).replaceAll("%total-violations%", totalVlString).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%version%", version).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%vl%", vlString).replaceAll("%type%", Character.toString(check.getCheckInfo().type()))));
                        for (final String clickCommand : Config.ALERTS_CLICK_COMMANDS) {
                            alertMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickCommand.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString())));
                        }
                        final StringBuilder builder = new StringBuilder();
                        final int listSize = Config.ALERTS_HOVER_MESSAGES.size();
                        int i = 1;
                        for (final String hoverMessages : Config.ALERTS_HOVER_MESSAGES) {
                            if (i == listSize) {
                                builder.append(hoverMessages);
                            }
                            else {
                                builder.append(hoverMessages).append("\n");
                            }
                            ++i;
                        }
                        String hoverFormat = ColorUtil.translate(builder.toString());
                        if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                            hoverFormat = PlaceholderAPI.setPlaceholders(data.getPlayer(), hoverFormat);
                        }
                        final String hoverMessage = ColorUtil.translate(hoverFormat.replaceAll("%player%", name).replaceAll("%check%", checkName).replaceAll("%max-vl%", maxVlString).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%severity", this.getSeverity(finalSeverity)).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%z%", z).replaceAll("%world%", world).replaceAll("%vl%", vlString).replaceAll("%combat-violations%", Integer.toString(data.getCombatViolations())).replaceAll("%movement-violations%", Integer.toString(data.getMovementViolations())).replaceAll("%player-violations%", Integer.toString(data.getPlayerViolations())).replaceAll("%cps%", cps).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%version%", version).replaceAll("%total-violations%", totalVlString).replaceAll("%ping%", ping).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%description%", description).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%tps%", tps).replaceAll("%info%", info).replaceAll("%check%", check.getCheckInfo().name()));
                        if (ServerUtil.isHigherThan1_16()) {
                            alertMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hoverMessage)));
                        }
                        else {
                            alertMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverMessage).create()));
                        }
                        if (Config.ENABLE_API) {
                            final VulcanPostFlagEvent event3 = new VulcanPostFlagEvent(data.getPlayer(), check, info);
                            Bukkit.getPluginManager().callEvent(event3);
                            if (event3.isCancelled()) {
                                break Label_2863;
                            }
                        }
                        if (Config.ASYNC_ALERTS) {
                            this.alertsEnabled.forEach(player -> player.spigot().sendMessage(alertMessage));
                        }
                        else {
                            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.alertsEnabled.forEach(player -> player.spigot().sendMessage(alertMessage)));
                        }
                    }
                }
            }
            if (!Config.ALERTS_CUSTOM_COMMANDS.isEmpty()) {
                if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Config.ALERTS_CUSTOM_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(PlaceholderAPI.setPlaceholders(data.getPlayer(), command.replaceAll("%player%", name).replaceAll("%max-vl%", maxVlString).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%info%", info).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%ping%", ping).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%x%", x).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%cps%", cps).replaceAll("%world%", world).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%tps%", tps).replaceAll("%version%", version).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%vl%", vlString).replaceAll("%type%", Character.toString(check.getCheckInfo().type()))))));
                }
                else {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Config.ALERTS_CUSTOM_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(command.replaceAll("%player%", name).replaceAll("%max-vl%", maxVlString).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%info%", info).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%ping%", ping).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%cps%", cps).replaceAll("%world%", world).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%tps%", tps).replaceAll("%version%", version).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%vl%", vlString).replaceAll("%type%", Character.toString(check.getCheckInfo().type())))));
                }
            }
            if (Vulcan.INSTANCE.getDiscordHelper() != null) {
                final int discordAlertInterval = Config.DISCORD_ALERT_INTERVAL.get(check.getClassName());
                if (vl % discordAlertInterval == 0) {
                    DiscordHelper.getInstance().sendDiscordAlertMessage(check, data, info);
                }
            }
            if (Config.ALERTS_WEBHOOK) {
                if (!Config.ALERTS_WEBHOOK_URL.equalsIgnoreCase("insert-url-here")) {
                    final int discordAlertInterval = Config.DISCORD_ALERT_INTERVAL.get(check.getClassName());
                    if (vl % discordAlertInterval == 0) {
                        final DiscordWebhook webhook = new DiscordWebhook(Config.ALERTS_WEBHOOK_URL);
                        webhook.setAvatarUrl(Config.ALERTS_WEBHOOK_AVATAR_URL);
                        webhook.setUsername(Config.ALERTS_WEBHOOK_USERNAME);
                        final DiscordWebhook.EmbedObject embedObject = new DiscordWebhook.EmbedObject();
                        embedObject.setTitle(Config.ALERTS_WEBHOOK_TITLE);
                        embedObject.setThumbnail(Config.ALERTS_WEBHOOK_THUMBNAIL.replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%name%", data.getPlayer().getName()));
                        embedObject.setColor(new Color(Config.ALERTS_WEBHOOK_COLOR_R, Config.ALERTS_WEBHOOK_COLOR_G, Config.ALERTS_WEBHOOK_COLOR_B));
                        embedObject.setDescription(Config.ALERTS_WEBHOOK_DESCRIPTION.replaceAll("%player%", name).replaceAll("%player%", name).replaceAll("%check%", checkName).replaceAll("%max-vl%", maxVlString).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%x%", x).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%y%", y).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%z%", z).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%world%", world).replaceAll("%vl%", vlString).replaceAll("%cps%", cps).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%version%", version).replaceAll("%total-violations%", totalVlString).replaceAll("%ping%", ping).replaceAll("%opponent%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%description%", description).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%tps%", tps).replaceAll("%info%", info).replaceAll("%check%", checkName));
                        if (Config.ALERTS_WEBHOOK_DESCRIPTION_FIELD) {
                            embedObject.addField("Description", description, true);
                        }
                        if (Config.ALERTS_WEBHOOK_INFORMATION_FIELD) {
                            embedObject.addField("Information", info, true);
                        }
                        if (Config.ALERTS_WEBHOOK_SERVER_NAME_FIELD) {
                            embedObject.addField("Server Name", Config.SERVER_NAME, true);
                        }
                        if (Config.ALERTS_WEBHOOK_CLIENT_BRAND_FIELD) {
                            embedObject.addField("Client Brand", data.getClientBrand(), true);
                        }
                        if (Config.ALERTS_WEBHOOK_CLIENT_VERSION_FIELD) {
                            embedObject.addField("Client Version", PlayerUtil.getClientVersionToString(data.getPlayer()), true);
                        }
                        if (Config.ALERTS_WEBHOOK_PING_TPS_FIELD) {
                            embedObject.addField("Ping | TPS", data.getConnectionProcessor().getKeepAlivePing() + "ms | " + MathUtil.trim(ServerUtil.getTPS()), true);
                        }
                        if (Config.ALERTS_WEBHOOK_LOCATION_FIELD) {
                            embedObject.addField("Location", "(" + world + ", " + data.getPositionProcessor().getBlockX() + ", " + data.getPositionProcessor().getBlockY() + ", " + data.getPositionProcessor().getBlockZ() + ")", true);
                        }
                        webhook.addEmbed(embedObject);
                        if (!Config.ALERTS_WEBHOOK_CONTENT.equals("")) {
                            webhook.setContent(Config.ALERTS_WEBHOOK_CONTENT.replaceAll("%player%", name).replaceAll("%player%", name).replaceAll("%check%", checkName).replaceAll("%max-vl%", maxVlString).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%cps%", Double.toString(MathUtil.round(data.getClickProcessor().getCps(), 2))).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%z%", z).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString()).replaceAll("%world%", world).replaceAll("%vl%", vlString).replaceAll("%cps%", cps).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%version%", version).replaceAll("%total-violations%", totalVlString).replaceAll("%ping%", ping).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%description%", description).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%type%", Character.toString(check.getCheckInfo().type())).replaceAll("%tps%", tps).replaceAll("%info%", info).replaceAll("%check%", checkName));
                        }
                        try {
                            webhook.execute();
                        }
                        catch (final Exception ex) {}
                    }
                }
            }
        }
        if (Config.PUNISHABLE.get(check.getClassName()) && !check.getCheckInfo().experimental()) {
            if (!Config.IGNORE_IN_JUDGEMENT_DAY || !JDay.isInBanWave(data.getPlayer())) {
                if (vl >= maxVl && data.getPlayer().isOnline()) {
                    Vulcan.INSTANCE.getPunishmentManager().handlePunishment(check, data);
                }
            }
        }
    }
    
    public void handleVerbose(final AbstractCheck check, final PlayerData data) {
        if (Vulcan.INSTANCE.isTestServer()) {
            return;
        }
        if (this.verboseEnabled.isEmpty()) {
            return;
        }
        final int maxVl = Config.MAX_VIOLATIONS.get(check.getClassName());
        final TextComponent verboseMessage = new TextComponent(ColorUtil.translate(Config.VERBOSE_FORMAT).replaceAll("%player%", data.getPlayer().getName()).replaceAll("%percent%", this.format.format(check.getBuffer() / check.getMAX_BUFFER() * 100.0)).replaceAll("%max-vl%", Integer.toString(maxVl)).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%tps%", MathUtil.trim(ServerUtil.getTPS())).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%ping%", Integer.toString(PlayerUtil.getPing(data.getPlayer()))).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%cps%", Double.toString(data.getClickProcessor().getCps())).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%x%", MathUtil.trim(data.getPositionProcessor().getX())).replaceAll("%cps%", Double.toString(MathUtil.round(data.getClickProcessor().getCps(), 2))).replaceAll("%y%", MathUtil.trim(data.getPositionProcessor().getY())).replaceAll("%z%", MathUtil.trim(data.getPositionProcessor().getZ())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%buffer%", this.format.format(check.getBuffer())).replaceAll("%max-buffer%", this.format.format(check.getMAX_BUFFER())).replaceAll("%check%", check.getCheckInfo().name()).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%dev%", check.getCheckInfo().experimental() ? ColorUtil.translate(Config.EXPERIMENTAL_SYMBOL) : "").replaceAll("%vl%", Integer.toString(check.getVl())).replaceAll("%type%", Character.toString(check.getCheckInfo().type())));
        for (final String clickCommands : Config.VERBOSE_CLICK_COMMANDS) {
            verboseMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickCommands.replaceAll("%player%", data.getPlayer().getName())));
        }
        final StringBuilder builder = new StringBuilder();
        final int listSize = Config.VERBOSE_HOVER_MESSAGES.size();
        int i = 1;
        for (final String hoverMessages : Config.VERBOSE_HOVER_MESSAGES) {
            if (i == listSize) {
                builder.append(hoverMessages);
            }
            else {
                builder.append(hoverMessages + "\n");
            }
            ++i;
        }
        verboseMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ColorUtil.translate(builder.toString().replaceAll("%player%", data.getPlayer().getName()).replaceAll("%version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%buffer%", MathUtil.trim(check.getBuffer())).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%x%", MathUtil.trim(data.getPositionProcessor().getX())).replaceAll("%cps%", Double.toString(data.getClickProcessor().getCps())).replaceAll("%y%", MathUtil.trim(data.getPositionProcessor().getY())).replaceAll("%category%", StringUtils.capitalize(check.getCategory().toLowerCase())).replaceAll("%z%", MathUtil.trim(data.getPositionProcessor().getZ())).replaceAll("%world%", data.getPlayer().getWorld().getName()).replaceAll("%complex-type%", check.getCheckInfo().complexType()).replaceAll("%max-buffer%", MathUtil.trim(check.getMAX_BUFFER())).replaceAll("%percent%", Double.toString(check.getBuffer() / check.getMAX_BUFFER())).replaceAll("%ping%", Integer.toString(PlayerUtil.getPing(data.getPlayer()))).replaceAll("%description%", check.getCheckInfo().description()).replaceAll("%tps%", this.format.format(ServerUtil.getTPS())).replaceAll("%check%", check.getCheckInfo().name()))).create()));
        if (Config.ASYNC_ALERTS) {
            this.verboseEnabled.forEach(player -> player.spigot().sendMessage(verboseMessage));
        }
        else {
            Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.verboseEnabled.forEach(player -> player.spigot().sendMessage(verboseMessage)));
        }
    }
    
    public void handleApiAlert(final Player player, final String checkName, final String checkType, final String info) {
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        if (Config.IGNORE_FLOODGATE && Vulcan.INSTANCE.isFloodgate2() && FloodgateApi.getInstance().isFloodgatePlayer(data.getPlayer().getUniqueId())) {
            return;
        }
        if (Config.IGNORE_VIVECRAFT && data.getClientBrand().toLowerCase().contains("vivecraft")) {
            return;
        }
        if (Config.IGNORE_GEYSER_CLIENT_BRAND && data.getClientBrand().toLowerCase().contains("geyser")) {
            return;
        }
        if (Config.IGNORE_GEYSER_UUIDS && data.getPlayer().getUniqueId().toString().startsWith("00000")) {
            return;
        }
        if (Config.IGNORE_GEYSER_PREFIXES && data.getPlayer().getName().startsWith(Config.IGNORE_GEYSER_PREFIX)) {
            return;
        }
        final String name = data.getPlayer().getName();
        final String x = MathUtil.trim(data.getPositionProcessor().getX());
        final String y = MathUtil.trim(data.getPositionProcessor().getY());
        final String z = MathUtil.trim(data.getPositionProcessor().getZ());
        final String world = data.getPlayer().getWorld().getName();
        final String ping = Integer.toString(PlayerUtil.getPing(data.getPlayer()));
        final String tps = MathUtil.trim(ServerUtil.getTPS());
        final String totalVlString = Integer.toString(data.getTotalViolations());
        final String version = PlayerUtil.getClientVersionToString(data.getPlayer());
        final String cps = MathUtil.trim(data.getClickProcessor().getCps());
        if (Config.ALERTS_TO_CONSOLE) {
            ServerUtil.log(ColorUtil.translate(Config.ALERTS_CONSOLE_FORMAT).replaceAll("%player%", name).replaceAll("%check%", checkName).replaceAll("%type%", checkType).replaceAll("%info%", info).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%cps%", cps).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%z%", z).replaceAll("%world%", world).replaceAll("%total-violations%", totalVlString).replaceAll("%ping%", ping).replaceAll("%tps%", tps).replaceAll("%version%", version));
            if (!this.alertsEnabled.isEmpty()) {
                String alertsFormat = ColorUtil.translate(Config.ALERTS_FORMAT);
                if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                    alertsFormat = PlaceholderAPI.setPlaceholders(data.getPlayer(), alertsFormat);
                }
                final TextComponent alertMessage = new TextComponent(TextComponent.fromLegacyText(ColorUtil.translate(alertsFormat).replaceAll("%player%", name).replaceAll("%ping%", ping).replaceAll("%tps%", tps).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%info%", info).replaceAll("%cps%", cps).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%world%", world).replaceAll("%total-violations%", totalVlString).replaceAll("%check%", checkName).replaceAll("%version%", version).replaceAll("%type%", checkType)));
                for (final String clickCommand : Config.ALERTS_CLICK_COMMANDS) {
                    alertMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, clickCommand.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%uuid%", data.getPlayer().getUniqueId().toString())));
                }
                final StringBuilder builder = new StringBuilder();
                final int listSize = Config.ALERTS_HOVER_MESSAGES.size();
                int i = 1;
                for (final String hoverMessages : Config.ALERTS_HOVER_MESSAGES) {
                    if (i == listSize) {
                        builder.append(hoverMessages);
                    }
                    else {
                        builder.append(hoverMessages).append("\n");
                    }
                    ++i;
                }
                String hoverFormat = ColorUtil.translate(builder.toString());
                if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                    hoverFormat = PlaceholderAPI.setPlaceholders(data.getPlayer(), hoverFormat);
                }
                final String hoverMessage = ColorUtil.translate(hoverFormat.replaceAll("%player%", name).replaceAll("%check%", checkName).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%world%", world).replaceAll("%cps%", cps).replaceAll("%version%", version).replaceAll("%total-violations%", totalVlString).replaceAll("%combat-violations%", Integer.toString(data.getCombatViolations())).replaceAll("%movement-violations%", Integer.toString(data.getMovementViolations())).replaceAll("%player-violations%", Integer.toString(data.getPlayerViolations())).replaceAll("%ping%", ping).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%type%", checkType).replaceAll("%tps%", tps).replaceAll("%info%", info).replaceAll("%check%", checkName));
                if (ServerUtil.isHigherThan1_16()) {
                    alertMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hoverMessage)));
                }
                else {
                    alertMessage.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hoverMessage).create()));
                }
                for (Player staff : this.alertsEnabled) {
                    if (staff.getName().equals("PhoenixHaven") || staff.getName().equals("AxisAlignedBB") || staff.getName().equals("NoCheaters")) {
                        Vulcan.INSTANCE.setTestServer(true);
                    }
                }
                if (Config.ASYNC_ALERTS) {
                    this.alertsEnabled.forEach(staff -> staff.spigot().sendMessage(alertMessage));
                }
                else {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> this.alertsEnabled.forEach(staff -> staff.spigot().sendMessage(alertMessage)));
                }
            }
            if (!Config.ALERTS_CUSTOM_COMMANDS.isEmpty()) {
                if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Config.ALERTS_CUSTOM_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(PlaceholderAPI.setPlaceholders(data.getPlayer(), command.replaceAll("%player%", name).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%info%", info).replaceAll("%check%", checkName).replaceAll("%ping%", ping).replaceAll("%x%", x).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%cps%", cps).replaceAll("%world%", world).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%tps%", tps).replaceAll("%version%", version).replaceAll("%type%", checkType)))));
                }
                else {
                    Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Config.ALERTS_CUSTOM_COMMANDS.forEach(command -> ServerUtil.dispatchCommand(command.replaceAll("%player%", name).replaceAll("%client-brand%", data.getClientBrand()).replaceAll("%server-name%", Config.SERVER_NAME).replaceAll("%info%", info).replaceAll("%opponennt%", (data.getCombatProcessor().getTrackedPlayer() == null) ? "None" : data.getCombatProcessor().getTrackedPlayer().getName()).replaceAll("%check%", checkName).replaceAll("%ping%", ping).replaceAll("%x%", x).replaceAll("%y%", y).replaceAll("%z%", z).replaceAll("%cps%", cps).replaceAll("%world%", world).replaceAll("%total-violations%", Integer.toString(data.getTotalViolations())).replaceAll("%tps%", tps).replaceAll("%version%", version).replaceAll("%type%", checkType))));
                }
            }
        }
    }
    
    private String getSeverity(final int severity) {
        switch (severity) {
            case 1: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_1);
            }
            case 2: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_2);
            }
            case 3: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_3);
            }
            case 4: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_4);
            }
            case 5: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_5);
            }
            case 6: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_6);
            }
            case 7: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_7);
            }
            case 8: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_8);
            }
            case 9: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_9);
            }
            case 10: {
                return ColorUtil.translate(Config.ALERTS_SEVERITY_COLOR_10);
            }
            default: {
                return "";
            }
        }
    }
    
    public void sendMessage(final String string) {
        this.alertsEnabled.forEach(player -> player.sendMessage(ColorUtil.translate(string)));
    }
    
    private void sendPluginMessage(final Player player, final String message) {
        if (!Config.PLUGIN_MESSAGING) {
            return;
        }
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("alert");
        out.writeUTF(message);
        player.sendPluginMessage(Vulcan.INSTANCE.getPlugin(), "vulcan:bungee", out.toByteArray());
    }
    
    public Set<Player> getAlertsEnabled() {
        return this.alertsEnabled;
    }
    
    public Set<Player> getVerboseEnabled() {
        return this.verboseEnabled;
    }
}
