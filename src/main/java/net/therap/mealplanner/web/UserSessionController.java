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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
        if (user != null) {
            if (user.getRole().equals("admin")) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/home";
            }
        }
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginSubmit(HttpServletRequest req) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    return "redirect:/admin/login";
                } else {
                    return "redirect:/login";
                }
            }

            String username = req.getParameter("lg_username");
            String password = utils.hashMd5(req.getParameter("lg_password")).trim();
            Map<String, String> messages = new HashMap<>();
            if (username == null || username.isEmpty()) {
                messages.put("username", "Username is required ");
            }
            if (password == null || password.isEmpty()) {
                messages.put("password", "Invalid Password");
            }

            log.debug("LOGIN doPost:: Username: " + username + " Password: " + password);
            if (messages.isEmpty()) {
                user = userDetailsService.validateUser(username, password);
                if (user != null) {
                    req.getSession().setAttribute("user", user);
                    log.debug("Logging in...........");
                    if (user.getRole().equals("admin")) {
                        return "redirect:/admin/home";
                    } else {
                        return "redirect:/home";
                    }
                } else {
                    log.debug("Logging failed...........");
                    messages.put("login", "Unknown login. Try again.");
                }

            }
            req.getSession().setAttribute("messages", messages);
            return "login";
        } catch (Exception e) {
            log.error("LoginController :: doPost: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignUp() {
        try {
            return "forward:/login";
        } catch (Exception e) {
            log.error("SignUpController :: doGet: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpSubmit(HttpServletRequest req) {
        log.info("Entered signup");
        try {
            String name = req.getParameter("sg_name");
            String email = req.getParameter("sg_email");
            String password = (signUpService.matchPassword(
                    req.getParameter("sg_password"), req.getParameter("sg_password2")))
                    ? req.getParameter("sg_password") : null;
            Map<String, String> messages = new HashMap<>();
            if (name == null || name.isEmpty()) {
                messages.put("username", "Name is required ");
            }
            if (email == null || name.isEmpty()) {
                messages.put("email", "Email is required ");
            }
            if (password == null || password.isEmpty()) {
                messages.put("password", "Invalid Password");
            }

            log.debug("SignUp doPost:: Username: " + name + " " + email + " " + password);
            if (messages.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setRole("user");
                user.setPassword(utils.hashMd5(password));
                user = userDetailsService.addNewUser(user);
                req.getSession().setAttribute("user", user);
                return "forward:/login";
            }
            req.setAttribute("messages", messages);
            return "forward:/login";
        } catch (Exception e) {
            log.error("SignUpController :: doPost: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        try {
            session.invalidate();
            log.debug("LogoutController:: LoginURI: ");
            return "redirect:/login";
        } catch (Exception e) {
            log.error("LogoutController :: doGet: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String pageNotFound(){
        return "404";
    }

}
