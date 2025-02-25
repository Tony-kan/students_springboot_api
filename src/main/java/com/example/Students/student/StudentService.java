package com.example.Students.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudentsService() {
        return List.of(
                new Student(
                        1L,
                        "Tony",
                        "tony@cybernet.com",
                        LocalDate.of(2000, Month.JUNE,5),
                        24
                ),
                new Student(
                        5L,
                        "Jane",
                        "jane@cybernet.com",
                        LocalDate.of(2004, Month.DECEMBER,23),
                        20
                ),
                new Student(
                        6L,
                        "James",
                        "james@cybernet.com",
                        LocalDate.of(2004, Month.DECEMBER,23),
                        20
                )
        );
    }
}
