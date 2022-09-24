package com.spendgo.dbproxy.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class AuroraConfig {

    @Bean(name = "aurora")
    @ConfigurationProperties(prefix = "aws.aurora")
    public DataSource auroraDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcAurora")
    @Autowired
    public JdbcTemplate auroraJdbcTemplate(@Qualifier("aurora") DataSource auroraDataSource) {
        return new JdbcTemplate(auroraDataSource);
    }
}
