package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ADMINISTRATOR")
@Getter
@Setter
@NoArgsConstructor
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(nullable = false, unique = true, length = 130)
    private String email;
    @Column(nullable = false, unique = true, length = 130)
    private String user;
    @Column(nullable = false, length = 30)
    private String password;
}
