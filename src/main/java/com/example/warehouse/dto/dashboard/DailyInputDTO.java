package com.example.warehouse.dto.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class DailyInputDTO {
    private List<TotalCostDTO> totalCost;
    private List<TotalAmountDTO> totalAmount;
}
