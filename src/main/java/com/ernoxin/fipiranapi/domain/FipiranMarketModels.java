package com.ernoxin.fipiranapi.domain;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranMarketModels {

    public record MarketSnapshotResult(
            List<MarketSnapshot> markets) {
    }

    public record MarketSnapshot(
            int marketType,
            String marketTitle,
            String lastDataDate,
            double indexValue,
            double indexChange,
            double equalWeightedIndexValue,
            double equalWeightedIndexChange,
            String activityDateTime,
            long totalTrades,
            double totalTradeValue,
            double totalTradeVolume,
            double marketValue,
            boolean open
    ) {
    }
}
