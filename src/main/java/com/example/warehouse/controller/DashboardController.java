package com.example.warehouse.controller;

import com.example.warehouse.dto.Response;
import com.example.warehouse.dto.dashboard.DailyInputDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/daily-inputs")
    @ResponseStatus(HttpStatus.OK)
    public Response getDailyInputs() {
        Response response = new Response();

        DailyInputDTO dto = dashboardService.getDailyInputs();

        response.setData(dto);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }

    @GetMapping("/daily-most-output-products")
    @ResponseStatus(HttpStatus.OK)
    public Response getDailyMostOutputProducts() {
        Response response = new Response();

        List<MostOutputProductsDTO> products = dashboardService.getDailyMostOutputProducts();

        response.setData(products);
        response.setMessage(HttpStatus.OK.name());
        return response;
    }
}
