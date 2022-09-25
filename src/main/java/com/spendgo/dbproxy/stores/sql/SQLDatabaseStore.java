package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.DatabaseStoreResult;
import com.spendgo.dbproxy.stores.Store;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class SQLDatabaseStore extends Store {
    private final static String BASE_PROPERTIES_KEY = "sql";

    public SQLDatabaseStore(ApplicationContext context, StoreAction action) {
        super(context, action);
    }

    @Override
    public DatabaseStoreResult executeAction() {
        JdbcTemplate template = getTemplate();
        SQLActionParser actionParser = new SQLActionParser(getAction(), getDefaultLimit());
        List<Map<String, Object>> result = template.queryForList(actionParser.getQuery());
        return new DatabaseStoreResult(result);
    }

    @Override
    protected String getDefaultLimitPropertyKey() {
        return BASE_PROPERTIES_KEY;
    }

    protected abstract JdbcTemplate getTemplate();
}
