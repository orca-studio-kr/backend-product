package xyz.product.orca_studio.module.product.application.search;

public interface ProductBulkIndexingService {
    void reindexAll();
    void reindexAllAsync();
    void reindexByCategory(Long categoryId);
}

