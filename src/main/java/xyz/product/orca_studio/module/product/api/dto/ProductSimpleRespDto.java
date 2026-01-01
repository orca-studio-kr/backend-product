package xyz.product.orca_studio.module.product.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Schema(description = "상품 목록 조회 응답 DTO")
@Getter
@Builder
public class ProductSimpleRespDto {
    @Schema(description = "상품 ID", example = "1")
    private Long productId;
    @Schema(description = "상품명", example = "멋진 티셔츠")
    private String name;
    @Schema(description = "상품 시작 가격", example = "29000")
    private BigDecimal startingPrice;
    @Schema(description = "상품 최고 가격 (옵션 포함)", example = "35000")
    private BigDecimal maxPrice;
    @Schema(description = "상품 썸네일 이미지 URL", example = "https://example.com/thumbnail.jpg")
    private String thumbnailUrl;
}

