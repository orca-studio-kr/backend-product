package xyz.product.orca_studio.module.product.application.search;

import xyz.product.orca_studio.module.product.domain.Product;

import java.util.List;

public interface ProductIndexingService {
    void indexProduct(Long productId);
    void indexProducts(List<Product> products);
    void deleteProductIndex(Long productId);
    void deleteAllIndices();
}

