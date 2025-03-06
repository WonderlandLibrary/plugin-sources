package dev.coldservices.check.impl.velocity;

import ac.artemis.packet.spigot.wrappers.GPacket;
import ac.artemis.packet.wrapper.client.PacketPlayClientFlying;
import ac.artemis.packet.wrapper.client.PacketPlayClientTransaction;
import cc.ghast.packet.wrapper.bukkit.Vector3D;
import cc.ghast.packet.wrapper.packet.play.client.GPacketPlayClientFlying;
import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerEntityVelocity;
import cc.ghast.packet.wrapper.packet.play.server.GPacketPlayServerTransaction;
import dev.coldservices.check.Check;
import dev.coldservices.check.api.annotations.CheckManifest;
import dev.coldservices.check.type.PacketCheck;
import dev.coldservices.data.PlayerData;
import dev.coldservices.exempt.ExemptType;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@CheckManifest(name = "Velocity", type = "A", description = "Detects vertical knockback modifications")
public class VelocityA extends Check implements PacketCheck {

    private boolean receivedTransaction;

    public VelocityA(PlayerData data) {
        super(data);
    }

    @Override
    public void handle(GPacket packet) {
        if(packet instanceof GPacketPlayServerEntityVelocity) {
            GPacketPlayServerEntityVelocity wrapper = (GPacketPlayServerEntityVelocity) packet;

            // Transaction yolluyoruz, normal sartlarda eğer oyuncunun pingi yüksek değilse,
            // Bir sonraki tick geçene kadar Bize geri Transaction yollaması gerekmekte.
            // Eğer yollamazsa ve pingliyse, receivedTransaction booleani ile false flag önlemiş oluyoruz.
            // Bypass çıkabilir o yüzden, badpacket checklerine önem göstermemiz gerekcek
            data.send(new GPacketPlayServerTransaction((byte) 0, data.getConnectionTracker().getTransactionId(), false));
        }

        if(packet instanceof PacketPlayClientTransaction) {
            receivedTransaction = true; // Yuksek pinge sahipleri, false flag attirmamak icin kontrol etmemiz gerek.
        }

        if(packet instanceof PacketPlayClientFlying) {
            boolean exempt = this.isExempt(ExemptType.FLIGHT, ExemptType.BOAT, ExemptType.VEHICLE, ExemptType.WEB,
                    ExemptType.TELEPORTED_RECENTLY, ExemptType.LIQUID, ExemptType.CLIMBABLE,
                    ExemptType.SOUL_SAND, ExemptType.SLIME, ExemptType.UNDER_BLOCK,
                    ExemptType.FALL_VELO);

            if(exempt) return;

            if(data.getVelocityTracker().getTicksSinceVelocity() < 5 && receivedTransaction) {
                double deltaY = data.getPositionTracker().getDeltaY();

                if(deltaY <= .1) {
                    if(this.buffer.increase() > 3) {
                        this.failNoBan("");
                        this.executeSetback(false, false);
                    }
                } else {
                    this.buffer.decrease();
                }
            }

            receivedTransaction = false;
        }
    }
}
