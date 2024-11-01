package com.elevatemc.anticheat.base.check.impl.fight.range;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.util.Vector3f;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Optional;

public class RangeB extends PacketCheck {

    private final double HITBOX = 0.4 / 0.995;
    public RangeB(PlayerData playerData) {
        super(playerData, "Range B", "Invalid hitbox attack range", new ViolationHandler(2, 3000L), Category.COMBAT, SubCategory.REACH, 0);
    }

    @Override
    public void handle(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            final WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            final Entity entity = SpigotReflectionUtil.getEntityById(wrapper.getEntityId());
            if (entity instanceof Player) {
                final Optional<Vector3f> vec3 = wrapper.getTarget();
                if (vec3.isPresent()) {
                    final Vector3f hitbox = vec3.get();
                    if (Math.abs(hitbox.getX()) > HITBOX || Math.abs(hitbox.getZ()) > HITBOX || Math.abs(hitbox.getY()) > 1.91) {
                        handleViolation(new DetailedPlayerViolation(this,"X " + Math.abs(hitbox.getX()) + " Z " + Math.abs(hitbox.getZ()) + " Y " + Math.abs(hitbox.getY())));
                    }
                }
            }
        }
    }
}
