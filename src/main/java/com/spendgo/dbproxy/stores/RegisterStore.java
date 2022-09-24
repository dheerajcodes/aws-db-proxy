package com.spendgo.dbproxy.stores;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterStore {
    String storeId();
}
