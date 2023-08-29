package com.example.warehouse.controller;

import com.example.warehouse.dto.CategoryDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Category;
import com.example.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Category> categories = categoryService.findAll(PageRequest.of(page, size));

        response.setData(categories);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Category category = categoryService.findById(id);

        response.setData(category);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Response searchByName(@RequestParam(name = "name") String name) {
        Response response = new Response();

        List<Category> categories = categoryService.findAllByNameContainingIgnoreCase(name);

        response.setData(categories);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody CategoryDTO dto) {
        Response response = new Response();

        Category category = new Category();

        categoryService.setAttributes(dto, category);

        Category savedCategory = categoryService.save(category);

        response.setData(savedCategory);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody CategoryDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Category category = categoryService.findById(id);

        categoryService.setAttributes(dto, category);

        Category savedCategory = categoryService.save(category);

        response.setData(savedCategory);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        categoryService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
