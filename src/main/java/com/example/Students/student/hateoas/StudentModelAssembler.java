package com.example.Students.student.hateoas;

import com.example.Students.student.Student;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, StudentModel> {
    @Override
    public StudentModel toModel(Student studentEntity) {
        return StudentModel
                .builder()
                .studentId(studentEntity.getId())
                .fullName(studentEntity.getFullName())
                .email(studentEntity.getEmail())
                .gender(studentEntity.getGender())
                .build();
    }

    @Override
    public CollectionModel<StudentModel> toCollectionModel(Iterable<? extends Student> entities){
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
