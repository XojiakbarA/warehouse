package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "input_products")
public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Date expireDate;

    @ManyToOne(optional = false)
    private Input input;
}
