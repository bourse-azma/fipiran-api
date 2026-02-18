package com.ernoxin.fipiranapi.service;

import com.ernoxin.fipiranapi.client.FipiranClient;
import com.ernoxin.fipiranapi.domain.FipiranCodalModels;
import com.ernoxin.fipiranapi.mapper.FipiranCodalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FipiranCodalService {

    private final FipiranClient client;
    private final FipiranCodalMapper mapper;

    public FipiranCodalModels.PublisherProfileResult getPublisher(String insCode) {
        return mapper.toPublisherProfile(insCode,
                client.get("/services/codal/publisher", Map.of("insCode", insCode)));
    }

    public FipiranCodalModels.StatementsResult getStatements(String insCode, int pageIndex, int pageSize) {
        return mapper.toStatements(insCode,
                client.get("/services/codal/statements", Map.of(
                        "insCode", insCode,
                        "pageIndex", pageIndex,
                        "pageSize", pageSize
                )));
    }
}
