package net.therap.mealplanner.web.helper;

import net.therap.mealplanner.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author bashir
 * @since 11/15/16
 */
@Component
public class LoginHelper {

    public boolean userAlreadyLoggedIn(User user) {

        if (null != user) {
            return true;
        } else {
            return false;
        }
    }

    public String redirectUserHome(User user) {

        if (("admin").equals(user.getRole())) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/home";
        }
    }

    public void persistSessionData(User user, HttpSession session) {
        session.setAttribute("user", user);
    }

}
