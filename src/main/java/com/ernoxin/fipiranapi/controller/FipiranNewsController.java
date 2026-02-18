package com.ernoxin.fipiranapi.controller;

import com.ernoxin.fipiranapi.common.api.ApiResponse;
import com.ernoxin.fipiranapi.domain.FipiranNewsModels;
import com.ernoxin.fipiranapi.service.FipiranNewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class FipiranNewsController {
    private final FipiranNewsService service;

    @GetMapping
    public ApiResponse<FipiranNewsModels.NewsListResult> listNews(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "20") int pageSize,
            @RequestParam(required = false) String agencyId
    ) {
        return ApiResponse.success(service.listNews(pageIndex, pageSize, agencyId));
    }

    @GetMapping("/agencies")
    public ApiResponse<FipiranNewsModels.AgencyListResult> listAgencies(
            @RequestParam(defaultValue = "1") int pageIndex,
            @RequestParam(defaultValue = "100") int pageSize
    ) {
        return ApiResponse.success(service.listAgencies(pageIndex, pageSize));
    }
}
