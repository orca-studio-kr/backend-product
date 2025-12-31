package xyz.product.orca_studio.module.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.product.orca_studio.module.product.domain.Product;
import xyz.product.orca_studio.module.product.domain.repository.ProductRepository;
import xyz.product.orca_studio.module.product.api.dto.ProductDetailRespDto;
import xyz.product.orca_studio.module.product.api.dto.ProductSimpleRespDto;
import xyz.product.orca_studio.module.product.mapper.ProductMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductSimpleRespDto> getProducts(Long categoryId) {
        List<Product> products;
        if (categoryId == null) {
            products = productRepository.findAllWithThumbnail();
        } else {
            products = productRepository.findAllByCategoryIdWithThumbnail(categoryId);
        }

        return productMapper.toSimpleDtoList(products);
    }

    @Override
    public ProductDetailRespDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("상품을 찾을 수 없습니다. id: " + productId));

        return productMapper.toDetailDto(product);
    }
}

