package fr.body.ectasy.util;

import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import net.md5.bungee.Ectasy;
import org.bukkit.entity.Player;

public class ChatUtil {
   public static void sendChat(Player var0, String var1) {
      var0.sendMessage(getColorByPlayer(var0) + "Ectasy§f > " + var1.replace("&", "§"));
   }

   public static String getColorByPlayer(Player var0) {
      User var1 = Ectasy.users.get(var0.getName());
      if (var1.rank == Rank.ADMIN) {
         return "§5";
      } else if (var1.rank == Rank.PREMIUM) {
         return "§6";
      } else if (var1.rank == Rank.DEVELOPER) {
         return "§a";
      } else {
         return var1.rank == Rank.LITE ? "§9" : "§4";
      }
   }

   public static String getColorByRank(Rank var0) {
      if (var0 == Rank.ADMIN) {
         return "§5";
      } else if (var0 == Rank.PREMIUM) {
         return "§6";
      } else if (var0 == Rank.DEVELOPER) {
         return "§a";
      } else {
         return var0 == Rank.LITE ? "§9" : "§4";
      }
   }
}
