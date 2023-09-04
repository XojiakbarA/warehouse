package com.example.warehouse.entity;

import com.example.warehouse.dto.dashboard.MostOutputProductsDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NamedNativeQuery(
        name = "OutputProduct.findDailyMostOutputProducts",
        query = "select p.name, sum(op.amount) from output_products op\n" +
                "join input_products ip on op.input_product_id=ip.id\n" +
                "join products p on ip.product_id=p.id\n" +
                "join outputs o on op.output_id=o.id\n" +
                "where cast(o.date as date) = cast(now() as date)\n" +
                "group by p.name order by sum(op.amount) desc;",
        resultSetMapping = "Mapping.MostOutputProductsDTO")
@SqlResultSetMapping(
        name = "Mapping.MostOutputProductsDTO",
        classes = @ConstructorResult(
                targetClass = MostOutputProductsDTO.class,
                columns = {@ColumnResult(name = "name"), @ColumnResult(name = "sum")})
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "output_products")
public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private InputProduct inputProduct;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Output output;
}
