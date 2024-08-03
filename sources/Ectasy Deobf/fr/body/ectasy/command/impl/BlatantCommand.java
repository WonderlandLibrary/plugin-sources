package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "blatant",
   description = "Enable or disable blatant mode.",
   blatant = false,
   rank = Rank.FREE
)
public class BlatantCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      var4.blatant = !var4.blatant;
      ChatUtil.sendChat(var4.player, "Blatant mode has been " + (var4.blatant ? "&aenabled" : "&cdisabled"));
   }

   public BlatantCommand() {
   }
}
