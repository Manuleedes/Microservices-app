package com.Lidigu.ecommerce.handler;

import java.util.Map;

public record errorResponse(
        Map<String, String> errors
        ) {
}
