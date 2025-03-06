package com.elevatemc.anticheat.base.check.impl.misc.refill;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClientStatus;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RefillG extends PacketCheck {

    /*
        Alright, current time of me making this is 7:36 AM, and my mind is spinning as I've been working on hood
        for the past 7 hours, consistent because refill's always been an issue, I patched most clients but there's
        this one client that has a way of executing refill and casually bypasses all the checks.

        So, here's the checks idea, we will not care for the pattern that the pots are clicked in, but what we will
        look at is these key factors:
        - Players current hotbar.
        - Players' time in the inventory (we will use ticks cause easier)
        - Changes between of the hotbar done for a certain amount of time.

        The way I'll be going after this is not a generic way but I'll just be testing it.
        SO! When the player clicks in their inventory we'll start tracking time in inventory.
        At the same time we will track the amount of items in the hotbar,

        The moment they send a WrapperPlayClientCloseWindow packet we'll check the changes made.
        The amount of items in the hotbar and check the difference from the size of when the inventory was open
        and when it was closed, we'll also be looking at the time the inventory was open for.

        For example, lets say they just ran out of pots in their hotbar, the amount of items in the hotbar are three.
        A sword, pearls and food.
        When they close it they've already refilled 6 pots, so that is the change in hotbar we're looking for,
        and I say again, if that changed was made in lets say less than 10 ticks they're defo cheating.

        I hope I explained this well, I'm exhausted.
     */

    int currentSize = -1, lastSize = -1;
    int ticksInInventory;

    boolean isInventory = false;
    public RefillG(PlayerData playerData) {
        super(playerData, "Refill Type G", "Client Refill", new ViolationHandler(10, 30000L), Category.MISC, SubCategory.REFILL, 1);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            if (isInventory) {
                ++ticksInInventory;
            } else {
                ticksInInventory = 0;
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLIENT_STATUS) {
            WrapperPlayClientClientStatus wrapper = new WrapperPlayClientClientStatus(event);

            if (wrapper.getAction() == WrapperPlayClientClientStatus.Action.OPEN_INVENTORY_ACHIEVEMENT) {
                isInventory = true;
                lastSize = getHotbarSize(playerData.getPlayer());
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            currentSize = getHotbarSize(playerData.getPlayer());

            int difference = currentSize - lastSize;

            if (playerData.getTicksExisted() < 100) return;

            if (ticksInInventory > 0 && ticksInInventory < 10 && difference >= 6 && actionTracker.getLastAttack() < 60) {
                handleViolation(new DetailedPlayerViolation(this, "T " + ticksInInventory + " D " + difference));
                staffAlert(new PlayerViolation(this));
            }
            isInventory = false;
        }
    }


    private int getHotbarSize(Player player) {
        int size = 0;
        ItemStack[] inventory = player.getInventory().getContents();
        for (int i = 0; i < 9; i++) {
            if (inventory[i] != null && inventory[i].getType() != Material.AIR) {
                size++;
            }
        }
        return size;
    }
}
