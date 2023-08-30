package com.example.warehouse.controller;

import com.example.warehouse.dto.Response;
import com.example.warehouse.dto.WarehouseDTO;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Warehouse> warehouses = warehouseService.findAll(PageRequest.of(page, size));

        response.setData(warehouses);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Warehouse warehouse = warehouseService.findById(id);

        response.setData(warehouse);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Response searchByName(@RequestParam(name = "name") String name) {
        Response response = new Response();

        List<Warehouse> warehouses = warehouseService.findAllByNameContainingIgnoreCase(name);

        response.setData(warehouses);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody WarehouseDTO dto) {
        Response response = new Response();

        Warehouse warehouse = new Warehouse();

        warehouseService.setAttributes(dto, warehouse);

        Warehouse savedWarehouse = warehouseService.save(warehouse);

        response.setData(savedWarehouse);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody WarehouseDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Warehouse warehouse = warehouseService.findById(id);

        warehouseService.setAttributes(dto, warehouse);

        Warehouse warehouseDB = warehouseService.save(warehouse);

        response.setData(warehouseDB);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        warehouseService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
