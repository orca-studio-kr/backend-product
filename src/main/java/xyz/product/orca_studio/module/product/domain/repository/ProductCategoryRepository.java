package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.product.orca_studio.module.product.domain.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}

