package xyz.product.orca_studio.module.product.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Schema(description = "상품 상세 조회 응답 DTO")
public class ProductDetailRespDto {

    @Schema(description = "상품 ID", example = "1")
    private final Long productId;

    @Schema(description = "상품명", example = "멋진 티셔츠")
    private final String name;

    @Schema(description = "상품 가격", example = "29000")
    private final Integer price;

    @Schema(description = "상품 설명", example = "아주 멋진 티셔츠입니다.")
    private final String description;

    @Schema(description = "상품 이미지 URL 목록")
    private final List<String> imageUrls;

    @Builder
    public ProductDetailRespDto(Long productId, String name, Integer price, String description, List<String> imageUrls) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrls = imageUrls;
    }
}

