package xyz.product.orca_studio.module.product.application.event;

import java.math.BigDecimal;
import java.util.List;

public record ProductUpdatedEvent(
    Long productId,
    String name,
    String description,
    BigDecimal price,
    Long categoryId,
    List<ProductImageInfo> images,
    List<ProductVariantInfo> variants
) {
}

