package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.Ectasy;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CommandInfo(
        name = "help",
        description = "View help page",
        rank = Rank.FREE
)
public class CommandHelp extends AbstractCommand {

    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){

        List<AbstractCommand> commands = Ectasy.getInstance().getCommandManager().getCommands().stream().map(cmd -> {
        if(cmd.getRank().ordinal() > user.getRank().ordinal())
            return null;
        return cmd;
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        commands.sort(Comparator.comparing(AbstractCommand::getName));
        List<List<AbstractCommand>> pages = new ArrayList<>();
        for(int i = 0; i < commands.size(); i += 5){
            pages.add(commands.subList(i, Math.min(i + 5, commands.size())));
        }
        int page = 0;
        if(args.length > 0){
            try{
                page = Integer.parseInt(args[0]) - 1;
            }catch(NumberFormatException e){
                user.sendMessage("Invalid page number.");
                return;
            }
        }

        if(page < 0 || page >= pages.size()){
            user.sendMessage("Invalid page number.");
            return;
        }
        user.getPlayer().sendMessage("");

        for(AbstractCommand cmd : pages.get(page)){
            user.sendMessage(user.getMainColor() + cmd.getName() + user.getSecondaryColor() + " - " + cmd.getDescription());
        }
        user.sendMessage("(Page " + user.getMainColor() + (page + 1) + user.getSecondaryColor() + " out of " + user.getMainColor() + pages.size() + user.getSecondaryColor() + ")");



    }

}
