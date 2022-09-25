package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RegisterStore(storeId = "redshift")
public final class RedshiftStore extends SQLDatabaseStore {
    private final static String BASE_PROPERTIES_KEY = "redshift";
    private static final String KEY_TEMPLATE_BEAN = "jdbcRedshift";
    private final JdbcTemplate jdbcTemplate;

    public RedshiftStore(ApplicationContext context, StoreAction action) {
        super(context, action);
        this.jdbcTemplate = context.getBean(KEY_TEMPLATE_BEAN, JdbcTemplate.class);
    }

    @Override
    protected String getDefaultLimitPropertyKey() {
        return String.join(".", super.getDefaultLimitPropertyKey(), BASE_PROPERTIES_KEY, "limit");
    }

    @Override
    protected JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
}
