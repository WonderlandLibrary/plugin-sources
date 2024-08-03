package dev.ectasy.api.commands;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;

import java.util.Arrays;
import java.util.List;

public class AbstractCommand {

    private CommandInfo info;

    public AbstractCommand(){
        if(!this.getClass().isAnnotationPresent(CommandInfo.class))
            throw new RuntimeException("CommandInfo annotation not present in class " + this.getClass().getName());
        this.info = this.getClass().getAnnotation(CommandInfo.class);
    }

    public CommandInfo getInfo(){
        return info;
    }

    public String getName(){
        return this.getInfo().name();
    }

    public String getDescription(){
        return this.getInfo().description();
    }

    public List<String> getAliases(){
        return Arrays.asList(this.getInfo().aliases());
    }

    public Rank getRank(){
        return this.getInfo().rank();
    }

    public boolean isBlatant(){
        return this.getInfo().blatant();
    }
    public boolean isAsync(){
        return this.getInfo().async();
    }

    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args){
        // Override this method in your command classes
    }


}
