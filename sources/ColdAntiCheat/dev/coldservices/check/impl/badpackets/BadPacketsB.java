package dev.coldservices.check.impl.badpackets;

import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.RotationCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;
import dev.coldservices.update.RotationUpdate;

@CheckManifest(name = "BadPackets", type = "B", description = "Detects if the pitch is over or below 90")
public class BadPacketsB extends Check implements RotationCheck {

    public BadPacketsB(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(RotationUpdate update) {
        boolean exempt = this.isExempt(ExemptType.JOIN, ExemptType.BOAT, ExemptType.TELEPORT, ExemptType.TELEPORTED_RECENTLY);

        if(exempt) return;

        float pitch = Math.abs(update.getPitch());

        if(pitch > 90 && this.buffer.increase() > 2) {
            this.failNoBan("over 90 pitch");
            this.executeSetback(false, true);
        } else {
            this.buffer.decrease();
        }
    }
}
