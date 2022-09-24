package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.DatabaseStoreResult;
import com.spendgo.dbproxy.stores.RegisterStore;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@RegisterStore(storeId = "redshift")
public final class RedshiftStore extends SQLDatabaseStore {
    private static final String KEY_TEMPLATE_BEAN = "jdbcRedshift";
    private final JdbcTemplate jdbcTemplate;
    private StoreAction action;

    public RedshiftStore(ApplicationContext context) {
        super(context);
        this.jdbcTemplate = context.getBean(KEY_TEMPLATE_BEAN, JdbcTemplate.class);
    }

    public void setStoreAction(StoreAction action) {
        this.action = action;
    }

    public StoreAction getStoreAction(StoreAction action) {
        return this.action;
    }

    public DatabaseStoreResult executeAction() {
        return super.executeAction(this.action);
    }

    @Override
    protected JdbcTemplate getTemplate() {
        return this.jdbcTemplate;
    }
}
