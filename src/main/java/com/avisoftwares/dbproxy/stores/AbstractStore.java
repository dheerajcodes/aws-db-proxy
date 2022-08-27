package com.avisoftwares.dbproxy.stores;

import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.results.StoreResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public abstract class AbstractStore {
    @Autowired
    private ApplicationContext context;

    protected ApplicationContext getContext() {
        return this.context;
    }

    public abstract StoreResult executeAction(StoreAction action);
}
