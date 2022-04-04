package com.plantariadoluis.plantaria.dtos;

import com.plantariadoluis.plantaria.models.OrderModel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class CustomerDto {

    @NotBlank
    private String name;
    @Email @NotBlank
    private String email;
    @CPF @NotBlank
    private String cpf;
    @NotBlank
    private String address;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String phone;
}
