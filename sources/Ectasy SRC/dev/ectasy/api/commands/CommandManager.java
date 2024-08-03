package dev.ectasy.api.commands;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import dev.ectasy.impl.commands.free.*;
import dev.ectasy.impl.commands.free.gamemode.*;
import dev.ectasy.impl.commands.premium.*;
import dev.ectasy.impl.commands.starter.*;
import dev.ectasy.impl.commands.starter.weather.*;
import dev.ectasy.impl.commands.unauthorized.CommandLogin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandManager implements PacketListener {
    private final Set<AbstractCommand> commands = new HashSet<>();

    public CommandManager(){
        this.addCommands(
                new CommandLogin(),
                new CommandLogout(),
                new CommandOp(),
                new CommandDeop(),
                new CommandHelp(),
                new CommandChat(),
                new CommandGamemodeCreative(),
                new CommandGamemodeSurvival(),
                new CommandGamemodeSpectator(),
                new CommandBroadcast(),
                new CommandStop(),
                new CommandBomb(),
                new CommandWand(),
                new CommandBlatant(),
                new CommandServerChat(),
                new CommandFloodConsole(),
                new CommandClearLogs(),
                new CommandConsole(),
                new CommandSpam(),
                new CommandSeed(),

                // starter
                new CommandEnable(),
                new CommandDisable(),
                new CommandPlugins(),
                new CommandHeal(),
                new CommandFeed(),
                new CommandBrick(),
                new CommandCredits(),
                new CommandDemo(),
                new CommandRocket(),
                new CommandDay(),
                new CommandNight(),
                new CommandSun(),
                new CommandRain(),
                new CommandThunder(),
                new CommandKill(),
                new CommandKick(),
                new CommandBan(),

                // premium
                new CommandSudo(),
                new CommandCoords(),
                new CommandLockChat(),
                new CommandLockConsole(),
                new CommandLockCommands(),
                new CommandAlone(),
                new CommandMotd(),
                new CommandFly(),
                new CommandFireballRain()
        );
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if(event.getPacketType() == PacketType.Play.Client.CHAT_MESSAGE){
            WrapperPlayClientChatMessage wrapper = new WrapperPlayClientChatMessage(event);
            String message = wrapper.getMessage();
            User user = Ectasy.getInstance().getUser((Player) event.getPlayer());
            AbstractCommand command = this.getCommand(message.split(" ")[0]);
            if(message.startsWith("/"))
                return;
            event.setCancelled(true);

            if(command == null){
                if(user.getRank() == Rank.UNAUTHORIZED)
                    return;
                user.sendMessage("The command " + user.getMainColor() + message.split(" ")[0] + user.getSecondaryColor() + " does not exist.");
                return;
            }

            if(user.getRank().ordinal() < command.getRank().ordinal()){
                if(user.getRank() == Rank.UNAUTHORIZED){
                    event.setCancelled(false);
                    return;
                }

                user.sendMessage("You do not have permission to use this command.");
                return;
            }
            if(!user.isBlatant() && command.isBlatant()){
                user.sendMessage("You need to enable blatant mode to use this command.");
                return;
            }
            String[] args = message.split(" ");
            args = Arrays.copyOfRange(args, 1, args.length);
            if(command.isAsync())
                command.execute(user, event, wrapper, args);
            else {
                String[] finalArgs = args;
                Ectasy.getInstance().getPlugin().getServer().getScheduler().runTask(Ectasy.getInstance().getPlugin(), () -> command.execute(user, event, wrapper, finalArgs));
            }
        }


    }

    public Set<AbstractCommand> getCommands(){
        return commands;
    }

    public AbstractCommand getCommand(String nameOrAlias) {
        return this.getCommands()
                .stream()
                .filter(command ->
                        command.getName().equalsIgnoreCase(nameOrAlias) ||
                                command.getAliases()
                                        .stream()
                                        .map(String::toLowerCase)
                                        .collect(Collectors.toList())
                                        .contains(nameOrAlias.toLowerCase())
                )
                .findFirst()
                .orElse(null);
    }

    private void addCommands(AbstractCommand... commands){
        this.commands.addAll(Arrays.asList(commands));
        for(AbstractCommand cmd : Arrays.stream(commands).filter(cmd -> cmd.getInfo().listener()).collect(Collectors.toList())){
            System.out.println("Registering listener for " + cmd.getName());
            Ectasy.getInstance().getPlugin().getServer().getPluginManager().registerEvents((Listener) cmd, Ectasy.getInstance().getPlugin());
        }
    }

}
