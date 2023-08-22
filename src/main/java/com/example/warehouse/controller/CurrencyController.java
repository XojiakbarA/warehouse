package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrencyDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Currency> currencies = currencyService.findAll(PageRequest.of(page, size));

        response.setData(currencies);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Currency currency = currencyService.findById(id);

        response.setData(currency);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody CurrencyDTO dto) {
        Response response = new Response();

        Currency currency = new Currency();

        currencyService.setAttributes(dto, currency);

        Currency savedCurrency = currencyService.save(currency);

        response.setData(savedCurrency);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody CurrencyDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Currency currency = currencyService.findById(id);

        currencyService.setAttributes(dto, currency);

        Currency savedCurrency = currencyService.save(currency);

        response.setData(savedCurrency);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        currencyService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
