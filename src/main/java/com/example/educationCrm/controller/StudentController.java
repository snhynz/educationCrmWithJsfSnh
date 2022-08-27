package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.*;
import com.example.educationCrm.service.SchoolService;
import com.example.educationCrm.service.StudentClassService;
import com.example.educationCrm.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Scope(value = "view")
@Controller
@Getter
@Setter
public class StudentController {

    private Student student;

    private Student selectedStudent;

    private List<Student> studentList;

    private List<School> schoolList;

    private List<StudentClass> studentClasses;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentClassService studentClassService;

    @PostConstruct
    public void init(){
        this.student = new Student();
        this.studentList = studentService.findAll();
        this.studentClasses = studentClassService.findAll();
        this.schoolList = schoolService.findAll();
    }
    public void save (){
        this.studentService.save(this.student);
        infoMessage("Kaydedildi",
                "Kaydedilen Öğrenci : ",this.student);
        this.studentList.add(this.student);
        this.student = new Student();
    }

    public void delete(Student student){
        this.studentService.delete(student);
        infoMessage("Silindi.",
                "Silinen Öğrenci : ",student);
        this.studentList.remove(student);
    }

    public void update(){
        this.studentService.update(this.selectedStudent);
        infoMessage("Güncellendi.",
                "Güncellenen Öğrenci : ",this.selectedStudent);
        this.selectedStudent = new Student();
    }

    public void selectStudent(Student student){
        this.selectedStudent =student;
    }

    public void infoMessage(String summary, String detail, Student student) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(summary,
                        detail + student.getName()));
    }
}
