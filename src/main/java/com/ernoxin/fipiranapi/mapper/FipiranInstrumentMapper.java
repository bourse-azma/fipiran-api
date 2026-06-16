package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.common.util.JalaliDateTimeFormatter;
import com.ernoxin.fipiranapi.domain.FipiranInstrumentModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranInstrumentMapper {

    private final FipiranMapperSupport support;

    public FipiranInstrumentModels.InstrumentCompareResult toCompareResult(JsonNode root) {
        List<FipiranInstrumentModels.InstrumentCompareItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            JsonNode instrument = n.path("instruments");
            JsonNode tx = n.path("instrumentTransactions");
            items.add(new FipiranInstrumentModels.InstrumentCompareItem(
                    support.text(instrument, "insCode"),
                    support.text(instrument, "smallSymbolName"),
                    support.text(instrument, "title"),
                    support.dbl(tx, "closingPrice"),
                    support.dbl(tx, "lastTransaction"),
                    support.dbl(tx, "changePrice"),
                    support.lng(tx, "numberOfTransactions"),
                    support.dbl(tx, "numberOfVolume"),
                    support.dbl(tx, "transactionValue"),
                    instrument,
                    tx
            ));
        }

        return new FipiranInstrumentModels.InstrumentCompareResult(
                new FipiranInstrumentModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranInstrumentModels.InstrumentSnapshotResult toSnapshotResult(String insCode, JsonNode root) {
        JsonNode firstItem = root.path("item").isArray() && !root.path("item").isEmpty()
                ? root.path("item").get(0)
                : root.path("item");

        JsonNode transactionNode = firstItem.path("instrumentTransaction");

        return new FipiranInstrumentModels.InstrumentSnapshotResult(
                insCode,
                resolveSnapshotLastTradeAt(transactionNode),
                firstItem.path("instrument"),
                transactionNode,
                support.list(firstItem.path("instrument5BestLimits")),
                support.list(firstItem.path("instrumentClientTypes"))
        );
    }

    private String resolveSnapshotLastTradeAt(JsonNode transactionNode) {
        String transactionDate = support.text(transactionNode, "transactionDate");
        Integer hEven = support.integerOrNull(transactionNode, "hEven");
        if (transactionDate.isBlank()) {
            return null;
        }

        if (hEven != null && hEven > 0) {
            return JalaliDateTimeFormatter.fromIsoDateAndHeven(transactionDate, hEven);
        }

        return JalaliDateTimeFormatter.normalizeDisplayDateTime(transactionDate);
    }

    public FipiranInstrumentModels.EfficiencyResult toEfficiencyResult(String insCode, String date, JsonNode root) {
        return new FipiranInstrumentModels.EfficiencyResult(
                insCode,
                date,
                support.dbl(root, "weeklyEfficiency"),
                support.dbl(root, "monthlyEfficiency"),
                support.dbl(root, "quarterlyEfficiency"),
                support.dbl(root, "sixMonthEfficiency"),
                support.dbl(root, "annualEfficiency")
        );
    }

    public FipiranInstrumentModels.PeriodicStatisticsResult toPeriodicStatsResult(String insCode, String date, JsonNode root) {
        return new FipiranInstrumentModels.PeriodicStatisticsResult(
                insCode,
                date,
                root.path("weekly"),
                root.path("monthly"),
                root.path("quarterly"),
                root.path("sixMonth"),
                root.path("annual")
        );
    }

    public FipiranInstrumentModels.InstrumentHistoryResult toHistoryResult(String insCode, JsonNode root) {
        List<FipiranInstrumentModels.HistoryItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(new FipiranInstrumentModels.HistoryItem(
                    support.text(n, "insCode"),
                    support.text(n, "transactionDate"),
                    support.dbl(n, "closingPrice"),
                    support.dbl(n, "adjPriceForward"),
                    support.dbl(n, "adjPriceBackward"),
                    support.dbl(n, "lastTransaction"),
                    support.dbl(n, "changePrice"),
                    support.dbl(n, "priceMin"),
                    support.dbl(n, "priceMax"),
                    support.dbl(n, "priceFirst"),
                    support.dbl(n, "priceYesterday"),
                    support.text(n, "lastStatus"),
                    support.lng(n, "numberOfTransactions"),
                    support.dbl(n, "numberOfVolume"),
                    support.dbl(n, "transactionValue"),
                    support.text(n, "hEven")
            ));
        }

        return new FipiranInstrumentModels.InstrumentHistoryResult(
                insCode,
                new FipiranInstrumentModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranInstrumentModels.IndustryListResult toIndustryListResult(JsonNode root) {
        List<FipiranInstrumentModels.IndustryItem> items = new ArrayList<>();
        for (JsonNode n : root) {
            items.add(new FipiranInstrumentModels.IndustryItem(
                    firstExistingText(n, "industryGroupCode", "industry", "industryCode", "code", "id"),
                    firstExistingText(n, "name", "title", "industryTitle"),
                    n
            ));
        }
        return new FipiranInstrumentModels.IndustryListResult(items);
    }

    public FipiranInstrumentModels.SubIndustryListResult toSubIndustryListResult(JsonNode root) {
        List<FipiranInstrumentModels.IndustryItem> items = new ArrayList<>();
        for (JsonNode n : root) {
            items.add(new FipiranInstrumentModels.IndustryItem(
                    firstExistingText(n, "industrySubCode", "subIndustry", "subIndustryCode", "code", "id"),
                    firstExistingText(n, "name", "title", "subIndustryTitle"),
                    n
            ));
        }
        return new FipiranInstrumentModels.SubIndustryListResult(items);
    }

    private String firstExistingText(JsonNode node, String... candidates) {
        for (String field : candidates) {
            JsonNode selected = node.path(field);
            if (!selected.isMissingNode() && !selected.isNull()) {
                String text = selected.asText("").trim();
                if (!text.isEmpty()) {
                    return text;
                }
            }
        }
        return "";
    }
}
