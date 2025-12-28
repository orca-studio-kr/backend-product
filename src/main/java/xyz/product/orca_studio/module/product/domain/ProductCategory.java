package xyz.product.orca_studio.module.product.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "tbl_product_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {

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

    // 연관관계 편의 메서드
    public void setParent(ProductCategory parent) {
        this.parent = parent;
        if (parent != null) {
            parent.getChildren().add(this);
        }
    }
}

