package com.folcode.challenge.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity(name = "usuario")
public class User extends EntityA {

    @Column(name = "apellido")
    private String lastName;

    @Column(name = "nombre")
    private String name;

    @NotNull
    @Column(unique = true)
    private Integer dni;

    @Column(name = "nacionalidad")
    private String nationality;

    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;

    @Column(name = "requisitos_cumplidos")
    private Boolean requirementsMet;

    private String email;

    @Column(name = "telefono")
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>();

    public User(Long id) {
        this.id = id;
    }
}
