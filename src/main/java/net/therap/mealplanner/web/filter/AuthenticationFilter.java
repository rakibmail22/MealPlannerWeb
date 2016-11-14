package net.therap.mealplanner.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author bashir
 * @since 10/24/16
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String loginUri = req.getContextPath() + "/login";
        String signUpUri = req.getContextPath() + "/signup";

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        boolean loginRequest = req.getRequestURI().equals(loginUri);
        boolean signUpRequest = req.getRequestURI().equals(signUpUri);

        if (loggedIn || loginRequest || signUpRequest || excludeFilter(req.getServletPath())) {
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) response).sendRedirect(loginUri);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean excludeFilter(String path) {

        if (path.startsWith("/statics/style/") || path.startsWith("/statics/script/")) {
            return true;
        } else {
            return false;
        }
    }
}
