package xyz.product.orca_studio.module.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import xyz.product.orca_studio.common.entity.BaseTimeEntity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE tbl_product SET deleted_at = NOW() WHERE product_id = ?")
@SQLRestriction("deleted_at IS NULL")
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @Builder
    public Product(ProductCategory category, String name, String description, BigDecimal price, ProductStatus status) {
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public void update(String name, String description, BigDecimal price, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public void updateVariants(List<ProductVariant> newVariants) {
        this.variants.clear();
        if (newVariants != null) {
            newVariants.forEach(variant -> {
                this.variants.add(variant);
                variant.setProduct(this);
            });
        }
    }

    public void updateImages(List<ProductImage> newImages) {
        this.images.clear();
        if (newImages != null) {
            newImages.forEach(image -> {
                this.images.add(image);
                image.setProduct(this);
            });
        }
    }

    public void changeStatus(ProductStatus status) {
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        this.status = this.status == null ? ProductStatus.ACTIVE : this.status;
    }
}
