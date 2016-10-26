package net.therap.mealplanner.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by rakib on 10/24/16.
 */
public class AuthenticationFIlter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String loginUri = req.getContextPath() + "/login";
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        boolean loginRequest = req.getRequestURI().equals(loginUri);

        if (loggedIn || loginRequest || excludeFilter(req.getRequestURI())) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) response).sendRedirect(loginUri);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean excludeFilter(String path) {
        if (path.startsWith("/jsp/style/") || path.startsWith("/jsp/script/")) {
            return true;
        } else {
            return false;
        }
    }
}
