package com.microservice.product.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "product")
@ToString
@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    private Date  insertDate;

    public void update(Product product){
        this.setName(product.name);
        this.setPrice(product.price);
        this.setId(product.id);
    }


}
