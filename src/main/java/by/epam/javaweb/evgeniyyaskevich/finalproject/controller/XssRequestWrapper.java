package by.epam.javaweb.evgeniyyaskevich.finalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssRequestWrapper extends HttpServletRequestWrapper {
    private static final String GREATER_SYMBOL = ">";
    private static final String LESS_SYMBOL = "<";
    private static final String GREATER_SYMBOL_REPLACEMENT = "&rt";
    private static final String LESS_SYMBOL_REPLACEMENT = "&lt";

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        String[] encodedValues = null;
        if (values != null) {
            encodedValues = new String[values.length];
            for (int i = 0; i < encodedValues.length; ++i) {
                encodedValues[i] = stripXss(values[i]);
            }
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String name) {
        return stripXss(super.getParameter(name));
    }

    @Override
    public String getHeader(String name) {
        return stripXss(super.getParameter(name));
    }

    private String stripXss(String value) {
        return (value == null)
                ? null
                : value.replaceAll(GREATER_SYMBOL, GREATER_SYMBOL_REPLACEMENT)
                        .replaceAll(LESS_SYMBOL, LESS_SYMBOL_REPLACEMENT);
    }
}
