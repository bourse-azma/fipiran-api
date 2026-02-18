package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranIndexModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranIndexMapper {

    private final FipiranMapperSupport support;

    public FipiranIndexModels.IndexCompareResult toIndexCompare(JsonNode root) {
        List<FipiranIndexModels.IndexSummary> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(toIndexSummary(n));
        }
        return new FipiranIndexModels.IndexCompareResult(
                new FipiranIndexModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranIndexModels.IndexDetailsResult toIndexDetails(JsonNode root) {
        return new FipiranIndexModels.IndexDetailsResult(toIndexSummary(root.path("item")));
    }

    public FipiranIndexModels.IndexConstituentsResult toIndexConstituents(String idxCode, String date, JsonNode root) {
        List<FipiranIndexModels.IndexConstituent> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            JsonNode instrument = n.path("instruments");
            JsonNode tx = n.path("instrumentTransactions");
            items.add(new FipiranIndexModels.IndexConstituent(
                    support.text(instrument, "insCode"),
                    support.text(instrument, "smallSymbolName"),
                    support.text(instrument, "title"),
                    instrument,
                    tx
            ));
        }

        return new FipiranIndexModels.IndexConstituentsResult(
                idxCode,
                date,
                new FipiranIndexModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    private FipiranIndexModels.IndexSummary toIndexSummary(JsonNode n) {
        return new FipiranIndexModels.IndexSummary(
                support.text(n, "insCode"),
                support.text(n, "title"),
                support.text(n, "date"),
                support.text(n, "clock"),
                support.integer(n, "numberSymbolsTraded"),
                support.integer(n, "numberSymbolsIncreased"),
                support.integer(n, "reducedNumberSymbols"),
                support.integer(n, "numberSymbolsWithoutChange"),
                support.integer(n, "numberSymbolsNotTraded"),
                support.integer(n, "numberBookingSymbols"),
                support.integer(n, "numberSuspendedSymbols"),
                support.integer(n, "totalNumberSymbols"),
                support.text(n, "timeMaximum"),
                support.text(n, "timeMinimum"),
                support.dbl(n, "lastValueDay"),
                support.dbl(n, "maximumValueDayIndex"),
                support.dbl(n, "minimumValueDayIndex"),
                support.dbl(n, "cashReturnIndexValue"),
                support.dbl(n, "percentageIndexChanges"),
                support.dbl(n, "percentageIndexChangesDayByPreviousDay"),
                support.dbl(n, "percentageAverageChangeSymbols"),
                support.dbl(n, "averagePercentageChangeSymbolsReduced"),
                support.dbl(n, "percentageMeanSymbolChangeIncreased"),
                support.dbl(n, "dailyEfficiency"),
                support.dbl(n, "weeklyEfficiency"),
                support.dbl(n, "monthlyEfficiency"),
                support.dbl(n, "quarterlyEfficiency"),
                support.dbl(n, "sixMonthEfficiency"),
                support.dbl(n, "annualEfficiency"),
                n
        );
    }
}
