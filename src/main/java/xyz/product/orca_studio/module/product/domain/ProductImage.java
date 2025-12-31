package xyz.product.orca_studio.module.product.domain;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import xyz.product.orca_studio.common.entity.BaseTimeEntity;

@Entity
@Getter
@Table(name = "tbl_product_img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "product")
@SQLDelete(sql = "UPDATE tbl_product_img SET deleted_at = NOW() WHERE img_id = ?")
@SQLRestriction("deleted_at IS NULL")
public class ProductImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "img_url", nullable = false)
    private String imageUrl;

    @Column(name = "`order`", nullable = false)
    private Integer order;

    @Enumerated(EnumType.STRING)
    @Column(name = "img_div", nullable = false, length = 50)
    private ImageDivision division;

    @Builder
    public ProductImage(Product product, String imageUrl, Integer order, ImageDivision division) {
        this.product = product;
        this.imageUrl = imageUrl;
        this.order = order;
        this.division = division;
    }
}

