package org.zerock.simpletodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SimpleTodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleTodoApplication.class, args);
    }

}
