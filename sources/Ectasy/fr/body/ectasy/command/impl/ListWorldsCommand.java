package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "listworlds",
   description = "List all the worlds",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {"listworld", "lw"}
)
public class ListWorldsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      Bukkit.getWorlds().forEach(w -> {
         ChatUtil.sendChat(var1.getPlayer(), w.getName());
      });
   }
}
