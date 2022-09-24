package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RegisterStore(storeId = "redshift")
public final class RedshiftStore extends SQLDatabaseStore {
    @Autowired
    @Qualifier("jdbcRedshift")
    private JdbcTemplate jdbcTemplate;

    @Override
    protected JdbcTemplate getTemplate() {
        return jdbcTemplate;
    }
}
