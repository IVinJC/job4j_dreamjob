package ru.job4j.dreamjob.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthFilter implements Filter {
    private final Set<String> mappingURI = ConcurrentHashMap.newKeySet();

    private Boolean uriCheck(String uri) {
        mappingURI.add("/loginPage");
        mappingURI.add("/login");
        mappingURI.add("/registration");
        return mappingURI.contains(uri);
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (uriCheck(req.getRequestURI())) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}