package com.spendgo.dbproxy.results;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class DatabaseStoreResult implements StoreResult {
    private final List<Map<String, Object>> resultMapList;
    private final ObjectMapper mapper;

    public DatabaseStoreResult(List<Map<String, Object>> resultMapList) {
        this.resultMapList = resultMapList;
        mapper = new ObjectMapper();
    }

    @Override
    public String toJSON() throws Exception {
        return mapper.writeValueAsString(resultMapList);
    }
}
