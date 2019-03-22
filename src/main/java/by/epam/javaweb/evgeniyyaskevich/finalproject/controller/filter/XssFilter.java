package by.epam.javaweb.evgeniyyaskevich.finalproject.controller.filter;

import by.epam.javaweb.evgeniyyaskevich.finalproject.controller.wrapper.XssRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "xssFilter", urlPatterns = {"/login", "/main", "/register"})
public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest)request), response);
    }
}
