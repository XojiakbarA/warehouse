package com.example.warehouse.repository;

import com.example.warehouse.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findAllByNameContainingIgnoreCase(String name);
}
