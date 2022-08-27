package com.example.educationCrm.controller;

import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.service.LessonService;
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
public class LessonController {
    private Lesson lesson;
    private Lesson selectedLesson;

    private List<Lesson> lessonList;

    @Autowired
    private LessonService lessonService;

    @PostConstruct
    private void init (){
        this.lesson=new Lesson();
        this.lessonList = lessonService.findAll();
    }

    public void save(){
        this.lessonService.save(this.lesson);
        infoMessage("Başarılı Kayıt ",
                "Kaydedilen Ders :",this.lesson);
        this.lesson=new Lesson();
    }

    public List<Lesson> getList(){return this.lessonService.findAll();}

    public void delete(Lesson lesson){
        this.lessonService.delete(lesson);
        infoMessage("Başarılı Silindi",
                "Silinen Ders : ",lesson);
        this.lessonList.remove(lesson);
    }
    public void update(){
        this.lessonService.update(this.selectedLesson);
        infoMessage("Güncellendi.",
                "Güncellenen Ders : ",this.selectedLesson);
        this.selectedLesson = new Lesson();
    }

    public void selectLesson(Lesson lesson){
        this.selectedLesson=lesson;
    }

    public void clearSelectedLesson(){
        this.selectedLesson=new Lesson();
    }
    public void selectLessonForUpdate(Lesson lesson){
        this.lesson = lesson;
    }
    public void infoMessage(String summary, String detail, Lesson lesson) {
        FacesContext context = FacesContext.getCurrentInstance();

        context.addMessage
                (null, new FacesMessage(summary,
                        detail+ lesson.getName()) );
    }
}
