package com.hjy.filter;

import com.hjy.utils.CurrentHolder;
import com.hjy.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

import java.io.IOException;

// 指定拦截的请求方式
//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {


    // 初始化方法，web启动是执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 需要放行
        // 0. 强制转换
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 1. 获取请求路径
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        // 2.判断是否登录
        if(requestURI.contains("/login")){
            log.info("登录操作, 放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        else if(requestURI.contains("/logout")){
            log.info("退出操作, 放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        // 3. 获取toke
        String token = request.getHeader("token");

        // 4. 验证token是否存在
        if(token == null||token.isEmpty()){
            log.info("token为空, 拦截");
            response.setStatus(401);
            return;
        }


        // 5. 验证token是否正确
        try {
            Claims claims = JwtUtils.parseToken(token);
            CurrentHolder.setCurrentId(Integer.parseInt(claims.get("id").toString()));
        } catch (Exception e) {
            log.info("token错误, 拦截");
            response.setStatus(401);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
        CurrentHolder.remove();
    }

    @Override
    public void destroy() {

    }
}
