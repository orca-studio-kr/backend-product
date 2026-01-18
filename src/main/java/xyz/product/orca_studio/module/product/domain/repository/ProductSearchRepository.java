package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import xyz.product.orca_studio.module.product.domain.ProductDocument;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductDocument, Long> {

    List<ProductDocument> findByCategoryId(Long categoryId);

    List<ProductDocument> findByStatus(String status);
}

