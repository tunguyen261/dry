package com.fpt.dry.service;

import com.fpt.dry.object.dto.mapper.ProductMapper;
import com.fpt.dry.object.dto.request.ProductRequest;
import com.fpt.dry.object.entity.Product;
import com.fpt.dry.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found any product with id " + id));
    }

    public List<Product> findALlProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequest request) {
        Product entity = productMapper.mapCreateRequestToEntity(request);

        return productRepository.save(entity);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        Product entity = findById(id);

        productMapper.updateEntity(entity, request);

        return productRepository.save(entity);
    }

    public void deleteProduct(Long id) {
        Product entity = findById(id);

        productRepository.delete(entity);
    }
}
