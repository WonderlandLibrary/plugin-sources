package me.vagdedes.spartan.k.d;

import java.util.ArrayList;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class RequestUtils
{
    public RequestUtils() {
        super();
    }
    
    public static String[] a(final String spec, final String str, final String requestMethod) {
        final HttpsURLConnection httpsURLConnection = (HttpsURLConnection)new URL(spec).openConnection();
        httpsURLConnection.addRequestProperty("User-Agent", "Spartan AntiCheat (" + str + ")");
        httpsURLConnection.setRequestMethod(requestMethod);
        httpsURLConnection.setReadTimeout(15000);
        httpsURLConnection.connect();
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        final ArrayList<String> list = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }
        bufferedReader.close();
        return list.<String>toArray(new String[0]);
    }
    
    public static String[] a(final String s, final String s2) {
        return a(s, s2, "GET");
    }
}
