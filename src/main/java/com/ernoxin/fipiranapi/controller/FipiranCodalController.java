package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.common.util.RequestParamSupport;
import com.ernoxin.fipiranapi.domain.FipiranCodalModels;
import com.ernoxin.fipiranapi.service.FipiranCodalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/codal")
@RequiredArgsConstructor
public class FipiranCodalController {
    private final FipiranCodalService service;

    @GetMapping("/publisher")
    public ApiResponse<FipiranCodalModels.PublisherProfileResult> getPublisher(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode
    ) {
        return ApiResponse.success(service.getPublisher(RequestParamSupport.require("instrumentCode", instrumentCode, insCode)));
    }

    @GetMapping("/statements")
    public ApiResponse<FipiranCodalModels.StatementsResult> getStatements(
            @RequestParam(name = "insCode", required = false) String insCode,
            @RequestParam(name = "instrumentCode", required = false) String instrumentCode,
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "20") int pageSize
    ) {
        return ApiResponse.success(service.getStatements(
                RequestParamSupport.require("instrumentCode", instrumentCode, insCode),
                pageIndex,
                pageSize
        ));
    }
}
