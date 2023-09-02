package com.example.warehouse.service.impl;

import com.example.warehouse.dto.ProductDTO;
import com.example.warehouse.entity.*;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentContentService attachmentContentService;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Product.class.getSimpleName(), id)
        );
    }

    @Override
    public List<Product> findAllByNameContainingIgnoreCase(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        Product product = findById(id);
        if (product.getPhoto() != null) {
            attachmentContentService.deleteByAttachmentId(product.getPhoto().getId());
        }
        productRepository.deleteById(id);
    }

    @Override
    public void setAttributes(ProductDTO dto, Product product) {
        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            product.setActive(dto.getActive());
        }
        if (dto.getCategoryId() != null) {
            Category category = categoryService.findById(dto.getCategoryId());
            checkActive(category);
            product.setCategory(category);
        }
        if (dto.getMeasurementId() != null) {
            Measurement measurement = measurementService.findById(dto.getMeasurementId());
            checkActive(measurement);
            product.setMeasurement(measurement);
        }
        if (dto.getPhoto() != null) {
            if (product.getPhoto() != null) {
                attachmentContentService.deleteByAttachmentId(product.getPhoto().getId());
            }

            MultipartFile file = dto.getPhoto();

            Attachment attachment = new Attachment();

            attachmentService.setAttributes(file, attachment);

            product.setPhoto(attachment);
        }

        product.setCode(UUID.randomUUID().toString());
    }
}
