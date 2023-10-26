package com.folcode.challenge.service;

import com.folcode.challenge.dto.StudentDTO;
import com.folcode.challenge.dto.StudentParamDTO;
import com.folcode.challenge.dto.StudentUpdateDTO;
import com.folcode.challenge.entity.Student;
import com.folcode.challenge.entity.User;
import com.folcode.challenge.mapper.StudentMapper;
import com.folcode.challenge.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    /**
     * Creates a new student entity in the database based on the provided StudentDTO object.
     * This method uses MapStruct for mapping between the StudentDTO and the Student entity,
     * eliminating the need for manual mapping boilerplate. Additionally, it sets up the associated
     * user for the student based on the user ID provided in the DTO.
     *
     * @param studentParamDTO The data transfer object containing the details of the student to be created.
     */
    @Override
    public void createStudent(StudentParamDTO studentParamDTO) {
        Student studentEntity = StudentMapper.INSTANCE.studentDTOtoStudent(studentParamDTO);
        studentEntity.setUser(new User(studentParamDTO.getUserId()));

        studentRepository.save(studentEntity);
    }

    /**
     * Retrieves a student from the database based on the provided student ID
     * and converts the student entity to a StudentDTO object, if found.
     *
     * @param studentId The ID of the student to be retrieved.
     * @return An Optional containing the StudentDTO object if the student is found,
     * or an empty Optional if not.
     */
    @Override
    public Optional<StudentDTO> findById(Long studentId) {
        return studentRepository.findById(studentId).map(StudentMapper.INSTANCE::studentToStudentDTO);
    }

    /**
     * Retrieves all students from the database and converts them to a list of StudentDTO objects.
     * @return A list of StudentDTO objects representing all the students in the database.
     */
    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(StudentMapper.INSTANCE::studentToStudentDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void updateById(Long studentId, StudentUpdateDTO studentUpdateDTO) {
        Optional<Student> student = studentRepository.findById(studentId);

        if (student.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Student studentEntity = student.get();
        if (studentUpdateDTO.getCareer() != null) studentEntity.setCareer(studentUpdateDTO.getCareer());
        if (studentUpdateDTO.getRegistrationDate() != null) studentEntity.setRegistrationDate(studentUpdateDTO.getRegistrationDate());
        if (studentUpdateDTO.getRegistryNumber() != null) studentEntity.setRegistryNumber(studentUpdateDTO.getRegistryNumber());

        this.save(studentEntity);
    }
}
