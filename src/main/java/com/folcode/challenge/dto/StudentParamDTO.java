package com.folcode.challenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.folcode.challenge.entity.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class StudentParamDTO {

    private Long id;

    @NotNull
    @JsonProperty("numero_registro")
    private Integer registryNumber;

    @NotNull
    @NotEmpty
    @JsonProperty("carrera")
    private String career;

    @JsonProperty("fecha_inscription")
    private LocalDateTime registrationDate;

    @NotNull
    @Min(1)
    @JsonProperty("usuario_id")
    private Long userId;

    @JsonProperty("usuario")
    private UserParamDTO user;

    public StudentParamDTO(Student student) {
        this.id = student.getId();
        this.registryNumber = student.getRegistryNumber();
        this.career = student.getCareer();
        this.registrationDate = student.getRegistrationDate();
        this.user = new UserParamDTO(student.getUser());
    }
}
