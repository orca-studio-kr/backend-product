package xyz.product.orca_studio.module.pricing.application;

import xyz.product.orca_studio.module.pricing.api.dto.PriceReqDto;
import xyz.product.orca_studio.module.pricing.api.dto.PriceRespDto;

public interface PricingService {
    PriceRespDto calculatePrice(PriceReqDto priceReqDto);
}

