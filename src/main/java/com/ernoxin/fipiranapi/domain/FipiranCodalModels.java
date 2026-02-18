package com.ernoxin.fipiranapi.domain;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public final class FipiranCodalModels {

    public record Paged<T>(
            List<T> items,
            int page,
            int pageSize,
            long totalCount
    ) {
    }

    public record PublisherProfileResult(
            String instrumentCode,
            PublisherProfile profile
    ) {
    }

    public record PublisherProfile(
            String name,
            String activitySubject,
            String executiveManager,
            String financialManager,
            String isinCode,
            String nationalCode,
            String auditorName,
            String listedCapital,
            String financialYear,
            String website,
            String email,
            String telephone,
            String fax,
            String address,
            String shareOfficeAddress,
            JsonNode raw
    ) {
    }

    public record StatementsResult(
            String instrumentCode,
            Paged<StatementItem> statements
    ) {
    }

    public record StatementItem(
            String title,
            String publishDateTime,
            boolean audited,
            String letterName,
            String letterType,
            String letterKind,
            String period,
            String auditorId,
            String auditorName,
            String yearEndToDate,
            String pdfUrl,
            String excelUrl,
            String htmlUrl,
            String attachmentUrl,
            JsonNode attachments,
            JsonNode raw
    ) {
    }
}
