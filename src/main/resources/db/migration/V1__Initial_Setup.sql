-- V1__Initial_Setup.sql

-- 상품 카테고리 테이블
CREATE TABLE tbl_product_category
(
    category_id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '카테고리 ID',
    parent_category_id  BIGINT COMMENT '부모 카테고리 ID',
    name                VARCHAR(255) NOT NULL COMMENT '카테고리명',
    INDEX idx_parent_category (parent_category_id)
);

-- 상품 테이블
CREATE TABLE tbl_product
(
    product_id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '상품 ID',
    category_id         BIGINT NOT NULL COMMENT '카테고리 ID',
    name                VARCHAR(255) NOT NULL COMMENT '상품명',
    description         TEXT COMMENT '상품설명',
    price               DECIMAL(10, 2) NOT NULL COMMENT '기본판매가',
    status              VARCHAR(50) NOT NULL DEFAULT 'ACTIVE' COMMENT '상품상태',
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tbl_product_category(category_id)
);

-- 상품 재고(옵션) 테이블
CREATE TABLE tbl_product_variant
(
    variant_id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '재고 ID',
    product_id          BIGINT NOT NULL COMMENT '상품 ID',
    color               VARCHAR(50) NOT NULL COMMENT '색상',
    size                VARCHAR(50) NOT NULL COMMENT '사이즈',
    stock               INT NOT NULL DEFAULT 0 COMMENT '재고수량',
    additional_price    DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '옵션추가금',
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
);

-- 상품 이미지 테이블
CREATE TABLE tbl_product_img
(
    img_id              BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '이미지 ID',
    product_id          BIGINT NOT NULL COMMENT '상품 ID',
    img_url             VARCHAR(255) NOT NULL COMMENT '이미지경로',
    `order`             INT NOT NULL COMMENT '이미지순서',
    img_div             VARCHAR(50) NOT NULL COMMENT '이미지구분',
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES tbl_product(product_id)
);


