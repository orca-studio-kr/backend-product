package xyz.product.orca_studio.module.pricing.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PriceReqDto {
    private Long productId;
    private List<Long> variantIds;
}
