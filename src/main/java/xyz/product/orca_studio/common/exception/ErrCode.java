package xyz.product.orca_studio.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrCode {
    String getCode();
    HttpStatus getStatus();
    String getMessage();
}
