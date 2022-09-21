package com.avisoftwares.dbproxy.stores.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuroraStore extends SQLDatabaseStore {
    @Autowired
    @Qualifier("jdbcAurora")
    protected JdbcTemplate jdbcTemplate;

    @Override
    protected JdbcTemplate getTemplate() {
        return jdbcTemplate;
    }
}
