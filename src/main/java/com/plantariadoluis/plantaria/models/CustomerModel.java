package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMER")
@Getter
@Setter
@NoArgsConstructor
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, length = 70)
    private String name;
    @Column(unique = true, length = 130)
    private String email;
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    @Column(length = 200)
    private String address;
    @Column(length = 6)
    private String numberAddress;
    @Column(length = 8)
    private String zipCode;
    @Column(length = 11)
    private String telephone;
}
