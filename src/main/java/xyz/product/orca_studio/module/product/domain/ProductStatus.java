package xyz.product.orca_studio.module.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    ACTIVE("ACTIVE", "판매중"),
    INACTIVE("INACTIVE", "비활성"),
    SOLD_OUT("SOLD_OUT", "품절");

    private final String statusCode;
    private final String description;

    public static ProductStatus fromStatusCode(String statusCode) {
        return Arrays.stream(values())
            .filter(status -> status.getStatusCode().equals(statusCode))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid ProductStatus code: " + statusCode));
    }
}
