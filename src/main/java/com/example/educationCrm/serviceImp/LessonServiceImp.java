package com.example.educationCrm.serviceImp;

import com.example.educationCrm.helper.ModelMapperHelper;
import com.example.educationCrm.model.dto.LessonDTO;
import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.model.entity.Teacher;
import com.example.educationCrm.repository.LessonRepository;
import com.example.educationCrm.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImp implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Lesson lesson) {
        //Lesson lesson1 = new Lesson(name);
        this.lessonRepository.save(lesson);
        //this.lessonRepository.save(lesson1);
    }

    @Transactional
    @Override
    public void update(Lesson lesson) {
        this.lessonRepository.save(lesson);
    }

    @Transactional
    @Override
    public void delete(Lesson lesson) {
        this.lessonRepository.delete(lesson);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Lesson> findAll() {
        return (List<Lesson>) this.lessonRepository.findAll();
    }
    @Override
    public Lesson findByName(String name) {
        return this.lessonRepository.findLessonByName(name);
    }
}
