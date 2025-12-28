-- V2__Insert_Initial_Data.sql

-- 카테고리 추가
INSERT INTO tbl_product_category (category_id, name, parent_category_id) VALUES (1, 'TOP', NULL);

-- [ORCA 21~25 디자인 상품군]
-- CREEPSTER LOGO HOODIE (GRAY, BLACK)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'CREEPSTER LOGO HOODIE_GRAY', 78000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '그레이', 'M', 100), (LAST_INSERT_ID(), '그레이', 'L', 100), (LAST_INSERT_ID(), '그레이', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'CREEPSTER LOGO HOODIE_BLACK', 78000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

-- ORCA X BABU T (BLACK, WHITE)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA X BABU T_BLACK', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA X BABU T_WHITE', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '화이트', 'M', 100), (LAST_INSERT_ID(), '화이트', 'L', 100), (LAST_INSERT_ID(), '화이트', 'XL', 100);

-- Gothic O LOGO SWEATSHIRT (BLACK, GRAY)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'Gothic O LOGO SWEATSHIRT_BLACK', 62000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'Gothic O LOGO SWEATSHIRT_GRAY', 62000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '그레이', 'M', 100), (LAST_INSERT_ID(), '그레이', 'L', 100), (LAST_INSERT_ID(), '그레이', 'XL', 100);

-- ORCA ESSAY T (WHITE, BLACK)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA ESSAY T_WHITE', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '화이트', 'M', 100), (LAST_INSERT_ID(), '화이트', 'L', 100), (LAST_INSERT_ID(), '화이트', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA ESSAY T_BLACK', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

-- BACK LOGO ESSAY T (WHITE, BLACK)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'BACK LOGO ESSAY T_WHITE', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '화이트', 'M', 100), (LAST_INSERT_ID(), '화이트', 'L', 100), (LAST_INSERT_ID(), '화이트', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'BACK LOGO ESSAY T_BLACK', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

-- B&T LSV T (BLACK, WHITE)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'B&T LSV T_BLACK', 49000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'B&T LSV T_WHITE', 49000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '화이트', 'M', 100), (LAST_INSERT_ID(), '화이트', 'L', 100), (LAST_INSERT_ID(), '화이트', 'XL', 100);

-- ORCA LOGO T (WHITE, BLACK, GRAY, NAVY)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA LOGO T_WHITE', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '화이트', 'M', 100), (LAST_INSERT_ID(), '화이트', 'L', 100), (LAST_INSERT_ID(), '화이트', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA LOGO T_BLACK', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA LOGO T_GRAY', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '그레이', 'M', 100), (LAST_INSERT_ID(), '그레이', 'L', 100), (LAST_INSERT_ID(), '그레이', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA LOGO T_NAVY', 39000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '네이비', 'M', 100), (LAST_INSERT_ID(), '네이비', 'L', 100), (LAST_INSERT_ID(), '네이비', 'XL', 100);

-- [ORCA 25 FW 상품군]
-- ORCA PRINT HOODIE (GRAY, BLACK)
INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA PRINT HOODIE_GRAY', 78000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '그레이', 'M', 100), (LAST_INSERT_ID(), '그레이', 'L', 100), (LAST_INSERT_ID(), '그레이', 'XL', 100);

INSERT INTO tbl_product (category_id, name, price) VALUES (1, 'ORCA PRINT HOODIE_BLACK', 78000.00);
INSERT INTO tbl_product_variant (product_id, color, size, stock) VALUES (LAST_INSERT_ID(), '블랙', 'M', 100), (LAST_INSERT_ID(), '블랙', 'L', 100), (LAST_INSERT_ID(), '블랙', 'XL', 100);
