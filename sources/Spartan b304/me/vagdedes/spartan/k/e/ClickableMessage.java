package me.vagdedes.spartan.k.e;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ClickableMessage
{
    public ClickableMessage() {
        super();
    }
    
    public static boolean a(final Player player, final String s, final String s2, final String s3) {
        return a(player, s, s2, s3, true);
    }
    
    public static boolean b(final Player player, final String s, final String s2, final String s3) {
        return b(player, s, s2, s3, true);
    }
    
    public static boolean a(final Player player, final String s, final String s2, final String s3, final boolean b) {
        try {
            final TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(s));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, s3));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(s2).create()));
            player.spigot().sendMessage((BaseComponent)textComponent);
            return true;
        }
        catch (Exception ex) {
            if (b) {
                player.sendMessage(s);
            }
            return false;
        }
    }
    
    public static boolean b(final Player player, final String s, final String s2, final String s3, final boolean b) {
        try {
            final TextComponent textComponent = new TextComponent(TextComponent.fromLegacyText(s));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, s3));
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(s2).create()));
            player.spigot().sendMessage((BaseComponent)textComponent);
            return true;
        }
        catch (Exception ex) {
            if (b) {
                player.sendMessage(s);
            }
            return false;
        }
    }
}
