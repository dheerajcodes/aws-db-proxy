package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.DatabaseStoreResult;
import com.spendgo.dbproxy.stores.Store;
import com.spendgo.dbproxy.stores.exceptions.StorePropertyException;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class SQLDatabaseStore extends Store {
    private static final String KEY_PROPERTY_LIMIT = "store.sql.limit";
    private int defaultLimit;

    public SQLDatabaseStore(ApplicationContext context) {
        super(context);
        String limitValue = context.getEnvironment().getProperty(KEY_PROPERTY_LIMIT, "");
        if (limitValue.equals("")) {
            throw new StorePropertyException(KEY_PROPERTY_LIMIT, "default limit must be provided for sql results");
        }
        try {
            defaultLimit = Integer.parseInt(limitValue);
        } catch (NumberFormatException exception) {
            throw new StorePropertyException(KEY_PROPERTY_LIMIT, "default limit must be a numeric value");
        }
    }

    @Override
    public DatabaseStoreResult executeAction(StoreAction action) {
        JdbcTemplate template = getTemplate();
        SQLActionParser actionParser = new SQLActionParser(action, defaultLimit);
        List<Map<String, Object>> result = template.queryForList(actionParser.getQuery());
        return new DatabaseStoreResult(result);
    }

    protected abstract JdbcTemplate getTemplate();
}
