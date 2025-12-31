package xyz.product.orca_studio.module.product.simulation;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.product.orca_studio.module.product.application.event.ProductCreatedEvent;
import xyz.product.orca_studio.module.product.application.event.ProductDeletedEvent;
import xyz.product.orca_studio.module.product.application.event.ProductUpdatedEvent;
import xyz.product.orca_studio.module.product.simulation.dto.CreateProductRequest;
import xyz.product.orca_studio.module.product.simulation.dto.UpdateProductRequest;

@Profile("dev")
@RestController
@RequestMapping("/api/simulate/products")
@RequiredArgsConstructor
// This controller should ideally be active only on 'dev' or 'simulation' profiles.
public class ProductSimulationController {

    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest request) {
        ProductCreatedEvent event = new ProductCreatedEvent(
            request.getName(),
            request.getDescription(),
            request.getPrice(),
            request.getCategoryId(),
            request.getImages(),
            request.getVariants()
        );
        eventPublisher.publishEvent(event);
        return ResponseEntity.ok("ProductCreatedEvent published.");
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        ProductUpdatedEvent event = new ProductUpdatedEvent(
                productId,
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCategoryId(),
                request.getImages(),
                request.getVariants()
        );
        eventPublisher.publishEvent(event);
        return ResponseEntity.ok("ProductUpdatedEvent published for product ID: " + productId);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        ProductDeletedEvent event = new ProductDeletedEvent(productId);
        eventPublisher.publishEvent(event);
        return ResponseEntity.ok("ProductDeletedEvent published for product ID: " + productId);
    }
}
