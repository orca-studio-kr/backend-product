package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.product.orca_studio.module.product.domain.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
}
