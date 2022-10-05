package com.spendgo.dbproxy.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class BlowfishEncoder {
    private final Cipher cipher;

    public BlowfishEncoder(SecretKeySpec secretKey) throws Exception {
        this.cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    }

    public byte[] encode(byte[] input) throws Exception {
        return cipher.doFinal(input);
    }
}
