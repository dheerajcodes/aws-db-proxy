package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RegisterStore(storeId = "aurora")
public final class AuroraStore extends SQLDatabaseStore {
    private static final String KEY_TEMPLATE_BEAN = "jdbcAurora";
    private final JdbcTemplate jdbcTemplate;

    public AuroraStore(ApplicationContext context) {
        super(context);
        this.jdbcTemplate = context.getBean(KEY_TEMPLATE_BEAN, JdbcTemplate.class);
    }

    @Override
    protected JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
}
