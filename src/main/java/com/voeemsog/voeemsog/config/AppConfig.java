package com.voeemsog.voeemsog.config;

import com.voeemsog.voeemsog.filters.AdminTokenFilter;
import com.voeemsog.voeemsog.services.AdminTokenService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
//    @Bean
//    public FilterRegistrationBean<AdminTokenFilter> adminAuthRegistration (AdminTokenService tokenService){
//        FilterRegistrationBean<AdminTokenFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AdminTokenFilter(tokenService));
//        registrationBean.addUrlPatterns("/admin", "/admin/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }
}
