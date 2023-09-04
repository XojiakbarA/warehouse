package com.example.warehouse.service;

import com.example.warehouse.dto.UserDTO;
import com.example.warehouse.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService extends ActiveCheckable {
    Page<User> findAll(Pageable pageable);

    List<User> findAll();

    User findById(Long id);

    User save(User user);

    void deleteById(Long id);

    void setAttributes(UserDTO dto, User user);
}
