package com.example.warehouse.entity;

import com.example.warehouse.dto.dashboard.TotalAmountDTO;
import com.example.warehouse.dto.dashboard.TotalCostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
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
@NamedNativeQuery(
        name = "InputProduct.findAllNearToExpire",
        query = "select ip.id, ip.amount, ip.expire_date as expireDate, ip.price from input_products ip where (extract(epoch from ip.expire_date - now())/86400) < (select value from remind_before where selected=true) and (ip.amount - (select coalesce(sum(amount), 0) from output_products where input_product_id=ip.id)) > 0",
        resultSetMapping = "Mapping.InputProduct")
@SqlResultSetMapping(
        name = "Mapping.InputProduct",
        classes = @ConstructorResult(
                targetClass = InputProduct.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "amount"), @ColumnResult(name = "price"),
                        @ColumnResult(name = "expireDate")
                })
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

    @Formula("(select (amount) - (select coalesce(sum(op.amount), 0) from output_products op where op.input_product_id=id))")
    private Double remaining;

    @Formula("(select extract(epoch from expire_date - now()) / 86400)")
    private Double nearToExpire;
}
