package xyz.product.orca_studio.module.product.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.product.orca_studio.common.dto.CommRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductSimpleRespDto;

import java.util.List;

@Tag(name = "상품", description = "상품 관련 API")
@RequestMapping("/api/v1/products")
public interface ProductApi {

    @Operation(summary = "상품 목록 조회", description = "전체 또는 카테고리별 상품 목록을 조회합니다.")
    @GetMapping
    ResponseEntity<CommRespDto<List<ProductSimpleRespDto>>> getProducts(
        @Parameter(description = "카테고리 ID") @RequestParam(required = false) Long categoryId);

    @Operation(summary = "상품 상세 조회", description = "특정 상품의 상세 정보를 조회합니다.")
    @GetMapping("/{productId}")
    ResponseEntity<CommRespDto<ProductDetailRespDto>> getProduct(
        @Parameter(description = "상품 ID") @PathVariable Long productId);
}

