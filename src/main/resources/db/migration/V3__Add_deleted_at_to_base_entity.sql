-- V3__Add_deleted_at_to_base_entity.sql

ALTER TABLE tbl_product_category
    ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일시';

ALTER TABLE tbl_product
    ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일시';

ALTER TABLE tbl_product_variant
    ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일시';

ALTER TABLE tbl_product_img
    ADD COLUMN deleted_at TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일시';
