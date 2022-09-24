package com.spendgo.dbproxy.stores;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;
import org.springframework.context.ApplicationContext;

public abstract class Store {
    protected ApplicationContext context;

    public Store(ApplicationContext context) {
        this.context = context;
    }

    public abstract StoreResult executeAction(StoreAction action);
}
