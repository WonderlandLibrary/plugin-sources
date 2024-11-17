package me.frep.vulcan.spigot.brand;

import java.util.Iterator;
import me.clip.placeholderapi.PlaceholderAPI;
import me.frep.vulcan.spigot.util.ServerUtil;
import me.frep.vulcan.spigot.util.PlayerUtil;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.util.ColorUtil;
import me.frep.vulcan.spigot.config.Config;
import org.apache.commons.lang.StringUtils;
import me.frep.vulcan.spigot.data.PlayerData;

public class ClientBrandManager
{
    public void handle(final PlayerData data, final byte[] bytes) {
        if (data.isHasSentClientBrand() || System.currentTimeMillis() - data.getLastClientBrandAlert() < 10000L || bytes.length <= 0) {
            return;
        }
        try {
            final String clientBrand = StringUtils.capitalize(new String(bytes, "UTF-8").substring(1)).replace(" (Velocity)", "");
            data.setClientBrand(clientBrand);
            if (!data.getPlayer().hasPermission("vulcan.bypass.client-brand.blacklist")) {
                for (final String brand : Config.BLOCKED_CLIENT_BRANDS) {
                    if (clientBrand.toLowerCase().contains(brand.toLowerCase())) {
                        data.getPlayer().sendMessage(ColorUtil.translate(Config.UNALLOWED_CLIENT_BRAND.replaceAll("%client-brand%", clientBrand)));
                        Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> data.getPlayer().kickPlayer(ColorUtil.translate(Config.UNALLOWED_CLIENT_BRAND.replaceAll("%client-brand%", clientBrand))));
                    }
                }
            }
            if (clientBrand.length() > 55 || clientBrand.contains("\n")) {
                data.getPlayer().sendMessage(ColorUtil.translate("&cInvalid client brand."));
                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> data.getPlayer().kickPlayer(ColorUtil.translate("&cInvalid client brand.")));
                return;
            }
            if (Config.CLIENT_BRAND_WHITELIST_ENABLED) {
                if (!data.getPlayer().hasPermission("vulcan.bypass.client-brand.whitelist")) {
                    boolean whitelisted = false;
                    for (final String string : Config.WHITELISTED_CLIENT_BRANDS) {
                        if (clientBrand.toLowerCase().contains(string.toLowerCase())) {
                            whitelisted = true;
                            break;
                        }
                    }
                    if (!whitelisted) {
                        Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> {
                            data.getPlayer().sendMessage(ColorUtil.translate(Config.UNALLOWED_CLIENT_BRAND.replaceAll("%client-brand%", clientBrand)));
                            data.getPlayer().kickPlayer(ColorUtil.translate(Config.UNALLOWED_CLIENT_BRAND.replaceAll("%client-brand%", clientBrand)));
                            return;
                        });
                    }
                }
            }
            Label_0586: {
                if (Config.CLIENT_BRAND_ALERTS) {
                    for (final String ignored : Config.CLIENT_BRAND_IGNORE_LIST) {
                        if (clientBrand.toLowerCase().contains(ignored.toLowerCase())) {
                            break Label_0586;
                        }
                    }
                    if (System.currentTimeMillis() - data.getLastClientBrandAlert() >= 10000L) {
                        if (Config.CLIENT_BRAND_CONSOLE) {
                            ServerUtil.log(ColorUtil.translate(Config.CLIENT_BRAND_CONSOLE_MESSAGE.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", clientBrand)));
                        }
                        if (!data.getPlayer().hasPermission("vulcan.bypass.client-brand-alerts")) {
                            if (Vulcan.INSTANCE.isPlaceHolderAPI()) {
                                String message = Config.CLIENT_BRAND_ALERT_MESSAGE.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand());
                                final String finalMessage;
                                message = (finalMessage = PlaceholderAPI.setPlaceholders(data.getPlayer(), message));
                                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Vulcan.INSTANCE.getAlertManager().sendMessage(finalMessage));
                            }
                            else {
                                Bukkit.getScheduler().runTask(Vulcan.INSTANCE.getPlugin(), () -> Vulcan.INSTANCE.getAlertManager().sendMessage(Config.CLIENT_BRAND_ALERT_MESSAGE.replaceAll("%player%", data.getPlayer().getName()).replaceAll("%client-version%", PlayerUtil.getClientVersionToString(data.getPlayer())).replaceAll("%client-brand%", data.getClientBrand())));
                            }
                            data.setLastClientBrandAlert(System.currentTimeMillis());
                        }
                    }
                }
            }
            data.setHasSentClientBrand(true);
        }
        catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
