package by.epam.javaweb.evgeniyyaskevich.finalproject.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "authorizationFilter", urlPatterns = {"/main", "/login", "/register"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();

        String[] parts = httpRequest.getRequestURI().split("/");
        String urlPattern = parts[parts.length - 1];

        Boolean isAuthorized = (Boolean) session.getAttribute("isAuthorized");
        if (isAuthorized != null) {
            if (isAuthorized) {
                if (urlPattern.equals("login")) {
                    httpResponse.sendRedirect("main");
                } else {
                    chain.doFilter(servletRequest, servletResponse);
                }
            } else {
                if (!urlPattern.equals("login"))  {
                    chain.doFilter(servletRequest, servletResponse);
                } else {
                    httpResponse.sendRedirect("login");
                }
            }
        } else {
            session.setAttribute("isAuthorized", false);
            if (urlPattern.equals("login")) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.sendRedirect("login");
            }
        }
    }
}
