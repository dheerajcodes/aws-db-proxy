package com.avisoftwares.dbproxy.stores;

import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.results.DatabaseStoreResult;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public abstract class SQLDatabaseStore extends Store {

    @Override
    public DatabaseStoreResult executeAction(StoreAction action) {
        String queryType = action.getOperator();
        String columns = action.getProjection();
        String tableName = action.getResource();
        String conditions = action.getFilter();
        String sqlString = queryType + " " + columns + " FROM " + tableName;
        boolean hasConditions = !conditions.trim().equals("");
        if (hasConditions) {
            sqlString += " " + conditions;
        }
        JdbcTemplate template = getTemplate();
        List<Map<String, Object>> result = template.queryForList(sqlString);
        return new DatabaseStoreResult(result);
    }

    protected abstract JdbcTemplate getTemplate();
}
