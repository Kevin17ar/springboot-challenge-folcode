package com.folcode.challenge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "alumno")
public class Student extends EntityA {

    @Column(name = "numero_registro", unique = true)
    private Integer registryNumber;

    @Column(name = "carrera")
    private String career;

    @CreationTimestamp
    @Column(name = "fecha_inscripcion")
    private LocalDateTime registrationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private User user;

}
