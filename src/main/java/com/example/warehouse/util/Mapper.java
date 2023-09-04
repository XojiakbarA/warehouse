package com.example.warehouse.util;

import com.example.warehouse.controller.NotificationDTO;
import com.example.warehouse.dto.CurrencyAvailableDTO;
import com.example.warehouse.dto.CurrencyViewDTO;
import com.example.warehouse.dto.UserViewDTO;
import com.example.warehouse.entity.Currency;
import com.example.warehouse.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {
    public CurrencyViewDTO currencyEntityToCurrencyViewDTO(Currency currency) {
        CurrencyViewDTO dto = new CurrencyViewDTO();
        dto.setId(currency.getId());
        dto.setCurrencyCode(currency.getCurrencyCode());
        dto.setCurrencyName(currency.getCurrencyCode().getDisplayName());
        dto.setActive(currency.getActive());
        return dto;
    }
    public CurrencyAvailableDTO currencyUtilToCurrencyAvailableDTO(java.util.Currency currency) {
        CurrencyAvailableDTO dto = new CurrencyAvailableDTO();
        dto.setCurrencyCode(currency.getCurrencyCode());
        dto.setCurrencyName(currency.getDisplayName());
        return dto;
    }
    public UserViewDTO userEntityToUserViewDTO(User user) {
        UserViewDTO dto = new UserViewDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setActive(user.getActive());
        dto.setCode(user.getCode());
        dto.setWarehouses(user.getWarehouses());
        List<NotificationDTO> notifications = user.getNotifications().stream().map(n -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            notificationDTO.setId(n.getId());
            notificationDTO.setMessage(n.getMessage());
            notificationDTO.setInputProduct(n.getInputProduct());
            return notificationDTO;
        }).toList();
        dto.setNotifications(notifications);
        return dto;
    }
}
