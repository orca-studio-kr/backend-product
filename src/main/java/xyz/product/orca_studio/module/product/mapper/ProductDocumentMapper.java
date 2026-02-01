package xyz.product.orca_studio.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.product.orca_studio.module.product.domain.*;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface ProductDocumentMapper {

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "status", expression = "java(product.getStatus().name())")
    @Mapping(target = "thumbnailUrl", expression = "java(extractThumbnailUrl(product))")
    @Mapping(target = "variants", source = "variants")
    @Mapping(target = "price", expression = "java(product.getPrice() != null ? product.getPrice().doubleValue() : null)")
    @Mapping(target = "minPrice", expression = "java(calculateMinPrice(product) != null ? calculateMinPrice(product).doubleValue() : null)")
    @Mapping(target = "maxPrice", expression = "java(calculateMaxPrice(product) != null ? calculateMaxPrice(product).doubleValue() : null)")
    ProductDocument toDocument(Product product);

    @Mapping(target = "id", source = "id")
    ProductDocument.ProductVariantDocument toVariantDocument(ProductVariant variant);

    default String extractThumbnailUrl(Product product) {
        return product.getImages().stream()
            .filter(img -> img.getDivision() == ImageDivision.THUMBNAIL)
            .findFirst()
            .map(ProductImage::getImageUrl)
            .orElse(null);
    }

    default BigDecimal calculateMinPrice(Product product) {
        BigDecimal basePrice = product.getPrice();
        BigDecimal minAdditional = product.getVariants().stream()
            .map(ProductVariant::getAdditionalPrice)
            .min(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);
        return basePrice.add(minAdditional);
    }

    default BigDecimal calculateMaxPrice(Product product) {
        BigDecimal basePrice = product.getPrice();
        BigDecimal maxAdditional = product.getVariants().stream()
            .map(ProductVariant::getAdditionalPrice)
            .max(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);
        return basePrice.add(maxAdditional);
    }
}

