package com.ernoxin.fipiranapi.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranInstrumentModels {

    public record Paged<T>(
            List<T> items,
            int page,
            int pageSize,
            long totalCount
    ) {
    }

    public record InstrumentCompareResult(
            Paged<InstrumentCompareItem> instruments) {
    }

    public record InstrumentCompareItem(
            String instrumentCode,
            String symbol,
            String name,
            double closingPrice,
            double lastPrice,
            double changePrice,
            long tradeCount,
            double tradeVolume,
            double tradeValue,
            JsonNode instrument,
            JsonNode transaction
    ) {
    }

    public record InstrumentSnapshotResult(
            String instrumentCode,
            JsonNode instrument,
            JsonNode transaction,
            List<JsonNode> bestLimits,
            List<JsonNode> clientTypes
    ) {
    }

    public record EfficiencyResult(
            String instrumentCode,
            String date,
            double weeklyEfficiency,
            double monthlyEfficiency,
            double quarterlyEfficiency,
            double sixMonthEfficiency,
            double annualEfficiency
    ) {
    }

    public record PeriodicStatisticsResult(
            String instrumentCode,
            String date,
            JsonNode weekly,
            JsonNode monthly,
            JsonNode quarterly,
            JsonNode sixMonth,
            JsonNode annual
    ) {
    }

    public record InstrumentHistoryResult(
            String instrumentCode,
            Paged<HistoryItem> history) {
    }

    public record HistoryItem(
            String instrumentCode,
            String transactionDate,
            double closingPrice,
            double adjustedPriceForward,
            double adjustedPriceBackward,
            double lastPrice,
            double changePrice,
            double minPrice,
            double maxPrice,
            double firstPrice,
            double yesterdayPrice,
            String lastStatus,
            long numberOfTransactions,
            double volume,
            double value,
            String time
    ) {
    }

    public record IndustryListResult(
            List<IndustryItem> industries) {
    }

    public record IndustryItem(
            String code,
            String title,
            JsonNode raw
    ) {
    }

    public record SubIndustryListResult(
            List<IndustryItem> subIndustries) {
    }
}
