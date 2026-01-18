package xyz.product.orca_studio.module.product.application.search;

public class ProductIndexingException extends RuntimeException {

    public ProductIndexingException(String message) {
        super(message);
    }

    public ProductIndexingException(String message, Throwable cause) {
        super(message, cause);
    }
}

