package com.spendgo.dbproxy.stores;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;
import org.springframework.context.ApplicationContext;

public abstract class Store {
    protected ApplicationContext context;
    private final StoreAction action;

    public Store(ApplicationContext context, StoreAction action) {
        this.context = context;
        this.action = action;
    }

    protected StoreAction getAction() {
        return this.action;
    }

    public abstract StoreResult executeAction();
}
