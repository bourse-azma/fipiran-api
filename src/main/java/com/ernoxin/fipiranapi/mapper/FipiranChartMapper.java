package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranChartModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranChartMapper {

    private final FipiranMapperSupport support;

    public FipiranChartModels.IndexValueChartResult toIndexValueChart(String insCode, boolean showAll, JsonNode root) {
        List<FipiranChartModels.IndexValuePoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.IndexValuePoint(
                    support.text(n, "date"),
                    support.dbl(n, "indexValue"),
                    support.dbl(n, "leastIndexTheDayValue"),
                    support.dbl(n, "mostIndexTheDayValue")
            ));
        }
        return new FipiranChartModels.IndexValueChartResult(insCode, showAll, points);
    }

    public FipiranChartModels.IndexEfficiencyChartResult toIndexEfficiencyChart(String insCodes, boolean showAll, JsonNode root) {
        List<FipiranChartModels.IndexEfficiencySeries> series = new ArrayList<>();
        for (JsonNode n : root) {
            series.add(new FipiranChartModels.IndexEfficiencySeries(
                    support.text(n, "insCode"),
                    support.text(n, "title"),
                    support.list(n.path("items"))
            ));
        }
        return new FipiranChartModels.IndexEfficiencyChartResult(insCodes, showAll, series);
    }

    public FipiranChartModels.FundNavChartResult toFundNavChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundNavPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundNavPoint(
                    support.text(n, "date"),
                    support.dbl(n, "issueNav"),
                    support.dbl(n, "cancelNav"),
                    support.dbl(n, "statisticalNav")
            ));
        }
        return new FipiranChartModels.FundNavChartResult(regNo, showAll, points);
    }

    public FipiranChartModels.FundNetAssetChartResult toFundNetAssetChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundNetAssetPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundNetAssetPoint(
                    support.text(n, "date"),
                    support.dbl(n, "netAsset"),
                    support.dbl(n, "unitsSubDAY"),
                    support.dbl(n, "unitsRedDAY")
            ));
        }
        return new FipiranChartModels.FundNetAssetChartResult(regNo, showAll, points);
    }

    public FipiranChartModels.FundEfficiencyChartResult toFundEfficiencyChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundEfficiencyPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundEfficiencyPoint(
                    support.text(n, "date"),
                    support.dbl(n, "dailyEfficiency"),
                    support.dbl(n, "weeklyEfficiency"),
                    support.dbl(n, "monthlyEfficiency"),
                    support.dbl(n, "quarterlyEfficiency"),
                    support.dbl(n, "sixMonthEfficiency"),
                    support.dbl(n, "annualEfficiency")
            ));
        }
        return new FipiranChartModels.FundEfficiencyChartResult(regNo, showAll, points);
    }

    public FipiranChartModels.FundPortfolioChartResult toFundPortfolioChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundPortfolioPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundPortfolioPoint(
                    support.text(n, "date"),
                    support.dbl(n, "stock"),
                    support.dbl(n, "bond"),
                    support.dbl(n, "deposit"),
                    support.dbl(n, "cash"),
                    support.dbl(n, "fiveBest"),
                    support.dbl(n, "other")
            ));
        }
        return new FipiranChartModels.FundPortfolioChartResult(regNo, showAll, points);
    }

    public FipiranChartModels.FundPossessionChartResult toFundPossessionChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundPossessionPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundPossessionPoint(
                    support.text(n, "date"),
                    support.dbl(n, "retInvPercent"),
                    support.dbl(n, "insInvPercent")
            ));
        }
        return new FipiranChartModels.FundPossessionChartResult(regNo, showAll, points);
    }

    public FipiranChartModels.FundAlphaBetaChartResult toFundAlphaBetaChart(String regNo, boolean showAll, JsonNode root) {
        List<FipiranChartModels.FundAlphaBetaPoint> points = new ArrayList<>();
        for (JsonNode n : root) {
            points.add(new FipiranChartModels.FundAlphaBetaPoint(
                    support.text(n, "date"),
                    support.dbl(n, "alpha"),
                    support.dbl(n, "beta")
            ));
        }
        return new FipiranChartModels.FundAlphaBetaChartResult(regNo, showAll, points);
    }
}
