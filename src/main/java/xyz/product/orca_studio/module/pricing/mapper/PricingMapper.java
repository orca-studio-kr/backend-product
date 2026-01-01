package xyz.product.orca_studio.module.pricing.mapper;

import org.mapstruct.Mapper;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface PricingMapper {
    PriceRespDto toPriceRespDto(BigDecimal originalPrice, BigDecimal discountAmount, BigDecimal finalPrice);
}

