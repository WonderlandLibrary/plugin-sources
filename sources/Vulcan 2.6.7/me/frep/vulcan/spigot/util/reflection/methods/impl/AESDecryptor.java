package me.frep.vulcan.spigot.util.reflection.methods.impl;

import java.util.Base64;
import java.security.spec.AlgorithmParameterSpec;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import me.frep.vulcan.spigot.util.reflection.methods.VulcanDecryptor;

public class AESDecryptor implements VulcanDecryptor
{
    @Override
    public byte[] decrypt(final byte[] input, final byte[] key, final byte[] iv) {
        try {
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(key, 0, key.length, "AES"), new IvParameterSpec(iv));
            return cipher.doFinal(Base64.getDecoder().decode(input));
        }
        catch (final Exception exception) {
            exception.printStackTrace();
            return new byte[0];
        }
    }
    
    @Override
    public String getIdentificationString() {
        return "AES";
    }
}
