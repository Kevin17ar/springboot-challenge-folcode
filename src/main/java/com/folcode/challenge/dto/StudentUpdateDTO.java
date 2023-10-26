package com.folcode.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentUpdateDTO {

    @JsonProperty("numero_registro")
    private Integer registryNumber;

    @JsonProperty("carrera")
    private String career;

    @JsonProperty("fecha_inscription")
    private LocalDateTime registrationDate;
}
