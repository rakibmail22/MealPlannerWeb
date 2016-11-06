package net.therap.mealplanner.web;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
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
public class LoginController {

    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView loginSubmit(HttpServletRequest req, HttpServletResponse resp){
        ModelAndView modelAndView = new ModelAndView("login");
        try {
            User user = (User) req.getSession().getAttribute("user");
            LOG.debug("TEST DEBUG" + user);
            if (user != null) {
                if (user != null) {
                    if (user.getRole().equals("admin")) {
                        resp.sendRedirect(req.getContextPath() + "/admin/home");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/home");
                    }
                }
            }

            UserDetailsService userDetailsService = new UserDetailsService();
            String username = req.getParameter("lg_username");
            String password = Utils.hashMd5(req.getParameter("lg_password")).trim();
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
                    LOG.debug("Logging in...........");
                    if (user.getRole().equals("admin")) {
                        resp.sendRedirect(req.getContextPath() + "/admin/home");
                    } else {
                        resp.sendRedirect(req.getContextPath() + "/home");
                    }
                    return;
                } else {
                    LOG.debug("Logging failed...........");
                    messages.put("login", "Unknown login. Try again.");
                }

            }
            req.setAttribute("messages", messages);
            req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        } catch (Exception e) {
            LOG.error("LoginController :: doPost: ", e);
        }
    }
}
