package com.example.warehouse.controller;

import com.example.warehouse.dto.ProductDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Product;
import com.example.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Product> products = productService.findAll(PageRequest.of(page, size));

        response.setData(products);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Product product = productService.findById(id);

        response.setData(product);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Response create(@ModelAttribute ProductDTO dto) {
        Response response = new Response();

        Product product = new Product();

        productService.setAttributes(dto, product);

        Product savedProduct = productService.save(product);

        response.setData(savedProduct);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Response update(@ModelAttribute ProductDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Product product = productService.findById(id);

        productService.setAttributes(dto, product);

        Product savedProduct = productService.save(product);

        response.setData(savedProduct);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        productService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
