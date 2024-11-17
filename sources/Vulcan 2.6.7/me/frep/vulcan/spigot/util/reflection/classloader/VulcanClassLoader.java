package me.frep.vulcan.spigot.util.reflection.classloader;

import me.frep.vulcan.spigot.util.reflection.instance.VulcanClazzInstance;

public class VulcanClassLoader extends ClassLoader
{
    private VulcanClazzInstance vulcanClazzInstance;
    
    public VulcanClassLoader(final ClassLoader parent, final VulcanClazzInstance vulcanClazzInstance) {
        super(parent);
        this.vulcanClazzInstance = vulcanClazzInstance;
    }
    
    public Class<?> findClass(final String name) throws ClassNotFoundException {
        if (this.vulcanClazzInstance != null) {
            final byte[] bytes = this.vulcanClazzInstance.getVulcanDecryptor().decrypt(this.vulcanClazzInstance.getEncBytes(), this.vulcanClazzInstance.getKey(), this.vulcanClazzInstance.getIv());
            this.vulcanClazzInstance = null;
            return this.defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }
}
