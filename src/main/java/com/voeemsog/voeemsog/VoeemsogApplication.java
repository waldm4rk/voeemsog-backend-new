package com.voeemsog.voeemsog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = "com.voeemsog.voeemsog")
public class VoeemsogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VoeemsogApplication.class, args);
    }

}
