package xyz.product.orca_studio.module.product.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(indexName = "products", createIndex = true)
@Setting(settingPath = "/elasticsearch/product-settings.json")
public class ProductDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String name;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String description;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Double)
    private Double minPrice;

    @Field(type = FieldType.Double)
    private Double maxPrice;

    @Field(type = FieldType.Long)
    private Long categoryId;

    @Field(type = FieldType.Keyword)
    private String categoryName;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Keyword)
    private String thumbnailUrl;

    @Field(type = FieldType.Nested)
    private Set<ProductVariantDocument> variants;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    private LocalDateTime updatedAt;

    @Builder
    public ProductDocument(Long id, String name, String description, Double price,
                           Double minPrice, Double maxPrice, Long categoryId,
                           String categoryName, String status, String thumbnailUrl,
                           Set<ProductVariantDocument> variants, LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.status = status;
        this.thumbnailUrl = thumbnailUrl;
        this.variants = variants;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ProductVariantDocument {

        @Field(type = FieldType.Long)
        private Long id;

        @Field(type = FieldType.Keyword)
        private String color;

        @Field(type = FieldType.Keyword)
        private String size;

        @Field(type = FieldType.Integer)
        private Integer stock;

        @Field(type = FieldType.Double)
        private Double additionalPrice;

        @Builder
        public ProductVariantDocument(Long id, String color, String size, Integer stock,
                                      Double additionalPrice) {
            this.id = id;
            this.color = color;
            this.size = size;
            this.stock = stock;
            this.additionalPrice = additionalPrice;
        }
    }
}

