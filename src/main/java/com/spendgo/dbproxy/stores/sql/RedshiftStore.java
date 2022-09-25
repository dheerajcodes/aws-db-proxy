package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RegisterStore(storeId = "redshift")
public final class RedshiftStore extends SQLDatabaseStore {
    private static final String KEY_TEMPLATE_BEAN = "jdbcRedshift";
    private final JdbcTemplate jdbcTemplate;
    private static final String KEY_PROPERTY_LIMIT = "store.sql.redshift.limit";

    public RedshiftStore(ApplicationContext context, StoreAction action) {
        super(context, action);
        this.jdbcTemplate = context.getBean(KEY_TEMPLATE_BEAN, JdbcTemplate.class);
    }

    @Override
    protected String getDefaultLimitPropertyKey() {
        return KEY_PROPERTY_LIMIT;
    }

    @Override
    protected JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
}
