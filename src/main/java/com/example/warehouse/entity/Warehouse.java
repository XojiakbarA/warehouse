package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "warehouses")
public class Warehouse extends BaseEntity {
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "warehouses")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> users;
}
