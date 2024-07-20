package kr.co.polycube.backendtest.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.regex.Pattern;

public class URLFilter implements Filter {

    private static final Pattern ALLOWED_CHARACTERS = Pattern.compile("^[a-zA-Z0-9&=:/?]*$");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();
        String queryString = httpRequest.getQueryString();

        if (queryString != null) {
            url += "?" + queryString;
        }

        if (!ALLOWED_CHARACTERS.matcher(url).matches()) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid characters in URL");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
