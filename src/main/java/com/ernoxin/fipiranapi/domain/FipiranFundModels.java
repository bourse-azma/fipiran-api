package com.ernoxin.fipiranapi.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranFundModels {

    public record Paged<T>(
            List<T> items,
            int page,
            int pageSize,
            long totalCount
    ) {
    }

    public record FundCompareResult(
            Paged<FundSummary> funds
    ) {
    }

    public record FundSummary(
            String registrationNumber,
            String name,
            String fundType,
            String investmentType,
            String rankLastUpdate,
            String initiationDate,
            double fundSize,
            double dailyEfficiency,
            double weeklyEfficiency,
            double monthlyEfficiency,
            double quarterlyEfficiency,
            double sixMonthEfficiency,
            double annualEfficiency,
            double efficiency,
            double issueNav,
            double cancelNav,
            double statisticalNav,
            double dividendIntervalPeriod,
            String date,
            String navAnnouncementAt,
            double netAsset,
            double investedUnits,
            List<String> websiteAddresses,
            String manager,
            String managerSeoRegisterNo,
            String auditor,
            String custodian,
            String guarantor,
            boolean completed,
            double fiveBest,
            double stock,
            double bond,
            double other,
            double cash,
            double deposit,
            double fundUnit,
            double commodity,
            int fundPublisher,
            JsonNode raw
    ) {
    }

    public record FundTypesResult(
            Paged<FundTypeItem> fundTypes
    ) {
    }

    public record FundTypeItem(
            String fundType,
            String title,
            boolean active
    ) {
    }

    public record FundDetailsResult(
            String registrationNumber,
            FundDetails details,
            JsonNode raw
    ) {
    }

    public record FundDetails(
            String registrationNumber,
            String officialRegistrationNumber,
            String name,
            String initiationDate,
            double fundSize,
            String fundType,
            String executiveManager,
            String lastModificationTime,
            String date,
            String navAnnouncementAt,
            double dailyEfficiency,
            double weeklyEfficiency,
            double monthlyEfficiency,
            double quarterlyEfficiency,
            double sixMonthEfficiency,
            double annualEfficiency,
            double dividendIntervalPeriod,
            double estimatedEarningRate,
            long institutionalInvestorCount,
            double institutionalInvestorPercent,
            double legalInvestorPercent,
            String marketMaker,
            double naturalInvestorPercent,
            long realInvestorCount,
            double realInvestorPercent,
            double netAsset,
            double investedUnits,
            double unitsRedeemedDay,
            double unitsRedeemedFromFirst,
            double unitsSubscribedDay,
            double unitsSubscribedFromFirst,
            double efficiency,
            double issueNav,
            double cancelNav,
            double statisticalNav,
            double fiveBest,
            double stock,
            double bond,
            double other,
            double cash,
            double deposit,
            double fundUnit,
            double commodity,
            String investmentType,
            String manager,
            String guarantorSeoRegisterNo,
            String auditor,
            List<String> websiteAddresses,
            String custodian,
            String guarantor,
            String investmentManager,
            double beta,
            double alpha,
            String seoRegisterDate,
            String registerDate,
            String nationalId,
            boolean completed,
            String instrumentCode,
            int fundPublisher,
            List<JsonNode> mutualFundLicenses,
            JsonNode raw
    ) {
    }
}
