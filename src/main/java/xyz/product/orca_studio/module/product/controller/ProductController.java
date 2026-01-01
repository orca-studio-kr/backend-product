package xyz.product.orca_studio.module.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.product.orca_studio.common.dto.CommRespDto;
import xyz.product.orca_studio.module.product.api.ProductApi;
import xyz.product.orca_studio.module.product.api.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductSimpleRespDto;
import xyz.product.orca_studio.module.product.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<CommRespDto<List<ProductSimpleRespDto>>> getProducts(Long categoryId) {
        log.info("상품 목록 조회 요청. categoryId: {}", categoryId);
        List<ProductSimpleRespDto> products = productService.getProducts(categoryId);
        log.info("상품 목록 조회 완료. 조회된 상품 수: {}", products.size());
        return ResponseEntity.ok(CommRespDto.success(products, "상품 목록 조회에 성공했습니다."));
    }

    @Override
    public ResponseEntity<CommRespDto<ProductDetailRespDto>> getProduct(Long productId, List<Long> variantIds) {
        log.info("상품 상세 조회 요청. productId: {}, variantIds: {}", productId, variantIds);
        ProductDetailRespDto product = productService.getProduct(productId, variantIds);
        log.info("상품 상세 조회 완료. productId: {}", productId);
        return ResponseEntity.ok(CommRespDto.success(product, "상품 상세 조회에 성공했습니다."));
    }
}
