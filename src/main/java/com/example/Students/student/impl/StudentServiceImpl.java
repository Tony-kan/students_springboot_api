package com.example.Students.student.impl;

import com.example.Students.Commons.ApiResponse;
import com.example.Students.StudentsApplication;
import com.example.Students.student.IStudentService;
import com.example.Students.student.Student;
import com.example.Students.student.IStudentRepository;
import com.example.Students.student.StudentController;
import com.example.Students.student.dto.CreateStudentRequest;
import com.example.Students.student.hateoas.StudentModel;
import com.example.Students.student.hateoas.StudentModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentModelAssembler studentModelAssembler;

    @Autowired
    public StudentServiceImpl(IStudentRepository studentRepository, StudentModelAssembler studentModelAssembler) {
        this.studentRepository = studentRepository;
        log.info("StudentService created ......................");
        this.studentModelAssembler = studentModelAssembler;
    }

    private final IStudentRepository studentRepository;


    @Override
    public ResponseEntity<PagedModel<?>> getAllStudents(int page,
                                                        int size,
                                                        PagedResourcesAssembler<Student> studentPagedResourcesAssembler) {
        Page<Student> pagedStudents = studentRepository.findAll(
                PageRequest.of(page,size, Sort.by("id").descending())
        );

        if(pagedStudents.hasContent()) {
            return ResponseEntity.ok(studentPagedResourcesAssembler.toModel(pagedStudents,studentModelAssembler));
        }
//        return studentRepository.findAll();
        return ResponseEntity.ok(studentPagedResourcesAssembler.toEmptyModel(pagedStudents, StudentModel.class));
    }

    @Override
    public ResponseEntity<StudentModel> getStudentProfile(UUID studentId) {

//        return ResponseEntity.ok(
//                StudentModelAssembler.toModel(
//                        studentRepository.findStudentByUUID(studentId).orElseThrow(()-> new IllegalStateException("Student with id : {studentId} not found"))
//                ).add(linkTo(methodOn(StudentController.class)
//                        .getAllStudents(0,20,null))
//                        .withRel("Students")
//                )
//        );
        return null;
    }

    @Override
    public ResponseEntity<StudentModel> getStudentById(UUID studentId) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> registerNewStudent(CreateStudentRequest createStudentRequest) {
        log.info("Registering new student ...... : ${student}");
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(createStudentRequest.getEmail());
        if (studentOptional.isPresent()) {
            log.info("Student already registered ...... : ${student}");
            throw new IllegalStateException("Email already registered");
        }
//     return   studentRepository.save(student);
        studentRepository.save(
                Student.builder()
                        .fullName(createStudentRequest.getFullName())
                        .email(createStudentRequest.getEmail())
                        .dob(createStudentRequest.getDob())
                        .gender(createStudentRequest.getGender())
                        .phoneNumber(createStudentRequest.getPhoneNumber())
                .build());

        return ResponseEntity.ok(
                ApiResponse.builder().success(true).message("Student Saved").build()
        );
    }

    @Override
    public ResponseEntity<ApiResponse> deleteStudent(Long id) {
        log.info("Deleting student with id...... : ${id}");

        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id : "+id+" not found");
        }
        studentRepository.deleteById(id);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Student Deleted")
                        .build());

    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> updateStudentDetails(UUID studentId,
                                                            String fullName,
                                                            String email,
                                                            LocalDate dob,
                                                            String gender) {

        Student student = studentRepository.findStudentByUUID(studentId).orElseThrow(()-> new IllegalStateException("Student with id : {studentId} not found"));


       if(fullName != null && !fullName.trim().isEmpty()) {
           Student.builder().fullName(fullName).build();
       }
       if(email != null && !email.trim().isEmpty()) {
           Student.builder().email(email).build();
       }

       if(dob != null) {
           Student.builder().dob(dob).build();
       }

       if(gender != null && !gender.trim().isEmpty()) {
           Student.builder().gender(gender).build();
       }

        return ResponseEntity.ok(ApiResponse.builder().success(true).message("Student Updated").build());
    }

//    @Override
//    public ResponseEntity<StudentsApplication> getStudentById(String studentId) {
//        if(studentRepository.findStudentByUUID(UUID.fromString(studentId)).isEmpty()) {
//            throw new IllegalStateException("Student with id : "+studentId+" not found");
//
//        }
//
//        return ResponseEntity.ok(
//                );
//    }
}
