package com.plantariadoluis.plantaria.dtos;

import com.plantariadoluis.plantaria.models.CustomerModel;
import com.plantariadoluis.plantaria.models.ProductModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {

    private long customerId;
    private CustomerModel customer;
    private List<ProductModel> products;
    private LocalDateTime dateModified;
    private int paymentMethod;
    private float freight;
    private float discount;
    @NotNull
    private float total;
    private int status;
}
