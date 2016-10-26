package net.therap.mealplanner.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rakib on 10/24/16.
 */
public class LogoutController extends HttpServlet {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginUri = req.getContextPath()+"/home";
        req.getSession().invalidate();
        LOG.debug("LogoutController:: LoginURI: "+loginUri);
        resp.sendRedirect(loginUri);
    }
}
