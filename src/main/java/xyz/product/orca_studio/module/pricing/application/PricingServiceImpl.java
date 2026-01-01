package xyz.product.orca_studio.module.pricing.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.product.orca_studio.module.pricing.api.dto.PriceReqDto;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;
import xyz.product.orca_studio.module.pricing.mapper.PricingMapper;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.ProductVariant;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;
import xyz.product.orca_studio.module.product.domain.repository.ProductVariantRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PricingServiceImpl implements PricingService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final PricingMapper pricingMapper;

    @Override
    public PriceRespDto calculatePrice(PriceReqDto priceReqDto) {
        Product product = productRepository.findById(priceReqDto.getProductId())
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<ProductVariant> selectedVariants = Collections.emptyList();
        if (priceReqDto.getVariantIds() != null && !priceReqDto.getVariantIds().isEmpty()) {
            selectedVariants = productVariantRepository.findAllById(priceReqDto.getVariantIds());
        }

        BigDecimal basePrice = product.getPrice();
        BigDecimal optionPrice = selectedVariants.stream()
            .map(ProductVariant::getAdditionalPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal originalPrice = basePrice.add(optionPrice);

        BigDecimal discountAmount = BigDecimal.ZERO; // TODO: 할인 및 프로모션 로직 적용
        BigDecimal finalPrice = originalPrice.subtract(discountAmount);

        return pricingMapper.toPriceRespDto(originalPrice, discountAmount, finalPrice);
    }
}
