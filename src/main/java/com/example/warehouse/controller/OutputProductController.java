package com.example.warehouse.controller;

import com.example.warehouse.dto.OutputProductDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Output;
import com.example.warehouse.entity.OutputProduct;
import com.example.warehouse.event.RemoveNearToExpirePublisher;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.OutputProductService;
import com.example.warehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output-products")
public class OutputProductController {

    @Autowired
    private OutputProductService outputProductService;
    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private OutputService outputService;
    @Autowired
    private RemoveNearToExpirePublisher removeNearToExpirePublisher;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<OutputProduct> outputProducts = outputProductService.findAll(PageRequest.of(page, size));

        response.setData(outputProducts);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response get(@PathVariable Long id) {
        Response response = new Response();

        OutputProduct outputProduct = outputProductService.findById(id);

        response.setData(outputProduct);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody OutputProductDTO dto) {
        Response response = new Response();

        OutputProduct outputProduct = new OutputProduct();

        Output output = outputService.findById(dto.getOutputId());

        outputProductService.setAttributes(dto, outputProduct, output);

        OutputProduct savedOutputProduct = outputProductService.save(outputProduct);

        inputProductService.subtractRemainingById(savedOutputProduct.getInputProduct().getId(), savedOutputProduct.getAmount());

        removeNearToExpirePublisher.publishNearToExpireEvent(savedOutputProduct.getInputProduct());

        response.setData(savedOutputProduct);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody OutputProductDTO dto, @PathVariable Long id) {
        Response response = new Response();

        OutputProduct outputProduct = outputProductService.findById(id);

        Output output = outputService.findById(dto.getOutputId());

        outputProductService.setAttributes(dto, outputProduct, output);

        OutputProduct savedOutputProduct = outputProductService.save(outputProduct);

        response.setData(savedOutputProduct);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        outputProductService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
