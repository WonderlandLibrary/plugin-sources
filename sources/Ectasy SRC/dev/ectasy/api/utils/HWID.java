package dev.ectasy.api.utils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.UUID;

public class HWID {

    public static String getServerId() {
        try {
            File bStatsFolder = new File("plugins/bStats");
            if (!bStatsFolder.exists())
                bStatsFolder.mkdir();

            File bStatsFile = new File("plugins/bStats/config.yml");
            if (!bStatsFile.exists()) {
                bStatsFile.createNewFile();
                // write to the file
                FileWriter writer = new FileWriter(bStatsFile);
                String[] bstatsComments = {
                        "# bStats collects some data for plugin authors like how many servers are using their plugins.",
                        "# To honor their work, you should not disable it.",
                        "# This has nearly no effect on the server performance!",
                        "# Check out https://bStats.org/ to learn more :)",
                        ""
                };

                for (String comment : bstatsComments) {
                    writer.write(comment + "\n");
                }

                String uuid = UUID.randomUUID().toString();
                writer.write("enabled: false\nserverUuid: " + uuid + "\nlogFailedRequests: false");
                writer.close();
                return uuid;
            }

            FileReader reader = new FileReader(bStatsFile);
            int data = reader.read();
            StringBuilder uuid = new StringBuilder();
            while (data != -1) {
                uuid.append((char) data);
                data = reader.read();
            }
            reader.close();

            if (!uuid.toString().contains("serverUuid: ")) {
                bStatsFile.delete();
                return getServerId();
            }

            return uuid.toString().split("serverUuid: ")[1].split("\n")[0];
        } catch (Exception ignored) { }

        return "unknown";
    }

    public static String getIp() {
        try {
            String hostname = System.getenv("HOSTNAME");
            if (hostname != null && hostname.startsWith("game-i-")) {
                String data = RequestUtils.get("https://api.minehut.com/server/" + hostname.split("game-i-")[1]);
                JSONObject json = new JSONObject(data);
                if (json.has("server") && json.getJSONObject("server").has("name"))
                    return json.getJSONObject("server").getString("name") + ".minehut.gg";
            }
        } catch (Exception e) {

        }

        return RequestUtils.get("https://api.ipify.org");
    }

}
