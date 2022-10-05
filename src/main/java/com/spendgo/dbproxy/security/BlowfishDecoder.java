package com.spendgo.dbproxy.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlowfishDecoder {
    private final Cipher cipher;

    public BlowfishDecoder(SecretKeySpec secretKey) throws Exception {
        this.cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    public byte[] decode(byte[] input) throws Exception {
        return cipher.doFinal(input);
    }
}
