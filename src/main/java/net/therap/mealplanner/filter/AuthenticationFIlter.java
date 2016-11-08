package net.therap.mealplanner.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author bashir
 * @since 10/24/16
 */
public class AuthenticationFIlter implements Filter {
    final static Logger log = LogManager.getLogger(SimpleLogger.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("AuthenticationFilter:: doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginUri = req.getContextPath() + "/login";
        String signUpUri = req.getContextPath() + "/signup";
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        boolean loginRequest = req.getRequestURI().equals(loginUri);
        boolean signUpRequest = req.getRequestURI().equals(signUpUri);

        if (loggedIn || loginRequest || signUpRequest || excludeFilter(req.getServletPath())) {
            log.debug("AuthenticationFilter:: forward");
            chain.doFilter(req, resp);
        } else {
            log.debug("AuthenticationFilter:: redirect");
            ((HttpServletResponse) response).sendRedirect(loginUri);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean excludeFilter(String path) {
        log.debug("Authentication Filter::Excluding Servlet Path " + path);
        if (path.startsWith("/jsp/style/") || path.startsWith("/jsp/script/")) {
            return true;
        } else {
            return false;
        }
    }
}
