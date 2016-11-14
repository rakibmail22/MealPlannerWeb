package net.therap.mealplanner.validator;

import net.therap.mealplanner.web.command.SignUpFormInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bashir
 * @since 11/14/16
 */
@Component
public class SignUpFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpFormInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyPassword", "verifyPassWord.required");

        SignUpFormInfo signUpFormInfo= (SignUpFormInfo) target;
        if (!signUpFormInfo.getPassword().equals(signUpFormInfo.getVerifyPassword())) {
            errors.rejectValue("password","password.mismatch");
            errors.rejectValue("verifyPassword","password.mismatch");
        }
    }
}
