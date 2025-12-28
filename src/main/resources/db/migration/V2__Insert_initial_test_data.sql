-- 초기 테스트 데이터 삽입
-- 전자기기 카테고리
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('스마트폰 Galaxy S24', '최신형 Galaxy 스마트폰, 256GB, 5G 지원', 1200000.00, 50, '전자기기', 'ACTIVE', 'SYSTEM', 'SYSTEM');

INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('노트북 MacBook Pro', 'M3 칩 탑재, 14인치, 512GB SSD', 2800000.00, 30, '전자기기', 'ACTIVE', 'SYSTEM', 'SYSTEM');

INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('무선 이어폰 AirPods Pro', '노이즈 캔슬링 기능, USB-C 충전', 350000.00, 100, '전자기기', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 의류 카테고리
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('겨울 패딩 자켓', '오리털 충전재, 방수 처리, 다양한 사이즈', 180000.00, 75, '의류', 'ACTIVE', 'SYSTEM', 'SYSTEM');

INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('청바지', '스트레치 소재, 슬림핏, 워싱 처리', 89000.00, 120, '의류', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 가구 카테고리
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('사무용 책상', '높이 조절 가능, 140cm x 70cm', 450000.00, 20, '가구', 'ACTIVE', 'SYSTEM', 'SYSTEM');

INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('인체공학 의자', '메쉬 소재, 요추 지지대, 팔걸이 조절', 320000.00, 35, '가구', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 도서 카테고리
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('클린 코드', '로버트 C. 마틴 저, 프로그래밍 베스트셀러', 32000.00, 150, '도서', 'ACTIVE', 'SYSTEM', 'SYSTEM');

INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('이펙티브 자바', '조슈아 블로크 저, 자바 개발자 필독서', 36000.00, 100, '도서', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 식품 카테고리
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('유기농 쌀', '국내산 유기농 쌀, 10kg', 45000.00, 200, '식품', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 재고 없는 상품 (테스트용)
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('한정판 스니커즈', '수량 제한 에디션, 품절 임박', 250000.00, 0, '신발', 'ACTIVE', 'SYSTEM', 'SYSTEM');

-- 비활성 상품 (테스트용)
INSERT INTO product (name, description, price, stock_quantity, category, status, created_by, updated_by)
VALUES ('구형 스마트폰', '단종 예정 모델', 500000.00, 10, '전자기기', 'INACTIVE', 'SYSTEM', 'SYSTEM');
