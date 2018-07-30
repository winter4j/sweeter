package com.song.sweeter.config.filter;


import com.song.sweeter.comm.Const;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter{
    private static Set<String> GreenUrlSet = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        GreenUrlSet.add("/");
        GreenUrlSet.add("/login");
        GreenUrlSet.add("/test");
        GreenUrlSet.add("/register");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String uri = httpRequest.getRequestURI();
        if(httpRequest.getSession().getAttribute(Const.SESSION_KEY_USER) != null){
            chain.doFilter(request, response);
            return;
        }
        //之前没登录过
        if(GreenUrlSet.contains(uri) || uri.endsWith(".html")){
            chain.doFilter(request, response);
            return;
        }
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect("/");
    }

    @Override
    public void destroy() {

    }
}
