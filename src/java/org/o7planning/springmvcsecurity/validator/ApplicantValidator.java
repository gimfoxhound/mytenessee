
package org.o7planning.springmvcsecurity.validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.o7planning.springmvcsecurity.model.ApplicantInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class ApplicantValidator implements Validator{
    
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ApplicantInfo.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        
        ApplicantInfo applicantInfo = (ApplicantInfo)target;
        
        // Check the fields of ApplicantInfo.
        // (See more in property file: messages/validator.property)
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.applicantForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.applicantForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "NotEmpty.applicantForm.position");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.applicantForm.gender");
        
        if(!emailValidator.isValid(applicantInfo.getEmail())){
            errors.rejectValue("email", "Pattern.applicantForm.email");
        }
        
        if(applicantInfo.getSkills()==null|| applicantInfo.getSkills().length==0){
            errors.rejectValue("skills", "Select.applicantForm.skills");
        }
        
    }
    
    
    
    
}
