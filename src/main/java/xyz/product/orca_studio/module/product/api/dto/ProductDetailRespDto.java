package xyz.product.orca_studio.module.product.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;

import java.util.List;

@Schema(description = "상품 상세 조회 응답 DTO")
public record ProductDetailRespDto(@Schema(description = "상품 ID", example = "1") Long productId,
                                   @Schema(description = "상품명", example = "멋진 티셔츠") String name,
                                   @Schema(description = "상품 설명", example = "아주 멋진 티셔츠입니다.") String description,
                                   @Schema(description = "상품 이미지 URL 목록") List<String> imageUrls,
                                   @Schema(description = "가격 정보") PriceRespDto priceInfo
) {

    @Builder
    public ProductDetailRespDto {
    }
}
