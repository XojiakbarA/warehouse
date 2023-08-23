package com.example.warehouse.service.impl;

import com.example.warehouse.dto.InputProductInnerDTO;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.entity.Product;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.InputProductRepository;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class InputProductServiceImpl implements InputProductService {

    @Autowired
    private InputProductRepository inputProductRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Page<InputProduct> findAll(Pageable pageable) {
        return inputProductRepository.findAll(pageable);
    }

    @Override
    public Page<InputProduct> findAllByInputId(Long inputId, Pageable pageable) {
        return inputProductRepository.findAllByInputId(inputId, pageable);
    }

    @Override
    public InputProduct findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(InputProduct.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return inputProductRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public InputProduct save(InputProduct inputProduct) {
        return inputProductRepository.save(inputProduct);
    }

    @Override
    public void deleteById(Long id) {
        inputProductRepository.deleteById(id);
    }

    @Override
    public void deleteAllByInputId(Long inputId) {
        inputProductRepository.deleteAllByInputId(inputId);
    }

    @Override
    public void setAttributes(InputProductInnerDTO dto, InputProduct inputProduct, Input input) {
        if (dto.getProductId() != null) {
            Product product = productService.findById(dto.getProductId());
            inputProduct.setProduct(product);
        }
        if (dto.getAmount() != null) {
            inputProduct.setAmount(dto.getAmount());
        }
        if (dto.getPrice() != null) {
            inputProduct.setPrice(dto.getPrice());
        }
        if (dto.getExpireDate() != null) {
            inputProduct.setExpireDate(dto.getExpireDate());
        }
        if (input != null) {
            inputProduct.setInput(input);
        }
    }
}
