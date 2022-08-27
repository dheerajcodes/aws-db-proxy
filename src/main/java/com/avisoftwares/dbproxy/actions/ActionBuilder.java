package com.avisoftwares.dbproxy.actions;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ActionBuilder {
    private final String actionJson;

    public ActionBuilder(String actionJson) {
        this.actionJson = actionJson;
    }

    public StoreAction build() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(actionJson, StoreAction.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
