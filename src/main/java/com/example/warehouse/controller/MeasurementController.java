package com.example.warehouse.controller;

import com.example.warehouse.dto.MeasurementDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Measurement> measurements = measurementService.findAll(PageRequest.of(page, size));

        response.setData(measurements);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Measurement measurement = measurementService.findById(id);

        response.setData(measurement);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody MeasurementDTO dto) {
        Response response = new Response();

        Measurement measurement = new Measurement();

        measurementService.setAttributes(dto, measurement);

        Measurement savedMeasurement = measurementService.save(measurement);

        response.setData(savedMeasurement);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody MeasurementDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Measurement measurement = measurementService.findById(id);

        measurementService.setAttributes(dto, measurement);

        Measurement savedMeasurement = measurementService.save(measurement);

        response.setData(savedMeasurement);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        measurementService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
