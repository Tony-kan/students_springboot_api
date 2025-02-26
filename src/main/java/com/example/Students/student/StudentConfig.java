package com.example.Students.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

                 Student Tony=   new Student(
                            "Tony",
                            "tony@cybernet.com",
                            LocalDate.of(2000, Month.JUNE,5),
                            24
                    );
                 Student Jane =   new Student(
                            "Jane",
                            "jane@cybernet.com",
                            LocalDate.of(2004, Month.DECEMBER,23),
                            20
                    );
                  Student James =  new Student(
                            "James",
                            "james@cybernet.com",
                            LocalDate.of(2004, Month.DECEMBER,23),
                            20
                    );
                  studentRepository.saveAll(List.of(Tony,Jane,James));

        };
    }
}
