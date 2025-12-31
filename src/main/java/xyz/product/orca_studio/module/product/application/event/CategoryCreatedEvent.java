package xyz.product.orca_studio.module.product.application.event;

public record CategoryCreatedEvent(
        Long categoryId,
        String name,
        Long parentId
) {
}

