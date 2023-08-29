package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrencyAvailableDTO;
import com.example.warehouse.dto.CurrencyDTO;
import com.example.warehouse.dto.CurrencyViewDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.service.CurrencyService;
import com.example.warehouse.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        Response response = new Response();

        List<Currency> currencyList = currencyService.findAll();

        List<CurrencyViewDTO> currencies = currencyList.stream().map(Mapper::currencyEntityToCurrencyViewDTO).toList();

        response.setData(currencies);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public Response searchAvailableByCurrency(@RequestParam(name = "name") String name) {
        Response response = new Response();

        Set<java.util.Currency> currencySet = currencyService.findAvailableCurrency(name);

        Set<CurrencyAvailableDTO> currencies = currencySet.stream().map(Mapper::currencyUtilToCurrencyAvailableDTO).collect(Collectors.toSet());

        response.setData(currencies);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Currency currencyDB = currencyService.findById(id);

        CurrencyViewDTO currency = Mapper.currencyEntityToCurrencyViewDTO(currencyDB);

        response.setData(currency);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody CurrencyDTO dto) {
        Response response = new Response();

        Currency newCurrency = new Currency();

        currencyService.setAttributes(dto, newCurrency);

        Currency savedCurrency = currencyService.save(newCurrency);

        CurrencyViewDTO currency = Mapper.currencyEntityToCurrencyViewDTO(savedCurrency);

        response.setData(currency);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody CurrencyDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Currency currencyDB = currencyService.findById(id);

        currencyService.setAttributes(dto, currencyDB);

        Currency savedCurrency = currencyService.save(currencyDB);

        CurrencyViewDTO currency = Mapper.currencyEntityToCurrencyViewDTO(savedCurrency);

        response.setData(currency);
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
