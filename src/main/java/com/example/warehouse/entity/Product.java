package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "products")
public class Product extends BaseEntity {
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment photo;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(optional = false)
    private Measurement measurement;
}
