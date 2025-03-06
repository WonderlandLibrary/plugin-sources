package com.elevatemc.anticheat.base.check.impl.misc.refill;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.item.type.ItemType;
import com.github.retrooper.packetevents.protocol.item.type.ItemTypes;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientClickWindow;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientCloseWindow;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RefillA extends PacketCheck {

    private long lastMove = -1;
    private int cancelMassMoveBuffer = 0;
    private int prediction = -1;

    public RefillA(PlayerData playerData) {
        super(playerData, "Refill A", "Auto-Refill Pattern", new ViolationHandler(5, 300), Category.MISC, SubCategory.REFILL, 0);
    }

    /*
        I tried to fix this LOL
     */
    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.CLICK_WINDOW) {
            final WrapperPlayClientClickWindow wrapper = new WrapperPlayClientClickWindow(event);

            if (wrapper.getWindowId() == 0
                    && wrapper.getSlot() >= 9
                    && wrapper.getSlot() <= 35
                    && wrapper.getButton() == 0
                    && wrapper.getWindowClickType() == WrapperPlayClientClickWindow.WindowClickType.QUICK_MOVE) {

                com.github.retrooper.packetevents.protocol.item.ItemStack item = wrapper.getCarriedItemStack();
                if (item == null) return;

                ItemType type = item.getType();
                if ((type.equals(ItemTypes.POTION) && (item.getDamageValue() == 16421 || item.getDamageValue() == 16389)) || type.equals(ItemTypes.MUSHROOM_STEW)) { // Checks for a healing item
                    long delay = System.currentTimeMillis() - lastMove;
                    if (delay == 0) {
                        cancelMassMoveBuffer++;
                        if (cancelMassMoveBuffer > 3) {
                            cancelMassMoveBuffer = 0;
                            resetBuffer();
                        }
                    }

                    int slot = wrapper.getSlot();
                    if (slot == prediction) {
                        if (increaseBuffer() > 18.0) {
                            handleViolation(new DetailedPlayerViolation(this, "T " + getBuffer()));
                            multiplyBuffer(.25);
                        }
                    } else {
                        resetBuffer();
                    }
                    prediction = predictNextSlot(playerData.getPlayer().getPlayer().getInventory(), slot);
                    lastMove = System.currentTimeMillis();
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.CLOSE_WINDOW) {
            WrapperPlayClientCloseWindow wrapped = new WrapperPlayClientCloseWindow(event);
            if (wrapped.getWindowId() == 0) {
                cancelMassMoveBuffer = 0;
            }
        }
    }

    public int predictNextSlot(Inventory inventory, int start) {
        int predicted = start + 1;
        boolean found = false;

        while (!found && predicted <= 35) {
            ItemStack item = inventory.getItem(predicted);
            if (item != null) {
                Material type = item.getType();
                if ((type.equals(Material.POTION) && (item.getDurability() == 16421 || item.getDurability() == 16389)) || type.equals(Material.MUSHROOM_SOUP)) { // Checks for a healing item
                    found = true;
                }
            }
            predicted++;
        }

        if (!found) {
            resetBuffer();
            prediction = -1;
        }

        return predicted - 1;
    }
}