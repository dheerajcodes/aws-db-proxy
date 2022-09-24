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
public class RedshiftConfig {

    @Bean(name = "redshift")
    @ConfigurationProperties(prefix = "aws.redshift")
    public DataSource redshiftDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcRedshift")
    @Autowired
    public JdbcTemplate redshiftJdbcTemplate(@Qualifier("redshift") DataSource redshiftDataSource) {
        return new JdbcTemplate(redshiftDataSource);
    }
}
