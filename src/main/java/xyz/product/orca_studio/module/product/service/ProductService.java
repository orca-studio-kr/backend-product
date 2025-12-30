package xyz.product.orca_studio.module.product.service;

import xyz.product.orca_studio.module.product.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.dto.ProductSimpleRespDto;

import java.util.List;

public interface ProductService {
    List<ProductSimpleRespDto> getProducts(Long categoryId);
    ProductDetailRespDto getProduct(Long productId);
}

