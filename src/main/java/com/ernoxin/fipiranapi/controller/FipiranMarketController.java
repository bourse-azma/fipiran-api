package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.domain.FipiranMarketModels;
import com.ernoxin.fipiranapi.service.FipiranMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/market")
@RequiredArgsConstructor
public class FipiranMarketController {
    private final FipiranMarketService service;

    @GetMapping("/last")
    public ApiResponse<FipiranMarketModels.MarketSnapshotResult> getMarketSnapshot() {
        return ApiResponse.success(service.getMarketSnapshot());
    }
}
