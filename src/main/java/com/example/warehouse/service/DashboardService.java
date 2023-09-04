package com.example.warehouse.service;

import com.example.warehouse.dto.dashboard.DailyInputDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.entity.InputProduct;

import java.util.List;

public interface DashboardService {
    DailyInputDTO getDailyInputs();

    List<MostOutputProductsDTO> getDailyMostOutputProducts();
    List<InputProduct> getAllNearToExpireInputProducts();
    Long getCountNearToExpireInputProducts();
}
