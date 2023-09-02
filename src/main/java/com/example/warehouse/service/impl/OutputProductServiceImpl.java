package com.example.warehouse.service.impl;

import com.example.warehouse.dto.OutputProductInnerDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.entity.Product;
import com.example.warehouse.exception.AmountExceedsException;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.OutputProductRepository;
import com.example.warehouse.service.OutputProductService;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return outputProductRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(OutputProduct.class.getSimpleName(), id)
        );
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
    public void setAttributes(OutputProductInnerDTO dto, OutputProduct outputProduct, Output output) {
        if (dto.getProductId() != null) {
            Product product = productService.findById(dto.getProductId());
            outputProduct.setProduct(product);
            checkActive(product);
            if (product.getRemaining() < dto.getAmount()) {
                throw new AmountExceedsException(product.getRemaining());
            }
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

    @Override
    public List<MostOutputProductsDTO> findDailyMostOutputProducts() {
        return outputProductRepository.findDailyMostOutputProducts();
    }
}
