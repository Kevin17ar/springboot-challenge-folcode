package com.folcode.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcode.challenge.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UserParamDTO {

    @NotNull
    private Integer dni;

    @NotNull
    @NotEmpty(message = "Campo no debe ser vacío")
    @JsonProperty("nombre")
    private String name;

    @NotNull
    @NotEmpty
    @JsonProperty("apellido")
    private String lastName;

    @NotNull
    @NotEmpty
    @JsonProperty("telefono")
    private String phone;

    @NotNull
    @Email
    private String email;

    @JsonProperty("nacionalidad")
    private String nationality;

    @JsonProperty("requisitos_cumplidos")
    private Boolean requirementsMet;

    @JsonProperty("fecha_nacimiento")
    private LocalDate birthDate;

    public UserParamDTO(User user) {
        this.dni = user.getDni();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.nationality = user.getNationality();
        this.requirementsMet = user.getRequirementsMet();
        this.birthDate = user.getBirthDate();
    }
}
