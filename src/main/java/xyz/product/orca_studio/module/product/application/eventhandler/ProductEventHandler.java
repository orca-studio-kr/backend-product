package xyz.product.orca_studio.module.product.application.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import xyz.product.orca_studio.module.product.application.event.*;
import xyz.product.orca_studio.module.product.application.search.ProductIndexingService;
import xyz.product.orca_studio.module.product.domain.*;
import xyz.product.orca_studio.module.product.domain.repository.ProductCategoryRepository;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventHandler {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final CacheEvictionHelper cacheEvictionHelper;
    private final ProductIndexingService productIndexingService;

    @EventListener
    @Transactional
    public void handleProductCreated(ProductCreatedEvent event) {
        log.info("상품 생성 이벤트 처리 시작. categoryId: {}", event.categoryId());

        ProductCategory category = productCategoryRepository.findById(event.categoryId())
            .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = Product.builder()
            .name(event.name())
            .description(event.description())
            .price(event.price())
            .category(category)
            .status(ProductStatus.ACTIVE)
            .build();

        updateProductDetails(product, event.images(), event.variants());

        Product savedProduct = productRepository.save(product);

        cacheEvictionHelper.evictAllProductListCache();

        applicationEventPublisher.publishEvent(new ProductIndexedEvent(savedProduct.getId()));
        log.info("상품 생성 완료 및 캐시 갱신. categoryId: {}", event.categoryId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void indexProductAfterCreation(ProductIndexedEvent event) {
        try {
            Product product = productRepository.findById(event.productId())
                .orElse(null);
            if (product != null) {
                productIndexingService.indexProduct(product.getId());
                log.info("상품 인덱싱 완료. productId: {}", product.getId());
            }
        } catch (Exception e) {
            log.error("상품 인덱싱 실패. productId: {}, error: {}", event.productId(), e.getMessage());
        }
    }

    @EventListener
    @Transactional
    public void handleProductUpdated(ProductUpdatedEvent event) {
        log.info("상품 수정 이벤트 처리 시작. productId: {}", event.productId());

        Product product = productRepository.findById(event.productId())
            .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        ProductCategory category = productCategoryRepository.findById(event.categoryId())
            .orElseThrow(() -> new IllegalArgumentException("카테고리가 존재하지 않습니다."));

        product.update(event.name(), event.description(), event.price(), category);

        updateProductDetails(product, event.images(), event.variants());

        cacheEvictionHelper.evictProductDetailCache(event.productId());
        cacheEvictionHelper.evictAllProductListCache();

        applicationEventPublisher.publishEvent(new ProductIndexedEvent(event.productId()));
        log.info("상품 수정 완료 및 캐시 갱신. productId: {}", event.productId());
    }

    @EventListener
    @Transactional
    public void handleProductDeleted(ProductDeletedEvent event) {
        log.info("상품 삭제 이벤트 처리 시작. productId: {}", event.productId());

        Product product = productRepository.findById(event.productId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepository.delete(product);

        cacheEvictionHelper.evictProductDetailCache(event.productId());
        cacheEvictionHelper.evictAllProductListCache();

        applicationEventPublisher.publishEvent(new ProductEvictEvent(event.productId()));
        log.info("상품 삭제 완료 및 캐시 갱신. productId: {}", event.productId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Async
    public void deleteProductIndexAfterDeletion(ProductEvictEvent event) {
        try {
            productIndexingService.deleteProductIndex(event.productId());
            log.info("상품 삭제 후 인덱스 삭제 완료. productId: {}", event.productId());
        } catch (Exception e) {
            log.error("상품 삭제 후 인덱스 삭제 실패. productId: {}, error: {}", event.productId(), e.getMessage());
        }
    }

    private void updateProductDetails(Product product, List<ProductImageInfo> images, List<ProductVariantInfo> variants) {
        if (images != null) {
            product.updateImages(images.stream()
                .map(imageInfo -> ProductImage.builder()
                    .imageUrl(imageInfo.imageUrl())
                    .order(imageInfo.order())
                    .division(ImageDivision.fromCode(imageInfo.imageDivision()))
                    .product(product)
                    .build())
                .collect(Collectors.toSet()));
        }

        if (variants != null) {
            product.updateVariants(variants.stream()
                .map(variantInfo -> ProductVariant.builder()
                    .color(variantInfo.color())
                    .size(variantInfo.size())
                    .stock(variantInfo.stock())
                    .additionalPrice(variantInfo.additionalPrice())
                    .product(product)
                    .build())
                .collect(Collectors.toSet()));
        }
    }
}
