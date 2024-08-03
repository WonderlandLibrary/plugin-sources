package dev.ectasy.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class RequestUtils {
    public static String get(String uri) {
        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
            con.setDoOutput(true);
            con.connect();
            BufferedReader isr = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return isr.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return null;
        }
    }
}