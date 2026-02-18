package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranNewsModels;
import com.ernoxin.fipiranapi.mapper.FipiranNewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranNewsService {

    private final FipiranClient client;
    private final FipiranNewsMapper mapper;

    public FipiranNewsModels.NewsListResult listNews(int pageIndex, int pageSize, String agencyId) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("pageIndex", pageIndex);
        query.put("pageSize", pageSize);
        query.put("agencyId", agencyId);
        return mapper.toNewsList(client.get("/services/news/list", query));
    }

    public FipiranNewsModels.AgencyListResult listAgencies(int pageIndex, int pageSize) {
        return mapper.toAgencyList(client.get("/services/news/agencylist", Map.of(
                "pageIndex", pageIndex,
                "pageSize", pageSize
        )));
    }
}
