package ru.wowtcgdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @PreDestroy
    void closeSessionFactory() {
        SessionCreator.sessionFactoryClose();
    }
};
