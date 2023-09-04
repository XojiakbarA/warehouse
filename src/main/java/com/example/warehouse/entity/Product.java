package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Formula;
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Measurement measurement;

    @Formula("(select coalesce(sum(ip.amount), 0) from input_products ip where ip.product_id=id) - (select coalesce(sum(op.amount), 0) from input_products ip join output_products op on ip.id=op.input_product_id where ip.product_id=id)")
    private Double remaining;
}
