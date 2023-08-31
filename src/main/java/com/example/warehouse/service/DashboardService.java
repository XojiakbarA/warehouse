package com.example.warehouse.service;

import com.example.warehouse.dto.dashboard.DailyInputDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;

import java.util.List;

public interface DashboardService {
    DailyInputDTO getDailyInputs();

    List<MostOutputProductsDTO> getDailyMostOutputProducts();
}
