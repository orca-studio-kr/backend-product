package xyz.product.orca_studio.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommRespDto<T> {
    private boolean success;
    private T data;
    private String message;
    private Map<String, Object> error;

    public static <T> CommRespDto<T> success(T data) {
        return new CommRespDto<>(true, data, null, null);
    }

    public static <T> CommRespDto<T> success(T data, String message) {
        return new CommRespDto<>(true, data, message, null);
    }

    public static CommRespDto<Void> fail(String message) {
        return new CommRespDto<>(false, null, message, null);
    }

    public static CommRespDto<Void> fail(String message, Map<String, Object> error) {
        return new CommRespDto<>(false, null, message, error);
    }
}
