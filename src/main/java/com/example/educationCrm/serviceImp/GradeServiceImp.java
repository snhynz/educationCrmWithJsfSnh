package com.example.educationCrm.serviceImp;

import com.example.educationCrm.helper.ModelMapperHelper;
import com.example.educationCrm.model.dto.GradeDTO;
import com.example.educationCrm.model.dto.GradeFilterLessonDTO;
import com.example.educationCrm.model.entity.Grade;
import com.example.educationCrm.model.entity.Student;
import com.example.educationCrm.model.entity.Teacher;
import com.example.educationCrm.repository.GradeRepository;
import com.example.educationCrm.repository.LessonRepository;
import com.example.educationCrm.repository.StudentRepository;
import com.example.educationCrm.repository.TeacherRepository;
import com.example.educationCrm.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
    Rest Template detayı için :
    https://www.baeldung.com/rest-template
    https://www.baeldung.com/spring-resttemplate-json-list
 */
@Service
public class GradeServiceImp implements GradeService {
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private ModelMapperHelper modelMapperHelper;

    @Transactional
    @Override
    public void update(Grade grade) {
            this.gradeRepository.save(grade);
    }

    @Transactional
    @Override
    public void save(Grade grade) {
        this.gradeRepository.save(grade);
    }

    @Transactional
    @Override
    public void delete(Grade grade) {
        /*Grade grade =
                this.gradeRepository.findById(id).get();
        grade.setActive(false);
        this.gradeRepository.save(grade);*/
        this.gradeRepository.delete(grade);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Grade> findAll() {
        return (List<Grade>) this.gradeRepository.findAll();
    }

    @Override
    public List<Object> getGradesByLessonName(String lessonName) {
        return this.gradeRepository
                .getGradesByLessonName(lessonName);
    }

    @Override
    public List<GradeFilterLessonDTO> getGradesByLessonNameWithDerived(String lessonName) {
       List<Grade> gradeLists = this.gradeRepository.
               findGradeByTeacher_LessonName(lessonName);
       List<GradeFilterLessonDTO> resultList =  new ArrayList<>();
       for(Grade g : gradeLists){
           GradeFilterLessonDTO gradeFilterLessonDTO = new GradeFilterLessonDTO();
           gradeFilterLessonDTO.setLessonName(g.getTeacher().getLesson().getName());
           gradeFilterLessonDTO.setName(g.getStudent().getName());
           gradeFilterLessonDTO.setSurname(g.getStudent().getSurname());
           gradeFilterLessonDTO.setGrade(g.getGrade());
           resultList.add(gradeFilterLessonDTO);
       }
       return resultList;
    }

    @Override
    public List<GradeDTO> getEntegrationGrade(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://92.205.21.233:8090/grade";
        /*//postForObject
        //data göndermek için
        Object[] objects
                = restTemplate.getForObject(fooResourceUrl, Object[].class);
        List<GradeDTO> response = Arrays.stream(objects)
                .map(object -> modelMapperHelper.convertToModel(object, GradeDTO.class))
                .collect(Collectors.toList());
         */
       ResponseEntity<List<GradeDTO>> responseEntity =
                restTemplate.exchange(
                        fooResourceUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<GradeDTO>>() {}
                );
        List<GradeDTO> response = responseEntity.getBody();
        return response;
    }
}
