package net.therap.mealplanner.web.helper;

import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.utils.Utils;
import net.therap.mealplanner.web.command.SignUpFormInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author bashir
 * @since 11/15/16
 */
@Component
public class SignUpHelper {

    @Autowired
    Utils utils;

    public User createNewUser(SignUpFormInfo signUpFormInfo) {

        User user = new User();
        user.setName(signUpFormInfo.getName());
        user.setRole("user");
        user.setEmail(signUpFormInfo.getEmail());
        user.setPassword(utils.hashMd5(signUpFormInfo.getPassword()));

        return user;
    }
}
