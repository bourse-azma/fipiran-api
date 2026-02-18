package com.ernoxin.fipiranapi.domain;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranNewsModels {

    public record Paged<T>(
            List<T> items,
            int page,
            int pageSize,
            long totalCount
    ) {
    }

    public record NewsListResult(
            Paged<NewsItem> news) {
    }

    public record NewsItem(
            String agencyId,
            String agencyTitle,
            String title,
            String description,
            String link,
            String publishedAt
    ) {
    }

    public record AgencyListResult(
            Paged<AgencyItem> agencies) {
    }

    public record AgencyItem(
            String agencyId,
            String agencyTitle
    ) {
    }
}
