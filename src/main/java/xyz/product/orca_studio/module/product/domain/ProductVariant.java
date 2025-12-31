package xyz.product.orca_studio.module.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import xyz.product.orca_studio.common.entity.BaseTimeEntity;

@Entity
@Getter
@Table(name = "tbl_product_variant")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "product")
@SQLDelete(sql = "UPDATE tbl_product_variant SET deleted_at = NOW() WHERE variant_id = ?")
@SQLRestriction("deleted_at IS NULL")
public class ProductVariant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "color", nullable = false, length = 50)
    private String color;

    @Column(name = "size", nullable = false, length = 50)
    private String size;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "additional_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal additionalPrice;

    @Builder
    public ProductVariant(Product product, String color, String size, Integer stock, BigDecimal additionalPrice) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.stock = stock;
        this.additionalPrice = additionalPrice;
    }
}

