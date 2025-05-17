package com.voeemsog.voeemsog.filters;

import com.voeemsog.voeemsog.services.AdminTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class AdminTokenFilter extends OncePerRequestFilter {

    private AdminTokenService adminTokenService;

    public AdminTokenFilter(AdminTokenService tokenService){
        this.adminTokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        if(path.equals("/admin/login")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if("admin_token".equals(cookie.getName())){
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if(token != null && adminTokenService.isTokenValid(token)){
            filterChain.doFilter(request, response);
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized");
        }
    }
}
