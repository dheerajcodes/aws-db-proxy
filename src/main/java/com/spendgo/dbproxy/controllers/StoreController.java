package com.spendgo.dbproxy.controllers;

import com.spendgo.dbproxy.actions.ActionBuilder;
import com.spendgo.dbproxy.actions.StoreAction;
import com.spendgo.dbproxy.results.StoreResult;
import com.spendgo.dbproxy.services.CipherService;
import com.spendgo.dbproxy.stores.StoreFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class StoreController {
    @Autowired
    StoreFactory storeFactory;
    @Autowired
    CipherService cipherService;

    @PostMapping(value = "/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> queryDataStore(@RequestBody Map<String, String> body) throws Exception {
        try {
            if (!body.containsKey("data")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            String data = body.get("data");
            String decodedJson = cipherService.decodeData(data);
            ActionBuilder actionBuilder = new ActionBuilder(decodedJson);
            StoreAction action = actionBuilder.build();
            StoreResult result = storeFactory.getStoreForAction(action).executeAction();
            String encodedData = cipherService.encodeData(result.toJSON());
            return ResponseEntity.status(HttpStatus.OK).body("{\"data\":\"" + encodedData + "\"}");
        } catch (Exception ex) {
            ex.printStackTrace();
            ArrayList<String> errors = new ArrayList<>();
            errors.add("\"" + ex.getMessage() + "\"");
            for (Throwable e = ex.getCause(); e != null; e = e.getCause()) {
                errors.add("\"" + e.getMessage() + "\"");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"errors\":" + errors + "}");
        }
    }
}
