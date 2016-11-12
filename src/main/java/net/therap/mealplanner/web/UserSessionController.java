package net.therap.mealplanner.web;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.SignUpService;
import net.therap.mealplanner.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author bashir
 * @since 11/6/16
 */

@Controller
public class UserSessionController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String displayLogin(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (userAlreadyLoggedIn(user)) {
            return redirectUserHome(user);
        }

        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginSubmit(HttpSession session,
                              @RequestParam(name = "lg_username", defaultValue = "") String username,
                              @RequestParam(name = "lg_password", defaultValue = "") String password) {

        User user = (User) session.getAttribute("user");

        if (userAlreadyLoggedIn(user)) {
            return redirectUserHome(user);
        }

        user = userDetailsService.validateUser(username, password);

        if (null != user) {
            session.setAttribute("user", user);
            return redirectUserHome(user);
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignUp() {

        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpSubmit(HttpSession session,
                               @RequestParam(name = "sg_name", defaultValue = "") String name,
                               @RequestParam(name = "sg_email", defaultValue = "") String email,
                               @RequestParam(name = "sg_password", defaultValue = "") String password,
                               @RequestParam(name = "sg_password2", defaultValue = "") String password2) {

        if (signUpService.isValidSignUpForm(name, email, password, password2)) {
            User user = signUpService.createNewUser(name, email, password);
            user = userDetailsService.addNewUser(user);
            session.setAttribute("user", user);
        }

        return "forward:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() {

        return "404";
    }

    public boolean userAlreadyLoggedIn(User user) {
        if (null != user) {
            return true;
        } else {
            return false;
        }
    }

    private String redirectUserHome(User user) {
        if (("admin").equals(user.getRole())) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/home";
        }
    }
}
