package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RegisterStore(storeId = "aurora")
public final class AuroraStore extends SQLDatabaseStore {
    private final static String BASE_PROPERTIES_KEY = "aurora";
    private static final String KEY_TEMPLATE_BEAN = "jdbcAurora";
    private final JdbcTemplate jdbcTemplate;

    public AuroraStore(ApplicationContext context, StoreAction action) {
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
