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
    CommandLineRunner commandLineRunner(IStudentRepository studentRepository) {
        return args -> {

            Student Tony1 = Student.builder()
                    .fullName("Tony1")
                    .email("tony1@gmail.com")
                    .gender("Male")
                    .phoneNumber("+265 999999")
                    .dob(LocalDate.of(1990, Month.JANUARY, 1))
                    .build();

            Student Jane = Student.builder()
                    .fullName("Jane")
                    .email("jane@gmail.com")
                    .gender("Female")
                    .phoneNumber("+265 999999")
                    .dob(LocalDate.of(2000, Month.JUNE, 23))
                    .build();

//            Student Tony1 = Student.builder()
//                    .fullName("Tony1")
//                    .email("tony1@gmail.com")
//                    .gender("Male")
//                    .phoneNumber("+265 999999")
//                    .dob(LocalDate.of(1990, Month.JANUARY, 1))
//                    .build();

//                 Student Tony=   new Student(
//                            "Tony",
//                            "tony@cybernet.com",
//                            LocalDate.of(2000, Month.JUNE,5),
//                            24
//                    );
//                 Student Jane =   new Student(
//                            "Jane",
//                            "jane@cybernet.com",
//                            LocalDate.of(2004, Month.DECEMBER,23),
//                            20
//                    );
//                  Student James =  new Student(
//                            "James",
//                            "james@cybernet.com",
//                            LocalDate.of(2004, Month.DECEMBER,23),
//                            20
//                    );
                  studentRepository.saveAll(List.of(Tony1,Jane));

        };
    }
}
