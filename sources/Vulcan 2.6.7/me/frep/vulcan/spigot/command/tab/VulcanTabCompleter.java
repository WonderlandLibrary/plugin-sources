package me.frep.vulcan.spigot.command.tab;

import javax.annotation.Nullable;
import me.frep.vulcan.spigot.config.Config;
import java.util.Collection;
import org.bukkit.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.jetbrains.annotations.NotNull;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class VulcanTabCompleter implements TabCompleter
{
    @Nullable
    public List<String> onTabComplete(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String alias, final String[] args) {
        if (args.length == 1) {
            final List<String> arguments = new ArrayList<String>();
            arguments.add("help");
            arguments.add("menu");
            arguments.add("gui");
            arguments.add("profile");
            arguments.add("ban");
            arguments.add("reload");
            arguments.add("disablecheck");
            arguments.add("violations");
            arguments.add("cps");
            arguments.add("checks");
            arguments.add("kb");
            arguments.add("reset");
            arguments.add("knockback");
            arguments.add("connection");
            arguments.add("freeze");
            arguments.add("rotate");
            arguments.add("shuffle");
            arguments.add("top");
            return (List)StringUtil.copyPartialMatches(args[0], arguments, new ArrayList());
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("disablecheck")) {
            return (List)StringUtil.copyPartialMatches(args[0], Config.ENABLED_CHECKS.keySet(), new ArrayList());
        }
        return null;
    }
}
