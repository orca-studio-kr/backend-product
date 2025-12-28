package xyz.product.orca_studio.module.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ImageDivision {
    THUMBNAIL("thumbnail", "썸네일 이미지"),
    DETAIL("detail", "상세 이미지");

    private final String code;
    private final String description;

    public static ImageDivision fromCode(String code) {
        return Arrays.stream(values())
            .filter(division -> division.getCode().equals(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid ImageDivision code: " + code));
    }
}

