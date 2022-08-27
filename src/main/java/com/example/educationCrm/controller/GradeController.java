package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.*;
import com.example.educationCrm.service.*;
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
public class GradeController {

    private Grade grade;

    private Grade selectedGrade;

    private List<Grade> gradeList;

    private List<Student> studentList;

    private List<Teacher> teacherList;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @PostConstruct
    public void init(){
        this.grade = new Grade();
        this.studentList = studentService.findAll();
        this.teacherList = teacherService.findAll();
    }
    public void save (){
        this.gradeService.save(this.grade);
        infoMessage("Kaydedildi",
                "Kaydedilen Not : ",this.grade);
        this.gradeList.add(this.grade);
        this.grade = new Grade();
    }

    public void delete(Grade grade){
        this.gradeService.delete(this.grade);
        infoMessage("Silindi.",
                "Silinen Not : ",this.grade);
        this.gradeList.remove(this.grade);
    }

    public void update(){
        this.gradeService.update(this.selectedGrade);
        infoMessage("Güncellendi.",
                "Güncellenen Not : ",this.selectedGrade);
        this.selectedGrade = new Grade();
    }

    public void selectGrade(Grade grade){
        this.selectedGrade =grade;
    }

    public void infoMessage(String summary, String detail, Grade grade) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,
                new FacesMessage(summary,
                        detail + grade.getGrade()));
    }
}
