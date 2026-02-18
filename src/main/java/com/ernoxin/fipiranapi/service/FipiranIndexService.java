package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranIndexModels;
import com.ernoxin.fipiranapi.mapper.FipiranIndexMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranIndexService {

    private final FipiranClient client;
    private final FipiranIndexMapper mapper;

    public FipiranIndexModels.IndexCompareResult getIndexCompare(int pageIndex, int pageSize) {
        return mapper.toIndexCompare(client.get("/services/index/indexcompare", Map.of(
                "pageIndex", pageIndex,
                "pageSize", pageSize
        )));
    }

    public FipiranIndexModels.IndexDetailsResult getIndexByCode(String insCode) {
        return mapper.toIndexDetails(client.get("/services/index/getindex", Map.of("insCode", insCode)));
    }

    public FipiranIndexModels.IndexConstituentsResult getConstituents(String idxCode, String date, Integer pageIndex, Integer pageSize) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("idxCode", idxCode);
        query.put("date", date);
        query.put("pageIndex", pageIndex);
        query.put("pageSize", pageSize);
        return mapper.toIndexConstituents(idxCode, date, client.get("/services/index/instruments", query));
    }
}
