package com.example.warehouse.service;

import com.example.warehouse.dto.ProductDTO;
import com.example.warehouse.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    void setAttributes(ProductDTO dto, Product product);
}
