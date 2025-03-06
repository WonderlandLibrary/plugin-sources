package me.frep.vulcan.spigot.bukkit;

import com.google.gson.JsonPrimitive;
import java.util.Map;
import java.util.concurrent.Callable;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import com.google.gson.JsonParser;
import org.bukkit.plugin.RegisteredServiceProvider;
import java.lang.reflect.Method;
import org.bukkit.entity.Player;
import java.util.Collection;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.Bukkit;
import java.io.IOException;
import java.util.UUID;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.List;
import org.bukkit.plugin.Plugin;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class Metrics
{
    private final ThreadFactory threadFactory;
    private final ScheduledExecutorService scheduler;
    public static final int B_STATS_VERSION = 1;
    private static final String URL = "https://bStats.org/submitData/bukkit";
    private boolean enabled;
    private static boolean logFailedRequests;
    private static boolean logSentData;
    private static boolean logResponseStatusText;
    private static String serverUUID;
    private final Plugin plugin;
    private final int pluginId;
    private final List<CustomChart> charts;
    
    public Metrics(final Plugin plugin, final int pluginId) {
        this.threadFactory = (task -> new Thread(task, "bStats-Metrics"));
        this.scheduler = Executors.newScheduledThreadPool(1, this.threadFactory);
        this.charts = new ArrayList<CustomChart>();
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null!");
        }
        this.plugin = plugin;
        this.pluginId = pluginId;
        final File bStatsFolder = new File(plugin.getDataFolder().getParentFile(), "bStats");
        final File configFile = new File(bStatsFolder, "config.yml");
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (!config.isSet("serverUuid")) {
            config.addDefault("enabled", true);
            config.addDefault("serverUuid", UUID.randomUUID().toString());
            config.addDefault("logFailedRequests", false);
            config.addDefault("logSentData", false);
            config.addDefault("logResponseStatusText", false);
            config.options().header("bStats collects some data for plugin authors like how many servers are using their plugins.\nTo honor their work, you should not disable it.\nThis has nearly no effect on the server performance!\nCheck out https://bStats.org/ to learn more :)").copyDefaults(true);
            try {
                config.save(configFile);
            }
            catch (final IOException ex) {}
        }
        this.enabled = config.getBoolean("enabled", true);
        Metrics.serverUUID = config.getString("serverUuid");
        Metrics.logFailedRequests = config.getBoolean("logFailedRequests", false);
        Metrics.logSentData = config.getBoolean("logSentData", false);
        Metrics.logResponseStatusText = config.getBoolean("logResponseStatusText", false);
        if (this.enabled) {
            boolean found = false;
            for (final Class<?> service : Bukkit.getServicesManager().getKnownServices()) {
                try {
                    service.getField("B_STATS_VERSION");
                    found = true;
                }
                catch (final NoSuchFieldException ex2) {
                    continue;
                }
                break;
            }
            Bukkit.getServicesManager().register(Metrics.class, this, plugin, ServicePriority.Normal);
            if (!found) {
                this.startSubmitting();
            }
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void addCustomChart(final CustomChart chart) {
        if (chart == null) {
            throw new IllegalArgumentException("Chart cannot be null!");
        }
        this.charts.add(chart);
    }
    
    private void startSubmitting() {
        final Runnable submitTask = () -> {
            if (!this.plugin.isEnabled()) {
                this.scheduler.shutdown();
                return;
            }
            else {
                Bukkit.getScheduler().runTask(this.plugin, this::submitData);
                return;
            }
        };
        final long initialDelay = (long)(60000.0 * (3.0 + Math.random() * 3.0));
        final long secondDelay = (long)(60000.0 * (Math.random() * 30.0));
        this.scheduler.schedule(submitTask, initialDelay, TimeUnit.MILLISECONDS);
        this.scheduler.scheduleAtFixedRate(submitTask, initialDelay + secondDelay, 1800000L, TimeUnit.MILLISECONDS);
    }
    
    public JsonObject getPluginData() {
        final JsonObject data = new JsonObject();
        final String pluginName = this.plugin.getDescription().getName();
        final String pluginVersion = this.plugin.getDescription().getVersion();
        data.addProperty("pluginName", pluginName);
        data.addProperty("id", this.pluginId);
        data.addProperty("pluginVersion", pluginVersion);
        final JsonArray customCharts = new JsonArray();
        for (final CustomChart customChart : this.charts) {
            final JsonObject chart = customChart.getRequestJsonObject();
            if (chart == null) {
                continue;
            }
            customCharts.add(chart);
        }
        data.add("customCharts", customCharts);
        return data;
    }
    
    private JsonObject getServerData() {
        int playerAmount;
        try {
            final Method onlinePlayersMethod = Class.forName("org.bukkit.Server").getMethod("getOnlinePlayers", (Class<?>[])new Class[0]);
            playerAmount = (onlinePlayersMethod.getReturnType().equals(Collection.class) ? ((Collection)onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).size() : ((Player[])onlinePlayersMethod.invoke(Bukkit.getServer(), new Object[0])).length);
        }
        catch (final Exception e) {
            playerAmount = Bukkit.getOnlinePlayers().size();
        }
        final int onlineMode = Bukkit.getOnlineMode() ? 1 : 0;
        final String bukkitVersion = Bukkit.getVersion();
        final String bukkitName = Bukkit.getName();
        final String javaVersion = System.getProperty("java.version");
        final String osName = System.getProperty("os.name");
        final String osArch = System.getProperty("os.arch");
        final String osVersion = System.getProperty("os.version");
        final int coreCount = Runtime.getRuntime().availableProcessors();
        final JsonObject data = new JsonObject();
        data.addProperty("serverUUID", Metrics.serverUUID);
        data.addProperty("playerAmount", playerAmount);
        data.addProperty("onlineMode", onlineMode);
        data.addProperty("bukkitVersion", bukkitVersion);
        data.addProperty("bukkitName", bukkitName);
        data.addProperty("javaVersion", javaVersion);
        data.addProperty("osName", osName);
        data.addProperty("osArch", osArch);
        data.addProperty("osVersion", osVersion);
        data.addProperty("coreCount", coreCount);
        return data;
    }
    
    private void submitData() {
        final JsonObject data = this.getServerData();
        final JsonArray pluginData = new JsonArray();
        for (final Class<?> service : Bukkit.getServicesManager().getKnownServices()) {
            try {
                service.getField("B_STATS_VERSION");
                for (final RegisteredServiceProvider<?> provider : Bukkit.getServicesManager().getRegistrations(service)) {
                    try {
                        final Object plugin = provider.getService().getMethod("getPluginData", (Class[])new Class[0]).invoke(provider.getProvider(), new Object[0]);
                        if (plugin instanceof JsonObject) {
                            pluginData.add((JsonElement)plugin);
                        }
                        else {
                            try {
                                final Class<?> jsonObjectJsonSimple = Class.forName("org.json.simple.JSONObject");
                                if (!plugin.getClass().isAssignableFrom(jsonObjectJsonSimple)) {
                                    continue;
                                }
                                final Method jsonStringGetter = jsonObjectJsonSimple.getDeclaredMethod("toJSONString", (Class<?>[])new Class[0]);
                                jsonStringGetter.setAccessible(true);
                                final String jsonString = (String)jsonStringGetter.invoke(plugin, new Object[0]);
                                final JsonObject object = new JsonParser().parse(jsonString).getAsJsonObject();
                                pluginData.add(object);
                            }
                            catch (final ClassNotFoundException e) {
                                if (!Metrics.logFailedRequests) {
                                    continue;
                                }
                                this.plugin.getLogger().log(Level.SEVERE, "Encountered unexpected exception", e);
                            }
                        }
                    }
                    catch (final NullPointerException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {}
                }
            }
            catch (final NoSuchFieldException ex2) {}
        }
        data.add("plugins", pluginData);
        new Thread(() -> {
            try {
                sendData(this.plugin, data);
            }
            catch (final Exception e2) {
                if (Metrics.logFailedRequests) {
                    this.plugin.getLogger().log(Level.WARNING, "Could not submit plugin stats of " + this.plugin.getName(), e2);
                }
            }
        }).start();
    }
    
    private static void sendData(final Plugin plugin, final JsonObject data) throws Exception {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (Bukkit.isPrimaryThread()) {
            throw new IllegalAccessException("This method must not be called from the main thread!");
        }
        if (Metrics.logSentData) {
            plugin.getLogger().info("Sending data to bStats: " + data);
        }
        final HttpsURLConnection connection = (HttpsURLConnection)new URL("https://bStats.org/submitData/bukkit").openConnection();
        final byte[] compressedData = compress(data.toString());
        connection.setRequestMethod("POST");
        connection.addRequestProperty("Accept", "application/json");
        connection.addRequestProperty("Connection", "close");
        connection.addRequestProperty("Content-Encoding", "gzip");
        connection.addRequestProperty("Content-Length", String.valueOf(compressedData.length));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("User-Agent", "MC-Server/1");
        connection.setDoOutput(true);
        try (final DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(compressedData);
        }
        final StringBuilder builder = new StringBuilder();
        try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        }
        if (Metrics.logResponseStatusText) {
            plugin.getLogger().info("Sent data to bStats and received response: " + (Object)builder);
        }
    }
    
    private static byte[] compress(final String str) throws IOException {
        if (str == null) {
            return null;
        }
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (final GZIPOutputStream gzip = new GZIPOutputStream(outputStream)) {
            gzip.write(str.getBytes(StandardCharsets.UTF_8));
        }
        return outputStream.toByteArray();
    }
    
    static {
        if (System.getProperty("bstats.relocatecheck") == null || !System.getProperty("bstats.relocatecheck").equals("false")) {
            final String defaultPackage = new String(new byte[] { 111, 114, 103, 46, 98, 115, 116, 97, 116, 115, 46, 98, 117, 107, 107, 105, 116 });
            final String examplePackage = new String(new byte[] { 121, 111, 117, 114, 46, 112, 97, 99, 107, 97, 103, 101 });
            if (Metrics.class.getPackage().getName().equals(defaultPackage) || Metrics.class.getPackage().getName().equals(examplePackage)) {
                throw new IllegalStateException("bStats Metrics class has not been relocated correctly!");
            }
        }
    }
    
    public abstract static class CustomChart
    {
        final String chartId;
        
        CustomChart(final String chartId) {
            if (chartId == null || chartId.isEmpty()) {
                throw new IllegalArgumentException("ChartId cannot be null or empty!");
            }
            this.chartId = chartId;
        }
        
        private JsonObject getRequestJsonObject() {
            final JsonObject chart = new JsonObject();
            chart.addProperty("chartId", this.chartId);
            try {
                final JsonObject data = this.getChartData();
                if (data == null) {
                    return null;
                }
                chart.add("data", data);
            }
            catch (final Throwable t) {
                if (Metrics.logFailedRequests) {
                    Bukkit.getLogger().log(Level.WARNING, "Failed to get data for custom chart with id " + this.chartId, t);
                }
                return null;
            }
            return chart;
        }
        
        protected abstract JsonObject getChartData() throws Exception;
    }
    
    public static class SimplePie extends CustomChart
    {
        private final Callable<String> callable;
        
        public SimplePie(final String chartId, final Callable<String> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final String value = this.callable.call();
            if (value == null || value.isEmpty()) {
                return null;
            }
            data.addProperty("value", value);
            return data;
        }
    }
    
    public static class AdvancedPie extends CustomChart
    {
        private final Callable<Map<String, Integer>> callable;
        
        public AdvancedPie(final String chartId, final Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final JsonObject values = new JsonObject();
            final Map<String, Integer> map = this.callable.call();
            if (map == null || map.isEmpty()) {
                return null;
            }
            boolean allSkipped = true;
            for (final Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    continue;
                }
                allSkipped = false;
                values.addProperty(entry.getKey(), entry.getValue());
            }
            if (allSkipped) {
                return null;
            }
            data.add("values", values);
            return data;
        }
    }
    
    public static class DrilldownPie extends CustomChart
    {
        private final Callable<Map<String, Map<String, Integer>>> callable;
        
        public DrilldownPie(final String chartId, final Callable<Map<String, Map<String, Integer>>> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        public JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final JsonObject values = new JsonObject();
            final Map<String, Map<String, Integer>> map = this.callable.call();
            if (map == null || map.isEmpty()) {
                return null;
            }
            boolean reallyAllSkipped = true;
            for (final Map.Entry<String, Map<String, Integer>> entryValues : map.entrySet()) {
                final JsonObject value = new JsonObject();
                boolean allSkipped = true;
                for (final Map.Entry<String, Integer> valueEntry : map.get(entryValues.getKey()).entrySet()) {
                    value.addProperty(valueEntry.getKey(), valueEntry.getValue());
                    allSkipped = false;
                }
                if (!allSkipped) {
                    reallyAllSkipped = false;
                    values.add(entryValues.getKey(), value);
                }
            }
            if (reallyAllSkipped) {
                return null;
            }
            data.add("values", values);
            return data;
        }
    }
    
    public static class SingleLineChart extends CustomChart
    {
        private final Callable<Integer> callable;
        
        public SingleLineChart(final String chartId, final Callable<Integer> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final int value = this.callable.call();
            if (value == 0) {
                return null;
            }
            data.addProperty("value", value);
            return data;
        }
    }
    
    public static class MultiLineChart extends CustomChart
    {
        private final Callable<Map<String, Integer>> callable;
        
        public MultiLineChart(final String chartId, final Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final JsonObject values = new JsonObject();
            final Map<String, Integer> map = this.callable.call();
            if (map == null || map.isEmpty()) {
                return null;
            }
            boolean allSkipped = true;
            for (final Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 0) {
                    continue;
                }
                allSkipped = false;
                values.addProperty(entry.getKey(), entry.getValue());
            }
            if (allSkipped) {
                return null;
            }
            data.add("values", values);
            return data;
        }
    }
    
    public static class SimpleBarChart extends CustomChart
    {
        private final Callable<Map<String, Integer>> callable;
        
        public SimpleBarChart(final String chartId, final Callable<Map<String, Integer>> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final JsonObject values = new JsonObject();
            final Map<String, Integer> map = this.callable.call();
            if (map == null || map.isEmpty()) {
                return null;
            }
            for (final Map.Entry<String, Integer> entry : map.entrySet()) {
                final JsonArray categoryValues = new JsonArray();
                categoryValues.add(new JsonPrimitive((Number)entry.getValue()));
                values.add(entry.getKey(), categoryValues);
            }
            data.add("values", values);
            return data;
        }
    }
    
    public static class AdvancedBarChart extends CustomChart
    {
        private final Callable<Map<String, int[]>> callable;
        
        public AdvancedBarChart(final String chartId, final Callable<Map<String, int[]>> callable) {
            super(chartId);
            this.callable = callable;
        }
        
        @Override
        protected JsonObject getChartData() throws Exception {
            final JsonObject data = new JsonObject();
            final JsonObject values = new JsonObject();
            final Map<String, int[]> map = this.callable.call();
            if (map == null || map.isEmpty()) {
                return null;
            }
            boolean allSkipped = true;
            for (final Map.Entry<String, int[]> entry : map.entrySet()) {
                if (entry.getValue().length == 0) {
                    continue;
                }
                allSkipped = false;
                final JsonArray categoryValues = new JsonArray();
                for (final int categoryValue : entry.getValue()) {
                    categoryValues.add(new JsonPrimitive((Number)categoryValue));
                }
                values.add(entry.getKey(), categoryValues);
            }
            if (allSkipped) {
                return null;
            }
            data.add("values", values);
            return data;
        }
    }
}
