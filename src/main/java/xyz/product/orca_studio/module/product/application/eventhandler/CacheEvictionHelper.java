package xyz.product.orca_studio.module.product.application.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CacheEvictionHelper {

    private final RedisTemplate<String, Object> redisTemplate;

    public void evictProductDetailCache(Long productId) {
        String pattern = "product::id::" + productId + "::variants::*";
        Set<String> keys = redisTemplate.keys(pattern);

        if (keys != null && !keys.isEmpty()) {
            Long deletedCount = redisTemplate.delete(keys);
            log.info("상품 상세 캐시 삭제 완료. productId: {}, 삭제된 키 수: {}", productId, deletedCount);
        } else {
            log.debug("삭제할 상품 상세 캐시가 없습니다. productId: {}", productId);
        }
    }

    public void evictAllProductListCache() {
        String pattern = "products::category::*";
        Set<String> keys = redisTemplate.keys(pattern);

        if (keys != null && !keys.isEmpty()) {
            Long deletedCount = redisTemplate.delete(keys);
            log.info("전체 상품 목록 캐시 삭제 완료. 삭제된 키 수: {}", deletedCount);
        } else {
            log.debug("삭제할 상품 목록 캐시가 없습니다");
        }
    }
}

