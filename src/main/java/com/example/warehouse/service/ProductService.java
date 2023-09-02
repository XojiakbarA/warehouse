package com.example.warehouse.service;

import com.example.warehouse.dto.ProductDTO;
import com.example.warehouse.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends ActiveCheckable {
    Page<Product> findAll(Pageable pageable);

    Product findById(Long id);

    List<Product> findAllByNameContainingIgnoreCase(String name);

    Product save(Product product);

    void deleteById(Long id);

    void setAttributes(ProductDTO dto, Product product);
}
