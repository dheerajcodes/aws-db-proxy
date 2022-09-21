package com.avisoftwares.dbproxy.controllers;

import com.avisoftwares.dbproxy.actions.ActionBuilder;
import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.results.StoreResult;
import com.avisoftwares.dbproxy.stores.StoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
            result = () -> "{\"error\":\"" + ex.getMessage() + "\"}";
        }
        return new ResponseEntity<>(result.toJSON(), HttpStatus.OK);
    }
}
