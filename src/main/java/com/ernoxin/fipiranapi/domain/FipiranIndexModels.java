package com.ernoxin.fipiranapi.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranIndexModels {

    public record Paged<T>(
            List<T> items,
            int page,
            int pageSize,
            long totalCount
    ) {
    }

    public record IndexCompareResult(
            Paged<IndexSummary> indexes
    ) {
    }

    public record IndexSummary(
            String indexCode,
            String title,
            String date,
            String clock,
            int tradedSymbols,
            int positiveSymbols,
            int negativeSymbols,
            int symbolsWithoutChange,
            int symbolsNotTraded,
            int symbolsBooked,
            int symbolsSuspended,
            int totalSymbols,
            String maximumTime,
            String minimumTime,
            double lastValue,
            double dayMaximumValue,
            double dayMinimumValue,
            double cashReturnIndexValue,
            double changePercent,
            double changePercentByPreviousDay,
            double averageSymbolChangePercent,
            double averageReducedSymbolChangePercent,
            double averageIncreasedSymbolChangePercent,
            double dailyEfficiency,
            double weeklyEfficiency,
            double monthlyEfficiency,
            double quarterlyEfficiency,
            double sixMonthEfficiency,
            double annualEfficiency,
            JsonNode raw
    ) {
    }

    public record IndexDetailsResult(
            IndexSummary index
    ) {
    }

    public record IndexConstituentsResult(
            String indexCode,
            String date,
            Paged<IndexConstituent> constituents
    ) {
    }

    public record IndexConstituent(
            String instrumentCode,
            String symbol,
            String name,
            JsonNode instrument,
            JsonNode transaction
    ) {
    }
}
