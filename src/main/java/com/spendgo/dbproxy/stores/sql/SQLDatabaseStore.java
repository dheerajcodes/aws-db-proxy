package com.spendgo.dbproxy.stores.sql;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.DatabaseStoreResult;
import com.spendgo.dbproxy.stores.Store;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class SQLDatabaseStore extends Store {
    @Value("${store.resultLimit}")
    private String defaultLimit;

    @Override
    public DatabaseStoreResult executeAction(StoreAction action) {
        JdbcTemplate template = getTemplate();
        SQLActionParser actionParser = new SQLActionParser(action, Integer.parseInt(defaultLimit));
        List<Map<String, Object>> result = template.queryForList(actionParser.getQuery());
        return new DatabaseStoreResult(result);
    }

    protected abstract JdbcTemplate getTemplate();
}
