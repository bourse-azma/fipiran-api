package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranFundModels;
import com.ernoxin.fipiranapi.mapper.FipiranFundMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranFundService {

    private final FipiranClient client;
    private final FipiranFundMapper mapper;

    public FipiranFundModels.FundCompareResult compareFunds(Map<String, Object> query) {
        return mapper.toFundCompare(client.get("/services/fund/fundcompare", query));
    }

    public FipiranFundModels.FundTypesResult getFundTypes(int pageIndex, int pageSize) {
        return mapper.toFundTypes(client.get("/services/fund/fundtype", Map.of(
                "pageIndex", pageIndex,
                "pageSize", pageSize
        )));
    }

    public FipiranFundModels.FundDetailsResult getFund(String regNo) {
        return mapper.toFundDetails(regNo, client.get("/services/fund/getfund", Map.of("regno", regNo)));
    }

    public Map<String, Object> buildCompareQuery(
            int pageIndex,
            int pageSize,
            String sort,
            String column,
            String fundType,
            String search
    ) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("pageIndex", pageIndex);
        query.put("pageSize", pageSize);
        query.put("sort", sort);
        query.put("column", column);
        query.put("fundType", fundType);
        query.put("name", search);
        return query;
    }
}
