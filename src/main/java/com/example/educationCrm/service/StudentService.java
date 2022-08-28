package com.example.educationCrm.service;

import com.example.educationCrm.model.dto.StudentDTO;
import com.example.educationCrm.model.dto.StudentInformationDTO;
import com.example.educationCrm.model.entity.Student;

import java.text.ParseException;
import java.util.List;

public interface StudentService {
    void save(Student student);
    StudentInformationDTO getNameSurnameById(Long id);

    void update(Student student);
    void delete(Student student);

    List<Student> findAll();
    List<StudentInformationDTO> getBySchoolName(String schoolName);

    Student findById(Long id);

    List<Student> findAllStudent();
}
