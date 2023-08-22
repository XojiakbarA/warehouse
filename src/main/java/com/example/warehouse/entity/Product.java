package com.example.warehouse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "products")
public class Product extends BaseEntity {
    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;
}
