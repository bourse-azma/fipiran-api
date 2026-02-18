package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.common.util.RequestParamSupport;
import com.ernoxin.fipiranapi.domain.FipiranIndexModels;
import com.ernoxin.fipiranapi.service.FipiranIndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/indexes")
@RequiredArgsConstructor
public class FipiranIndexController {
    private final FipiranIndexService service;

    @GetMapping
    public ApiResponse<FipiranIndexModels.IndexCompareResult> compareIndexes(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return ApiResponse.success(service.getIndexCompare(pageIndex, pageSize));
    }

    @GetMapping("/details")
    public ApiResponse<FipiranIndexModels.IndexDetailsResult> getIndexByCode(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "indexCode", required = false) String indexCode
    ) {
        return ApiResponse.success(service.getIndexByCode(RequestParamSupport.require("indexCode", indexCode, insCode)));
    }

    @GetMapping("/constituents")
    public ApiResponse<FipiranIndexModels.IndexConstituentsResult> getConstituents(
            @RequestParam(name = "idxCode", required = false) String idxCode,
            @RequestParam(name = "indexCode", required = false) String indexCode,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "tradeDate", required = false) String tradeDate,
            @RequestParam(required = false) Integer pageIndex,
            @RequestParam(required = false) Integer pageSize
    ) {
        return ApiResponse.success(service.getConstituents(
                RequestParamSupport.require("indexCode", indexCode, idxCode),
                RequestParamSupport.require("tradeDate", tradeDate, date),
                pageIndex,
                pageSize
        ));
    }
}
