package com.example.warehouse.service.impl;

import com.example.warehouse.dto.dashboard.DailyInputDTO;
import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import com.example.warehouse.dto.dashboard.TotalAmountDTO;
import com.example.warehouse.dto.dashboard.TotalCostDTO;
import com.example.warehouse.entity.InputProduct;
import com.example.warehouse.service.DashboardService;
import com.example.warehouse.service.InputProductService;
import com.example.warehouse.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private InputProductService inputProductService;
    @Autowired
    private OutputProductService outputProductService;
    @Override
    public DailyInputDTO getDailyInputs() {
        DailyInputDTO dto = new DailyInputDTO();

        List<TotalCostDTO> totalCost = inputProductService.findDailyInputTotalCost();
        List<TotalAmountDTO> totalAmount = inputProductService.findDailyInputTotalAmount();
        dto.setTotalCost(totalCost);
        dto.setTotalAmount(totalAmount);

        return dto;
    }

    @Override
    public List<MostOutputProductsDTO> getDailyMostOutputProducts() {
        return outputProductService.findDailyMostOutputProducts();
    }

    @Override
    public List<InputProduct> getAllNearToExpireInputProducts() {
        return inputProductService.findAllNearToExpire();
    }

    @Override
    public Long getCountNearToExpireInputProducts() {
        return inputProductService.countNearToExpire();
    }
}
