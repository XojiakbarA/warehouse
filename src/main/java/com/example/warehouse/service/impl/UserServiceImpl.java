package com.example.warehouse.service.impl;

import com.example.warehouse.dto.UserDTO;
import com.example.warehouse.entity.User;
import com.example.warehouse.entity.Warehouse;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.service.UserService;
import com.example.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WarehouseService warehouseService;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(User.class.getSimpleName(), id)
        );
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void setAttributes(UserDTO dto, User user) {
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPassword() != null) {
            String password = Base64.getEncoder().encodeToString(dto.getPassword().getBytes());
            user.setPassword(password);
        }
        if (dto.getActive() != null) {
            user.setActive(dto.getActive());
        }
        if (dto.getWarehouseIds() != null) {
            Set<Warehouse> warehouses = dto.getWarehouseIds().stream()
                    .map(id -> {
                        Warehouse warehouse = warehouseService.findById(id);
                        checkActive(warehouse);
                        return warehouse;
                    })
                    .collect(Collectors.toSet());
            user.setWarehouses(warehouses);
        }
        user.setCode(UUID.randomUUID().toString());
    }
}
