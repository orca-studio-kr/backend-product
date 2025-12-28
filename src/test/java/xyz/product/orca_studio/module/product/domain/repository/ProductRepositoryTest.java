package xyz.product.orca_studio.module.product.domain.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.product.orca_studio.module.product.domain.Product;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ProductRepository 테스트
 * Flyway 초기 데이터 세팅 검증
 */
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testInitialDataLoading() {
        // Given: Flyway에 의해 초기 데이터가 로드됨
        
        // When: 모든 상품 조회
        List<Product> products = productRepository.findAll();
        
        // Then: 12개의 테스트 상품이 로드되어 있어야 함
        assertThat(products).isNotEmpty();
        assertThat(products.size()).isEqualTo(12);
        
        // 모든 상품이 필수 필드를 가지고 있어야 함
        assertThat(products).allMatch(p -> p.getName() != null);
        assertThat(products).allMatch(p -> p.getPrice() != null);
        assertThat(products).allMatch(p -> "SYSTEM".equals(p.getCreatedBy()));
    }
    
    @Test
    void testProductCategories() {
        // Given: Flyway에 의해 다양한 카테고리의 상품이 로드됨
        
        // When: 모든 상품 조회
        List<Product> products = productRepository.findAll();
        
        // Then: 다양한 카테고리가 존재해야 함
        long categoryCount = products.stream()
                .map(Product::getCategory)
                .distinct()
                .count();
        
        assertThat(categoryCount).isGreaterThanOrEqualTo(5); // 전자기기, 의류, 가구, 도서, 식품
    }
    
    @Test
    void testProductStatus() {
        // Given: Flyway에 의해 다양한 상태의 상품이 로드됨
        
        // When: 모든 상품 조회
        List<Product> products = productRepository.findAll();
        
        // Then: ACTIVE 상태의 상품이 대부분이어야 함
        long activeCount = products.stream()
                .filter(p -> "ACTIVE".equals(p.getStatus()))
                .count();
        
        assertThat(activeCount).isGreaterThan(0);
        
        // INACTIVE 상태의 상품도 있어야 함 (테스트용)
        long inactiveCount = products.stream()
                .filter(p -> "INACTIVE".equals(p.getStatus()))
                .count();
        
        assertThat(inactiveCount).isGreaterThan(0);
    }
}
