package com.example.Students.student;

import com.example.Students.Commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name="students",indexes = {
        @Index(name="student_uuid_index",columnList = "uuid")
})
@Getter
@Setter
@ToString
@AllArgsConstructor()
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends BaseEntity {
//    @Id
//    @SequenceGenerator(
//            name = "student_sequence",
//            sequenceName = "student_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "student_sequence"
//    )


    @Column(name = "name",nullable = false,length = 90)
     String name;

    @Column(name = "email",nullable = false,length = 90)
     String email;

    @Column(name = "dob",nullable = false,length = 90)
    LocalDate dob;

    @Column(name = "age",nullable = false,length = 90)
    Integer age;

//    public Student(String name, String email, LocalDate dob, Integer age) {
//        this.name = name;
//        this.email = email;
//        this.dob = dob;
//        this.age = age;
//    }

}

