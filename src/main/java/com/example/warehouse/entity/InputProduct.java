package com.example.warehouse.entity;

import com.example.warehouse.dto.dashboard.TotalAmountDTO;
import com.example.warehouse.dto.dashboard.TotalCostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@NamedNativeQuery(
        name = "InputProduct.findDailyInputTotalCost",
        query = "select c.currency_code as currencyCode, sum(ip.price) as sum from input_products ip join inputs i on ip.input_id = i.id join currencies c on i.currency_id=c.id where cast(i.date as date) = cast(now() as date) group by c.currency_code",
        resultSetMapping = "Mapping.TotalCostDTO")
@SqlResultSetMapping(
        name = "Mapping.TotalCostDTO",
        classes = @ConstructorResult(
                targetClass = TotalCostDTO.class,
                columns = {@ColumnResult(name = "currencyCode"), @ColumnResult(name = "sum")})
)
@NamedNativeQuery(
        name = "InputProduct.findDailyInputTotalAmount",
        query = "select m.name as measurementName, sum(ip.amount) as sum from input_products ip join inputs i on ip.input_id=i.id join products p on ip.product_id=p.id join measurements m on m.id=p.measurement_id where cast(i.date as date) = cast(now() as date) group by m.name",
        resultSetMapping = "Mapping.TotalAmountDTO")
@SqlResultSetMapping(
        name = "Mapping.TotalAmountDTO",
        classes = @ConstructorResult(
                targetClass = TotalAmountDTO.class,
                columns = {@ColumnResult(name = "measurementName"), @ColumnResult(name = "sum")})
)
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Input input;
}
