package com.example.warehouse.controller;

import com.example.warehouse.dto.*;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.service.OutputProductService;
import com.example.warehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outputs")
public class OutputController {

    @Autowired
    private OutputService outputService;
    @Autowired
    private OutputProductService outputProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Output> outputs = outputService.findAll(PageRequest.of(page, size));

        response.setData(outputs);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}/output-products")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllInputProductsByInputId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<OutputProduct> outputProducts = outputProductService.findAllByOutputId(id, PageRequest.of(page, size));

        response.setData(outputProducts);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response get(@PathVariable Long id) {
        Response response = new Response();

        Output output = outputService.findById(id);

        response.setData(output);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Response create(@RequestBody OutputSaveDTO dto) {
        Response response = new Response();

        Output output = new Output();

        outputService.setAttributes(dto, output);

        Output savedOutput = outputService.save(output);

        if (dto.getOutputProducts() != null) {
            for (OutputProductInnerDTO outputProductDTO : dto.getOutputProducts()) {
                OutputProduct outputProduct = new OutputProduct();

                outputProductService.setAttributes(outputProductDTO, outputProduct, savedOutput);

                outputProductService.save(outputProduct);
            }
        }

        response.setData(output);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Response update(@RequestBody OutputUpdateDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Output output = outputService.findById(id);

        outputService.setAttributes(dto, output);

        Output savedOutput = outputService.save(output);

        response.setData(savedOutput);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        outputService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
