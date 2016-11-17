package net.therap.mealplanner.web.helper;

import net.therap.mealplanner.domain.Role;
import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.utils.URL;
import net.therap.mealplanner.web.security.AuthUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * @author bashir
 * @since 11/15/16
 */
@Component
public class LoginHelper {

    public boolean userAlreadyLoggedIn(AuthUser authUser) {

        return null != authUser;
    }

    public String redirectUserHome(AuthUser authUser) {

        if (Role.admin.equals(authUser.getUserRole())) {
            return "redirect:/"+ URL.ADMIN_HOME;
        } else {
            return "redirect:/"+URL.USER_HOME;
        }
    }

    public AuthUser persistSessionData(User user, HttpSession session) {

        AuthUser authUser = new AuthUser(user.getId(), user.getRole());
        session.setAttribute("user", authUser);

        return authUser;
    }
}
