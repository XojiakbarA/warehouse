package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "currencies")
public class Currency implements Activable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private java.util.Currency currencyCode;

    @Column(nullable = false)
    private Boolean active = true;
}
