package com.spendgo.dbproxy.controllers;

import com.spendgo.dbproxy.actions.ActionBuilder;
import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;
import com.spendgo.dbproxy.stores.StoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MainController {
    @Autowired
    StoreFactory storeFactory;

    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> queryDataStore(RequestEntity<String> request) throws Exception {
        StoreResult result;
        try {
            ActionBuilder actionBuilder = new ActionBuilder(request.getBody());
            StoreAction action = actionBuilder.build();
            result = storeFactory.getStore(action.getStore()).executeAction(action);
        } catch (Exception ex) {
            ex.printStackTrace();
            ArrayList<String> errors = new ArrayList<>();
            errors.add("\"" + ex.getMessage() + "\"");
            for (Throwable e = ex.getCause(); e != null; e = e.getCause()) {
                errors.add("\"" + e.getMessage() + "\"");
            }
            result = () -> "{\"error\":" + errors + "}";
        }
        return new ResponseEntity<>(result.toJSON(), HttpStatus.OK);
    }
}
