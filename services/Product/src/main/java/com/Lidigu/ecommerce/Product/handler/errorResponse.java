package com.Lidigu.ecommerce.Product.handler;

import java.util.Map;

public record errorResponse(
        Map<String, String> errors
        ) {
}
