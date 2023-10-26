package com.folcode.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private Long id;

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
