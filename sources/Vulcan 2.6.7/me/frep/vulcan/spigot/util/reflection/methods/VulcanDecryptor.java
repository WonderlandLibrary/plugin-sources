package me.frep.vulcan.spigot.util.reflection.methods;

public interface VulcanDecryptor
{
    byte[] decrypt(final byte[] p0, final byte[] p1, final byte[] p2);
    
    String getIdentificationString();
}
