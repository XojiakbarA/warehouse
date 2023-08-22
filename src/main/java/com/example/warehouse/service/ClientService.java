package com.example.warehouse.service;

import com.example.warehouse.dto.ClientDTO;
import com.example.warehouse.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<Client> findAll(Pageable pageable);

    Client findById(Long id);

    Client save(Client client);

    void deleteById(Long id);

    void setAttributes(ClientDTO dto, Client client);
}
