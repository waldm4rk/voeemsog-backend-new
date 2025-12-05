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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Order(1)
@Component
public class AdminTokenFilter extends OncePerRequestFilter {

    private final AdminTokenService adminTokenService;
    @Autowired
    public AdminTokenFilter(AdminTokenService tokenService){
        this.adminTokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        String ctx = request.getContextPath();
        if (ctx != null && !ctx.isEmpty() && path.startsWith(ctx)) {
            path = path.substring(ctx.length());
        }
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }

        if(!path.startsWith("/admin") || path.equals("/admin/login") || path.equals("/admin/login/") || path.equals("/admin/api")|| path.equals("/admin/api/") || path.equals("/admin/check-cookie")){
            filterChain.doFilter(request, response);
            return;
        }
        String token = Arrays.stream(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(c -> "admin_token".equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if(token != null && adminTokenService.isTokenValid(token)){
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            "admin",     // principal
                            null,        // credentials
                            List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);
            filterChain.doFilter(request, response);
        }else{
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized");
        }
    }
}
