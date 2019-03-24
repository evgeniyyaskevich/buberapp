package by.epam.javaweb.evgeniyyaskevich.finalproject.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "accessFilter", urlPatterns = {"/order"})
public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String[] parts = httpRequest.getRequestURI().split("/");
        String urlPattern = parts[parts.length - 1];

        HttpSession session = httpRequest.getSession();
        if (urlPattern.equals("order") && session.getAttribute("application") != null) {
            chain.doFilter(request, response);
        } else {
          httpResponse.sendRedirect("main");
        }
    }
}