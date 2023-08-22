package com.example.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "categories")
public class Category extends BaseEntity {
    @ManyToOne
    private Category parentCategory;
}
