package com.example.educationCrm.serviceImp;

import com.example.educationCrm.model.dto.StudentDTO;
import com.example.educationCrm.model.dto.StudentInformationDTO;
import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.repository.SchoolRepository;
import com.example.educationCrm.repository.StudentClassRepository;
import com.example.educationCrm.repository.StudentRepository;
import com.example.educationCrm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private StudentClassRepository studentClassRepository;
    /*private final SimpleDateFormat formatter
            = new SimpleDateFormat("dd-MM-yyyy"
            , Locale.ENGLISH);*/

    @Transactional
    @Override
    public void save(Student student) {
        this.studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    @Override
    public StudentInformationDTO getNameSurnameById(Long id) {
        return this.studentRepository.getStudentById(id);
    }

    @Transactional
    @Override
    public void update(Student student) {
            this.studentRepository.save(student);
    }

    @Transactional
    @Override
    public void delete(Student student) {
        this.studentRepository.delete(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) this.studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<StudentInformationDTO> getBySchoolName(String schoolName) {
        return this.studentRepository.findStudentBySchoolName(schoolName);
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }
}
