package com.example.warehouse.controller;

import com.example.warehouse.dto.RemindBeforeDTO;
import com.example.warehouse.dto.Response;
import com.example.warehouse.entity.RemindBeforeOption;
import com.example.warehouse.exception.RemindBeforeOptionIsSelectedException;
import com.example.warehouse.service.RemindBeforeOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remind-before-options")
public class RemindBeforeOptionController {

    @Autowired
    private RemindBeforeOptionService remindBeforeOptionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getAll() {
        Response response = new Response();

        List<RemindBeforeOption> remindBeforeOptionList = remindBeforeOptionService.findAll();

        response.setData(remindBeforeOptionList);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody RemindBeforeDTO dto) {
        Response response = new Response();

        RemindBeforeOption remindBeforeOption = new RemindBeforeOption();

        remindBeforeOption.setValue(dto.getValue());

        RemindBeforeOption savedRemindBeforeOption = remindBeforeOptionService.save(remindBeforeOption);

        response.setData(savedRemindBeforeOption);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response update(@PathVariable Long id) {
        Response response = new Response();

        remindBeforeOptionService.findAll().forEach(r -> {
                    r.setSelected(false);
                    remindBeforeOptionService.save(r);
        });

        RemindBeforeOption remindBeforeOption = remindBeforeOptionService.findById(id);
        remindBeforeOption.setSelected(true);
        RemindBeforeOption savedRemindBeforeOption = remindBeforeOptionService.save(remindBeforeOption);

        response.setData(savedRemindBeforeOption);
        response.setMessage(HttpStatus.CREATED.name());
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Response delete(@PathVariable Long id) {
        Response response = new Response();

        RemindBeforeOption remindBeforeOption = remindBeforeOptionService.findById(id);

        if (remindBeforeOption.getSelected()) {
            throw new RemindBeforeOptionIsSelectedException(remindBeforeOption.getValue() + " days");
        }

        remindBeforeOptionService.deleteById(id);

        response.setMessage(HttpStatus.ACCEPTED.name());
        return response;
    }
}
