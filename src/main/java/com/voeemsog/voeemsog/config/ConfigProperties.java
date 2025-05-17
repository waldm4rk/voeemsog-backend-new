package com.voeemsog.voeemsog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "admin")
public class ConfigProperties {
    @Getter
    @Setter
    private String api;
}
