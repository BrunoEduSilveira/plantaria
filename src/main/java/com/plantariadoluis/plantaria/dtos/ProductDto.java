package com.plantariadoluis.plantaria.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProductDto {

    @NotBlank
    private String name;
    private float price;
    @NotBlank
    private String note;
    private int quantity;
}
