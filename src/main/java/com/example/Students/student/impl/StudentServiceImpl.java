package com.example.Students.student.impl;

import com.example.Students.Commons.ApiResponse;
import com.example.Students.student.IStudentService;
import com.example.Students.student.Student;
import com.example.Students.student.IStudentRepository;
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

    public ResponseEntity<ApiResponse> registerNewStudent(Student student) {
        log.info("Registering new student ...... : ${student}");
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            log.info("Student already registered ...... : ${student}");
            throw new IllegalStateException("Email already registered");
        }
     return    studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        log.info("Deleting student with id...... : ${id}");

        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Student with id : "+id+" not found");
        }
        studentRepository.deleteById(id);

    }

    @Transactional
    public void updateStudentDetails(Long studentId, String name, String email, LocalDate dob) {

    }

    public ResponseEntity<ApiResponse> getUser(String studentId) {
    }
}
