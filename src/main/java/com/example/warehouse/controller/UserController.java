package com.example.warehouse.controller;

import com.example.warehouse.dto.Response;
import com.example.warehouse.dto.UserDTO;
import com.example.warehouse.dto.UserViewDTO;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;
import com.example.warehouse.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private Mapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        Response response = new Response();

        Page<User> userPage = userService.findAll(PageRequest.of(page, size));

        Page<UserViewDTO> users = userPage.map(u -> mapper.userEntityToUserViewDTO(u));

        response.setData(users);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getById(@PathVariable Long id) {
        Response response = new Response();

        UserViewDTO user = mapper.userEntityToUserViewDTO(userService.findById(id));

        response.setData(user);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/{id}/notifications")
    @ResponseStatus(HttpStatus.OK)
    public Response getByIdNotifications(@PathVariable Long id) {
        Response response = new Response();

        UserViewDTO user = mapper.userEntityToUserViewDTO(userService.findById(id));

        response.setData(user.getNotifications());
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody UserDTO dto) {
        Response response = new Response();

        User user = new User();

        userService.setAttributes(dto, user);

        UserViewDTO savedUser = mapper.userEntityToUserViewDTO(userService.save(user));

        response.setData(savedUser);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody UserDTO dto, @PathVariable Long id) {
        Response response = new Response();

        User user = userService.findById(id);

        userService.setAttributes(dto, user);

        UserViewDTO savedUser = mapper.userEntityToUserViewDTO(userService.save(user));

        response.setData(savedUser);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        userService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
