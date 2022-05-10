package com.plantariadoluis.plantaria.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id")
    private long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerModel customer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<ProductModel> products;
    @Column(nullable = false)
    private LocalDateTime dateCreated;
    @Column
    private LocalDateTime dateModified;
    @Column(nullable = false, length = 2)
    private int paymentMethod;
    private float freight;
    private float discount;
    @Column(nullable = false)
    private float total;
    @Column(nullable = false, length = 2)
    private int status;
}
