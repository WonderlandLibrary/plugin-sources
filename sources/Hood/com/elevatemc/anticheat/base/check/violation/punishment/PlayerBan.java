package com.elevatemc.anticheat.base.check.violation.punishment;

import com.elevatemc.anticheat.base.PlayerData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PlayerBan {
    private final PlayerData playerData;

    private final String reason;
}