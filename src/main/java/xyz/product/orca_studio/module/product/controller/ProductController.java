package xyz.product.orca_studio.module.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import xyz.product.orca_studio.common.dto.CommRespDto;
import xyz.product.orca_studio.module.product.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.dto.ProductSimpleRespDto;
import xyz.product.orca_studio.module.product.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<CommRespDto<List<ProductSimpleRespDto>>> getProducts(Long categoryId) {
        List<ProductSimpleRespDto> products = productService.getProducts(categoryId);
        return ResponseEntity.ok(CommRespDto.success(products, "상품 목록 조회에 성공했습니다."));
    }

    @Override
    public ResponseEntity<CommRespDto<ProductDetailRespDto>> getProduct(Long productId) {
        ProductDetailRespDto product = productService.getProduct(productId);
        return ResponseEntity.ok(CommRespDto.success(product, "상품 상세 조회에 성공했습니다."));
    }
}

