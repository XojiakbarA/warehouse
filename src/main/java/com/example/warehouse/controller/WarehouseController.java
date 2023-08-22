package com.example.warehouse.controller;

import com.example.warehouse.dto.Response;
import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Pageable pageable = PageRequest.of(page, size);
        Page<Warehouse> warehouses = warehouseService.findAll(pageable);

        response.setData(warehouses);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Optional<Warehouse> optional = warehouseService.findById(id);
        if (optional.isPresent()) {
            response.setData(optional.get());
            response.setMessage(HttpStatus.OK.name());
        } else {
            response.setMessage(HttpStatus.NOT_FOUND.name());
        }

        return response;
    }

    @PostMapping
    public Response create(@RequestBody WarehouseDTO dto) {
        Response response = new Response();

        Warehouse warehouse = new Warehouse();

        warehouseService.setAttributes(dto, warehouse);

        Warehouse warehouseDB = warehouseService.save(warehouse);

        response.setData(warehouseDB);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    public Response update(@RequestBody WarehouseDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Optional<Warehouse> optional = warehouseService.findById(id);
        if (optional.isEmpty()) {
            response.setMessage(HttpStatus.NOT_FOUND.name());
            return response;
        }

        Warehouse warehouse = optional.get();

        warehouseService.setAttributes(dto, warehouse);

        Warehouse warehouseDB = warehouseService.save(warehouse);

        response.setData(warehouseDB);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        if (warehouseService.existsById(id)) {
            warehouseService.deleteById(id);
            response.setMessage(HttpStatus.NOT_FOUND.name());
        } else {
            response.setMessage(HttpStatus.OK.name());
        }

        return response;
    }
}
