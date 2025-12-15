package xyz.product.orca_studio.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.product.orca_studio.common.dto.CommRespDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class CommAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CommRespDto<?>> handleException(ResourceNotFoundException e) {
        String traceId = UUID.randomUUID().toString();

        log.warn("[{}] traceId={}, message={}", e.getClass().getSimpleName(), traceId, e.getMessage(), e);

        Map<String, Object> err = new HashMap<>();
        err.put("traceId", traceId);
        err.put("code", CommErrCode.NOT_FOUND.getCode());
        err.put("detail", e.getMessage());
        err.put("timestamp", LocalDateTime.now().toString());

        CommRespDto<?> response = CommRespDto.fail("요청하신 데이터가 존재하지 않습니다. 다시 시도해주세요.", err);

        return ResponseEntity
            .status(CommErrCode.NOT_FOUND.getStatus())
            .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommRespDto<?>> handleException(Exception e) {
        String traceId = UUID.randomUUID().toString();

        log.warn("[{}] traceId={}, message={}", e.getClass().getSimpleName(), traceId, e.getMessage(), e);

        Map<String, Object> err = new HashMap<>();
        err.put("traceId", traceId);
        err.put("code", CommErrCode.INTERNAL_SERVER_ERROR.getCode());
        err.put("detail", e.getMessage());
        err.put("timestamp", LocalDateTime.now().toString());

        CommRespDto<?> response = CommRespDto.fail("서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.", err);

        return ResponseEntity
            .status(CommErrCode.INTERNAL_SERVER_ERROR.getStatus())
            .body(response);
    }
}
