package xyz.product.orca_studio.module.product.application.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductBulkIndexingServiceImpl implements ProductBulkIndexingService {

    private final ProductRepository productRepository;
    private final ProductIndexingService productIndexingService;

    private static final int BATCH_SIZE = 100;

    @Override
    @Transactional(readOnly = true)
    public void reindexAll() {
        log.info("전체 상품 재인덱싱 시작");

        try {
            long totalCount = productRepository.count();
            int totalPages = (int) Math.ceil((double) totalCount / BATCH_SIZE);

            log.info("전체 상품 수: {}, 배치 수: {}", totalCount, totalPages);

            for (int i = 0; i < totalPages; i++) {
                Pageable pageable = PageRequest.of(i, BATCH_SIZE);
                Page<Product> productPage = productRepository.findAll(pageable);

                List<Product> products = productPage.getContent();
                if (!products.isEmpty()) {
                    productIndexingService.indexProducts(products);
                    log.info("배치 {} / {} 완료 ({} 건)", i + 1, totalPages, products.size());
                }
            }

            log.info("전체 상품 재인덱싱 완료. 총 {}건", totalCount);
        } catch (Exception e) {
            log.error("전체 상품 재인덱싱 실패. error: {}", e.getMessage(), e);
            throw new ProductIndexingException("전체 상품 재인덱싱에 실패했습니다.", e);
        }
    }

    @Override
    @Async
    @Transactional(readOnly = true)
    public void reindexAllAsync() {
        log.info("비동기 전체 상품 재인덱싱 시작");

        try {
            reindexAll();
        } catch (Exception e) {
            log.error("비동기 재인덱싱 실패", e);
            throw new ProductIndexingException("비동기 재인덱싱에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void reindexByCategory(Long categoryId) {
        log.info("카테고리별 상품 재인덱싱 시작. categoryId: {}", categoryId);

        try {
            int page = 0;
            Page<Product> productPage;
            int totalProcessed = 0;

            do {
                Pageable pageable = PageRequest.of(page, BATCH_SIZE);
                productPage = productRepository.findAll(pageable);

                List<Product> products = productPage.getContent().stream()
                    .filter(p -> p.getCategory().getId().equals(categoryId))
                    .toList();

                if (!products.isEmpty()) {
                    productIndexingService.indexProducts(products);
                    totalProcessed += products.size();
                    log.info("카테고리 {} 배치 {} 완료 ({} 건)", categoryId, page + 1, products.size());
                }

                page++;
            } while (productPage.hasNext());

            log.info("카테고리별 상품 재인덱싱 완료. categoryId: {}, 총 {}건", categoryId, totalProcessed);
        } catch (Exception e) {
            log.error("카테고리별 재인덱싱 실패. categoryId: {}, error: {}", categoryId, e.getMessage(), e);
            throw new ProductIndexingException("카테고리별 재인덱싱에 실패했습니다.", e);
        }
    }
}

