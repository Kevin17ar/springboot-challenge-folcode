package com.folcode.challenge.mapper;

import com.folcode.challenge.dto.StudentDTO;
import com.folcode.challenge.dto.StudentParamDTO;
import com.folcode.challenge.dto.StudentUpdateDTO;
import com.folcode.challenge.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentParamDTO studentToUStudentDTO(Student student);
    Student studentDTOtoStudent(StudentParamDTO studentParamDTO);

    StudentDTO studentToStudentDTO(Student student);

    /*@Mapping(target = "*",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student studentUpdateDTOToStudent(StudentUpdateDTO studentUpdateDTO);*/
}
