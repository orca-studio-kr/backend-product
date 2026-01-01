package xyz.product.orca_studio.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.ProductImage;
import xyz.product.orca_studio.module.product.api.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductSimpleRespDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    imports = {ProductImage.class, Collectors.class}
)
public interface ProductMapper {

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "thumbnailUrl", expression = "java(product.getImages().isEmpty() ? null : product.getImages().stream().findFirst().map(ProductImage::getImageUrl).orElse(null))")
    @Mapping(target = "startingPrice", source = "price")
    @Mapping(target = "maxPrice", expression = "java(product.getPrice().add(product.getVariants().stream().map(xyz.product.orca_studio.module.product.domain.ProductVariant::getAdditionalPrice).max(java.math.BigDecimal::compareTo).orElse(java.math.BigDecimal.ZERO)))")
    ProductSimpleRespDto toSimpleDto(Product product);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "imageUrls", expression = "java(product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList()))")
    @Mapping(target = "priceInfo", source = "priceRespDto")
    ProductDetailRespDto toDetailDto(Product product, PriceRespDto priceRespDto);

    List<ProductSimpleRespDto> toSimpleDtoList(List<Product> products);
}
