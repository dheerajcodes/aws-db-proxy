package com.spendgo.dbproxy.stores;

import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.stores.exceptions.DuplicateStoreIdException;
import com.spendgo.dbproxy.stores.exceptions.UnknownStoreException;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StoreFactory {
    private static final Map<String, Class<? extends Store>> registeredStores;

    static {
        // Find and register all stores classes
        registeredStores = new HashMap<>();
        Reflections reflections = new Reflections(StoreFactory.class.getPackage().getName());
        Set<Class<? extends Store>> storeClasses = reflections.getSubTypesOf(Store.class);
        for (Class<? extends Store> storeClass : storeClasses) {
            RegisterStore annotation = storeClass.getDeclaredAnnotation(RegisterStore.class);
            if (annotation == null) continue;
            String storeId = annotation.storeId();
            if (registeredStores.containsKey(storeId)) {
                throw new DuplicateStoreIdException(storeId, storeClass);
            }
            registeredStores.put(storeId, storeClass);
        }
    }

    @Autowired
    private ApplicationContext context;

    public Store getStoreForAction(StoreAction action) throws Exception {
        String storeKey = action.getStore();
        Class<? extends Store> storeClass = registeredStores.get(storeKey);
        if (storeClass == null) {
            throw new UnknownStoreException(storeKey);
        }
        Constructor<? extends Store> constructor = storeClass.getConstructor(ApplicationContext.class, StoreAction.class);
        return constructor.newInstance(context, action);
    }
}
