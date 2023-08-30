package com.example.warehouse.controller;

import com.example.warehouse.dto.Response;
import com.example.warehouse.dto.SupplierDTO;
import com.example.warehouse.entity.Supplier;
import com.example.warehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Supplier> suppliers = supplierService.findAll(PageRequest.of(page, size));

        response.setData(suppliers);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Supplier supplier = supplierService.findById(id);

        response.setData(supplier);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Response searchByName(@RequestParam(name = "name") String name) {
        Response response = new Response();

        List<Supplier> suppliers = supplierService.findAllByNameContainingIgnoreCase(name);

        response.setData(suppliers);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody SupplierDTO dto) {
        Response response = new Response();

        Supplier supplier = new Supplier();

        supplierService.setAttributes(dto, supplier);

        Supplier savedSupplier = supplierService.save(supplier);

        response.setData(savedSupplier);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody SupplierDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Supplier supplier = supplierService.findById(id);

        supplierService.setAttributes(dto, supplier);

        Supplier savedSupplier = supplierService.save(supplier);

        response.setData(savedSupplier);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        supplierService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
