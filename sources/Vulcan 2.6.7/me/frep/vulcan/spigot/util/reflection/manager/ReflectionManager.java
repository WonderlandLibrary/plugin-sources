package me.frep.vulcan.spigot.util.reflection.manager;

import java.util.Arrays;
import java.io.IOException;
import java.util.Objects;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import me.frep.vulcan.spigot.util.reflection.instance.VulcanClazzInstance;
import org.bukkit.plugin.Plugin;
import me.frep.vulcan.spigot.Vulcan;
import org.bukkit.Bukkit;
import me.frep.vulcan.spigot.VulcanPlugin;
import java.util.Collections;
import me.frep.vulcan.spigot.util.reflection.methods.impl.AESDecryptor;
import me.frep.vulcan.spigot.util.reflection.methods.VulcanDecryptor;
import java.util.List;
import me.frep.vulcan.spigot.util.reflection.classloader.VulcanClassLoader;

public class ReflectionManager
{
    private VulcanClassLoader vulcanClassLoader;
    private Object clazzInstance;
    private final List<VulcanDecryptor> vulcanDecryptorList;
    
    public ReflectionManager() {
        this.vulcanDecryptorList = Collections.singletonList(new AESDecryptor());
    }
    
    public void loadBootstrapClass() {
        this.vulcanClassLoader = new VulcanClassLoader(VulcanPlugin.class.getClassLoader(), this.generateEncClazzInstance());
        try {
            final Class<?> clazz = this.vulcanClassLoader.findClass("me.frep.vulcan.spigot.util.reflection.EncryptedClass");
            if (clazz != null) {
                this.clazzInstance = clazz.getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
            }
        }
        catch (final Exception e) {
            Bukkit.getPluginManager().disablePlugin(Vulcan.INSTANCE.getPlugin());
        }
    }
    
    private VulcanClazzInstance generateEncClazzInstance() {
        final InputStream inputStream = ReflectionManager.class.getClassLoader().getResourceAsStream(this.getInternalPath());
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final byte[] data = new byte[16384];
        try {
            int nRead;
            while ((nRead = Objects.requireNonNull(inputStream).read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
        }
        catch (final IOException e) {
            Bukkit.getPluginManager().disablePlugin(Vulcan.INSTANCE.getPlugin());
        }
        final byte[] content = buffer.toByteArray();
        final byte[] method = Arrays.copyOfRange(content, content.length - 3, content.length);
        final String methodStr = new String(method);
        final VulcanDecryptor vulcanDecryptor = this.getVulcanDecryptorList().stream().filter(vulcanDecryptor1 -> vulcanDecryptor1.getIdentificationString().equals(methodStr)).findAny().orElse(null);
        if (vulcanDecryptor == null) {
            return null;
        }
        final int METHOD_LENGTH = 3;
        final int IV_LENGTH = 16;
        final byte[] iv = Arrays.copyOfRange(content, content.length - (METHOD_LENGTH + IV_LENGTH), content.length - METHOD_LENGTH);
        final int KEY_LENGTH = 16;
        final int to = content.length - (METHOD_LENGTH + IV_LENGTH + KEY_LENGTH);
        final byte[] key = Arrays.copyOfRange(content, to, content.length - (METHOD_LENGTH + IV_LENGTH));
        final byte[] end = Arrays.copyOfRange(content, 0, to);
        return new VulcanClazzInstance(vulcanDecryptor, end, key, iv);
    }

    public String getInternalPath() {
        return ".classpath";
    }
    
    public VulcanClassLoader getVulcanClassLoader() {
        return this.vulcanClassLoader;
    }
    
    public Object getClazzInstance() {
        return this.clazzInstance;
    }
    
    public List<VulcanDecryptor> getVulcanDecryptorList() {
        return this.vulcanDecryptorList;
    }
}
