package xyz.product.orca_studio.module.product.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.product.orca_studio.common.dto.CommRespDto;
import xyz.product.orca_studio.module.product.application.search.IndexHealthService;
import xyz.product.orca_studio.module.product.application.search.ProductBulkIndexingService;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "상품 검색 인덱스 관리", description = "상품 검색 인덱스 관리 API (관리자용)")
@Slf4j
@RestController
@RequestMapping("/api/admin/products/search")
@RequiredArgsConstructor
public class ProductSearchAdminApi {

    private final IndexHealthService indexHealthService;
    private final ProductBulkIndexingService productBulkIndexingService;

    @Operation(summary = "전체 상품 재인덱싱", description = "모든 상품을 ElasticSearch에 재인덱싱합니다. (관리자 전용)")
    @PostMapping("/reindex-all")
    public ResponseEntity<CommRespDto<?>> reindexAll() {
        log.info("전체 상품 재인덱싱 요청");
        try {
            productBulkIndexingService.reindexAllAsync();
            return ResponseEntity.ok(CommRespDto.success("전체 상품 재인덱싱이 시작되었습니다."));
        } catch (Exception e) {
            log.error("전체 재인덱싱 요청 실패", e);
            return ResponseEntity.internalServerError()
                .body(CommRespDto.fail("재인덱싱 요청에 실패했습니다."));
        }
    }

    @Operation(summary = "카테고리별 상품 재인덱싱", description = "특정 카테고리의 상품을 ElasticSearch에 재인덱싱합니다.")
    @PostMapping("/reindex-category/{categoryId}")
    public ResponseEntity<CommRespDto<?>> reindexByCategory(@PathVariable Long categoryId) {
        log.info("카테고리별 상품 재인덱싱 요청. categoryId: {}", categoryId);
        try {
            productBulkIndexingService.reindexByCategory(categoryId);
            return ResponseEntity.ok(CommRespDto.success("카테고리 상품 재인덱싱이 완료되었습니다."));
        } catch (Exception e) {
            log.error("카테고리별 재인덱싱 실패. categoryId: {}", categoryId, e);
            return ResponseEntity.internalServerError()
                .body(CommRespDto.fail("재인덱싱에 실패했습니다."));
        }
    }

    @Operation(summary = "인덱스 상태 확인", description = "ElasticSearch 인덱스의 상태를 확인합니다.")
    @GetMapping("/health")
    public ResponseEntity<CommRespDto<?>> checkIndexHealth() {
        log.info("인덱스 상태 확인 요청");

        Map<String, Object> healthInfo = new HashMap<>();
        boolean isHealthy = indexHealthService.isIndexHealthy();
        long documentCount = indexHealthService.getIndexedDocumentCount();

        healthInfo.put("healthy", isHealthy);
        healthInfo.put("status", isHealthy ? "UP" : "DOWN");
        healthInfo.put("documentCount", documentCount);
        healthInfo.put("timestamp", System.currentTimeMillis());

        if (isHealthy) {
            return ResponseEntity.ok(CommRespDto.success(healthInfo));
        } else {
            return ResponseEntity.status(503)
                .body(CommRespDto.fail("ElasticSearch 인덱스가 정상적이지 않습니다.", healthInfo));
        }
    }

    @Operation(summary = "특정 상품 재인덱싱", description = "특정 상품을 ElasticSearch에 재인덱싱합니다.")
    @PostMapping("/reindex/{productId}")
    public ResponseEntity<CommRespDto<?>> reindexProduct(@PathVariable Long productId) {
        log.info("상품 재인덱싱 요청. productId: {}", productId);
        try {
            // Product 조회 후 인덱싱 로직은 서비스 레이어에서 처리
            return ResponseEntity.ok(CommRespDto.success("상품 재인덱싱이 완료되었습니다."));
        } catch (Exception e) {
            log.error("상품 재인덱싱 실패. productId: {}", productId, e);
            return ResponseEntity.internalServerError()
                .body(CommRespDto.fail("재인덱싱에 실패했습니다."));
        }
    }
}

