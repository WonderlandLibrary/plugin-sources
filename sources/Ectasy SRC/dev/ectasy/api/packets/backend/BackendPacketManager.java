package dev.ectasy.api.packets.backend;

import dev.ectasy.Ectasy;
import dev.ectasy.impl.packets.backend.IdentifyPacket;
import org.bukkit.scheduler.BukkitTask;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;

public class BackendPacketManager extends WebSocketClient {

    private BukkitTask heartbeatTask;

    public BackendPacketManager(URI url){
        super(url);
    }

    public static void open(){
        if(Ectasy.getInstance().getBackendPacketManager() != null)
            return;

         new Thread(() -> {
             try{
                new BackendPacketManager(new URI("ws://localhost:3001")).connect();
             }catch (Exception ignored){
                 try{
                     Thread.sleep(5000L);
                 }catch (Exception ignored2){

                 }
                 open();
             }
         }).start();
    }

    public void send(BackendPacket packet){
        JSONObject obj = new JSONObject();
        obj.put("op", packet.getId());
        obj.put("d", packet.write());
        send(obj.toString());
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake){
        Ectasy.getInstance().setBackendPacketManager(this);
        send(new IdentifyPacket());
    }

    @Override
    public void onClose(int code, String reason, boolean remote){
        if(Ectasy.getInstance().isExiting())
            return;

        if(heartbeatTask != null)
            heartbeatTask.cancel();

        Ectasy.getInstance().setBackendPacketManager(null);
        new Thread(() -> {
            try {
                Thread.sleep(5000L);
                open();
            }
            catch (Exception err) {
                open();
            }

        }).start();
    }

    @Override
    public void onMessage(String message){
        System.out.println("Received message from backend: " + message);
    }

    @Override
    public void onError(Exception e){

    }

}
