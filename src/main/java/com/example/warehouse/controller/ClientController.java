package com.example.warehouse.controller;

import com.example.warehouse.dto.ClientDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.Client;
import com.example.warehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<Client> clients = clientService.findAll(PageRequest.of(page, size));

        response.setData(clients);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        Client client = clientService.findById(id);

        response.setData(client);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody ClientDTO dto) {
        Response response = new Response();

        Client client = new Client();

        clientService.setAttributes(dto, client);

        Client savedClient = clientService.save(client);

        response.setData(savedClient);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody ClientDTO dto, @PathVariable Long id) {
        Response response = new Response();

        Client client = clientService.findById(id);

        clientService.setAttributes(dto, client);

        Client savedClient = clientService.save(client);

        response.setData(savedClient);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        clientService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
