package com.example.Students.student.hateoas;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentModel extends RepresentationModel<StudentModel> {
    UUID studentId;
    String fullName;
    String email;
    String gender;
    String phoneNumber;
    LocalDate dob;
}
