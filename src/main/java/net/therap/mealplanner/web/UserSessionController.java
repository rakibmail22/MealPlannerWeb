package net.therap.mealplanner.web;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.SignUpService;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
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
    UserDetailsService userDetailsService;

    @Autowired
    SignUpService signUpService;

    @Autowired
    Utils utils;

    static final Logger log = LogManager.getLogger(SimpleLogger.class);

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
        try {
            User user = (User) session.getAttribute("user");
            if (userAlreadyLoggedIn(user)) {
                return redirectUserHome(user);
            }
            password = utils.hashMd5(password);
            user = userDetailsService.validateUser(username, password);
            if (user != null) {
                session.setAttribute("user", user);
                log.debug("UserSessionController : loginSubmit ::: Username: " + username);
                return redirectUserHome(user);
            } else {
                log.debug("UserSessionController : loginSubmit ::: Login Failed");
                return "login";
            }
        } catch (Exception e) {
            log.error("UserSessionController : loginSubmit ::: exception ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignUp() {
        try {
            return "login";
        } catch (Exception e) {
            log.error("SignUpController :: doGet: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpSubmit(HttpSession session,
                               @RequestParam(name = "sg_name", defaultValue = "") String name,
                               @RequestParam(name = "sg_email", defaultValue = "") String email,
                               @RequestParam(name = "sg_password", defaultValue = "") String password,
                               @RequestParam(name = "sg_password2", defaultValue = "") String password2) {
        try {
            if (signUpService.isValidSignUpForm(name, email, password, password2)) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setRole("user");
                user.setPassword(utils.hashMd5(password));
                user = userDetailsService.addNewUser(user);
                session.setAttribute("user", user);
            }
            log.error("UserSessionController : signUpSubmit::: name "+name);
            return "forward:/login";
        } catch (Exception e) {
            log.error("UserSessionController : signUpSubmit::: exception "+e);
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        try {
            session.invalidate();
            log.debug("UserSessionController : logout");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("UserSessionController : logout::: exception "+e);
            return null;
        }
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound() {
        log.debug("UserSessionController : pageNotFound");
        return "404";
    }

    public boolean userAlreadyLoggedIn(User user) {
        if (user != null) {
            log.debug("UserSessionController : userAlreadyLoggedIn ::: "+true);
            return true;
        } else {
            log.debug("UserSessionController : userAlreadyLoggedIn ::: "+false);
            return false;
        }
    }

    private String redirectUserHome(User user) {
        if (user.getRole().equals("admin")) {
            log.debug("UserSessionController : redirectUserHome ::: admin");
            return "redirect:/admin/home";
        } else {
            log.debug("UserSessionController : redirectUserHome ::: user");
            return "redirect:/home";
        }
    }

}
