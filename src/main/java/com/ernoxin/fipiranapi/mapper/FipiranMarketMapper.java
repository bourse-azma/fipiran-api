package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranMarketModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranMarketMapper {

    private final FipiranMapperSupport support;

    public FipiranMarketModels.MarketSnapshotResult toMarketSnapshotResult(JsonNode root) {
        List<FipiranMarketModels.MarketSnapshot> items = new ArrayList<>();
        for (JsonNode n : root) {
            items.add(new FipiranMarketModels.MarketSnapshot(
                    support.integer(n, "type"),
                    support.text(n, "typeTitle"),
                    support.text(n, "lastDataDate"),
                    support.dbl(n, "indexLastValue"),
                    support.dbl(n, "indexChange"),
                    support.dbl(n, "indexEqualWeightedLastValue"),
                    support.dbl(n, "indexEqualWeightedChange"),
                    support.text(n, "marketActivityDeven"),
                    support.lng(n, "marketActivityZTotTran"),
                    support.dbl(n, "marketActivityQTotCap"),
                    support.dbl(n, "marketActivityQTottran"),
                    support.dbl(n, "marketValue"),
                    support.bool(n, "isOpen")
            ));
        }
        return new FipiranMarketModels.MarketSnapshotResult(items);
    }
}
