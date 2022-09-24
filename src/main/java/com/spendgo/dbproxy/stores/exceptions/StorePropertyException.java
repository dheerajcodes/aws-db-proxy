package com.spendgo.dbproxy.stores.exceptions;

public class StorePropertyException extends RuntimeException {
    public StorePropertyException(String propertyKey, String message) {
        super(propertyKey + " property error - " + message);
    }
}
