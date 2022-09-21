package com.avisoftwares.dbproxy.stores.sql;

import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.stores.sql.exceptions.ActionParserException;

import java.util.ArrayList;

public class SQLActionParser {
    private final String operator;
    private final String aggregate;
    private final String columns;
    private final String table;
    private final String joins;
    private final String conditions;
    private final String group;
    private final String having;
    private final String sort;
    private final String limit;
    private final String offset;

    private final int defaultLimit;
    private String queryString; // Cached Query

    public SQLActionParser(StoreAction action, int defaultLimit) {
        this.operator = getValueOrDefault(action.getOperator(), "");
        this.aggregate = getValueOrDefault(action.getAggregate(), "");
        this.columns = getValueOrDefault(action.getProjection(), "");
        this.table = getValueOrDefault(action.getResource(), "");
        this.joins = getValueOrDefault(action.getJoin(), "");
        this.conditions = getValueOrDefault(action.getFilter(), "");
        this.group = getValueOrDefault(action.getGroup(), "");
        this.having = getValueOrDefault(action.getGroupFilter(), "");
        this.sort = getValueOrDefault(action.getSort(), "");
        this.limit = getValueOrDefault(action.getLimit(), "");
        this.offset = getValueOrDefault(action.getOffset(), "");
        this.defaultLimit = defaultLimit;
        this.queryString = "";
    }

    public String getQuery() throws ActionParserException {
        if (!queryString.equals("")) return queryString;
        ArrayList<String> clauses = new ArrayList<>();
        clauses.add(getValueOrDefault(this.operator, "SELECT"));
        clauses.add(getColumns());
        clauses.add("FROM");
        if (this.table.equals("")) {
            throw new ActionParserException("request did not specify the target table in 'resource' key ");
        }
        clauses.add(this.table);
        clauses.add(getJoins());
        clauses.add(getWhereConditions());
        clauses.add(getGroupBy());
        clauses.add(getHavingConditions());
        clauses.add(getSort());
        clauses.add(getLimitOffset());

        this.queryString = String.join(" ", clauses);
        return this.queryString;
    }

    private String getValueOrDefault(String value, String defaultValue) {
        return value == null || value.trim().equals("") ? defaultValue : value;
    }

    private String getColumns() {
        ArrayList<String> cols = new ArrayList<>();
        if (!aggregate.equals("")) cols.add(aggregate);
        if (!columns.equals("")) cols.add(columns);
        return getValueOrDefault(String.join(",", cols), "*");
    }

    private String getJoins() {
        if (this.joins.equals("")) return "";
        // Format for Joins - Semicolon-delimited list of four comma-separated values -
        // join-type,join table,column of join table,column of main table
        // e.g. INNER,<table-1>,<col-table-1>,<col-main-table>;OUTER,<table-2>,<col-table-2>,<col-main-table>; ... and so on.
        String[] joins = this.joins.trim().split("\\s*;\\s*");
        ArrayList<String> joinClauseList = new ArrayList<>();
        for (String join : joins) {
            String[] parts = join.split("\\s*,\\s*");
            if (parts.length != 4) {
                throw new ActionParserException("invalid 'join' in request - " + this.joins);
            }
            String joinClause = parts[0] + " JOIN " +
                    parts[1] +
                    " ON " +
                    parts[1] +
                    "." +
                    parts[2] +
                    " = " +
                    this.table +
                    "." +
                    parts[3];
            joinClauseList.add(joinClause);
        }
        return String.join(",", joinClauseList);
    }

    private String getWhereConditions() {
        if (this.conditions.equals("")) return "";
        return "WHERE " + this.conditions;
    }

    private String getGroupBy() {
        if (this.group.equals("")) return "";
        return "GROUP BY " + this.group;
    }

    private String getHavingConditions() {
        if (getGroupBy().equals("") || this.having.equals("")) return "";
        return "HAVING " + this.having;
    }

    private String getSort() {
        if (this.sort.equals("")) return "";
        return "ORDER BY " + this.sort;
    }

    private String getLimitOffset() {
        String offset = this.offset.equals("") ? "OFFSET 0" : "OFFSET " + this.offset;
        int limitValue = defaultLimit;
        if (!limit.equals("")) {
            int customLimit = Integer.parseInt(limit);
            limitValue = Math.min(customLimit, defaultLimit);
        }
        String limit = "LIMIT " + limitValue;
        return limit + " " + offset;
    }
}
