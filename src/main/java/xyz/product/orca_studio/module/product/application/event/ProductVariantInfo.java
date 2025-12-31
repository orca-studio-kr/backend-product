package xyz.product.orca_studio.module.product.application.event;

import java.math.BigDecimal;

public record ProductVariantInfo(
        String color,
        String size,
        Integer stock,
        BigDecimal additionalPrice
) {
}
