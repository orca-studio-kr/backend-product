-- Product 테이블 생성
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL COMMENT '상품명',
    description TEXT COMMENT '상품 설명',
    price DECIMAL(19, 2) NOT NULL COMMENT '가격',
    stock_quantity INT NOT NULL DEFAULT 0 COMMENT '재고 수량',
    category VARCHAR(100) COMMENT '카테고리',
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE' COMMENT '상품 상태 (ACTIVE, INACTIVE, DELETED)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    created_by VARCHAR(100) COMMENT '생성자',
    updated_by VARCHAR(100) COMMENT '수정자'
);

-- 인덱스 생성
CREATE INDEX idx_product_category ON product(category);
CREATE INDEX idx_product_status ON product(status);
CREATE INDEX idx_product_created_at ON product(created_at);
