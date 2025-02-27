package com.example.Students.student.impl;

import com.example.Students.student.IStudentService;
import com.example.Students.student.Student;
import com.example.Students.student.IStudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentServiceImpl {

    @Autowired
    public StudentServiceImpl(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        log.info("StudentService created ......................");
    }

    private final IStudentRepository studentRepository;



    public List<Student> getStudentsService() {
            return studentRepository.findAll();
    }

    public void registerNewStudent(Student student) {
        log.info("Registering new student ...... : ${student}");
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            log.info("Student already registered ...... : ${student}");
            throw new IllegalStateException("Email already registered");
        }
         studentRepository.save(student);
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

//        Optional<Student> student = studentRepository.findById(studentId);
//
//        if (!student.isPresent()) {
//            log.info("Student with id : "+studentId+" not found");
//            throw new IllegalStateException("Student with id : "+studentId+" not found");
//        }
    Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException("Student with id : "+studentId+" not found"));

    if(name!=null && !student.getName().equals(name)) {
        student.setName(name);
    }

    if(email!=null && !student.getEmail().equals(email)) {
        student.setEmail(email);
    }

        studentRepository.save(student);
    }
}
