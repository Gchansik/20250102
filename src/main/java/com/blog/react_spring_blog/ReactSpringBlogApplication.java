package com.blog.react_spring_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ReactSpringBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactSpringBlogApplication.class, args);
	}

}
