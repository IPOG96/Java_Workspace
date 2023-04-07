package com.coding.Book_API.config;


import com.coding.Book_API.dto.RequestMeta;
import com.coding.Book_API.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    JwtUtils jwtUtils;


    private RequestMeta requestMeta;


    public JwtInterceptor(RequestMeta requestMeta) {
        this.requestMeta = requestMeta;

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        String auth = request.getHeader("authorization");
        if (!request.getRequestURI().contains("login") || request.getRequestURI().contains("signup")) {
//            jwtUtils.verify(auth);

            Claims claims = jwtUtils.verify(auth);

            requestMeta.setUserName(claims.get("name").toString());
            requestMeta.setUserId(Long.valueOf(claims.getIssuer()));
            requestMeta.setUserType(claims.get("type").toString());
            requestMeta.setEmailId(claims.get("emailId").toString());


        }


        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
