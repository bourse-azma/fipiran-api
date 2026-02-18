package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranNewsModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranNewsMapper {

    private final FipiranMapperSupport support;

    public FipiranNewsModels.NewsListResult toNewsList(JsonNode root) {
        List<FipiranNewsModels.NewsItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(new FipiranNewsModels.NewsItem(
                    support.text(n, "agencyId"),
                    support.text(n, "agencyTitle"),
                    support.text(n, "title"),
                    support.text(n, "description"),
                    support.text(n, "link"),
                    support.text(n, "publichDate")
            ));
        }
        return new FipiranNewsModels.NewsListResult(
                new FipiranNewsModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }

    public FipiranNewsModels.AgencyListResult toAgencyList(JsonNode root) {
        List<FipiranNewsModels.AgencyItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(new FipiranNewsModels.AgencyItem(
                    support.text(n, "agencyId"),
                    support.text(n, "agencyTitle")
            ));
        }
        return new FipiranNewsModels.AgencyListResult(
                new FipiranNewsModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }
}
