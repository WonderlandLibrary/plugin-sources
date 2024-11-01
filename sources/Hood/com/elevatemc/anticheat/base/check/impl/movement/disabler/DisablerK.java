package com.elevatemc.anticheat.base.check.impl.movement.disabler;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.check.violation.impl.PlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class DisablerK extends PacketCheck {

    public DisablerK(PlayerData playerData) {
        super(playerData, "Disabler K", "Teleport values in fight.", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.DISABLER, 1);
    }

    /*
        This is a simple check but efficient.
        Of course, it's another way to make sure the player is using a teleport packet abuse disabler.
        We're checking if the player is currently attacking, currently teleporting and sending
        deltaYaw and deltaPitch values that combined are equal to 0.0 (Teleport delta values)
        This is NOT possible.
     */

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.KEEP_ALIVE) {
            if (System.currentTimeMillis() - playerData.lastJoin < 3000L) return;
           int combatTicks = actionTracker.getLastAttack();
           int teleportTicks = teleportTracker.getSinceTeleportTicks();

           float deltaYaw = rotationTracker.getDeltaYaw();
           float deltaPitch = rotationTracker.getDeltaPitch();
           // Attacking and teleporting at the same time?
           boolean invalid = combatTicks <= 1 && teleportTicks <= 1 && (deltaYaw + deltaPitch == 0.0 || deltaYaw == 0 && deltaPitch > 0 || deltaPitch == 0 && deltaYaw > 0);

           if (invalid) {
               if (increaseBuffer() > 2) {
                   handleViolation(new DetailedPlayerViolation(this, "C " + combatTicks + " D 0 P 0"));
                   staffAlert(new PlayerViolation(this));
               }
           }
        }
    }
}
