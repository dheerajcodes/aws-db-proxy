package com.spendgo.dbproxy.actions;


public class StoreAction {
    private String store;
    private String operator;
    private String aggregate;
    private String projection;
    private String resource;
    private String join;
    private String filter;
    private String group;
    private String groupFilter;
    private String sort;
    private String limit;
    private String offset;

    public String getStore() {
        return store;
    }

    public String getOperator() {
        return operator;
    }

    public String getAggregate() {
        return aggregate;
    }

    public String getProjection() {
        return projection;
    }

    public String getResource() {
        return resource;
    }

    public String getJoin() {
        return join;
    }

    public String getFilter() {
        return filter;
    }

    public String getGroup() {
        return group;
    }

    public String getGroupFilter() {
        return groupFilter;
    }

    public String getSort() {
        return sort;
    }

    public String getLimit() {
        return limit;
    }

    public String getOffset() {
        return offset;
    }
}
