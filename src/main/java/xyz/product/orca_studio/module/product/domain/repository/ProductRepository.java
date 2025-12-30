package xyz.product.orca_studio.module.product.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.product.orca_studio.module.product.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
        SELECT p
        FROM Product p
        LEFT JOIN FETCH p.images i
        WHERE i.order = 0 OR i.id IS NULL
    """)
    List<Product> findAllWithThumbnail();

    @Query("""
        SELECT p
        FROM Product p
        LEFT JOIN FETCH p.images i
        WHERE p.category.id = :categoryId
        AND (i.order = 0 OR i.id IS NULL)
    """)
    List<Product> findAllByCategoryIdWithThumbnail(@Param("categoryId") Long categoryId);
}
