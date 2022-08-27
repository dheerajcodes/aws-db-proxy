package com.avisoftwares.dbproxy.controllers;

import com.avisoftwares.dbproxy.actions.ActionBuilder;
import com.avisoftwares.dbproxy.actions.StoreAction;
import com.avisoftwares.dbproxy.results.DatabaseStoreResult;
import com.avisoftwares.dbproxy.results.StoreResult;
import com.avisoftwares.dbproxy.stores.AbstractStore;
import com.avisoftwares.dbproxy.stores.AuroraStore;
import com.avisoftwares.dbproxy.stores.RedshiftStore;
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
    AuroraStore auroraStore;
    @Autowired
    RedshiftStore redshiftStore;

    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> queryDataStore(RequestEntity<String> request) throws Exception {
        ActionBuilder actionBuilder = new ActionBuilder(request.getBody());
        StoreAction action = actionBuilder.build();
        StoreResult result;
        switch (action.getStore()) {
            case "aurora":
                result = queryAuroraStore(action);
                break;
            case "redshift":
                result = queryRedshiftStore(action);
                break;
            default:
                result = () -> "{\"message\":\"unknown store value (allowed values - aurora and redshift)\"}";
        }
        return new ResponseEntity<>(result.toJSON(), HttpStatus.OK);
    }

    DatabaseStoreResult queryAuroraStore(StoreAction action) {
        return auroraStore.executeAction(action);
    }

    DatabaseStoreResult queryRedshiftStore(StoreAction action) {
        return redshiftStore.executeAction(action);
    }
}
