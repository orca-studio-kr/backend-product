package xyz.product.orca_studio.module.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.product.orca_studio.module.pricing.api.dto.PriceReqDto;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;
import xyz.product.orca_studio.module.pricing.application.PricingService;
import xyz.product.orca_studio.module.pricing.mapper.PricingMapper;
import xyz.product.orca_studio.module.product.api.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductSimpleRespDto;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;
import xyz.product.orca_studio.module.product.mapper.ProductMapper;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PricingService pricingService;
    private final PricingMapper pricingMapper;

    @Override
    @Cacheable(value = "products", key = "'category::' + (#categoryId != null ? #categoryId : 'all')")
    public List<ProductSimpleRespDto> getProducts(Long categoryId) {
        log.debug("상품 목록 조회 시작. categoryId: {}", categoryId);
        List<Product> products;
        if (categoryId == null) {
            log.debug("전체 상품 조회");
            products = productRepository.findAllWithThumbnail();
        } else {
            log.debug("카테고리별 상품 조회. categoryId: {}", categoryId);
            products = productRepository.findAllByCategoryIdWithThumbnail(categoryId);
        }
        log.debug("상품 목록 조회 완료. 조회된 상품 수: {}", products.size());
        return productMapper.toSimpleDtoList(products);
    }

    @Override
    @Cacheable(value = "product", key = "'id::' + #productId + '::variants::' + (#variantIds != null ? #variantIds.toString() : 'none')")
    public ProductDetailRespDto getProduct(Long productId, List<Long> variantIds) {
        log.debug("상품 상세 조회 시작. productId: {}", productId);
        Product product = productRepository.findProductWithDetails(productId)
            .orElseThrow(() -> {
                log.error("상품을 찾을 수 없습니다. id: {}", productId);
                return new EntityNotFoundException("상품을 찾을 수 없습니다. id: " + productId);
            });

        PriceRespDto priceRespDto;
        if (variantIds != null && !variantIds.isEmpty()) {
            PriceReqDto priceReqDto = new PriceReqDto(productId, variantIds);
            priceRespDto = pricingService.calculatePrice(priceReqDto);
        } else {
            priceRespDto = pricingMapper.toPriceRespDto(product.getPrice(), BigDecimal.ZERO, product.getPrice());
        }

        log.debug("상품 상세 조회 완료. productId: {}", productId);
        return productMapper.toDetailDto(product, priceRespDto);
    }
}
