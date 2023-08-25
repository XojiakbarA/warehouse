package com.example.warehouse.service.impl;

import com.example.warehouse.dto.OutputProductInnerDTO;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.entity.Product;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.OutputProductRepository;
import com.example.warehouse.service.OutputProductService;
import com.example.warehouse.service.ProductService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class OutputProductServiceImpl implements OutputProductService {

    @Autowired
    private OutputProductRepository outputProductRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Page<OutputProduct> findAll(Pageable pageable) {
        return outputProductRepository.findAll(pageable);
    }

    @Override
    public Page<OutputProduct> findAllByOutputId(Long outputId, Pageable pageable) {
        return outputProductRepository.findAllByOutputId(outputId, pageable);
    }

    @Override
    public OutputProduct findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(OutputProduct.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return outputProductRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public OutputProduct save(OutputProduct outputProduct) {
        return outputProductRepository.save(outputProduct);
    }

    @Override
    public void deleteById(Long id) {
        outputProductRepository.deleteById(id);
    }

    @Override
    public void deleteAllByOutputId(Long outputId) {
        outputProductRepository.deleteAllByOutputId(outputId);
    }

    @Override
    public void setAttributes(OutputProductInnerDTO dto, OutputProduct outputProduct, Output output) {
        if (dto.getProductId() != null) {
            Product product = productService.findById(dto.getProductId());
            outputProduct.setProduct(product);
        }
        if (dto.getAmount() != null) {
            outputProduct.setAmount(dto.getAmount());
        }
        if (dto.getPrice() != null) {
            outputProduct.setPrice(dto.getPrice());
        }
        if (output != null) {
            outputProduct.setOutput(output);
        }
    }
}
