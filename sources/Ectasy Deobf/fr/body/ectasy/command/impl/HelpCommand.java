package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.md5.bungee.Ectasy;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * If this works, then it does, otherwise idfk what to do since even chatgpt failed fixing it :skull:
 */
@CommandInfo(name = "help", description = "Show all commands.", blatant = false, rank = Rank.FREE)
public class HelpCommand extends AbstractCommand {

   @Override
   public void execute(AsyncPlayerChatEvent asyncPlayerChatEvent, String string, String[] stringArray) {
      User user = Ectasy.users.get(asyncPlayerChatEvent.getPlayer().getName());
      int n = stringArray.length == 0 ? 1 : Integer.parseInt(stringArray[0]);

      List<AbstractCommand> list = Ectasy.commandManager.getCommands().stream()
              .filter(command -> user.rank.index >= command.requiredRank.index)
              .collect(Collectors.toList());

      double d = (double) list.size() / 10.0;
      int n2 = (int) Math.ceil(d);

      if (n <= 0 || n > n2) {
         ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "Please, use help 1-" + n2);
         return;
      }

      List<List<AbstractCommand>> choppedList = chopped(list, 10);
      List<AbstractCommand> list2 = choppedList.get(n - 1);

      ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "****** Ectasy ******");
      for (AbstractCommand abstractCommand : list2) {
         ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(),
                 (abstractCommand.isBlatant() ? "§e⚠ §f" : "") + abstractCommand.getName() + " : " + abstractCommand.getDescription());
      }
      ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "*** " + n + "/" + n2 + " ***");
      ChatUtil.sendChat(asyncPlayerChatEvent.getPlayer(), "Total count: " + list.size());
   }

   static List<List<AbstractCommand>> chopped(List<AbstractCommand> list, int n) {
      List<List<AbstractCommand>> arrayList = new ArrayList<>();
      int n2 = list.size();
      for (int i = 0; i < n2; i += n) {
         arrayList.add(new ArrayList<>(list.subList(i, Math.min(n2, i + n))));
      }
      return arrayList;
   }
}
