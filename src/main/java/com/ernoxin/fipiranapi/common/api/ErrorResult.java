package com.ernoxin.fipiranapi.common.api;

import java.util.List;

public record ErrorResult(
        List<String> details
) {
}
