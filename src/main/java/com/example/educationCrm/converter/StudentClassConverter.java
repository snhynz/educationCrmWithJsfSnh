package com.example.educationCrm.converter;

import com.example.educationCrm.model.entity.Lesson;
import com.example.educationCrm.model.entity.StudentClass;
import com.example.educationCrm.service.StudentClassService;
import com.example.educationCrm.serviceImp.LessonServiceImp;
import com.example.educationCrm.serviceImp.StudentClassImp;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "studentClassConverter")
public class StudentClassConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent uiComponent, String value) {

        if (value == null || value.equals("")) {
            return null;
        }
        StudentClassImp studentClassImp = context.getApplication()
                .evaluateExpressionGet(context, "#{studentClassImp}"
                        , StudentClassImp.class);
        return studentClassImp.findByName(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof StudentClass) {
            return ((StudentClass) value).getName();
        }
        return "";
    }
}
