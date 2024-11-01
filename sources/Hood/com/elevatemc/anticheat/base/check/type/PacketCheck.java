package com.elevatemc.anticheat.base.check.type;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.Check;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.event.PacketSendEvent;

public abstract class PacketCheck extends Check {
    public PacketCheck(PlayerData playerData, String name, String desc, ViolationHandler violationHandler, Category category, SubCategory subCategory, int falseProbability) {
        super(playerData, name, desc, violationHandler, category, subCategory, falseProbability);
    }

    public abstract void handle(PacketReceiveEvent event);

    public void handle(PacketSendEvent event) {};
}