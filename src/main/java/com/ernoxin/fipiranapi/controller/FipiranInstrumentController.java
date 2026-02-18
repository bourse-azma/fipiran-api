package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.common.util.RequestParamSupport;
import com.ernoxin.fipiranapi.domain.FipiranInstrumentModels;
import com.ernoxin.fipiranapi.service.FipiranInstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/instruments")
@RequiredArgsConstructor
public class FipiranInstrumentController {
    private final FipiranInstrumentService service;

    @GetMapping
    public ApiResponse<FipiranInstrumentModels.InstrumentCompareResult> compareInstruments(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String column,
            @RequestParam(name = "markettype", required = false) String marketTypeLegacy,
            @RequestParam(name = "marketType", required = false) String marketType,
            @RequestParam(name = "symboltype", required = false) String symbolTypeLegacy,
            @RequestParam(name = "symbolType", required = false) String symbolType,
            @RequestParam(name = "symbolstatus", required = false) String symbolStatusLegacy,
            @RequestParam(name = "symbolStatus", required = false) String symbolStatus,
            @RequestParam(required = false) String industry,
            @RequestParam(required = false) String industryCode,
            @RequestParam(required = false) String subIndustry,
            @RequestParam(required = false) String subIndustryCode,
            @RequestParam(required = false) String symbol,
            @RequestParam(required = false) String idxCode,
            @RequestParam(required = false) String indexCode,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String companyName
    ) {
        Map<String, Object> query = new LinkedHashMap<>();
        query.put("pageIndex", pageIndex);
        query.put("pageSize", pageSize);
        query.put("sort", sort);
        query.put("column", column);
        query.put("markettype", RequestParamSupport.firstNonBlank(marketType, marketTypeLegacy));
        query.put("symboltype", RequestParamSupport.firstNonBlank(symbolType, symbolTypeLegacy));
        query.put("symbolstatus", RequestParamSupport.firstNonBlank(symbolStatus, symbolStatusLegacy));
        query.put("industry", RequestParamSupport.firstNonBlank(industryCode, industry));
        query.put("subIndustry", RequestParamSupport.firstNonBlank(subIndustryCode, subIndustry));
        query.put("symbol", symbol);
        query.put("idxCode", RequestParamSupport.firstNonBlank(indexCode, idxCode));
        query.put("company", RequestParamSupport.firstNonBlank(companyName, company));
        return ApiResponse.success(service.compareInstruments(query));
    }

    @GetMapping("/snapshot")
    public ApiResponse<FipiranInstrumentModels.InstrumentSnapshotResult> getInstrument(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode
    ) {
        return ApiResponse.success(service.getInstrument(RequestParamSupport.require("instrumentCode", instrumentCode, insCode)));
    }

    @GetMapping("/efficiency")
    public ApiResponse<FipiranInstrumentModels.EfficiencyResult> getEfficiency(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode,
            @RequestParam String date
    ) {
        return ApiResponse.success(service.getEfficiency(
                RequestParamSupport.require("instrumentCode", instrumentCode, insCode),
                date
        ));
    }

    @GetMapping("/periodic-statistics")
    public ApiResponse<FipiranInstrumentModels.PeriodicStatisticsResult> getPeriodicStatistics(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode,
            @RequestParam String date
    ) {
        return ApiResponse.success(service.getPeriodicStatistics(
                RequestParamSupport.require("instrumentCode", instrumentCode, insCode),
                date
        ));
    }

    @GetMapping("/history")
    public ApiResponse<FipiranInstrumentModels.InstrumentHistoryResult> getHistory(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode,
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "30") int pageSize
    ) {
        return ApiResponse.success(service.getHistory(
                RequestParamSupport.require("instrumentCode", instrumentCode, insCode),
                pageIndex,
                pageSize
        ));
    }

    @GetMapping("/industries")
    public ApiResponse<FipiranInstrumentModels.IndustryListResult> getIndustries() {
        return ApiResponse.success(service.getIndustries());
    }

    @GetMapping("/sub-industries")
    public ApiResponse<FipiranInstrumentModels.SubIndustryListResult> getSubIndustries() {
        return ApiResponse.success(service.getSubIndustries());
    }
}
