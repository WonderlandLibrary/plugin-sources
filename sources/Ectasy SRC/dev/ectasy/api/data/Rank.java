package dev.ectasy.api.data;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public enum Rank {
    UNAUTHORIZED(ChatColor.GRAY, ChatColor.WHITE),
    FREE(ChatColor.LIGHT_PURPLE, ChatColor.WHITE),
    STARTER(ChatColor.BLUE, ChatColor.WHITE),
    PREMIUM(ChatColor.GREEN, ChatColor.WHITE),
    ADVANCED(ChatColor.DARK_PURPLE, ChatColor.WHITE),
    ADMIN(ChatColor.YELLOW, ChatColor.WHITE),
    DEVELOPER(ChatColor.GOLD, ChatColor.WHITE);

    private final ChatColor mainColor;
    private final ChatColor secondaryColor;
    Rank(ChatColor mainColor, ChatColor secondaryColor) {
        this.mainColor = mainColor;
        this.secondaryColor = secondaryColor;
    }

    public ChatColor getMainColor() {
        return mainColor;
    }

    public ChatColor getSecondaryColor() {
        return secondaryColor;
    }

}
