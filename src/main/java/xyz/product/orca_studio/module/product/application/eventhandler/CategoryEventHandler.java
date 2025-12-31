package xyz.product.orca_studio.module.product.application.eventhandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.product.orca_studio.module.product.application.event.CategoryCreatedEvent;
import xyz.product.orca_studio.module.product.application.event.CategoryDeletedEvent;
import xyz.product.orca_studio.module.product.application.event.CategoryUpdatedEvent;
import xyz.product.orca_studio.module.product.domain.ProductCategory;
import xyz.product.orca_studio.module.product.domain.repository.ProductCategoryRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryEventHandler {

    private final ProductCategoryRepository categoryRepository;

    @EventListener
    public void handleCategoryCreated(CategoryCreatedEvent event) {
        log.info("Handling category created event: {}", event);
        ProductCategory parent = event.parentId() != null ?
                categoryRepository.findById(event.parentId()).orElse(null) : null;

        ProductCategory newCategory = ProductCategory.builder()
            .id(event.categoryId())
            .name(event.name())
            .parent(parent)
            .build();

        categoryRepository.save(newCategory);
    }

    @EventListener
    public void handleCategoryUpdated(CategoryUpdatedEvent event) {
        log.info("Handling category updated event: {}", event);
        categoryRepository.findById(event.categoryId()).ifPresent(category -> {
            ProductCategory parent = event.parentId() != null ?
                categoryRepository.findById(event.parentId()).orElse(null) : null;
            category.update(event.name(), parent);
            categoryRepository.save(category);
        });
    }

    @EventListener
    public void handleCategoryDeleted(CategoryDeletedEvent event) {
        log.info("Handling category deleted event: {}", event);
        categoryRepository.findById(event.categoryId())
            .ifPresent(categoryRepository::delete);
    }
}

