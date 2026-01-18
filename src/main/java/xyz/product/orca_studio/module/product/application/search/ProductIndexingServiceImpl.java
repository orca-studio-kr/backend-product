package xyz.product.orca_studio.module.product.application.search;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.ProductDocument;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;
import xyz.product.orca_studio.module.product.domain.repository.ProductSearchRepository;
import xyz.product.orca_studio.module.product.mapper.ProductDocumentMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductIndexingServiceImpl implements ProductIndexingService {

    private final ProductSearchRepository productSearchRepository;
    private final ProductDocumentMapper productDocumentMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public void indexProduct(Long productId) {
        try {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("인덱싱하려는 상품이 존재하지 않습니다."));

            ProductDocument document = productDocumentMapper.toDocument(product);
            productSearchRepository.save(document);
            log.info("상품 인덱싱 완료. productId: {}", productId);
        } catch (Exception e) {
            log.error("상품 인덱싱 실패. productId: {}, error: {}", productId, e.getMessage(), e);
            throw new ProductIndexingException("상품 인덱싱에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void indexProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            log.warn("인덱싱할 상품 목록이 비어있습니다.");
            return;
        }

        try {
            List<ProductDocument> documents = products.stream()
                .map(productDocumentMapper::toDocument)
                .toList();
            productSearchRepository.saveAll(documents);
            log.info("상품 대량 인덱싱 완료. count: {}", products.size());
        } catch (Exception e) {
            log.error("상품 대량 인덱싱 실패. count: {}, error: {}", products.size(), e.getMessage(), e);
            throw new ProductIndexingException("상품 대량 인덱싱에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteProductIndex(Long productId) {
        try {
            productSearchRepository.deleteById(productId);
            log.info("상품 인덱스 삭제 완료. productId: {}", productId);
        } catch (Exception e) {
            log.error("상품 인덱스 삭제 실패. productId: {}, error: {}", productId, e.getMessage(), e);
            throw new ProductIndexingException("상품 인덱스 삭제에 실패했습니다.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteAllIndices() {
        try {
            productSearchRepository.deleteAll();
            log.info("모든 상품 인덱스 삭제 완료");
        } catch (Exception e) {
            log.error("모든 상품 인덱스 삭제 실패. error: {}", e.getMessage(), e);
            throw new ProductIndexingException("모든 상품 인덱스 삭제에 실패했습니다.", e);
        }
    }
}

