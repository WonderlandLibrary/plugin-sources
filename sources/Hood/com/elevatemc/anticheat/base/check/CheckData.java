package com.elevatemc.anticheat.base.check;

import com.elevatemc.anticheat.HoodPlugin;
import com.elevatemc.anticheat.base.PlayerData;
import com.elevatemc.anticheat.base.check.type.PacketCheck;
import com.elevatemc.anticheat.base.check.type.PositionCheck;
import com.elevatemc.anticheat.base.check.type.RotationCheck;
import com.elevatemc.anticheat.manager.CheckManager;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CheckData {
    private Set<Check> checks;

    private Set<PacketCheck> packetChecks;

    private Set<PositionCheck> positionChecks;

    private Set<RotationCheck> rotationChecks;

    public void enable(PlayerData playerData) {
        CheckManager checkManager = HoodPlugin.INSTANCE.getCheckManager();
        checks = checkManager.getConstructors().stream()
                .map(clazz -> {
                        try {
                            return (Check) clazz.newInstance(playerData);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                })
                .collect(Collectors.toSet());

        packetChecks = checks.stream()
                .filter(PacketCheck.class::isInstance)
                .map(PacketCheck.class::cast)
                .collect(Collectors.toSet());

        positionChecks = checks.stream()
                .filter(PositionCheck.class::isInstance)
                .map(PositionCheck.class::cast)
                .collect(Collectors.toSet());

        rotationChecks = checks.stream()
                .filter(RotationCheck.class::isInstance)
                .map(RotationCheck.class::cast)
                .collect(Collectors.toSet());
    }

    public <T> T getCheck(Class<T> clazz) {
        return (T) checks.stream()
                .filter(check -> check.getClass() == clazz)
                .findFirst()
                .orElse(null);
    }
}