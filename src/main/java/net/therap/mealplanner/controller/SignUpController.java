package net.therap.mealplanner.controller;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.service.SignUpService;
import net.therap.mealplanner.service.UserDetailsService;
import net.therap.mealplanner.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bashir
 * @since 10/26/16
 */
public class SignUpController extends HttpServlet {
    final static Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.getRequestDispatcher(req.getContextPath() + "/login").forward(req, resp);
            return;
        }catch (Exception e) {
            LOG.error("SignUpController :: doGet: ",e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        LOG.info("Entered signup");
        try {
             SignUpService signUpService = new SignUpService();
            UserDetailsService userDetailsService = new UserDetailsService();
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
                user.setPassword(Utils.hashMd5(password));
                user = userDetailsService.addNewUser(user);
                req.getSession().setAttribute("user", user);
                req.getRequestDispatcher(req.getContextPath() + "/login").forward(req, resp);
                return;

            }
            req.setAttribute("messages", messages);
            req.getRequestDispatcher(req.getContextPath() + "/jsp/login.jsp").forward(req, resp);
        }catch (Exception e) {
            LOG.error("SignUpController :: doPost: ",e);
        }
    }
}
