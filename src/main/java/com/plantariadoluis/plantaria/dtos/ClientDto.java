package com.plantariadoluis.plantaria.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ClientDto {

    @NotBlank
    private String name;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String address;
    private String numberAddress;
    private String zipCode;
    private String telephone;
}
