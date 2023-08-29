package com.example.warehouse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "categories")
public class Category extends BaseEntity {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parentCategory;
}
