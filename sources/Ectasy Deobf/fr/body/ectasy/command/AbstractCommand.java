package fr.body.ectasy.command;

import fr.body.ectasy.user.Rank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import net.md5.bungee.Ectasy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@Getter
@Setter
public abstract class AbstractCommand {
   public List<String> aliases;
   private Boolean blatant;
   public List<String> unusedList;
   private String name = null;
   private String description = null;
   public Rank requiredRank;

   public void removeItemFromUnusedList(String var1) {
      this.unusedList.remove(var1);
   }

   public AbstractCommand() {
      this.blatant = null;
      this.aliases = new ArrayList<>();
      this.unusedList = new ArrayList<>();
      this.name = this.getClass().getAnnotation(CommandInfo.class).name();
      this.description = this.getClass().getAnnotation(CommandInfo.class).description();
      this.blatant = this.getClass().getAnnotation(CommandInfo.class).blatant();
      this.requiredRank = this.getClass().getAnnotation(CommandInfo.class).rank();

      for(String var4 : this.getClass().getAnnotation(CommandInfo.class).aliases()) {
         this.aliases.add(var4.toLowerCase());
      }

      try {
         if (Arrays.stream(this.getClass().getInterfaces()).anyMatch(cmd -> cmd.getName().equalsIgnoreCase(Listener.class.getName()))) {
            Bukkit.getServer().getPluginManager().registerEvents((Listener)this, Ectasy.parentPlugin);
         }
      } catch (NullPointerException var5) {
         Ectasy.commandManager.getCommands().remove(this);
         var5.printStackTrace();
         Ectasy.parentPlugin.getLogger().severe("Command " + this.getName() + " is missing some CommandInfo.");
      }
   }

   public abstract void execute(AsyncPlayerChatEvent var1, String var2, String[] var3);

   /**
    * Apparently Lombok doesn't care about this LOL
    * @return this.blatant
    */
   public boolean isBlatant() {
      return blatant;
   }
}
