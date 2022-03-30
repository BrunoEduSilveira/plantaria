package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "CLIENTE")
@Getter
@Setter
@NoArgsConstructor
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", columnDefinition = "BINARY(16)")
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
