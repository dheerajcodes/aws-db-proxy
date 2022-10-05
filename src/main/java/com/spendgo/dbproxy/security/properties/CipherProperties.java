package com.spendgo.dbproxy.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cipher")
public class CipherProperties {
    private String sharedKey;

    public String getSharedKey() {
        return this.sharedKey;
    }

    public void setSharedKey(String sharedKey) {
        this.sharedKey = sharedKey;
    }
}
