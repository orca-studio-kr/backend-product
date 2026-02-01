package xyz.product.orca_studio.module.product.application.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import xyz.product.orca_studio.module.product.domain.ProductDocument;
import xyz.product.orca_studio.module.product.domain.repository.ProductSearchRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IndexHealthServiceImpl implements IndexHealthService {

    private final ProductSearchRepository productSearchRepository;

    @Override
    public boolean isIndexHealthy() {
        try {
            long count = productSearchRepository.count();
            log.debug("ElasticSearch 인덱스 상태 확인. 총 문서 수: {}", count);
            return true;
        } catch (Exception e) {
            log.error("ElasticSearch 인덱스 상태 확인 실패. error: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    public long getIndexedDocumentCount() {
        try {
            return productSearchRepository.count();
        } catch (Exception e) {
            log.error("인덱스 문서 수 조회 실패. error: {}", e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public Optional<ProductDocument> findById(Long productId) {
        try {
            return productSearchRepository.findById(productId);
        } catch (Exception e) {
            log.error("상품 인덱스 조회 실패. productId: {}, error: {}", productId, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean isProductIndexed(Long productId) {
        try {
            return productSearchRepository.existsById(productId);
        } catch (Exception e) {
            log.error("상품 인덱스 존재 여부 확인 실패. productId: {}, error: {}", productId, e.getMessage(), e);
            return false;
        }
    }
}

