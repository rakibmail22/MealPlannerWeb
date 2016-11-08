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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView modelAndView = new ModelAndView("login");
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equals("admin")) {
                return new ModelAndView("redirect:/admin/home");
            } else {
                return new ModelAndView("redirect:/home");
            }
        }
        return modelAndView;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView loginSubmit(HttpServletRequest req, HttpServletResponse resp) {
        try {
            ModelAndView modelAndView = new ModelAndView("login");
            User user = (User) req.getSession().getAttribute("user");
            LOG.debug("TEST DEBUG" + user);
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    return new ModelAndView("redirect:/admin/login");
                } else {
                    return new ModelAndView("redirect:/login");
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

            LOG.debug("LOGIN doPost:: Username: " + username + " Password: " + password);
            if (messages.isEmpty()) {
                user = userDetailsService.validateUser(username, password);
                if (user != null) {
                    req.getSession().setAttribute("user", user);
                    modelAndView.addObject("user", user);
                    LOG.debug("Logging in...........");
                    if (user.getRole().equals("admin")) {
                        modelAndView.setViewName("redirect:/admin/home");
                    } else {
                        modelAndView.setViewName("redirect:/home");
                    }
                    return modelAndView;
                } else {
                    LOG.debug("Logging failed...........");
                    messages.put("login", "Unknown login. Try again.");
                }

            }
            modelAndView.addObject("messages", messages);
            return new ModelAndView();
        } catch (Exception e) {
            LOG.error("LoginController :: doPost: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView displaySignUp(HttpServletRequest req, HttpServletResponse resp) {
        try {
            return new ModelAndView("forward:/login");
        } catch (Exception e) {
            LOG.error("SignUpController :: doGet: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signUpSubmit(HttpServletRequest req, HttpServletResponse resp) {
        LOG.info("Entered signup");
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

            LOG.debug("SignUp doPost:: Username: " + name + " " + email + " " + password);
            if (messages.isEmpty()) {
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setRole("user");
                user.setPassword(utils.hashMd5(password));
                user = userDetailsService.addNewUser(user);
                req.getSession().setAttribute("user", user);
                return new ModelAndView("forward:/login");
            }
            req.setAttribute("messages", messages);
            return new ModelAndView("forward:/login");
        } catch (Exception e) {
            LOG.error("SignUpController :: doPost: ", e);
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getSession().invalidate();
            LOG.debug("LogoutController:: LoginURI: ");
            return new ModelAndView("redirect:/login");
        } catch (Exception e) {
            LOG.error("LogoutController :: doGet: ", e);
            return null;
        }
    }

}
