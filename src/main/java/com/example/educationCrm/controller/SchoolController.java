package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.School;
import com.example.educationCrm.service.SchoolService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Scope(value = "view")
@Controller
@Getter
@Setter
public class SchoolController {
    private School school;
    @Autowired
    private SchoolService schoolService;

    @PostConstruct
    public void init(){
        this.school = new School();
    }

    public void save(){
        this.schoolService.save(this.school);
    }
}
