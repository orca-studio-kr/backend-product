package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.product.orca_studio.module.product.domain.Product;

/**
 * Product 리포지토리
 * 상품 데이터 접근을 위한 인터페이스
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
