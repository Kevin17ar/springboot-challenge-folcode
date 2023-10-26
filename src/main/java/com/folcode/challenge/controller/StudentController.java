package com.folcode.challenge.controller;

import com.folcode.challenge.dto.StudentDTO;
import com.folcode.challenge.dto.StudentParamDTO;
import com.folcode.challenge.dto.StudentUpdateDTO;
import com.folcode.challenge.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentParamDTO studentParamDTO, Errors errors) {

        if (errors.hasErrors()) {
            Map<String, Object> body = new HashMap<>();
            List<String> errosList = errors.getFieldErrors().stream().map(error -> "[" + error.getField() + "] " + error.getDefaultMessage()).collect(Collectors.toList());

            body.put("timestamp", LocalDateTime.now());
            body.put("status", 400);
            body.put("errors", errosList);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        studentService.createStudent(studentParamDTO);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<?> findStudentById(@PathVariable Long studentId) {

        Optional<StudentDTO> student = studentService.findById(studentId);

        if (student.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<StudentDTO> students = studentService.findAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteById(@PathVariable Long studentId) {
        try {
            studentService.deleteById(studentId);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @Valid @RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateById(studentId, studentUpdateDTO);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
