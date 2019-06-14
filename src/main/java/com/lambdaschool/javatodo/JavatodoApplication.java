package com.lambdaschool.javatodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JavatodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavatodoApplication.class, args);
    }

}
