package com.spendgo.dbproxy.stores;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;

public abstract class Store {
    public abstract StoreResult executeAction(StoreAction action);
}
