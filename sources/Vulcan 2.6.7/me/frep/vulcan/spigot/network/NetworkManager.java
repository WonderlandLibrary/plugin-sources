package me.frep.vulcan.spigot.network;

import io.github.retrooper.packetevents.event.impl.PlayerEjectEvent;
import io.github.retrooper.packetevents.utils.player.ClientVersion;
import org.bukkit.entity.Player;
import me.frep.vulcan.spigot.config.Config;
import io.github.retrooper.packetevents.event.impl.PostPlayerInjectEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import me.frep.vulcan.spigot.data.PlayerData;
import me.frep.vulcan.spigot.packet.Packet;
import io.github.retrooper.packetevents.packetwrappers.play.in.custompayload.WrappedPacketInCustomPayload;
import me.frep.vulcan.spigot.Vulcan;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.priority.PacketEventPriority;
import io.github.retrooper.packetevents.event.PacketListenerAbstract;

public class NetworkManager extends PacketListenerAbstract
{
    public NetworkManager(final PacketEventPriority priority) {
        super(priority);
        this.filterAll();
        this.addClientSidedPlayFilter((byte) -93, (byte) -96, (byte) -94, (byte) -95, (byte) -100, (byte) -71, (byte) -88, (byte) -103, (byte) -111, (byte) -108, (byte) -68, (byte) -69,(byte)  -105,(byte)  -104,(byte)  -86,(byte)  -87,(byte)  -98, (byte) -85,(byte)  -78,(byte)  -110,(byte)  -92,(byte)  -70,(byte)  -75, (byte) -107, (byte) 28);
        this.addServerSidedPlayFilter((byte)-26,(byte) -13,(byte) -48,(byte) 3,(byte) -17,(byte) -3,(byte) -36,(byte) 23,(byte) -10,(byte) 27,(byte) -11,(byte) -23,(byte) -25,(byte) 29,(byte) 1,(byte) 22,(byte) -38);
    }
    
    @Override
    public void onPacketPlayReceive(final PacketPlayReceiveEvent event) {
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(event.getPlayer());
        if (data != null) {
            if (event.getPacketId() == -103) {
                final WrappedPacketInCustomPayload wrapper = new WrappedPacketInCustomPayload(event.getNMSPacket());
                if (wrapper.getChannelName().toLowerCase().contains("brand")) {
                    Vulcan.INSTANCE.getClientBrandManager().handle(data, wrapper.getData());
                }
            }
            Vulcan.INSTANCE.getPacketExecutor().execute(() -> Vulcan.INSTANCE.getReceivingPacketProcessor().handle(data, new Packet(Packet.Direction.RECEIVE, event.getNMSPacket(), event.getPacketId())));
        }
    }
    
    @Override
    public void onPacketPlaySend(final PacketPlaySendEvent event) {
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(event.getPlayer());
        if (data == null || (event.getPacketId() == -26 && (data.getCombatProcessor().getTrackedPlayer() == null || data.getCombatProcessor().getLastTrackedPlayer() == null))) {
            return;
        }
        Vulcan.INSTANCE.getPacketExecutor().execute(() -> Vulcan.INSTANCE.getSendingPacketProcessor().handle(data, new Packet(Packet.Direction.SEND, event.getNMSPacket(), event.getPacketId())));
    }
    
    @Override
    public void onPostPlayerInject(final PostPlayerInjectEvent event) {
        final Player player = event.getPlayer();
        final boolean npc = player.hasMetadata("NPC") || player.hasMetadata("npc");
        if (!npc) {
            Vulcan.INSTANCE.getPlayerDataManager().add(player);
        }
        final PlayerData data = Vulcan.INSTANCE.getPlayerDataManager().getPlayerData(player);
        if (data == null) {
            return;
        }
        final ClientVersion clientVersion = event.getClientVersion();
        data.setClientVersion(clientVersion);
        if (player.hasPermission("vulcan.alerts") && Config.TOGGLE_ALERTS_ON_JOIN) {
            Vulcan.INSTANCE.getAlertManager().toggleAlerts(player);
        }
        data.getPositionProcessor().onJoin();
        data.getActionProcessor().onJoin();
    }
    
    @Override
    public void onPlayerEject(final PlayerEjectEvent event) {
        Vulcan.INSTANCE.getPlayerDataManager().remove(event.getPlayer());
    }
}
