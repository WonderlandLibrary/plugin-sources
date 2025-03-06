package com.elevatemc.anticheat.base.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import com.elevatemc.anticheat.util.chat.CC;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

@Getter
public class HoodCommand extends BaseCommand {
    public void sendHoodLine(final CommandSender sender) {
        sender.sendMessage(CC.translate("&8&l------»&r  " + "&c&lHood" + "  §8§l«------"));
    }

    public void sendLineBreak(final Player sender) {
        sender.sendMessage(CC.translate("&8&m--------------------------------"));
    }

    public void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(CC.translate(message));
    }

    public void sendMessage(final Player sender, final String message) {
        sender.sendMessage(CC.translate(message));
    }

    @Override
    public List<String> tabComplete(final CommandIssuer issuer, final String commandLabel, final String[] args, final boolean isAsync) throws IllegalArgumentException {
        return super.tabComplete(issuer, commandLabel, args, isAsync);
    }
}