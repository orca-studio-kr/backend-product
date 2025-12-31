package xyz.product.orca_studio.module.product.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "상품 목록 조회 응답 DTO")
public record ProductSimpleRespDto(@Schema(description = "상품 ID", example = "1") Long productId,
                                   @Schema(description = "상품명", example = "멋진 티셔츠") String name,
                                   @Schema(description = "상품 가격", example = "29000") Integer price,
                                   @Schema(description = "상품 썸네일 이미지 URL", example = "https://example.com/thumbnail.jpg") String thumbnailUrl) {

    @Builder
    public ProductSimpleRespDto(Long productId, String name, Integer price, String thumbnailUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.thumbnailUrl = thumbnailUrl;
    }
}

