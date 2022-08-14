package com.example.educationCrm.service;

import com.example.educationCrm.model.dto.LessonDTO;
import com.example.educationCrm.model.entity.Lesson;

import java.util.List;

public interface LessonService {
    public void save(Lesson lesson);
    public void update(LessonDTO lessonDTO);
    public void delete(Lesson lesson);
    public List<Lesson> findAll();
}
