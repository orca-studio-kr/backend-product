package xyz.product.orca_studio.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.ProductImage;
import xyz.product.orca_studio.module.product.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.dto.ProductSimpleRespDto;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(
    componentModel = "spring",
    imports = {ProductImage.class, Collectors.class}
)
public interface ProductMapper {

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "thumbnailUrl", expression = "java(product.getImages().isEmpty() ? null : product.getImages().getFirst().getImageUrl())")
    ProductSimpleRespDto toSimpleDto(Product product);

    @Mapping(target = "productId", source = "id")
    @Mapping(target = "imageUrls", expression = "java(product.getImages().stream().map(ProductImage::getImageUrl).collect(Collectors.toList()))")
    ProductDetailRespDto toDetailDto(Product product);

    List<ProductSimpleRespDto> toSimpleDtoList(List<Product> products);
}

