package com.elevatemc.anticheat.base.check.impl.fight.killaura;

import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.violation.category.Category;
import com.elevatemc.anticheat.base.check.violation.category.SubCategory;
import com.elevatemc.anticheat.base.check.violation.handler.ViolationHandler;
import com.elevatemc.anticheat.base.check.violation.impl.DetailedPlayerViolation;
import com.elevatemc.anticheat.base.tracker.impl.PredictionTracker;
import com.elevatemc.anticheat.util.anticheat.BotUtils;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
public class KillAuraA extends PacketCheck {

    int lastEntityId = -1, hits;
    public KillAuraA(PlayerData playerData) {
        super(playerData, "Aura A", "Multi-Aura", new ViolationHandler(1, 3000L), Category.COMBAT, SubCategory.KILL_AURA, 0);
    }

    @Override
    public void handle(final PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            Entity entity = SpigotReflectionUtil.getEntityById(wrapper.getEntityId());

            if (wrapper.getAction() != WrapperPlayClientInteractEntity.InteractAction.ATTACK || !(entity instanceof Player)) {
                return;
            }

            int id = wrapper.getEntityId();

            if (id != lastEntityId) {
                if (++hits > 1) {
                    handleViolation(new DetailedPlayerViolation(this,"H " + hits + " ID " + id + " L " + lastEntityId));
                    if (!predictionTracker.hasBot) BotUtils.spawnBotEntity(playerData, PredictionTracker.BotTypes.WATCHDOG);
                }
            }

            lastEntityId = id;
        } else if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType())) {
            hits = 0;
        }
    }
}