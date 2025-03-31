package org.example.exercisespringallabout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

public class ExerciseSpringAllAboutApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseSpringAllAboutApplication.class, args);
    }

}
