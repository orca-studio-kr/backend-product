package xyz.product.orca_studio.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommErrCode implements ErrCode {
    INVALID_REQUEST("C001", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    METHOD_NOT_ALLOWED("C002", HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR("C003", HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다."),
    NOT_FOUND("C004", HttpStatus.NOT_FOUND, "요청하신 데이터가 존재하지 않습니다.");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
