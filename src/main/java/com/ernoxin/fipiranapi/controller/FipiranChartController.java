package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.common.util.RequestParamSupport;
import com.ernoxin.fipiranapi.domain.FipiranChartModels;
import com.ernoxin.fipiranapi.service.FipiranChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/charts")
@RequiredArgsConstructor
public class FipiranChartController {
    private final FipiranChartService service;

    @GetMapping("/indexes/value")
    public ApiResponse<FipiranChartModels.IndexValueChartResult> getIndexValueChart(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "indexCode", required = false) String indexCode,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getIndexValueChart(
                RequestParamSupport.require("indexCode", indexCode, insCode),
                showAll
        ));
    }

    @GetMapping("/indexes/efficiency")
    public ApiResponse<FipiranChartModels.IndexEfficiencyChartResult> getIndexEfficiencyChart(
            @RequestParam(name = "insCodes", required = false) String insCodes,
            @RequestParam(name = "indexCodes", required = false) String indexCodes,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getIndexEfficiencyChart(
                RequestParamSupport.require("indexCodes", indexCodes, insCodes),
                showAll
        ));
    }

    @GetMapping("/funds/nav")
    public ApiResponse<FipiranChartModels.FundNavChartResult> getFundNavChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getFundNavChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }

    @GetMapping("/funds/net-assets")
    public ApiResponse<FipiranChartModels.FundNetAssetChartResult> getFundNetAssetChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getFundNetAssetChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }

    @GetMapping("/funds/efficiency")
    public ApiResponse<FipiranChartModels.FundEfficiencyChartResult> getFundEfficiencyChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getFundEfficiencyChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }

    @GetMapping("/funds/portfolio")
    public ApiResponse<FipiranChartModels.FundPortfolioChartResult> getPortfolioChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getPortfolioChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }

    @GetMapping("/funds/possession")
    public ApiResponse<FipiranChartModels.FundPossessionChartResult> getPossessionChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getPossessionChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }

    @GetMapping("/funds/alpha-beta")
    public ApiResponse<FipiranChartModels.FundAlphaBetaChartResult> getFundAlphaBetaChart(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber,
            @RequestParam(defaultValue = "true") boolean showAll
    ) {
        return ApiResponse.success(service.getAlphaBetaChart(
                RequestParamSupport.require("registrationNumber", registrationNumber, regNo),
                showAll
        ));
    }
}
