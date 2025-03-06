package me.frep.vulcan.spigot.check.impl.combat.aim;

import me.frep.vulcan.spigot.packet.Packet;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.check.api.CheckInfo;
import me.frep.vulcan.spigot.check.AbstractCheck;

@CheckInfo(name = "Aim", type = 'R', complexType = "Divisor X", description = "Subtle aim assist modifications.")
public class AimR extends AbstractCheck
{
    public AimR(final PlayerData data) {
        super(data);
    }
    
    @Override
    public void handle(final Packet packet) {
        if (!packet.isRotation() || this.hitTicks() < 3) {}
    }
}
