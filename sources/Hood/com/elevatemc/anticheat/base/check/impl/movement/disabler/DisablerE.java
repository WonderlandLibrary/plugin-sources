package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientSteerVehicle;

public class DisablerE extends PacketCheck {
    public DisablerE(PlayerData playerData) {
        super(playerData, "Disabler E", "Invalid SteerVehicle packets.", new ViolationHandler(2, 3000L), Category.MOVEMENT, SubCategory.DISABLER, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.STEER_VEHICLE) {
            WrapperPlayClientSteerVehicle steerVehicle = new WrapperPlayClientSteerVehicle(event);

            float forward = Math.abs(steerVehicle.getForward());
            float side = Math.abs(steerVehicle.getSideways());

            boolean invalid = forward > .98F || side > .98F;

            if (invalid) {
                handleViolation(new DetailedPlayerViolation(this,"F " + forward + " S " + side));
            }
        }
    }
}