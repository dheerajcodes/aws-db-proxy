package com.spendgo.dbproxy.services;

import com.spendgo.dbproxy.security.BlowfishDecoder;
import com.spendgo.dbproxy.security.BlowfishEncoder;
import com.spendgo.dbproxy.security.properties.CipherProperties;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class CipherService {
    private final BlowfishEncoder encoder;
    private final BlowfishDecoder decoder;

    public CipherService(CipherProperties properties) throws Exception {
        SecretKeySpec sharedSecretKey = new SecretKeySpec(
                properties.getSharedKey().getBytes(),
                "Blowfish");
        this.encoder = new BlowfishEncoder(sharedSecretKey);
        this.decoder = new BlowfishDecoder(sharedSecretKey);
    }

    public String encodeData(String data) throws Exception {
        Base64.Encoder b64Encoder = Base64.getEncoder();
        return b64Encoder.encodeToString(this.encoder.encode(data.getBytes()));
    }

    public String decodeData(String data) throws Exception {
        Base64.Decoder b64Decoder = Base64.getDecoder();
        return new String(this.decoder.decode(b64Decoder.decode(data)));
    }
}
