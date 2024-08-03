package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "seed",
   description = "Get the world seed",
   blatant = false,
   rank = Rank.FREE
)
public class WorldSeedCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ChatUtil.sendChat(var1.getPlayer(), "Seed : " + var1.getPlayer().getWorld().getSeed());
   }
}
