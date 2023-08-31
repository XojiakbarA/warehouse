package com.example.warehouse.controller;

import com.example.warehouse.dto.InputProductDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input-products")
public class InputProductController {

    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private InputService inputService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<InputProduct> inputProducts = inputProductService.findAll(PageRequest.of(page, size));

        response.setData(inputProducts);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response get(@PathVariable Long id) {
        Response response = new Response();

        InputProduct inputProduct = inputProductService.findById(id);

        response.setData(inputProduct);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody InputProductDTO dto) {
        Response response = new Response();

        InputProduct inputProduct = new InputProduct();

        Input input = inputService.findById(dto.getInputId());

        inputProductService.setAttributes(dto, inputProduct, input);

        InputProduct savedInputProduct = inputProductService.save(inputProduct);

        response.setData(savedInputProduct);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody InputProductDTO dto, @PathVariable Long id) {
        Response response = new Response();

        InputProduct inputProduct = inputProductService.findById(id);

        Input input = inputService.findById(dto.getInputId());

        inputProductService.setAttributes(dto, inputProduct, input);

        InputProduct savedInputProduct = inputProductService.save(inputProduct);

        response.setData(savedInputProduct);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        inputProductService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
