package me.frep.vulcan.spigot.util.reflection.instance;

import me.frep.vulcan.spigot.util.reflection.methods.VulcanDecryptor;

public class VulcanClazzInstance
{
    private final VulcanDecryptor vulcanDecryptor;
    private final byte[] encBytes;
    private final byte[] key;
    private final byte[] iv;
    
    public VulcanClazzInstance(final VulcanDecryptor vulcanDecryptor, final byte[] encBytes, final byte[] key, final byte[] iv) {
        this.vulcanDecryptor = vulcanDecryptor;
        this.encBytes = encBytes;
        this.key = key;
        this.iv = iv;
    }
    
    public byte[] getKey() {
        return this.key;
    }
    
    public byte[] getIv() {
        return this.iv;
    }
    
    public VulcanDecryptor getVulcanDecryptor() {
        return this.vulcanDecryptor;
    }
    
    public byte[] getEncBytes() {
        return this.encBytes;
    }
}
