package net.therap.mealplanner.web.validator;

import net.therap.mealplanner.utils.Utils;
import net.therap.mealplanner.web.command.LoginFormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author bashir
 * @since 11/14/16
 */
@Component
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginFormInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","username.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password","password.required");

    }
}
