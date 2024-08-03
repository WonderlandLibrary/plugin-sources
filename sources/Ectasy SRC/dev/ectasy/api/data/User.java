package dev.ectasy.api.data;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User {

    private Rank rank = Rank.UNAUTHORIZED;
    private ChatColor mainColor = this.getRank().getMainColor();
    private ChatColor secondaryColor = this.getRank().getSecondaryColor();
    private final UUID uuid;
    private boolean blatant = false;

    public User(UUID uuid){
        this.uuid = uuid;
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public Rank getRank(){
        return rank;
    }

    public void setRank(Rank rank){
        this.setRank(rank, false);
    }

    public ChatColor getMainColor() {
        return mainColor;
    }

    public void setMainColor(ChatColor mainColor) {
        this.mainColor = mainColor;
    }

    public ChatColor getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(ChatColor secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public void setRank(Rank rank, boolean ignoreColors) {
        this.rank = rank;
        if (ignoreColors)
            return;

        this.setMainColor(rank.getMainColor());
        this.setSecondaryColor(rank.getSecondaryColor());
    }

    public void sendMessage(String message){
        if(this.getPlayer() == null)
            return;
        this.getPlayer().sendMessage(this.getMainColor() + "Ectasy" +  "§8 » " + this.getSecondaryColor() + ChatColor.translateAlternateColorCodes('&', message));
    }

    public boolean isBlatant() {
        return blatant;
    }

    public void setBlatant(boolean blatant) {
        this.blatant = blatant;
    }

}
