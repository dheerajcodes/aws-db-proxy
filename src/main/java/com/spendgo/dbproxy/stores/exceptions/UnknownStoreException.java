package com.spendgo.dbproxy.stores.exceptions;

public class UnknownStoreException extends RuntimeException {
    public UnknownStoreException(String storeKey) {
        super("could not find any store registered with given key '" + storeKey + "'");
    }
}
