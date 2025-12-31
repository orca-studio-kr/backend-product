package xyz.product.orca_studio.module.product.simulation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.product.orca_studio.module.product.application.event.ProductImageInfo;
import xyz.product.orca_studio.module.product.application.event.ProductVariantInfo;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
    private List<ProductImageInfo> images;
    private List<ProductVariantInfo> variants;
}
