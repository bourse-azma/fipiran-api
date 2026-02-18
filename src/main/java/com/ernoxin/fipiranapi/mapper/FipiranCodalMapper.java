package com.ernoxin.fipiranapi.mapper;

import com.ernoxin.fipiranapi.common.util.FipiranMapperSupport;
import com.ernoxin.fipiranapi.domain.FipiranCodalModels;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FipiranCodalMapper {

    private final FipiranMapperSupport support;

    public FipiranCodalModels.PublisherProfileResult toPublisherProfile(String insCode, JsonNode root) {
        return new FipiranCodalModels.PublisherProfileResult(
                insCode,
                new FipiranCodalModels.PublisherProfile(
                        support.text(root, "name"),
                        support.text(root, "activitySubject"),
                        support.text(root, "executiveManager"),
                        support.text(root, "financialManager"),
                        support.text(root, "isinCode"),
                        support.text(root, "nationalCode"),
                        support.text(root, "auditorName"),
                        support.text(root, "listedCapital"),
                        support.text(root, "financialYear"),
                        support.text(root, "website"),
                        support.text(root, "email"),
                        support.text(root, "telNo"),
                        support.text(root, "faxNo"),
                        support.text(root, "address"),
                        support.text(root, "shareOfficeAddress"),
                        root
                )
        );
    }

    public FipiranCodalModels.StatementsResult toStatements(String insCode, JsonNode root) {
        List<FipiranCodalModels.StatementItem> items = new ArrayList<>();
        for (JsonNode n : root.path("items")) {
            items.add(new FipiranCodalModels.StatementItem(
                    support.text(n, "title"),
                    support.text(n, "publishDateTime"),
                    support.bool(n, "isAudited"),
                    support.text(n, "letterName"),
                    support.text(n, "letterType"),
                    support.text(n, "letterKind"),
                    support.text(n, "period"),
                    support.text(n, "auditorId"),
                    support.text(n, "auditorName"),
                    support.text(n, "yearEndToDate"),
                    support.text(n, "pdfUrl"),
                    support.text(n, "excelUrl"),
                    support.text(n, "htmlUrl"),
                    support.text(n, "attachmentUrl"),
                    n.path("attachments"),
                    n
            ));
        }

        return new FipiranCodalModels.StatementsResult(
                insCode,
                new FipiranCodalModels.Paged<>(items, support.page(root), support.pageSize(root), support.totalCount(root))
        );
    }
}
