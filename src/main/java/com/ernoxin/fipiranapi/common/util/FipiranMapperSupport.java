package com.ernoxin.fipiranapi.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FipiranMapperSupport {

    public String text(JsonNode node, String field) {
        return node.path(field).asText("");
    }

    public int integer(JsonNode node, String field) {
        return node.path(field).asInt(0);
    }

    public Integer integerOrNull(JsonNode node, String field) {
        JsonNode selected = node.path(field);
        if (selected.isMissingNode() || selected.isNull()) {
            return null;
        }

        if (selected.isNumber()) {
            return selected.asInt();
        }

        String text = selected.asText("").trim();
        if (text.isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public long lng(JsonNode node, String field) {
        return node.path(field).asLong(0L);
    }

    public double dbl(JsonNode node, String field) {
        return node.path(field).asDouble(0D);
    }

    public boolean bool(JsonNode node, String field) {
        return node.path(field).asBoolean(false);
    }

    public List<JsonNode> list(JsonNode arrayNode) {
        List<JsonNode> items = new ArrayList<>();
        if (arrayNode != null && arrayNode.isArray()) {
            arrayNode.forEach(items::add);
        }
        return items;
    }

    public List<String> textList(JsonNode node) {
        List<String> items = new ArrayList<>();
        if (node == null || node.isMissingNode() || node.isNull()) {
            return items;
        }

        if (node.isArray()) {
            node.forEach(item -> {
                String text = item.asText("").trim();
                if (!text.isEmpty()) {
                    items.add(text);
                }
            });
            return items;
        }

        String single = node.asText("").trim();
        if (!single.isEmpty()) {
            items.add(single);
        }
        return items;
    }

    public int page(JsonNode node) {
        return integer(node, "pageNumber");
    }

    public int pageSize(JsonNode node) {
        return integer(node, "pageSize");
    }

    public long totalCount(JsonNode node) {
        return lng(node, "totalCount");
    }
}
