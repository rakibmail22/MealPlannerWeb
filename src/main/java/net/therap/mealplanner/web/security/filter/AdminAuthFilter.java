package net.therap.mealplanner.web.security.filter;

import net.therap.mealplanner.domain.Role;
import net.therap.mealplanner.domain.User;
import net.therap.mealplanner.web.security.AuthUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bashir
 * @since 10/30/16
 */
public class AdminAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        AuthUser authUser= (AuthUser) req.getSession().getAttribute("user");
        if (Role.admin.equals(authUser.getUserRole())) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/404");
        }

    }

    @Override
    public void destroy() {

    }
}
