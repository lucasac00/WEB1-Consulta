package org.consulta.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

@WebFilter("/*")
public class LanguageFilterController implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initial configuration if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        String lang = null;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("lang".equals(cookie.getName())) {
                    lang = cookie.getValue();
                    break;
                }
            }
        }

        if (lang == null) {
            lang = "pt"; // Idioma padrão
        }

        Config.set(session, Config.FMT_LOCALE, lang);
        Config.set(session, Config.FMT_LOCALIZATION_CONTEXT, "messages");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
