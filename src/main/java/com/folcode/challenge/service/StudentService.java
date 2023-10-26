package com.folcode.challenge.service;

import com.folcode.challenge.dto.StudentDTO;
import com.folcode.challenge.dto.StudentParamDTO;
import com.folcode.challenge.dto.StudentUpdateDTO;
import com.folcode.challenge.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void save(Student student);

    void createStudent(StudentParamDTO studentParamDTO);

    Optional<StudentDTO> findById(Long studentId);

    List<StudentDTO> findAll();

    void deleteById(Long studentId);

    void updateById(Long studentId, StudentUpdateDTO studentUpdateDTO);
}
