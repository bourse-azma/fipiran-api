package com.ernoxin.fipiranapi.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranChartModels {

    public record IndexValueChartResult(
            String indexCode,
            boolean showAll,
            List<IndexValuePoint> points
    ) {
    }

    public record IndexValuePoint(
            String date,
            double indexValue,
            double dayMinValue,
            double dayMaxValue
    ) {
    }

    public record IndexEfficiencyChartResult(
            String indexCodes,
            boolean showAll,
            List<IndexEfficiencySeries> series
    ) {
    }

    public record IndexEfficiencySeries(
            String indexCode,
            String title,
            List<JsonNode> points
    ) {
    }

    public record FundNavChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundNavPoint> points
    ) {
    }

    public record FundNavPoint(
            String date,
            double issueNav,
            double cancelNav,
            double statisticalNav
    ) {
    }

    public record FundNetAssetChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundNetAssetPoint> points
    ) {
    }

    public record FundNetAssetPoint(
            String date,
            double netAsset,
            double unitsSubDay,
            double unitsRedDay
    ) {
    }

    public record FundEfficiencyChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundEfficiencyPoint> points
    ) {
    }

    public record FundEfficiencyPoint(
            String date,
            double dailyEfficiency,
            double weeklyEfficiency,
            double monthlyEfficiency,
            double quarterlyEfficiency,
            double sixMonthEfficiency,
            double annualEfficiency
    ) {
    }

    public record FundPortfolioChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundPortfolioPoint> points
    ) {
    }

    public record FundPortfolioPoint(
            String date,
            double stock,
            double bond,
            double deposit,
            double cash,
            double fiveBest,
            double other
    ) {
    }

    public record FundPossessionChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundPossessionPoint> points
    ) {
    }

    public record FundPossessionPoint(
            String date,
            double realInvestorPercent,
            double institutionalInvestorPercent
    ) {
    }

    public record FundAlphaBetaChartResult(
            String registrationNumber,
            boolean showAll,
            List<FundAlphaBetaPoint> points
    ) {
    }

    public record FundAlphaBetaPoint(
            String date,
            double alpha,
            double beta
    ) {
    }
}
