package com.folcode.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateDTO {

    private Integer dni;

    @JsonProperty("nombre")
    private String name;

    @JsonProperty("apellido")
    private String lastName;

    @JsonProperty("telefono")
    private String phone;

    private String email;

    @JsonProperty("nacionalidad")
    private String nationality;

    @JsonProperty("requisitos_cumplidos")
    private Boolean requirementsMet;

    @JsonProperty("fecha_nacimiento")
    private LocalDate birthDate;
}
