package com.avisoftwares.dbproxy.stores;

import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.results.StoreResult;

public abstract class Store {
    public abstract StoreResult executeAction(StoreAction action);
}
