package com.microservice.product.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
@ToString
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Product  extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The product name can't be null")
    @NotBlank(message = "Product name can't be blank")
    @Size(max = 50, message = "The product name is too long")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "The product price can't be null")
    private Double price;


}
