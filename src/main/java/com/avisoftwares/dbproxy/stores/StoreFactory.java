package com.avisoftwares.dbproxy.stores;

import com.avisoftwares.dbproxy.stores.exceptions.UnknownStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StoreFactory {
    private static final Map<String, Class<? extends Store>> registeredStores;
    public static final String STORE_KEY_REDSHIFT = "redshift";
    public static final String STORE_KEY__AURORA = "aurora";

    static {
        registeredStores = new HashMap<>();
        registeredStores.put(STORE_KEY_REDSHIFT, RedshiftStore.class);
        registeredStores.put(STORE_KEY__AURORA, AuroraStore.class);
        // Register more stores here
    }

    @Autowired
    private ApplicationContext context;

    public Store getStore(String storeKey) throws UnknownStoreException {
        Class<? extends Store> storeClass = registeredStores.get(storeKey);
        if (storeClass == null) {
            throw new UnknownStoreException(storeKey);
        }
        return context.getBean(storeClass);
    }
}
