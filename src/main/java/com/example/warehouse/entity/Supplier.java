package com.example.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "suppliers")
public class Supplier extends BaseEntity {
    @Column(nullable = false)
    private String phoneNumber;
}
