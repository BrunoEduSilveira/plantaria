package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, length = 130)
    private String name;
    @Column(nullable = false)
    private float price;
    private String note;
}
