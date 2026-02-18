package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.common.util.RequestParamSupport;
import com.ernoxin.fipiranapi.domain.FipiranFundModels;
import com.ernoxin.fipiranapi.service.FipiranFundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class FipiranFundController {
    private final FipiranFundService service;

    @GetMapping
    public ApiResponse<FipiranFundModels.FundCompareResult> compareFunds(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "25") int pageSize,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String column,
            @RequestParam(required = false) String fundType,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String name
    ) {
        return ApiResponse.success(service.compareFunds(service.buildCompareQuery(
                pageIndex,
                pageSize,
                sort,
                column,
                fundType,
                RequestParamSupport.firstNonBlank(search, name)
        )));
    }

    @GetMapping("/types")
    public ApiResponse<FipiranFundModels.FundTypesResult> getFundTypes(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "100") int pageSize
    ) {
        return ApiResponse.success(service.getFundTypes(pageIndex, pageSize));
    }

    @GetMapping("/details")
    public ApiResponse<FipiranFundModels.FundDetailsResult> getFund(
            @RequestParam(name = "regNo", required = false) String regNo,
            @RequestParam(name = "registrationNumber", required = false) String registrationNumber
    ) {
        return ApiResponse.success(service.getFund(RequestParamSupport.require("registrationNumber", registrationNumber, regNo)));
    }
}
