package net.therap.mealplanner.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bashir
 * @since 10/24/16
 */
public class LogoutController extends HttpServlet {
    static final Logger LOG = LogManager.getLogger(SimpleLogger.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String loginUri = req.getContextPath() + "/login";
            req.getSession().invalidate();
            LOG.debug("LogoutController:: LoginURI: " + loginUri);
            resp.sendRedirect(loginUri);
        } catch (Exception e) {
            LOG.error("LogoutController :: doGet: ", e);
        }
    }
}
