package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranMarketModels;
import com.ernoxin.fipiranapi.mapper.FipiranMarketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FipiranMarketService {

    private final FipiranClient client;
    private final FipiranMarketMapper mapper;

    public FipiranMarketModels.MarketSnapshotResult getMarketSnapshot() {
        return mapper.toMarketSnapshotResult(client.get("/services/index/marketlast"));
    }
}
