package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ADMINISTRATOR")
@Getter
@Setter
@NoArgsConstructor
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_id")
    private long id;
    @Column(nullable = false, unique = true, length = 130)
    private String email;
    @Column(nullable = false, unique = true, length = 130)
    private String user;
    @Column(nullable = false, length = 30)
    private String password;
}
