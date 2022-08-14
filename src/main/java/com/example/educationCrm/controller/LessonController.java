package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.service.LessonService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

@Scope(value = "view")
@Controller
@Getter
@Setter
public class LessonController {
    private Lesson lesson;

    @Autowired
    private LessonService lessonService;

    @PostConstruct
    private void init (){
        this.lesson=new Lesson();
    }

    public void save(){
        this.lessonService.save(this.lesson);
    }

    public List<Lesson> getList(){return this.lessonService.findAll();}

    public void delete(Lesson lesson){
        this.lessonService.delete(lesson);
    }
    public void update(Lesson lesson){
        this.lesson=lesson;
    }
}
