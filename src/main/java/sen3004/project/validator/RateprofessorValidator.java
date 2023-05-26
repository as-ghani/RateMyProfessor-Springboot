package sen3004.project.validator;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sen3004.project.model.Professor;

@Component
public class RateprofessorValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz){
        return Professor.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target,Errors errors){
        Professor professor=(Professor)target;
        LocalDate currentDate=LocalDate.now();
        int age=Period.between(professor.getDateOfBirth(), currentDate).getYears();
        if(age<18) {
            errors.rejectValue("dateOfBirth","my.custom.err");
        }
    }
}
