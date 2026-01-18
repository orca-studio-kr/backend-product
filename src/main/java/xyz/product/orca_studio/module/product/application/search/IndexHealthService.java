package xyz.product.orca_studio.module.product.application.search;

import xyz.product.orca_studio.module.product.domain.ProductDocument;

import java.util.Optional;

public interface IndexHealthService {
    boolean isIndexHealthy();
    long getIndexedDocumentCount();
    Optional<ProductDocument> findById(Long productId);
    boolean isProductIndexed(Long productId);
}
