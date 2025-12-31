package xyz.product.orca_studio.module.product.application.event;

public record CategoryUpdatedEvent(
    Long categoryId,
    String name,
    Long parentId
) {
}
