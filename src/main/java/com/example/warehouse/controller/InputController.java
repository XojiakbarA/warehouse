package com.example.warehouse.controller;

import com.example.warehouse.dto.InputUpdateDTO;
import com.example.warehouse.dto.InputProductInnerDTO;
import com.example.warehouse.dto.InputSaveDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Input;
import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.event.NearToExpirePublisher;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inputs")
public class InputController {

    @Autowired
    private InputService inputService;
    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private NearToExpirePublisher nearToExpirePublisher;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Input> inputs = inputService.findAll(PageRequest.of(page, size));

        response.setData(inputs);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}/input-products")
    @ResponseStatus(HttpStatus.OK)
    public Response getAllInputProductsByInputId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<InputProduct> inputProducts = inputProductService.findAllByInputId(id, PageRequest.of(page, size));

        response.setData(inputProducts);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response get(@PathVariable Long id) {
        Response response = new Response();

        Input input = inputService.findById(id);

        response.setData(input);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Response create(@RequestBody InputSaveDTO dto) {
        Response response = new Response();

        Input input = new Input();

        inputService.setAttributes(dto, input);

        Input savedInput = inputService.save(input);

        if (dto.getInputProducts() != null) {
            for (InputProductInnerDTO inputProductDTO : dto.getInputProducts()) {
                InputProduct inputProduct = new InputProduct();

                inputProductService.setAttributes(inputProductDTO, inputProduct, savedInput);

                InputProduct savedInputProduct = inputProductService.save(inputProduct);

                nearToExpirePublisher.publishNearToExpireEvent(savedInputProduct);
            }
        }

        response.setData(savedInput);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public Response update(@RequestBody InputUpdateDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Input input = inputService.findById(id);

        inputService.setAttributes(dto, input);

        Input savedInput = inputService.save(input);

        response.setData(savedInput);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        inputService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
