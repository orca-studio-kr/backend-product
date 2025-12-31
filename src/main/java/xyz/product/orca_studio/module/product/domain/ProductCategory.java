package xyz.product.orca_studio.module.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import xyz.product.orca_studio.common.entity.BaseTimeEntity;

@Entity
@Getter
@Table(name = "tbl_product_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "parent")
@SQLDelete(sql = "UPDATE tbl_product_category SET deleted_at = NOW() WHERE category_id = ?")
@SQLRestriction("deleted_at IS NULL")
public class ProductCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductCategory> children = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Builder
    public ProductCategory(Long id, String name, ProductCategory parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public void update(String name, ProductCategory parent) {
        this.name = name;
        this.setParent(parent);
    }

    public void setParent(ProductCategory parent) {
        if (this.parent == parent) return;
        if (this.parent != null) this.parent.getChildren().remove(this);

        this.parent = parent;
        if (parent != null && !parent.getChildren().contains(this)) {
            parent.getChildren().add(this);
        }
    }
}
