package com.spendgo.dbproxy;

import com.spendgo.dbproxy.security.properties.CipherProperties;
import com.spendgo.dbproxy.security.properties.RsaKeyProperties;
import com.spendgo.dbproxy.security.properties.TokenProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyProperties.class, TokenProperties.class, CipherProperties.class})
public class DbProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbProxyApplication.class, args);
    }

}
