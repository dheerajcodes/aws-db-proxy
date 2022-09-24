package com.spendgo.dbproxy.stores.exceptions;

public class DuplicateStoreIdException extends RuntimeException {
    public DuplicateStoreIdException(String id, Class<?> storeClass) {
        super("could not register store '" + storeClass.getName() + "' with existing id '" + id + "'");
    }
}
