package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.product.orca_studio.module.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
