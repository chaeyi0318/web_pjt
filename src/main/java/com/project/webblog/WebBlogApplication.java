package com.project.webblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@PropertySource(value = "application.properties")
public class WebBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBlogApplication.class, args);
    }

}
