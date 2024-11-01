package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.util.mcp.PlayerUtil;
import com.elevatemc.anticheat.util.packet.PacketUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class KillAuraD extends PacketCheck {

    Entity target;

    public KillAuraD(PlayerData playerData) {
        super(playerData, "Aura D", "Checks for keepsprint", new ViolationHandler(5, 3000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (PacketUtil.isFlying(event.getPacketType())) {
            if (actionTracker.getLastAttack() < 3) {
                double deltaXZ = positionTracker.getDeltaXZ();
                double acceleration = positionTracker.getAcceleration();

                float baseSpeed = PlayerUtil.getBaseSpeed(playerData, .23F);

                long delay = swingTracker.getDelay();
                boolean sprinting = actionTracker.isSprinting();
                boolean validTarget = target != null && target instanceof Player && !target.hasMetadata("npc");
                boolean validVersion = playerData.getClientVersion().isOlderThan(ClientVersion.V_1_9);

                boolean invalid = acceleration < .0025 && sprinting && deltaXZ > baseSpeed
                        && delay < 200 && validTarget && validVersion && !playerData.getPlayer().getAllowFlight();

                if (invalid) {
                    if (increaseBuffer() > 5) {
                        multiplyBuffer(.35);
                        handleViolation(new DetailedPlayerViolation(this,"A " + acceleration));
                    }
                } else {
                    decreaseBufferBy(.75);
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            Entity entity = SpigotReflectionUtil.getEntityById(wrapper.getEntityId());

            if (entity instanceof Player && wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
                target = entity;
            }
        }
    }
}
