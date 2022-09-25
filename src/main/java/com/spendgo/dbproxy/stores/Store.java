package com.spendgo.dbproxy.stores;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;
import com.spendgo.dbproxy.stores.exceptions.StorePropertyException;
import org.springframework.context.ApplicationContext;

public abstract class Store {
    protected ApplicationContext context;
    private final StoreAction action;

    private int defaultLimit;

    public Store(ApplicationContext context, StoreAction action) {
        this.context = context;
        this.action = action;
        String limitKey = getDefaultLimitPropertyKey();
        String limitValue = context.getEnvironment().getProperty(limitKey, "");
        if (limitValue.equals("")) {
            throw new StorePropertyException(limitKey, "default limit must be provided for sql results");
        }
        try {
            this.defaultLimit = Integer.parseInt(limitValue);
        } catch (NumberFormatException exception) {
            throw new StorePropertyException(limitKey, "default limit must be a numeric value");
        }
    }

    protected StoreAction getAction() {
        return this.action;
    }

    public abstract StoreResult executeAction();

    protected abstract String getDefaultLimitPropertyKey();

    public void setDefaultLimit(int limit) {
        this.defaultLimit = limit;
    }

    public int getDefaultLimit() {
        return this.defaultLimit;
    }
}
