package net.therap.mealplanner.service;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bashir
 * @since 10/26/16
 */
@Service
public class SignUpService {

    @Autowired
    Utils utils;

    final static Logger log = LogManager.getLogger(SimpleLogger.class);

    public boolean isValidSignUpForm(String name, String email, String password, String password2) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()
                || name == null || email == null || password == null || password2 == null) {
            return false;
        } else {
            return password.equals(password2);
        }
    }

    public User createNewUser(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setRole("user");
        user.setEmail(email);
        user.setPassword(utils.hashMd5(password));
        return user;
    }
}
