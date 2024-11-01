package com.elevatemc.anticheat.base.check.impl.movement.scaffold;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.math.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import org.bukkit.Material;

public class ScaffoldA extends PacketCheck {

    public ScaffoldA(PlayerData playerData) {
        super(playerData, "Scaffold A", "Invalid acceleration whilst placing blocks", ViolationHandler.EXPERIMENTAL, Category.MOVEMENT, SubCategory.SCAFFOLD, 2);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT) {

            if (!playerData.getPlayer().getLocation().subtract(0.0, 2.0, 0.0).getBlock().getType().equals(Material.AIR)) return;
            if (!playerData.getPlayer().getItemInHand().getType().isBlock()) return;

            double acceleration = positionTracker.getAcceleration();

            if (MathUtil.isScientificNotation(acceleration) && rotationTracker.getDeltaPitch() > 20.0f) {
                handleViolation(new DetailedPlayerViolation(this, "A " + acceleration + " P " + rotationTracker.getDeltaPitch()));
            }
        }
    }
}
