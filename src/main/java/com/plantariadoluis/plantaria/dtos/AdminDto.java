package com.plantariadoluis.plantaria.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
public class AdminDto {

    @NotNull
    @NotEmpty
    @Email
    @Size(max = 50)
    private String email;
    @NotBlank
    @Size(min = 4, max = 30)
    private String user;
    @NotBlank
    @Size(min = 6, max = 30)
    private String password;
}
