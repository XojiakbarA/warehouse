package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "products")
public class Product extends BaseEntity {
    @ManyToOne
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment photo;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;
}
