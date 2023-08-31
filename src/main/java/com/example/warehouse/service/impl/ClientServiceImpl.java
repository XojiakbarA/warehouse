package com.example.warehouse.service.impl;

import com.example.warehouse.dto.ClientDTO;
import com.example.warehouse.entity.Client;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.ClientRepository;
import com.example.warehouse.service.ClientService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client findById(Long id) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(Client.class.getSimpleName(), id);
            return new ResourceNotFoundException(message);
        };
        return clientRepository.findById(id).orElseThrow(supplier);
    }

    @Override
    public List<Client> findAllByNameContainingIgnoreCase(String name) {
        return clientRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void setAttributes(ClientDTO dto, Client client) {
        if (dto.getName() != null) {
            client.setName(dto.getName());
        }
        if (dto.getActive() != null) {
            client.setActive(dto.getActive());
        }
        if (dto.getPhoneNumber() != null) {
            client.setPhoneNumber(dto.getPhoneNumber());
        }
    }
}
