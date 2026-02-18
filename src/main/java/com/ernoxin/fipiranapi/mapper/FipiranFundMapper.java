package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranFundModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranFundMapper {

    private final FipiranMapperSupport support;

    public FipiranFundModels.FundCompareResult toFundCompare(JsonNode root) {
        List<FipiranFundModels.FundSummary> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(toFundSummary(n));
        }
        return new FipiranFundModels.FundCompareResult(
                new FipiranFundModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranFundModels.FundTypesResult toFundTypes(JsonNode root) {
        List<FipiranFundModels.FundTypeItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(new FipiranFundModels.FundTypeItem(
                    support.text(n, "fundType"),
                    support.text(n, "name"),
                    support.bool(n, "isActive")
            ));
        }
        return new FipiranFundModels.FundTypesResult(
                new FipiranFundModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranFundModels.FundDetailsResult toFundDetails(String regNo, JsonNode root) {
        JsonNode item = root.path("item");
        return new FipiranFundModels.FundDetailsResult(regNo, toFundDetailsItem(item), item);
    }

    private FipiranFundModels.FundSummary toFundSummary(JsonNode n) {
        return new FipiranFundModels.FundSummary(
                firstExistingText(n, "regNo", "registrationNumber"),
                support.text(n, "name"),
                support.text(n, "fundType"),
                support.text(n, "typeOfInvest"),
                support.text(n, "rankLastUpdate"),
                support.text(n, "initiationDate"),
                support.dbl(n, "fundSize"),
                support.dbl(n, "dailyEfficiency"),
                support.dbl(n, "weeklyEfficiency"),
                support.dbl(n, "monthlyEfficiency"),
                support.dbl(n, "quarterlyEfficiency"),
                support.dbl(n, "sixMonthEfficiency"),
                support.dbl(n, "annualEfficiency"),
                support.dbl(n, "efficiency"),
                support.dbl(n, "issueNav"),
                support.dbl(n, "cancelNav"),
                support.dbl(n, "statisticalNav"),
                support.dbl(n, "dividendIntervalPeriod"),
                support.text(n, "date"),
                support.dbl(n, "netAsset"),
                support.dbl(n, "investedUnits"),
                support.textList(n.path("websiteAddress")),
                support.text(n, "manager"),
                support.text(n, "managerSeoRegisterNo"),
                support.text(n, "auditor"),
                support.text(n, "custodian"),
                support.text(n, "guarantor"),
                support.bool(n, "isCompleted"),
                support.dbl(n, "fiveBest"),
                support.dbl(n, "stock"),
                support.dbl(n, "bond"),
                support.dbl(n, "other"),
                support.dbl(n, "cash"),
                support.dbl(n, "deposit"),
                support.dbl(n, "fundUnit"),
                support.dbl(n, "commodity"),
                support.integer(n, "fundPublisher"),
                n
        );
    }

    private FipiranFundModels.FundDetails toFundDetailsItem(JsonNode item) {
        return new FipiranFundModels.FundDetails(
                firstExistingText(item, "regNo", "registrationNumber"),
                support.text(item, "registrationNumber"),
                support.text(item, "name"),
                support.text(item, "initiationDate"),
                support.dbl(item, "fundSize"),
                support.text(item, "fundType"),
                support.text(item, "executiveManager"),
                support.text(item, "lastModificationTime"),
                support.text(item, "date"),
                support.dbl(item, "dailyEfficiency"),
                support.dbl(item, "weeklyEfficiency"),
                support.dbl(item, "monthlyEfficiency"),
                support.dbl(item, "quarterlyEfficiency"),
                support.dbl(item, "sixMonthEfficiency"),
                support.dbl(item, "annualEfficiency"),
                support.dbl(item, "dividendIntervalPeriod"),
                support.dbl(item, "estimatedEarningRate"),
                support.lng(item, "insInvNo"),
                support.dbl(item, "insInvPercent"),
                support.dbl(item, "legalPercent"),
                support.text(item, "marketMaker"),
                support.dbl(item, "naturalPercent"),
                support.lng(item, "retInvNo"),
                support.dbl(item, "retInvPercent"),
                support.dbl(item, "netAsset"),
                support.dbl(item, "investedUnits"),
                support.dbl(item, "unitsRedDAY"),
                support.dbl(item, "unitsRedFromFirst"),
                support.dbl(item, "unitsSubDAY"),
                support.dbl(item, "unitsSubFromFirst"),
                support.dbl(item, "efficiency"),
                support.dbl(item, "issueNav"),
                support.dbl(item, "cancelNav"),
                support.dbl(item, "statisticalNav"),
                support.dbl(item, "fiveBest"),
                support.dbl(item, "stock"),
                support.dbl(item, "bond"),
                support.dbl(item, "other"),
                support.dbl(item, "cash"),
                support.dbl(item, "deposit"),
                support.dbl(item, "fundUnit"),
                support.dbl(item, "commodity"),
                support.text(item, "typeOfInvest"),
                support.text(item, "manager"),
                support.text(item, "guarantorSeoRegisterNo"),
                support.text(item, "auditor"),
                support.textList(item.path("websiteAddress")),
                support.text(item, "custodian"),
                support.text(item, "guarantor"),
                support.text(item, "investmentManager"),
                support.dbl(item, "beta"),
                support.dbl(item, "alpha"),
                support.text(item, "seoRegisterDate"),
                support.text(item, "registerDate"),
                support.text(item, "nationalId"),
                support.bool(item, "isCompleted"),
                support.text(item, "insCode"),
                support.integer(item, "fundPublisher"),
                support.list(item.path("mutualFundLicenses")),
                item
        );
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
